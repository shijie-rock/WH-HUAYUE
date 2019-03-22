/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.client
 * 文   件  名:HyLmsClient.java
 * 创 建日期:2018年10月16日-下午6:24:51
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.stream.StreamWriteFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.youbus.framework.comm.AppLog;

/**
 * 类名称:HyLmsClient
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午6:24:51
 * 修改备注:
 * @version 1.0.0
 */
public class HyLmsClient {
	private static Logger logger=Logger.getLogger(HyLmsClient.class);
	private static Logger deLog=AppLog.getInstance().getDELog();
	private IoSession session;
	private static final String MSG_SPLIT=LineDelimiter.WINDOWS.getValue();
	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HyLmsClient().connect();
	}
	NioSocketConnector connector=null;
	 public void connect() {
//		HyLmsClientConstant.init();
		logger.info("start socketconnect ：" + HyLmsClientConstant.SERVER_HOST
				+ ":" + HyLmsClientConstant.SERVER_PORT);
		HyLmsClientConstant.connecting();//标示有一个connection 正在连接
		
    	ClientHelper.getInstance().beatIntime();//当前心跳超时次数清零
		
    	connector = new NioSocketConnector();
		connector
				.setConnectTimeoutMillis(HyLmsClientConstant.CONNECT_TIME_OUT * 1000);
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec1",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),MSG_SPLIT,MSG_SPLIT)));//utf-8
		KeepAliveMessageFactory heartBeatFactory = new ClientKeepAliveMessageFactoryImpl();
		// 下面注释掉的是自定义Handler方式
		KeepAliveRequestTimeoutHandler heartBeatHandler = new ClientKeepAliveRequestTimeoutHandlerImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
				IdleStatus.READER_IDLE, heartBeatHandler);
		heartBeat.setRequestTimeoutHandler(heartBeatHandler);
		// 设置是否forward到下一个filter
		heartBeat.setForwardEvent(true);
		// 设置心跳频率
//		heartBeat.setRequestInterval(20);
		heartBeat.setRequestInterval(HyLmsClientConstant.HEART_BEAT_RATE);
		connector.getFilterChain().addLast("heartbeat", heartBeat);
		connector.getFilterChain().addLast("codec", new StreamWriteFilter());
		connector.setHandler(new ClientMsgHandler());//
		connector.setDefaultRemoteAddress(new InetSocketAddress(
				HyLmsClientConstant.SERVER_HOST,
				HyLmsClientConstant.SERVER_PORT));
		connector.getSessionConfig().setKeepAlive(true);//
//		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 45);
		connector.getSessionConfig().setTcpNoDelay(true);//设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出
		/**/
		
		connector.addListener(new IoServiceListener(){
			@Override
			public void sessionDestroyed(IoSession arg0) throws Exception {
				deLog.error("=========IoServiceListener sessionDestroyed=========");
//				connect(connector);
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
//		        		  new HyLmsClient().connect();//改为只启动一次连接 //20190314
		        		  connect(connector);//改为只启动一次连接 //20190314
		        	}else{
		        		  deLog.error(" HyLmsClientConstant.isConnecting():= "+HyLmsClientConstant.isConnecting());
		        	}
		        }
			}

			@Override
			public void serviceActivated(IoService arg0) throws Exception {
				// TODO Auto-generated method stub
				deLog.error("=========IoServiceListener serviceActivated==========");
			}

			@Override
			public void serviceDeactivated(IoService arg0) throws Exception {
				// TODO Auto-generated method stub
				deLog.error("=========IoServiceListener serviceDeactivated=========");
			}

			@Override
			public void serviceIdle(IoService arg0, IdleStatus arg1)
					throws Exception {
				// TODO Auto-generated method stub
				deLog.error("=========IoServiceListener serviceIdle=========");
			}

			@Override
			public void sessionClosed(IoSession arg0) throws Exception {
				// TODO Auto-generated method stub
				deLog.error("=========IoServiceListener sessionClosed=========");
			}

			@Override
			public void sessionCreated(IoSession arg0) throws Exception {
				// TODO Auto-generated method stub
				deLog.error("=========IoServiceListener sessionCreated=========");
			}
		});
		
		connect(connector);
	}
	 
	 private void connect(NioSocketConnector connector) {
		for (;;) {
			try {
				ConnectFuture future = connector.connect();
				future.awaitUninterruptibly();
				ClientHelper.getInstance().setCONNECTOR(connector);
				session = future.getSession();
				if (session.isConnected()) {
					logger.info("socketconnect["
							+ connector.getDefaultRemoteAddress().getHostName()
							+ ":"
							+ connector.getDefaultRemoteAddress().getPort()
							+ "] millisecond");
					ClientHelper.getInstance().setSESSION_CHANNAL(session);
					
					HyLmsClientConstant.connectingNot();
					
					//改在sessionopen时，发送
					/**
					if(!HyLmsClientConstant.IS_SYS_LOGIN_IN){
						//
						BaseRequestParamBean reqBean=HyMessageFactory.createSysLoginMsg();
						session.write(HyLmsSignUtil.getRequestBeanJson(reqBean));
						TmExMsgPOFactory.insertReq(reqBean);
					}
					*/
					break;
				}
			} catch (Exception ex) {
				logger.info("socketconnect["
						+ connector.getDefaultRemoteAddress().getHostName()
						+ ":" + connector.getDefaultRemoteAddress().getPort()
						+ "] fail. retry after " + 3000
						+ " millisecond, error:" + ex.getMessage());
			} finally {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
