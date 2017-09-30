/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service.thread
 * ��   ��  ��:PLSender.java
 * �� ������:2017��1��7��-����9:49:42
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
 * ������:PLSender
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��1��7�� ����9:49:42
 * �޸ı�ע:
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
		log.debug("PLSendThread:="+this+"��ʼִ��");
		AlarmFactory.PLJudge(temp);
		log.debug("PLSendThread:="+this+"����ִ��");	
	
	}
}
