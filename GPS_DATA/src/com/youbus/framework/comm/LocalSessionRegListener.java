/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:LocalSessionRegListener.java
 * �� ������:2015��7��2��-����4:04:05
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ������:LocalSessionRegListener
 * ������:��������Session�ĵ���ǳ���ͬ������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��7��2�� ����4:04:05
 * �޸ı�ע:
 * @version 1.0.0
 */
public class LocalSessionRegListener implements HttpSessionListener {

	/**
	 * ����һ���µ�ʵ�� LocalSessionRegListener.
	 *
	 */
	public LocalSessionRegListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		ServletContext ctx = event.getSession( ).getServletContext( );  
		String sessionId=event.getSession().getId();
		System.out.println(this.getClass().getName()+":sessionCreated["+sessionId+"]");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//TODO Auto-generated method stub
		String sessionId=event.getSession().getId();
		System.out.println(this.getClass().getName()+":sessionDestroyed ["+sessionId+"]");
//		YoubusNativeCacheService.AGENT_SESSION_CACHE.remove(sessionId);//�ǳ���ֱ������Ự
	}

	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
