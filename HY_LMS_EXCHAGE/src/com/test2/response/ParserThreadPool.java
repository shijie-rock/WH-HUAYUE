/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response
 * ��   ��  ��:ParserThreadPool.java
 * �� ������:2019��3��21��-����12:10:23
 * Copyright @ 2019-YouBus.com.cn
 */
package com.test2.response;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ������:ParserThreadPool
 * ������:�������̳߳�(����)
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2019��3��21�� ����12:10:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ParserThreadPool {
	
	private static class ParserThreadPoolHolder{
		private static ParserThreadPool instance=new ParserThreadPool();
	}
	
	private ExecutorService fixedThreadPool;
	
	private ParserThreadPool(){
		fixedThreadPool = Executors.newFixedThreadPool(20);  
	}
	
	public static ParserThreadPool getInstance(){
		System.out.println(ParserThreadPoolHolder.instance);
		return ParserThreadPoolHolder.instance;
	}
	
	
	
//	private Object testString=new Object();
//	
//	public Object getO(){
//		return testString;
//	}
	
	public ExecutorService getPool(){
		return fixedThreadPool;
	}
	
	public static void main(String args[]){
		for(int i=0;i<100;i++){
			System.out.println(ParserThreadPool.getInstance().getPool());
		}

	}

}
