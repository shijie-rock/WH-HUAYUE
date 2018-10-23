/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.client
 * 文   件  名:ClientKeepAliveMessageFactoryImpl.java
 * 创 建日期:2018年10月16日-下午6:43:09
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.test2.common.HyLmsClientConstant;
import com.youbus.framework.comm.AppLog;

/**
 * 类名称:ClientKeepAliveMessageFactoryImpl
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午6:43:09
 * 修改备注:
 * @version 1.0.0
 */
public class ClientKeepAliveMessageFactoryImpl implements
		KeepAliveMessageFactory {
	private static Logger logger=Logger.getLogger(ClientKeepAliveMessageFactoryImpl.class);
	private static Logger deLog=AppLog.getInstance().getDELog();
	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest(org.apache.mina.core.session.IoSession)
	 */
	/**
	 * 返回给服务端消息
	 */
	@Override
	public Object getRequest(IoSession arg0) {
		// TODO Auto-generated method stub
		System.out.println("client getRequest :="+HyLmsClientConstant.HEART_BEAT_CLIENT_RESQ);
		return HyLmsClientConstant.HEART_BEAT_CLIENT_RESQ;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 接受到的服务器数据包
	 */
	@Override
	public Object getResponse(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
		System.out.println("client getResponse :="+message);
//		return message;
		return HyLmsClientConstant.HEART_BEAT_SERVER_RESP;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 客户端接收到服务器发送的数据
	 */
	@Override
	public boolean isRequest(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
//		if(message.equals(HyLmsClientConstant.HEART_BEAT_SERVER_RESP)){
//			logger.info("log接收到服务器心数据包引发心跳事件  心跳数据包是》》" + message);
//			System.out.println("sys接收到服务器心数据包引发心跳事件  心跳数据包是》》" + message);
//			return true;
//		}else{
//			return false;
//		}
		
		if(message.equals(HyLmsClientConstant.HEART_BEAT_CLIENT_RESQ)){
			logger.info("isRequest: " + message);
			deLog.debug("客户端发送心跳");
			return true;
		}else{
			logger.warn("isRequest: " + message);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 判断客户端发送的是否是客户端请求消息
	 */
	@Override
	public boolean isResponse(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
//		if(message.equals(HyLmsClientConstant.HEART_BEAT_CLIENT_RESQ)){
//			logger.info("客户端发送数据包中引发心跳事件: " + message);
//			System.out.println("客户端发送数据包中引发心跳事件: " + message);
//			return true;
//		}else{
//			return false;
//		}
		
		if(message.equals(HyLmsClientConstant.HEART_BEAT_SERVER_RESP)){
			logger.info("isResponse: " + message);
			deLog.debug("客户端收到心跳");
			return true;
		}else{
			logger.warn("isResponse: " + message);
			return false;
		}
	}

}
