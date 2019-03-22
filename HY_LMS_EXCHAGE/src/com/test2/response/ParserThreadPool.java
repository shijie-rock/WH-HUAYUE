/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response
 * 文   件  名:ParserThreadPool.java
 * 创 建日期:2019年3月21日-下午12:10:23
 * Copyright @ 2019-YouBus.com.cn
 */
package com.test2.response;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类名称:ParserThreadPool
 * 类描述:处理器线程池(单例)
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2019年3月21日 下午12:10:23
 * 修改备注:
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
