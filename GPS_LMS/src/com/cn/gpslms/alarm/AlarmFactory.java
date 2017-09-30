/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.pofactory
 * 文   件  名:AlarmFactory.java
 * 创 建日期:2016年9月3日-下午4:34:26
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.alarm;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.httpclient.RemoteLMSNewClient;
import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.po.NewVehicleGpsDataPO;
import com.cn.gpslms.po.TmChaosuAlarmBeanPO;
import com.cn.gpslms.po.TmTingcheAlarmBeanPO;
import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.cn.gpslms.util.AppLog;
import com.cn.gpslms.util.GPSUtil;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;

/**
 * 类名称:AlarmFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年9月3日 下午4:34:26
 * 修改备注:
 * @version 1.0.0
 */
public class AlarmFactory {
	private static Logger log=Logger.getLogger(AlarmFactory.class);
	private static Logger alarmLog=AppLog.getInstance().getDELog();
	private static Logger baseLog=AppLog.getInstance().getBASELog();
	/**
	 * 创建一个新的实例 AlarmFactory.
	 *
	 */
	public AlarmFactory() {
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
		System.out.println(0==new Double(0.0d));
		isInHourScope(0, 1);

	}
	
	private static ConcurrentHashMap<String,AlarmBean> CHAOSU_CACHE=new ConcurrentHashMap<String,AlarmBean>();//超速行驶
	private static ConcurrentHashMap<String,AlarmBean> TINGCHE_CACHE=new ConcurrentHashMap<String,AlarmBean>();//异常停车
	
	/**
	 * 从数据库中获取暂存数据，放入超速缓存
	 * 方   法  名:initAlarmCSCache
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param key
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AlarmBean initAlarmCSCache(Connection conn,String key){//系统启动时,或者当缓存为空时数据比较数据时 从数据库中 装入 缓存数据
		//GET DATA FROM DB
		TmChaosuAlarmBeanPO caPOCon=new TmChaosuAlarmBeanPO();
		caPOCon.setSimNo(key);
		caPOCon.setStatus("1");
		
		caPOCon=POFactory.getByPriKey(conn, caPOCon);
		AlarmBean bean=new AlarmBean();
		if(caPOCon!=null){
			try {
				BeanUtils.copyProperties(bean, caPOCon);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}else{
			bean.setSimNo(key);
		}
		//PUT CHAOSU_CACHE
		CHAOSU_CACHE.put(key, bean);
		return bean;
	}
	/**
	 * 从数据库中获取暂存数据，放入停车缓存
	 * 方   法  名:initAlarmTCCache
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param key
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AlarmBean initAlarmTCCache(Connection conn,String key){//系统启动时,或者当缓存为空时数据比较数据时 从数据库中 装入 缓存数据
		//GET DATA FROM DB
		//PUT CHAOSU_CACHE
		TmTingcheAlarmBeanPO caPOCon=new TmTingcheAlarmBeanPO();
		caPOCon.setSimNo(key);
		caPOCon.setStatus("1");
		
		caPOCon=POFactory.getByPriKey(conn, caPOCon);
		AlarmBean bean=new AlarmBean();
		if(caPOCon!=null){
			try {
				BeanUtils.copyProperties(bean, caPOCon);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}else{
			bean.setSimNo(key);
		}
		//PUT CHAOSU_CACHE
		TINGCHE_CACHE.put(key, bean);
		return bean;
	}
	
	
	
	public static void chaosuJudge(EtVehicleGpsDataPO gpsData){
		//
		if(gpsData==null){
			log.warn("EtVehicleGpsDataPO 数据为空");
			return;
		}
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
//			if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
			log.warn("ETVD_SIM_NO 或者 ETVD_SPEED 或者 ETVD_SYS_TIME 数据为空 ："+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		boolean alarmDone=false;//处理一批只报警一次
		String alarmStatus="S01"; //S01：超速行驶
		try{
			conn=DBService.getInstance().getConnection();
		String key=gpsData.getEtvdSimNo();
		AlarmBean value=CHAOSU_CACHE.get(key);
		if(value==null)value=initAlarmCSCache(conn, key);
		Double speed=Double.valueOf(gpsData.getEtvdSpeed());
		boolean needJudge=false;
//		needJudge="putong".equals(gpsData.getVehicleType)&&100<=speed||"weixian".equals(gpsData.getVehicleType)&&80<=speed;
		needJudge=100<speed||80<speed;//TEST //TODO
		
		Date gpsTime=gpsData.getCreateTime();
//		Date gpsTime=gpsData.getEtvdSysTime();
		Date baseTime=value.getStartTime();
		
		if(baseTime!=null&&baseTime.compareTo(gpsTime)>=0){
			log.warn("缓存时间["+baseTime+"]晚于gps时间["+gpsTime+"]退出比较："+gpsData.toXMLString());
			return;
		}
//		needJudge=needJudge&&(baseTime==null||baseTime.compareTo(gpsTime)<=0);
		
		if(needJudge){
			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度超速["+speed+"] 进入超速判断");
//			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getEtvdSysTime()+"]速度超速["+speed+"] 进入超速判断");
			log.debug("超速GPS数据SIM_NO["+value.getSimNo()+"]："+gpsData.toXMLString());
			if(value.getSpeed()==null||value.getSpeed()==0d)value.setSpeed(speed);
			if(baseTime==null)value.setStartTime(gpsTime);
			Double avgSpeed=(value.getSpeed()+speed)/2;
			value.setSpeed(avgSpeed);
//			boolean avgSpeedSupTag=100<speed||80<speed;
//			if(avgSpeedSupTag){
			if(gpsTime.getTime()-value.getStartTime().getTime()>90*1000){//超速90秒
				alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
				if(alarmDone){
					alarmDone=true;
					alarmLog.warn("超速90秒报警SIM_NO["+value.getSimNo()+"]："+gpsData.toXMLString());
					//INSERT DB TABLE ALARM
					//SEND WS CLIENT
					String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
					TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "超速90秒报警SIM_NO["+value.getSimNo()+"]", alarmXML, gpsData);
					log.warn(" SEND SERVER ALARM XML := "+alarmXML);
					RemoteLMSNewClient.sendAlarmData(alarmXML);
					log.warn(" SEND SERVER ALARM END ");
					
					value.setSpeed(null);
					value.setStartTime(null);
					log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度["+avgSpeed+"] -已超速报警-清除缓存");
					
				}else{
					alarmLog.warn("超速90秒报警SIM_NO["+value.getSimNo()+"]-本次已报警，无需再次报警："+gpsData.toXMLString());
				}
				}
//			}else{
//				
//				//do nothing
//			}
			
		}else{
			value.setSpeed(null);
			value.setStartTime(null);
			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度正常["+speed+"] 清除缓存");
//			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getEtvdSysTime()+"]速度正常["+speed+"] 清除缓存");
		}
		
		CHAOSU_CACHE.put(key, value);
		//insert db
		conn.setAutoCommit(false);
		TmChaosuAlarmBeanPO caPOValue=new TmChaosuAlarmBeanPO();
		caPOValue.setSimNo(key);
		POFactory.delete(conn, caPOValue);
		BeanUtils.copyProperties(caPOValue, value);
		caPOValue.setImportTime(new Date(System.currentTimeMillis()));
		caPOValue.setImportBy(0);
		POFactory.insert(conn, caPOValue);
		conn.commit();
		
		//CONN CLOSE
		
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Throwable t){
			t.printStackTrace();
			log.error(t.getMessage());
		}finally{
			try {
				if(conn!=null)conn.close();;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error(" close conn error");
				log.error(e.getMessage());
			} catch (Throwable t){
				t.printStackTrace();
				log.error(" close conn error");
				log.error(t.getMessage());
			}
			
		}
	}
	
	
	public static void tingcheJudge(EtVehicleGpsDataPO gpsData){
		//
		if(gpsData==null){
			log.warn("EtVehicleGpsDataPO 数据为空");
			return;
		}
//		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
			log.warn("ETVD_SIM_NO 或者 ETVD_SPEED 或者 ETVD_SYS_TIME 数据为空 ："+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		String alarmStatus="S03"; //S03：事故报警
		boolean alarmDone=false;//处理一批只报警一次
		try{
			conn=DBService.getInstance().getConnection();
			conn.setAutoCommit(false);
			
		String key=gpsData.getEtvdSimNo();
		AlarmBean value=TINGCHE_CACHE.get(key);
		if(value==null)value=initAlarmTCCache(conn, key);
		Double speed=Double.valueOf(gpsData.getEtvdSpeed());
//		Date gpsTime=gpsData.getEtvdSysTime();
		Date gpsTime=gpsData.getCreateTime();
		Calendar gpsCal=Calendar.getInstance();
		gpsCal.setTime(gpsTime);
		
		boolean needJudge=false;
//		needJudge=0==speed&&(!"weixian".equals(gpsData.getVehicleType)||("weixian".equals(gpsData.getVehicleType)&&gpsCal.get(Calendar.HOUR_OF_DAY)>=7&&gpsCal.get(Calendar.HOUR_OF_DAY)<=18));
		needJudge=0==speed;//TEST //TODO
		
		Date baseTime=value.getStartTime();
		if(baseTime!=null&&baseTime.compareTo(gpsTime)>=0){
			log.warn("缓存时间["+baseTime+"]晚于gps时间["+gpsTime+"]退出比较："+gpsData.toXMLString());
			return;
		}
//		needJudge=needJudge&&(baseTime==null||baseTime.compareTo(gpsTime)<=0);
		
		if(needJudge){
//			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getEtvdSysTime()+"]速度为0["+speed+"] 进入停车判断");
			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度为0["+speed+"] 进入停车判断");
			log.debug("异常停车GPS数据SIM_NO["+value.getSimNo()+"]："+gpsData.toXMLString());
			if(baseTime==null)value.setStartTime(gpsTime);
			value.setSpeed(speed);
			Calendar valueDateCal=Calendar.getInstance();
			valueDateCal.setTime(value.getStartTime());
			
			if(gpsCal.get(Calendar.DAY_OF_MONTH)>valueDateCal.get(Calendar.DAY_OF_MONTH))
				value.setStartTime(gpsTime);//排除跨夜
			
//		    if("weixian".equals(gpsData.getVehicleType)){
//				
//			}else{//putong	
//				
//			}
			
			if(gpsTime.getTime()-value.getStartTime().getTime()>60*60*1000){//异常停车报警超过1h
//				alram;//TODO
				alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
				if(alarmDone){
					alarmLog.warn("异常停车报警SIM_NO["+value.getSimNo()+"]："+gpsData.toXMLString());
					//INSERT DB TABLE ALARM
					//SEND WS CLIENT
					String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
					TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "异常停车报警SIM_NO["+value.getSimNo()+"]", alarmXML, gpsData);
					log.warn(" SEND SERVER ALARM XML := "+alarmXML);
					RemoteLMSNewClient.sendAlarmData(alarmXML);
					log.warn(" SEND SERVER ALARM END ");
					
					value.setSpeed(null);
					value.setStartTime(null);
					log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度["+speed+"] -已超停报警-清除缓存");
					
				}else{
					alarmLog.warn("异常停车报警SIM_NO["+value.getSimNo()+"]-本次已报警，无需再次报警："+gpsData.toXMLString());
				}
			}
			
		}else{
			value.setSpeed(null);
			value.setStartTime(null);
			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getCreateTime()+"]速度正常["+speed+"] 清除缓存");
//			log.debug("车辆 ["+gpsData.getEtvdSimNo()+"]时间["+gpsData.getEtvdSysTime()+"]速度正常["+speed+"] 清除缓存");
		}
		
		TINGCHE_CACHE.put(key, value);
		//insert db
//		conn.setAutoCommit(false);
		TmTingcheAlarmBeanPO caPOValue=new TmTingcheAlarmBeanPO();
		caPOValue.setSimNo(key);
		POFactory.delete(conn, caPOValue);
		BeanUtils.copyProperties(caPOValue, value);
		caPOValue.setImportTime(new Date(System.currentTimeMillis()));
		caPOValue.setImportBy(0);
		POFactory.insert(conn, caPOValue);
		conn.commit();
		//CONN CLOSE
		
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Throwable t){
			t.printStackTrace();
			log.error(t.getMessage());
		}finally{
			try {
				if(conn!=null)conn.close();;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error(" close conn error");
				log.error(e.getMessage());
			} catch (Throwable t){
				t.printStackTrace();
				log.error(" close conn error");
				log.error(t.getMessage());
			}
			
		}
	}
	
	/**
	 * 疲劳驾驶
	 * 连续驾驶4小时休息时间少于20分钟；
	   24小时累计驾车时间超过8小时；
	 * 方   法  名:PLJudge
	 * 方法描述:
	 * 参         数:@param gpsData
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void PLJudge(EtVehicleGpsDataPO gpsData){
		//
		if(gpsData==null){
			log.warn("EtVehicleGpsDataPO 数据为空");
			return;
		}
//		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
			log.warn("ETVD_SIM_NO 或者 ETVD_SPEED 或者 ETVD_SYS_TIME 数据为空 ："+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		String alarmStatus="S02"; //S02：疲劳驾驶
		boolean alarmDone=false;//处理一批只报警一次
		try{
			conn=DBService.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String simNo=gpsData.getEtvdSimNo();
//			确定每辆车 每分钟2条记录
			Double speed=Double.valueOf(gpsData.getEtvdSpeed());
			
//			Date gpsTime=gpsData.getEtvdSysTime();
			Date gpsTime=gpsData.getCreateTime();
			
			TargetDataPOFactory.clearCurrentPLVehicleGpsData(conn, simNo, gpsTime);
			
			TargetDataPOFactory.recordPLSourceDate(conn, gpsData);
			
			boolean needJudge=false;
			needJudge=0<speed;//速度为0 ，不判断是否疲劳
			
			if(needJudge){
				
				if(TargetDataPOFactory.sum24PLVehicleGpsData(conn, simNo)&&isInHourScope(0, 1)){//24小时内 8小时总驾驶时间,0-1点判断24小时问题
//				alram;//TODO
					alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
					if(alarmDone){
						alarmLog.warn("疲劳驾驶：24小时内 8小时总驾驶时间 报警SIM_NO["+simNo+"]："+gpsData.toXMLString());
						//INSERT DB TABLE ALARM
						//SEND WS CLIENT
						String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
						TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "疲劳驾驶：24小时内 8小时总驾驶时间 报警SIM_NO["+simNo+"]：", alarmXML, gpsData);
						log.warn(" SEND SERVER ALARM XML := "+alarmXML);
						RemoteLMSNewClient.sendAlarmData(alarmXML);
						log.warn(" SEND SERVER ALARM END ");
						
					}else{
						alarmLog.warn("疲劳驾驶：报警SIM_NO["+simNo+"]-本次已报警，无需再次报警："+gpsData.toXMLString());
					}
				}else{
					if(TargetDataPOFactory.sum4PLVehicleGpsData(conn, simNo)){ //连续驾驶4小时休息时间少于20分钟
						alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
						if(alarmDone){
							alarmLog.warn("疲劳驾驶：连续驾驶4小时休息时间少于20分钟 报警SIM_NO["+simNo+"]："+gpsData.toXMLString());
							//INSERT DB TABLE ALARM
							//SEND WS CLIENT
							String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
							TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "疲劳驾驶：连续驾驶4小时休息时间少于20分钟 报警SIM_NO["+simNo+"]：", alarmXML, gpsData);
							log.warn(" SEND SERVER ALARM XML := "+alarmXML);
							RemoteLMSNewClient.sendAlarmData(alarmXML);
							log.warn(" SEND SERVER ALARM END ");
							
						}else{
							alarmLog.warn("疲劳驾驶：报警SIM_NO["+simNo+"]-本次已报警，无需再次报警："+gpsData.toXMLString());
						}	
					}
				}
				
			}
			conn.commit();
			//CONN CLOSE
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Throwable t){
			t.printStackTrace();
			log.error(t.getMessage());
		}finally{
			try {
				if(conn!=null)conn.close();;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error(" close conn error");
				log.error(e.getMessage());
			} catch (Throwable t){
				t.printStackTrace();
				log.error(" close conn error");
				log.error(t.getMessage());
			}
			
		}
	}
	
	/**
	 * 固定间隔向LMS 发送请求
	 * 方   法  名:sendGpsBaseData
	 * 方法描述:
	 * 参         数:@param gpsDataList
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @throws MalformedURLException 
	 * @exception
	 * @since  1.0.0
	 */
	public static void sendGpsBaseData(List<NewVehicleGpsDataPO> gpsDataList) throws MalformedURLException{
		String reqXML=GPSUtil.packWSXml(gpsDataList);
		baseLog.warn(" SEND SERVER BASE GPS XML := "+reqXML);
		RemoteLMSNewClient.sendAlarmData(reqXML);
		baseLog.warn(" SEND SERVER BASE GPS END ");
	}
	
	/**
	 * 当前时间，是否在beginClock 和 endClock内
	 * 方   法  名:isInHourScope
	 * 方法描述:
	 * 参         数:@param beginClock
	 * 参         数:@param endClock
	 * 参         数:@return
	 * 返   回  值:boolean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static boolean isInHourScope(int beginClock,int endClock){
		Calendar c=Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int houre=c.get(Calendar.HOUR_OF_DAY);
		System.out.println(houre);
		return houre>=beginClock&&houre<=endClock;
		
	}

}
