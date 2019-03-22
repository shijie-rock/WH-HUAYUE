/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.common
 * 文   件  名:MinaListener.java
 * 创 建日期:2018年10月17日-下午3:47:01
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.test2.client.HyLmsClient;

/**Mina监听，用于随Tomcat启停
 * 类名称:MinaListener
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月17日 下午3:47:01
 * 修改备注:
 * @version 1.0.0
 */
public class MinaListener implements ServletContextListener {
	private static Logger logger=Logger.getLogger(MinaListener.class);
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("客户端关闭开始");
		logger.debug("客户端关闭开始");
		HyLmsClientConstant.NEED_CLOSE=true;
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
		
		HyLmsClientConstant.connectingNot();
		
		System.out.println("客户端关闭结束");
		logger.debug("客户端关闭结束");
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("客户端启动开始");
		logger.debug("客户端启动开始");
//		HyLmsClientConstant.init();
		HyLmsClientConstant.NEED_CLOSE=false;
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10000);//等待10秒执行
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.debug("初始化连接参数配置开始");
				HyLmsClientConstant.init();
				logger.debug("初始化连接参数配置结束");
				
				System.out.println("连接线程启动开始");
				logger.debug("连接线程启动开始");
				new HyLmsClient().connect();
				System.out.println("连接线程启动结束");
				logger.debug("连接线程启动结束");
			}
		});
		thread.start();
		
		System.out.println("客户端启动完毕");
		logger.debug("客户端启动完毕");
	}

}
