/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.pofactory
 * 文   件  名:TargetDataPOFactory.java
 * 创 建日期:2016年8月28日-上午11:09:47
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.pofactory;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.po.NewVehicleGpsDataPO;
import com.cn.gpslms.po.NewVehiclePO;
import com.cn.gpslms.po.TmGpsLmsAlarmFlowPO;
import com.cn.gpslms.po.TmPlAlarmBeanPO;
import com.cn.gpslms.service.SQLCommonService;
import com.cn.gpslms.util.DBConUtil;
import com.cn.gpslms.util.GPSUtil;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;

/**
 * 类名称:TargetDataPOFactory
 * 类描述:目标库
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月28日 上午11:09:47
 * 修改备注:
 * @version 1.0.0
 */
public class TargetDataPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TargetDataPOFactory.class);
	/**
	 * 创建一个新的实例 TargetDataPOFactory.
	 *
	 */
	public TargetDataPOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	private static ConcurrentHashMap<String,NewVehiclePO> VEHICLE_CACHE=new ConcurrentHashMap<String,NewVehiclePO>();//车辆基本信息 -车牌号，车辆bean
	
	/**
	 * 从待插入库查询最大的id，及需要被导入的数据起点
	 * 方   法  名:QueryImportDataBaseId
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static long queryImportDataBaseId(Connection conn){
		long baseId=0;
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,null);
		try {
			DynaBean bean=DBConUtil.getSingleResult(conn, sql, "BASE_ID_BEAN");
			if(bean!=null){
				baseId=bean.getInt("BASE_ID");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseId;
	}
	
	//废弃
	public static List<NewVehicleGpsDataPO> getNeedSendGpsDataList(Connection conn){
		
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,null);
		try {
			return DBConUtil.getPOResult(conn, sql, new NewVehicleGpsDataPO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	
	//new_vehicle_gps_data
	/**
	 * 
	 * 方   法  名:clearNewVehicleGpsData
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void clearNewVehicleGpsData(Connection conn){
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,null);
		try {
			DBConUtil.update(conn, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//PL_DRIVE_TABLE
//	public static void clearPL_DRIVE_TABLEData(Connection conn){
//		
//		
//	}
	
	
	/**
	 * 查询在10分钟内，是否有相同的报警，有则不需要发送报警
	 * 方   法  名:queryNeedAlarmTag
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param alarmTypeCode
	 * 参         数:@param simNo
	 * 参         数:@return
	 * 返   回  值:boolean ture 没有，需要发报警，false 有，不需要发报警
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static boolean queryNeedAlarmTag(Connection conn,String alarmTypeCode,String simNo){
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,new String[]{simNo});
		List list=null;
		try {
			list=DBConUtil.getResult(conn, sql, "EXISTS_SAME_ALARM");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list==null||list.size()==0;
	}
	
	
	public static void recordAlarmContent(Connection conn,String alarmTypeCode,String alarmDesc,String alarmXml,EtVehicleGpsDataPO gpsData){
		TmGpsLmsAlarmFlowPO gpsAlarmFPOCon=new TmGpsLmsAlarmFlowPO();
		gpsAlarmFPOCon.setAddress(gpsData.getEtvdRemark());
		gpsAlarmFPOCon.setAlarmTime(new Date(System.currentTimeMillis()));
		gpsAlarmFPOCon.setAlarmTypeCode(alarmTypeCode);
		gpsAlarmFPOCon.setAlarmTypeDesc(alarmDesc);
		gpsAlarmFPOCon.setAlarmXml(alarmXml);
		gpsAlarmFPOCon.setEtvdId(gpsData.getEtvdId());
		gpsAlarmFPOCon.setEtvdLatitude(gpsData.getEtvdLatitude());
		gpsAlarmFPOCon.setEtvdLongitude(gpsData.getEtvdLongitude());
		gpsAlarmFPOCon.setImportBy(0);
		gpsAlarmFPOCon.setImportTime(new Date(System.currentTimeMillis()));
		gpsAlarmFPOCon.setSimNo(gpsData.getEtvdSimNo());
		if(gpsData.getEtvdSpeed()!=null)
		gpsAlarmFPOCon.setSpeed(Double.valueOf(gpsData.getEtvdSpeed()));
		gpsAlarmFPOCon.setStatus("1");
		POFactory.insert(conn, gpsAlarmFPOCon);
	}
	
	/**
	 * 定时将车辆信息存入缓存（）
	 * 方   法  名:refreshVehicle
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void refreshVehicle(Connection conn){
		NewVehiclePO vehiclePOCon=new NewVehiclePO();
//		vehiclePOCon.setStatus//TOOD
		List<NewVehiclePO> list=POFactory.select(conn, vehiclePOCon);
		if(list!=null&&list.size()>0)
			for(NewVehiclePO bean:list){
				String license=bean.getEtveCard();
				if("宁波万华一腾物流有限公司".equals(bean.getEtveBillParty()))bean.setEtveBillNo("0000800585");
				else if("烟台万华合成革集团华悦汽车运输有限公司".equals(bean.getEtveBillParty()))bean.setEtveBillNo("0000800001");
				else if("宁波大榭开发区华中物流有限公司".equals(bean.getEtveBillParty()))bean.setEtveBillNo("0000800040");
				else{
					
				}
				log.debug(" 车牌["+license+"]对应公司 :"+bean.getEtveBillParty());
				VEHICLE_CACHE.put(license, bean);
			}
		GPSUtil.logMap(VEHICLE_CACHE, "刷新车辆缓存", log);
	}
	
	public static String getBillCode(String license){
		return VEHICLE_CACHE.get(license)!=null?VEHICLE_CACHE.get(license).getEtveBillNo():"";
	}

	
	/**
	 * 清理超过25小时的车辆速度分钟记录(疲劳)
	 * 方   法  名:clearPLVehicleGpsData
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void clearPLVehicleGpsData(Connection conn){
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,null);
		try {
			DBConUtil.update(conn, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 清理当前车辆相同分钟的记录
	 * 方   法  名:clearCurrentPLVehicleGpsData
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void clearCurrentPLVehicleGpsData(Connection conn,String simNo,Date startDate){
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String dateMinute=DBConUtil.handleTimestamp3(startDate);
		String sql=SQLCommonService.getSql(key,new String[]{simNo,dateMinute});
		try {
			DBConUtil.update(conn, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void recordPLSourceDate(Connection conn,EtVehicleGpsDataPO gpsData){
		TmPlAlarmBeanPO plAlarmFPOCon=new TmPlAlarmBeanPO();
		plAlarmFPOCon.setImportBy(0);
		plAlarmFPOCon.setImportTime(new Date(System.currentTimeMillis()));
		plAlarmFPOCon.setSimNo(gpsData.getEtvdSimNo());
		if(gpsData.getEtvdSpeed()!=null)
			plAlarmFPOCon.setSpeed(Double.valueOf(gpsData.getEtvdSpeed()));
		plAlarmFPOCon.setStatus("1");
		plAlarmFPOCon.setStartTime(gpsData.getCreateTime());
		POFactory.insert(conn, plAlarmFPOCon);
		
	}
	
	/**
	 * 24小时累计驾车时间超过8小时
	 * 方   法  名:sum24PLVehicleGpsData
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param simNo
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static boolean sum24PLVehicleGpsData(Connection conn,String simNo){
		
		int LIMIT_COUNT=8*60;
		
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,new String[]{simNo});
		try {
			DynaBean bean=DBConUtil.getSingleResult(conn, sql,"24HBean");
			if(bean!=null){
				int nowCount=0;
				Object countObj=bean.get("H24H");
				if(countObj instanceof Long)nowCount=((Long)countObj).intValue();
				else nowCount=(Integer)countObj;
				return LIMIT_COUNT<=nowCount;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 连续驾驶4小时休息时间少于20分钟
	 * 方   法  名:sum4PLVehicleGpsData
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param simNo
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static boolean sum4PLVehicleGpsData(Connection conn,String simNo){
		int MIN_LIMIT_COUNT=20;
		int LIMIT_COUNT=4*60-MIN_LIMIT_COUNT;
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,new String[]{simNo});
		try {
			DynaBean bean=DBConUtil.getSingleResult(conn, sql,"4HBean");
			if(bean!=null){
				int nowCount=0;
				Object countObj=bean.get("H4H");
				if(countObj instanceof Long)nowCount=((Long)countObj).intValue();
				else nowCount=(Integer)countObj;
				return LIMIT_COUNT<=nowCount;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
//		MethodName methodName = new MethodName();
		String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("class name: " + clazz + " Method Name " + method);
		
		String str="SELECT * FROM tm_gps_lms_alarm_flow WHERE  DATE_ADD(ALARM_TIME,INTERVAL 60 minute)>NOW() AND SIM_NO=''{0}''AND STATUS='1'";
		String result=MessageFormat.format(str, new String[]{"123"});
		System.out.println(result);
		
	}
	
	private static String getThisClassSimpleName(){
		String className=Thread.currentThread().getStackTrace()[1].getClassName();
		return className.substring(className.lastIndexOf(".")+1);
	}
}
