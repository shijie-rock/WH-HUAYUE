/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:GetSrcVehicleInfoService.java
 * 创 建日期:2016年9月11日-下午8:51:19
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.po.EtVehiclePO;
import com.cn.gpslms.po.NewVehiclePO;
import com.cn.gpslms.pofactory.SrcDataPOFatory;
import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;

/**
 * 类名称:GetSrcVehicleInfoService
 * 类描述:定时从oracle库中获取车辆基本信息（车牌，是否危险品车等）,同步到map中
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年9月11日 下午8:51:19
 * 修改备注:
 * @version 1.0.0
 */
public class GetSrcVehicleInfoService implements Service {
	private static Logger log=Logger.getLogger(GetSrcVehicleInfoService.class);
	
	private static int SKIP_TIME=60;//分钟 从oracle中获取车辆基本信息
	Connection srcCon=null;
	Connection targetCon=null;
	 
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		try {
			if(srcCon!=null)srcCon.close();
			if(targetCon!=null)targetCon.close();
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
		srcCon=DBService.getInstance().getConnection("dataSourceOra");
		targetCon=DBService.getInstance().getConnection();
		
		synVehicleData(srcCon, targetCon);//同步车辆基本信息
    	log.debug("开始执行 从本地库中获取车辆基本信息-刷新进入缓存 ");
		TargetDataPOFactory.refreshVehicle(targetCon);//刷新进入Map
		log.debug("结束执行 从本地库中获取车辆基本信息-刷新进入缓存");
		
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	    	log.debug("开始执行 从oracle库中获取车辆基本信息 ");
	        System.out.println("-------开始执行 从oracle库中获取车辆基本信息 --------");
	       
	        synVehicleData(srcCon, targetCon);
			TargetDataPOFactory.refreshVehicle(targetCon);

	        System.out.println("-------结束执行 从oracle库中获取车辆基本信息 --------");
	        log.debug("结束执行从oracle库中获取车辆基本信息 ");
	      }
	    }, SKIP_TIME*60*1000, SKIP_TIME*60*1000);
	}
	
	private static void synVehicleData(Connection srcConn,Connection targetConn){
		try{
		List<EtVehiclePO> srcList= SrcDataPOFatory.queryVehicleBaseInfo(srcConn);
		NewVehiclePO newVehiclePO;
		if(srcList!=null&&srcList.size()>0){
		targetConn.setAutoCommit(false);
		newVehiclePO=new NewVehiclePO();
//		newVehiclePO.setlicense();//先删除 原license
		POFactory.delete(targetConn, newVehiclePO);
		for(EtVehiclePO srcBean:srcList){
			newVehiclePO=new NewVehiclePO();
			BeanUtils.copyProperties(newVehiclePO, srcBean);
//			newVehiclePO.setid//自增长 id
			POFactory.insert(targetConn, newVehiclePO);
			
			}
		targetConn.commit();
		 }
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
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
