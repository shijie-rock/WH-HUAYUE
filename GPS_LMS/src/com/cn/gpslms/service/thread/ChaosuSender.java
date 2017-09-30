/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service.thread
 * ��   ��  ��:ChaosuSender.java
 * �� ������:2016��12��3��-����8:45:00
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cn.gpslms.alarm.AlarmFactory;
import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.service.InitSendThreadParamService;

/**
 * ������:ChaosuSender
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��12��3�� ����8:45:00
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ChaosuSender {

	
	private static Logger log=Logger.getLogger(ChaosuSender.class);
	private static ChaosuSender instance;
	public static ChaosuSender getInstance(){
		if(instance==null)instance=new ChaosuSender();
		return instance;
	}
	
	private ExecutorService fixedThreadPool=null;
	
	private ChaosuSender() {
		// TODO Auto-generated constructor stub
		fixedThreadPool = Executors.newFixedThreadPool(InitSendThreadParamService.ChaosuThreadPoolSize); 
	}
	
	public void chaosuJudge(EtVehicleGpsDataPO temp){
		ChaosuSendThread csThread=new ChaosuSendThread(temp);
		fixedThreadPool.execute(csThread);
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
class ChaosuSendThread implements Runnable{
	private static Logger log=Logger.getLogger(ChaosuSendThread.class);
	private boolean RUN_TAG=true;
	public void setOff(){
		this.RUN_TAG=false;
	}
	
	private EtVehicleGpsDataPO temp;
	
	public ChaosuSendThread(EtVehicleGpsDataPO temp){
		this.temp= temp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!RUN_TAG)return;
		log.debug("ChaosuSendThread:="+this+"��ʼִ��");
		AlarmFactory.chaosuJudge(temp);
		log.debug("ChaosuSendThread:="+this+"����ִ��");	
		
	}
	
}
