/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service.thread
 * ��   ��  ��:TingcheSender.java
 * �� ������:2016��12��3��-����8:56:53
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
 * ������:TingcheSender
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��12��3�� ����8:56:53
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TingcheSender {

	private static Logger log=Logger.getLogger(TingcheSender.class);
	private static TingcheSender instance;
	public static TingcheSender getInstance(){
		if(instance==null)instance=new TingcheSender();
		return instance;
	}
	
	private ExecutorService fixedThreadPool=null;
	
	private TingcheSender() {
		// TODO Auto-generated constructor stub
		fixedThreadPool = Executors.newFixedThreadPool(InitSendThreadParamService.TingcheThreadPoolSize); 
	}
	
	public void tingcheJudge(EtVehicleGpsDataPO temp){
		TingcheSendThread tsThread=new TingcheSendThread(temp);
		fixedThreadPool.execute(tsThread);
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
		System.out.println(200*24*60);
	}

}
class TingcheSendThread implements Runnable{
	private static Logger log=Logger.getLogger(TingcheSendThread.class);
	private boolean RUN_TAG=true;
	public void setOff(){
		this.RUN_TAG=false;
	}
	
	private EtVehicleGpsDataPO temp;
	
	public TingcheSendThread(EtVehicleGpsDataPO temp){
		this.temp= temp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!RUN_TAG)return;
		log.debug("TingcheSendThread:="+this+"��ʼִ��");
		AlarmFactory.tingcheJudge(temp);
		log.debug("TingcheSendThread:="+this+"����ִ��");	
	
	}
}
