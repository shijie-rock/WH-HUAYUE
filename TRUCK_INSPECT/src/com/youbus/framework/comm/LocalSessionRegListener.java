/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:LocalSessionRegListener.java
 * 创 建日期:2015年7月2日-下午4:04:05
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 类名称:LocalSessionRegListener
 * 类描述:监听本地Session的登入登出，同步缓存
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年7月2日 下午4:04:05
 * 修改备注:
 * @version 1.0.0
 */
public class LocalSessionRegListener implements HttpSessionListener {

	/**
	 * 创建一个新的实例 LocalSessionRegListener.
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
//		YoubusNativeCacheService.AGENT_SESSION_CACHE.remove(sessionId);//登出，直接清除会话
	}

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

	}

}
