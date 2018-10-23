/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:InitSendThreadParamService.java
 * �� ������:2016��12��3��-����6:29:56
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**
 * ������:InitSendThreadParamService
 * ������:�����̲߳�������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��12��3�� ����6:29:56
 * �޸ı�ע:
 * @version 1.0.0
 */
public class InitSendThreadParamService implements Service {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	public static int ChaosuThreadPoolSize=1;/*��lms�����߳����̱߳����صĴ�С*/
	public static int TingcheThreadPoolSize=1;/*��lms������ͣ�������̳߳صĴ�С*/
	public static int PLThreadPoolSize=1;/*��lms����ƣ�ͱ����̳߳صĴ�С*/
	
	private static Logger log=Logger.getLogger(InitSendThreadParamService.class);
	
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map paramMap) throws FrameException {
		// TODO Auto-generated method stub
		log.debug("InitSendThreadParamService init Begin... ...");
		//ReportExportThreadPoolSize
		if(paramMap.containsKey("ChaosuThreadPoolSize"))ChaosuThreadPoolSize=Integer.valueOf((String)paramMap.get("ChaosuThreadPoolSize"));
		if(paramMap.containsKey("TingcheThreadPoolSize"))TingcheThreadPoolSize=Integer.valueOf((String)paramMap.get("TingcheThreadPoolSize"));
		if(paramMap.containsKey("PLThreadPoolSize"))PLThreadPoolSize=Integer.valueOf((String)paramMap.get("PLThreadPoolSize"));

		log.debug(" ��ʼ�� ChaosuThreadPoolSize :="+ChaosuThreadPoolSize);
		log.debug(" ��ʼ�� TingcheThreadPoolSize :="+TingcheThreadPoolSize);
		log.debug(" ��ʼ�� PLThreadPoolSize :="+PLThreadPoolSize);
		
		log.debug("InitSendThreadParamService init End... ...");
		
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
