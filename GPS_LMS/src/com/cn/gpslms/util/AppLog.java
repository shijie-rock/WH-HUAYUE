/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:AppLog.java
 * �� ������:2015��4��29��-����11:55:59
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.util;

import org.apache.log4j.Logger;

/**
 * ������:AppLog
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��29�� ����11:55:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AppLog {

	/**
	 * ����һ���µ�ʵ�� AppLog.
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
