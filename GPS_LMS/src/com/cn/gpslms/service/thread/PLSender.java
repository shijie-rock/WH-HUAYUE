/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service.thread
 * 文   件  名:PLSender.java
 * 创 建日期:2017年1月7日-下午9:49:42
 * Copyright @ 2017-YouBus.com.cn
 */
package com.cn.gpslms.service.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cn.gpslms.alarm.AlarmFactory;
import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.service.InitSendThreadParamService;

/**
 * 类名称:PLSender
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年1月7日 下午9:49:42
 * 修改备注:
 * @version 1.0.0
 */
public class PLSender {
	
	private static Logger log=Logger.getLogger(PLSender.class);
	private static PLSender instance;
	public static PLSender getInstance(){
		if(instance==null)instance=new PLSender();
		return instance;
	}
	
	private ExecutorService fixedThreadPool=null;
	
	private PLSender() {
		// TODO Auto-generated constructor stub
		fixedThreadPool = Executors.newFixedThreadPool(InitSendThreadParamService.PLThreadPoolSize); 
	}
	
	public void PLJudge(EtVehicleGpsDataPO temp){
		PLSendThread plThread=new PLSendThread(temp);
		fixedThreadPool.execute(plThread);
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
class PLSendThread implements Runnable{
	private static Logger log=Logger.getLogger(PLSendThread.class);
	private boolean RUN_TAG=true;
	public void setOff(){
		this.RUN_TAG=false;
	}
	
	private EtVehicleGpsDataPO temp;
	
	public PLSendThread(EtVehicleGpsDataPO temp){
		this.temp= temp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!RUN_TAG)return;
		log.debug("PLSendThread:="+this+"开始执行");
		AlarmFactory.PLJudge(temp);
		log.debug("PLSendThread:="+this+"结束执行");	
	
	}
}
