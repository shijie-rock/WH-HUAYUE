/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:ClearNewVehicleGPSDataService.java
 * 创 建日期:2016年9月3日-下午11:16:23
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
 * 类名称:ClearNewVehicleGPSDataService
 * 类描述:每10 分钟定时清理new_vehicle_gps_data，PL_DRIVE_TABLE gps时间>过期25小时 数据
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年9月3日 下午11:16:23
 * 修改备注:
 * @version 1.0.0
 */
public class ClearNewVehicleGPSDataService implements Service {
	private static Logger log=Logger.getLogger(ClearNewVehicleGPSDataService.class);
	/**
	 * 创建一个新的实例 ClearNewVehicleGPSDataService.
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
	    	log.debug("开始执行 清除 gps 及 疲劳驾驶表  ");
	        System.out.println("-------开始执行 清除 gps 及 疲劳驾驶表   --------");
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
	        System.out.println("-------结束执行 清除 gps 及 疲劳驾驶表   --------");
	        log.debug("结束执行 清除 gps 及 疲劳驾驶表   ");
	      }
	    }, 10*1000,10*60*1000);//间隔10分钟
	}

}
