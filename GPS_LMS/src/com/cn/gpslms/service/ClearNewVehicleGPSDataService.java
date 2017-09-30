/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:ClearNewVehicleGPSDataService.java
 * �� ������:2016��9��3��-����11:16:23
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;

/**
 * ������:ClearNewVehicleGPSDataService
 * ������:ÿ10 ���Ӷ�ʱ����new_vehicle_gps_data��PL_DRIVE_TABLE gpsʱ��>����25Сʱ ����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��9��3�� ����11:16:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ClearNewVehicleGPSDataService implements Service {
	private static Logger log=Logger.getLogger(ClearNewVehicleGPSDataService.class);
	/**
	 * ����һ���µ�ʵ�� ClearNewVehicleGPSDataService.
	 *
	 */
	public ClearNewVehicleGPSDataService() {
		// TODO Auto-generated constructor stub
	}
	Connection targetConn=null;
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		try {
			if(targetConn!=null)targetConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(" close conn error");
			log.error(e.getMessage());
		} catch (Throwable t){
			t.printStackTrace();
			log.error(" close conn error");
			log.error(t.getMessage());
		}
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
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		
		targetConn=DBService.getInstance().getConnection();
		
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	    	log.debug("��ʼִ�� ��� gps �� ƣ�ͼ�ʻ��  ");
	        System.out.println("-------��ʼִ�� ��� gps �� ƣ�ͼ�ʻ��   --------");
	        try {
	        	targetConn.setAutoCommit(false);
				TargetDataPOFactory.clearNewVehicleGpsData(targetConn);
				TargetDataPOFactory.clearPLVehicleGpsData(targetConn);//TODO
//				TargetDataPOFactory.clearPL_DRIVE_TABLEData(targetConn);//TODO
				targetConn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} 
	        System.out.println("-------����ִ�� ��� gps �� ƣ�ͼ�ʻ��   --------");
	        log.debug("����ִ�� ��� gps �� ƣ�ͼ�ʻ��   ");
	      }
	    }, 10*1000,10*60*1000);//���10����
	}

}
