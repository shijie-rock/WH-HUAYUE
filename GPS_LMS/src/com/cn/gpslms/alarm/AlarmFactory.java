/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.pofactory
 * ��   ��  ��:AlarmFactory.java
 * �� ������:2016��9��3��-����4:34:26
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
 * ������:AlarmFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��9��3�� ����4:34:26
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AlarmFactory {
	private static Logger log=Logger.getLogger(AlarmFactory.class);
	private static Logger alarmLog=AppLog.getInstance().getDELog();
	private static Logger baseLog=AppLog.getInstance().getBASELog();
	/**
	 * ����һ���µ�ʵ�� AlarmFactory.
	 *
	 */
	public AlarmFactory() {
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
		System.out.println(0==new Double(0.0d));
		isInHourScope(0, 1);

	}
	
	private static ConcurrentHashMap<String,AlarmBean> CHAOSU_CACHE=new ConcurrentHashMap<String,AlarmBean>();//������ʻ
	private static ConcurrentHashMap<String,AlarmBean> TINGCHE_CACHE=new ConcurrentHashMap<String,AlarmBean>();//�쳣ͣ��
	
	/**
	 * �����ݿ��л�ȡ�ݴ����ݣ����볬�ٻ���
	 * ��   ��  ��:initAlarmCSCache
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@param key
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AlarmBean initAlarmCSCache(Connection conn,String key){//ϵͳ����ʱ,���ߵ�����Ϊ��ʱ���ݱȽ�����ʱ �����ݿ��� װ�� ��������
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
	 * �����ݿ��л�ȡ�ݴ����ݣ�����ͣ������
	 * ��   ��  ��:initAlarmTCCache
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@param key
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AlarmBean initAlarmTCCache(Connection conn,String key){//ϵͳ����ʱ,���ߵ�����Ϊ��ʱ���ݱȽ�����ʱ �����ݿ��� װ�� ��������
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
			log.warn("EtVehicleGpsDataPO ����Ϊ��");
			return;
		}
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
//			if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
			log.warn("ETVD_SIM_NO ���� ETVD_SPEED ���� ETVD_SYS_TIME ����Ϊ�� ��"+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		boolean alarmDone=false;//����һ��ֻ����һ��
		String alarmStatus="S01"; //S01��������ʻ
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
			log.warn("����ʱ��["+baseTime+"]����gpsʱ��["+gpsTime+"]�˳��Ƚϣ�"+gpsData.toXMLString());
			return;
		}
//		needJudge=needJudge&&(baseTime==null||baseTime.compareTo(gpsTime)<=0);
		
		if(needJudge){
			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶȳ���["+speed+"] ���볬���ж�");
//			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getEtvdSysTime()+"]�ٶȳ���["+speed+"] ���볬���ж�");
			log.debug("����GPS����SIM_NO["+value.getSimNo()+"]��"+gpsData.toXMLString());
			if(value.getSpeed()==null||value.getSpeed()==0d)value.setSpeed(speed);
			if(baseTime==null)value.setStartTime(gpsTime);
			Double avgSpeed=(value.getSpeed()+speed)/2;
			value.setSpeed(avgSpeed);
//			boolean avgSpeedSupTag=100<speed||80<speed;
//			if(avgSpeedSupTag){
			if(gpsTime.getTime()-value.getStartTime().getTime()>90*1000){//����90��
				alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
				if(alarmDone){
					alarmDone=true;
					alarmLog.warn("����90�뱨��SIM_NO["+value.getSimNo()+"]��"+gpsData.toXMLString());
					//INSERT DB TABLE ALARM
					//SEND WS CLIENT
					String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
					TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "����90�뱨��SIM_NO["+value.getSimNo()+"]", alarmXML, gpsData);
					log.warn(" SEND SERVER ALARM XML := "+alarmXML);
					RemoteLMSNewClient.sendAlarmData(alarmXML);
					log.warn(" SEND SERVER ALARM END ");
					
					value.setSpeed(null);
					value.setStartTime(null);
					log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶ�["+avgSpeed+"] -�ѳ��ٱ���-�������");
					
				}else{
					alarmLog.warn("����90�뱨��SIM_NO["+value.getSimNo()+"]-�����ѱ����������ٴα�����"+gpsData.toXMLString());
				}
				}
//			}else{
//				
//				//do nothing
//			}
			
		}else{
			value.setSpeed(null);
			value.setStartTime(null);
			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶ�����["+speed+"] �������");
//			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getEtvdSysTime()+"]�ٶ�����["+speed+"] �������");
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
			log.warn("EtVehicleGpsDataPO ����Ϊ��");
			return;
		}
//		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
			log.warn("ETVD_SIM_NO ���� ETVD_SPEED ���� ETVD_SYS_TIME ����Ϊ�� ��"+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		String alarmStatus="S03"; //S03���¹ʱ���
		boolean alarmDone=false;//����һ��ֻ����һ��
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
			log.warn("����ʱ��["+baseTime+"]����gpsʱ��["+gpsTime+"]�˳��Ƚϣ�"+gpsData.toXMLString());
			return;
		}
//		needJudge=needJudge&&(baseTime==null||baseTime.compareTo(gpsTime)<=0);
		
		if(needJudge){
//			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getEtvdSysTime()+"]�ٶ�Ϊ0["+speed+"] ����ͣ���ж�");
			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶ�Ϊ0["+speed+"] ����ͣ���ж�");
			log.debug("�쳣ͣ��GPS����SIM_NO["+value.getSimNo()+"]��"+gpsData.toXMLString());
			if(baseTime==null)value.setStartTime(gpsTime);
			value.setSpeed(speed);
			Calendar valueDateCal=Calendar.getInstance();
			valueDateCal.setTime(value.getStartTime());
			
			if(gpsCal.get(Calendar.DAY_OF_MONTH)>valueDateCal.get(Calendar.DAY_OF_MONTH))
				value.setStartTime(gpsTime);//�ų���ҹ
			
//		    if("weixian".equals(gpsData.getVehicleType)){
//				
//			}else{//putong	
//				
//			}
			
			if(gpsTime.getTime()-value.getStartTime().getTime()>60*60*1000){//�쳣ͣ����������1h
//				alram;//TODO
				alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
				if(alarmDone){
					alarmLog.warn("�쳣ͣ������SIM_NO["+value.getSimNo()+"]��"+gpsData.toXMLString());
					//INSERT DB TABLE ALARM
					//SEND WS CLIENT
					String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
					TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "�쳣ͣ������SIM_NO["+value.getSimNo()+"]", alarmXML, gpsData);
					log.warn(" SEND SERVER ALARM XML := "+alarmXML);
					RemoteLMSNewClient.sendAlarmData(alarmXML);
					log.warn(" SEND SERVER ALARM END ");
					
					value.setSpeed(null);
					value.setStartTime(null);
					log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶ�["+speed+"] -�ѳ�ͣ����-�������");
					
				}else{
					alarmLog.warn("�쳣ͣ������SIM_NO["+value.getSimNo()+"]-�����ѱ����������ٴα�����"+gpsData.toXMLString());
				}
			}
			
		}else{
			value.setSpeed(null);
			value.setStartTime(null);
			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getCreateTime()+"]�ٶ�����["+speed+"] �������");
//			log.debug("���� ["+gpsData.getEtvdSimNo()+"]ʱ��["+gpsData.getEtvdSysTime()+"]�ٶ�����["+speed+"] �������");
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
	 * ƣ�ͼ�ʻ
	 * ������ʻ4Сʱ��Ϣʱ������20���ӣ�
	   24Сʱ�ۼƼݳ�ʱ�䳬��8Сʱ��
	 * ��   ��  ��:PLJudge
	 * ��������:
	 * ��         ��:@param gpsData
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void PLJudge(EtVehicleGpsDataPO gpsData){
		//
		if(gpsData==null){
			log.warn("EtVehicleGpsDataPO ����Ϊ��");
			return;
		}
//		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getEtvdSysTime()==null){
		if(StringUtils.isBlank(gpsData.getEtvdSpeed())||StringUtils.isBlank(gpsData.getEtvdSimNo())||gpsData.getCreateTime()==null){
			log.warn("ETVD_SIM_NO ���� ETVD_SPEED ���� ETVD_SYS_TIME ����Ϊ�� ��"+gpsData.toXMLString());
			return;
		}
		Connection conn=null;
		String alarmStatus="S02"; //S02��ƣ�ͼ�ʻ
		boolean alarmDone=false;//����һ��ֻ����һ��
		try{
			conn=DBService.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String simNo=gpsData.getEtvdSimNo();
//			ȷ��ÿ���� ÿ����2����¼
			Double speed=Double.valueOf(gpsData.getEtvdSpeed());
			
//			Date gpsTime=gpsData.getEtvdSysTime();
			Date gpsTime=gpsData.getCreateTime();
			
			TargetDataPOFactory.clearCurrentPLVehicleGpsData(conn, simNo, gpsTime);
			
			TargetDataPOFactory.recordPLSourceDate(conn, gpsData);
			
			boolean needJudge=false;
			needJudge=0<speed;//�ٶ�Ϊ0 �����ж��Ƿ�ƣ��
			
			if(needJudge){
				
				if(TargetDataPOFactory.sum24PLVehicleGpsData(conn, simNo)&&isInHourScope(0, 1)){//24Сʱ�� 8Сʱ�ܼ�ʻʱ��,0-1���ж�24Сʱ����
//				alram;//TODO
					alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
					if(alarmDone){
						alarmLog.warn("ƣ�ͼ�ʻ��24Сʱ�� 8Сʱ�ܼ�ʻʱ�� ����SIM_NO["+simNo+"]��"+gpsData.toXMLString());
						//INSERT DB TABLE ALARM
						//SEND WS CLIENT
						String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
						TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "ƣ�ͼ�ʻ��24Сʱ�� 8Сʱ�ܼ�ʻʱ�� ����SIM_NO["+simNo+"]��", alarmXML, gpsData);
						log.warn(" SEND SERVER ALARM XML := "+alarmXML);
						RemoteLMSNewClient.sendAlarmData(alarmXML);
						log.warn(" SEND SERVER ALARM END ");
						
					}else{
						alarmLog.warn("ƣ�ͼ�ʻ������SIM_NO["+simNo+"]-�����ѱ����������ٴα�����"+gpsData.toXMLString());
					}
				}else{
					if(TargetDataPOFactory.sum4PLVehicleGpsData(conn, simNo)){ //������ʻ4Сʱ��Ϣʱ������20����
						alarmDone=TargetDataPOFactory.queryNeedAlarmTag(conn, alarmStatus, gpsData.getEtvdSimNo());
						if(alarmDone){
							alarmLog.warn("ƣ�ͼ�ʻ��������ʻ4Сʱ��Ϣʱ������20���� ����SIM_NO["+simNo+"]��"+gpsData.toXMLString());
							//INSERT DB TABLE ALARM
							//SEND WS CLIENT
							String alarmXML=GPSUtil.packWSAlarmXml(new AlarmXmlBean(gpsData, alarmStatus));
							TargetDataPOFactory.recordAlarmContent(conn, alarmStatus, "ƣ�ͼ�ʻ��������ʻ4Сʱ��Ϣʱ������20���� ����SIM_NO["+simNo+"]��", alarmXML, gpsData);
							log.warn(" SEND SERVER ALARM XML := "+alarmXML);
							RemoteLMSNewClient.sendAlarmData(alarmXML);
							log.warn(" SEND SERVER ALARM END ");
							
						}else{
							alarmLog.warn("ƣ�ͼ�ʻ������SIM_NO["+simNo+"]-�����ѱ����������ٴα�����"+gpsData.toXMLString());
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
	 * �̶������LMS ��������
	 * ��   ��  ��:sendGpsBaseData
	 * ��������:
	 * ��         ��:@param gpsDataList
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	 * ��ǰʱ�䣬�Ƿ���beginClock �� endClock��
	 * ��   ��  ��:isInHourScope
	 * ��������:
	 * ��         ��:@param beginClock
	 * ��         ��:@param endClock
	 * ��         ��:@return
	 * ��   ��  ֵ:boolean
	 * ��   ��  ��:rock
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
