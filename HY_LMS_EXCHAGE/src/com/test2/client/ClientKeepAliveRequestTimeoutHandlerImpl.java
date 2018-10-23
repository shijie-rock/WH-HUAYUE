/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.client
 * 文   件  名:ClientKeepAliveRequestTimeoutHandlerImpl.java
 * 创 建日期:2018年10月16日-下午6:48:29
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

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
	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#keepAliveRequestTimedOut(org.apache.mina.filter.keepalive.KeepAliveFilter, org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter arg0, IoSession arg1)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("client heartbeat timeout.");
	}

}
