/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusThreadService.java
 * �� ������:2016��5��11��-����12:47:58
 * Copyright @ 2016-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.framework.comm.servicethread.FileExportService;

/**
 * ������:YoubusAppCFGLoadService
 * ������:Ӧ�����ò�������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��5��11�� ����12:47:58
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YoubusAppCFGLoadService implements Service {
	private static Logger log=Logger.getLogger(YoubusAppCFGLoadService.class);
	/**
	 * ����һ���µ�ʵ�� YoubusThreadService.
	 *
	 */
	public static int ReportExportThreadPoolSize=1;/*����excel �ļ������̣߳�ȱʡ�̳߳ش�С*/
	public static int DailyHtmlThreadPoolSize=1;/*daily����html�̣߳�ȱʡ�̳߳ش�С*/
	public YoubusAppCFGLoadService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		FileExportService.getInstance().destroyThreadPool();/*����excel �ļ������̣߳��̳߳عر�*/
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map params) throws FrameException {
		// TODO Auto-generated method stub
		log.debug("YoubusAppCFGLoadService init Begin... ...");
		//ReportExportThreadPoolSize
		if(params.containsKey("ReportExportThreadPoolSize"))ReportExportThreadPoolSize=Integer.valueOf((String)params.get("ReportExportThreadPoolSize"));
		if(params.containsKey("DailyHtmlThreadPoolSize"))DailyHtmlThreadPoolSize=Integer.valueOf((String)params.get("DailyHtmlThreadPoolSize"));

		log.debug("YoubusThreadService init End... ...");
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
