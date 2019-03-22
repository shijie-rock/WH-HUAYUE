/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test.client
 * 文   件  名:ClientMessageHandler.java
 * 创 建日期:2018年10月16日-上午11:20:08
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.joda.time.LocalDateTime;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.test2.msgfactory.HyMessageFactory;
import com.test2.response.HyResponseParserThread;
import com.test2.response.ParserThreadPool;
import com.youbus.framework.comm.AppLog;

/**
 * 类名称:ClientMessageHandler
 * 类描述:客户端接收到服务端消息之后进行处理
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 上午11:20:08
 * 修改备注:
 * @version 1.0.0
 */
public class ClientMsgHandler extends IoHandlerAdapter {
	private static Logger deLog=AppLog.getInstance().getDELog();
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        System.out.println("session created with IP: " + clientIp);
        deLog.debug("session created with IP: " + clientIp);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        System.out.println("session opened with IP: " + clientIp);
//        SessionManager.getManager().add(session);
        deLog.debug("session opened with IP: " + clientIp);
        //再次赋值session
    	ClientHelper.getInstance().setSESSION_CHANNAL(session);
        
        //session open 时，增加登录一次
    	try{
    		BaseRequestParamBean reqBean=HyMessageFactory.createSysLoginMsg();
    		session.write(HyLmsSignUtil.getRequestBeanJson(reqBean));
    		TmExMsgPOFactory.insertReq(reqBean);
    	}catch(Throwable t){
			t.printStackTrace();
			deLog.error("处理会话打开逻辑失败："+t.getMessage());
    		
    	}

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("session closed ");
        deLog.debug("session closed ");
//        SessionManager.getManager().remove(session);       
        /*
        int i=0;
            while(true) { //通道关闭重连
                try {
                    Thread.sleep(3000);
                    // 这里是异步操作 连接后立即返回
                    ConnectionConfig connectionConfig = new ConnectionConfig.Builder(19999).setIp("127.0.0.1").setTimeInterval(20).build();
                    ConnectionManager connectionManager = new ConnectionManager(connectionConfig);
                    boolean isConnected=connectionManager.connect();
                    if(isConnected) {
                        break;
                    }else{
                        System.err.println("服务端连接超时，请检查网络");
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                	
                }
            }*/
        
        //20190314-解决 服务器频繁重启，导致客户存有多个session，先关闭原session
        
        //add begin
		NioSocketConnector  CONNECTOR = ClientHelper.getInstance().getCONNECTOR();
		IoSession SESSION_CHANNAL = ClientHelper.getInstance().getSESSION_CHANNAL();
		if(SESSION_CHANNAL!=null)
		while( !SESSION_CHANNAL.isIdle(IdleStatus.BOTH_IDLE) 
				&& SESSION_CHANNAL.isConnected() ){
			try {
				Thread.sleep(2000);//判断session 是否是空闲，不空闲则等待
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(CONNECTOR!=null)
		CONNECTOR.dispose();
		HyLmsClientConstant.connectingNot();
        //add end
		
        if(!HyLmsClientConstant.NEED_CLOSE){
        	if(!HyLmsClientConstant.isConnecting()){
        		  new HyLmsClient().connect();//改为只启动一次连接 //20190314
        	}else{
        		  deLog.error(" HyLmsClientConstant.isConnecting():= "+HyLmsClientConstant.isConnecting());
        	}
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        System.out.println("client message received with message: "+"["+new LocalDateTime()+"]" + message.toString());
        deLog.debug(" 收到服务端消息: "+message);
        
//        HyResponseUtil.parseReceiveMsg(message.toString());
        //改为线程方式
//        Thread t=new Thread(new HyResponseParserThread(message.toString()));
//        t.start();
        
        //改为线程池方式：
        
        ExecutorService es= ParserThreadPool.getInstance().getPool();
        deLog.debug(" 线程池："+es);
        es.execute(new HyResponseParserThread(message.toString()));
        
        deLog.debug(" 进入线程池处理 : "+message);
        deLog.debug(" 处理服务端消息 结束 : "+message);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        System.out.println("session in idle");
        deLog.debug(" session in idle ");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        System.out.println("exception");
        deLog.debug(" session exception ");
        session.closeOnFlush();
//        SessionManager.getManager().remove(session);
    }
}
