/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:AppLog.java
 * 创 建日期:2015年4月29日-下午11:55:59
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.util;

import org.apache.log4j.Logger;

/**
 * 类名称:AppLog
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月29日 下午11:55:59
 * 修改备注:
 * @version 1.0.0
 */
public class AppLog {

	/**
	 * 创建一个新的实例 AppLog.
	 *
	 */
	final static String DE_LOG="DELog";
	final static String APP_LOG="AppLog";
	final static String BASELog="BASELog";
//	private Logger log;
	private AppLog(){
	}
//	private static boolean flag=false;
	private static AppLog aLog=new AppLog();
	public static AppLog getInstance(){
		if(null==aLog){
			aLog=new AppLog();
		}
		return aLog;
	}
	
	public Logger getBASELog(){
		return  Logger.getLogger(BASELog);
	}
	public Logger getDELog(){
		return  Logger.getLogger(DE_LOG);
	}
	public Logger getAppLog(){
		return Logger.getLogger(APP_LOG);
	}
//	public void debug(Object msg,Throwable t){
//		if(true==flag){
//			log.debug(msg,t);
//		}
//	}
}
