/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.server
 * 文   件  名:ServerKeepAliveMessageFactoryImpl.java
 * 创 建日期:2018年10月16日-下午5:56:20
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.test2.common.HyLmsServerConstant;

/**
 * 类名称:ServerKeepAliveMessageFactoryImpl
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午5:56:20
 * 修改备注:
 * @version 1.0.0
 */
public class ServerKeepAliveMessageFactoryImpl implements
		KeepAliveMessageFactory {
	private static Logger logger=Logger.getLogger(ServerKeepAliveMessageFactoryImpl.class);
	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest(org.apache.mina.core.session.IoSession)
	 */
	
	/** 心跳包内容 */
//	private static final String SERVERHEARTBEATREQUEST = "1111";
//	private static final String SERVERHEARTBEATRESPONSE = "1112";
	
	/**
	 * 返回给客户端的心跳包数据 return 返回结果才是客户端收到的心跳包数据
	 */
	@Override
	public Object getRequest(IoSession arg0) {
		// TODO Auto-generated method stub
		System.out.println("server getRequest :="+HyLmsServerConstant.HEART_BEAT_SERVER_RESP);
		return HyLmsServerConstant.HEART_BEAT_SERVER_RESP;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 接受到的客户端数据包
	 */
	@Override
	public Object getResponse(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
		System.out.println("server getResponse:="+message);
		return message;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 判断是否是客户端发送来的的心跳包此判断影响 KeepAliveRequestTimeoutHandler实现类
	 * 判断是否心跳包发送超时
	 */
	@Override
	public boolean isRequest(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
		if(message.equals(HyLmsServerConstant.HEART_BEAT_CLIENT_RESQ)){
				logger.info("正常:   "+"接收到客户端心数据包引发心跳事件 心跳数据包是》》" + message);
				System.out.println("正常:   "+"接收到客户端心数据包引发心跳事件 心跳数据包是》》" + message);
				return true;
			}
				logger.info("不正常:  "+"客户端发过来的不是"+HyLmsServerConstant.HEART_BEAT_CLIENT_RESQ+"  服务器端收到的是:"+message.toString());
				System.out.println("不正常:  "+"客户端发过来的不是"+HyLmsServerConstant.HEART_BEAT_CLIENT_RESQ+"  服务器端收到的是:"+message.toString());
				return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	/**
	 * 判断发送信息是否是心跳数据包此判断影响 KeepAliveRequestTimeoutHandler实现类
	 * 判断是否心跳包发送超时
	 */
	@Override
	public boolean isResponse(IoSession arg0, Object message) {
		// TODO Auto-generated method stub
		if(message.equals(HyLmsServerConstant.HEART_BEAT_SERVER_RESP)){
			logger.info("服务器发送数据包中引发心跳事件: " + message);
			return true;
		}
			return false;

	}

}
