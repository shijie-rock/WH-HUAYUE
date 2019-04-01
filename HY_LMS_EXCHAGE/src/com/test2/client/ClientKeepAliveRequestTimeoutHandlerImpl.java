/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.client
 * 文   件  名:ClientKeepAliveRequestTimeoutHandlerImpl.java
 * 创 建日期:2018年10月16日-下午6:48:29
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.youbus.framework.comm.AppLog;

/**
 * 类名称:ClientKeepAliveRequestTimeoutHandlerImpl
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午6:48:29
 * 修改备注:
 * @version 1.0.0
 */
public class ClientKeepAliveRequestTimeoutHandlerImpl implements
		KeepAliveRequestTimeoutHandler {
	private static Logger logger=Logger.getLogger(ClientKeepAliveRequestTimeoutHandlerImpl.class);
	private static Logger deLog=AppLog.getInstance().getDELog();
	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#keepAliveRequestTimedOut(org.apache.mina.filter.keepalive.KeepAliveFilter, org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter arg0, IoSession arg1)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("client heartbeat timeout.");
		//发起重连
		
		int timeOutTimes=ClientHelper.getInstance().beatTimeOut();//当前连续超时次数
		if(HyLmsClientConstant.HEARB_BEAT_TIME_OUT_MAX_TIMES<=timeOutTimes){
//			先执行关闭
			/*
			NioSocketConnector  CONNECTOR = ClientHelper.getInstance().getCONNECTOR();
			IoSession SESSION_CHANNAL = ClientHelper.getInstance().getSESSION_CHANNAL();
			if(SESSION_CHANNAL!=null)
			while( !SESSION_CHANNAL.isIdle(IdleStatus.BOTH_IDLE) 
					&& SESSION_CHANNAL.isConnected() ){
				try {
					Thread.sleep(3000);//判断session 是否是空闲，不空闲则等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(CONNECTOR!=null)
			CONNECTOR.dispose();
			System.out.println("客户端关闭结束");
			logger.debug("客户端关闭结束");
			
//			再执行重连
	        if(!HyLmsClientConstant.NEED_CLOSE){
	        	logger.info(" 已经超过允许最大心跳超时次数，发起重新连接.");
	            new HyLmsClient().connect();
	        }
	        */
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
		
	}

}
