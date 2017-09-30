/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:SendBaseGPSDataToLMSService.java
 * �� ������:2016��9��3��-����10:50:50
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.alarm.AlarmFactory;
import com.cn.gpslms.httpclient.RemoteLMSNewClient;
import com.cn.gpslms.po.NewVehicleGpsDataPO;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;

/**
 * ������:SendBaseGPSDataToLMSService
 * ������:��ʱ��LMS ���� gps Ԫ����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��9��3�� ����10:50:50
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SendBaseGPSDataToLMSService implements Service {
	private static Logger log=Logger.getLogger(SendBaseGPSDataToLMSService.class);
	/**
	 * ����һ���µ�ʵ�� SendBaseGPSDataToLMSService.
	 *
	 */
	private static Map<String,NewVehicleGpsDataPO> map=new ConcurrentHashMap<String, NewVehicleGpsDataPO>();
	private static Date lastSendTime=null;//�ϴη���gps����ʱ��
	
	private static int SKIP_TIME=30;//���� ��lms ���� gps Ԫ���ݣ�ʱ��������ʼֵ������Ӧ�ɽӿ�SI_381_GPS_OnlinetrackFrequency_Out ���� 
	Connection targetConn=null;
	public SendBaseGPSDataToLMSService() {
		// TODO Auto-generated constructor stub
	}

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
		
//	    Timer timer = new Timer();
//	    timer.schedule(new TimerTask() {
//	      public void run() {
//	    	log.debug("��ʼִ�� gps data send to LMS ");
//	        System.out.println("-------��ʼִ�� gps data send to LMS --------");
//	        try {
//				sendToLMS(targetConn);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//				log.error(e.getMessage());
//			} catch (SQLException e) {
//				e.printStackTrace();
//				log.error(e.getMessage());
//			}
//	        System.out.println("-------����ִ�� gps data send to LMS --------");
//	        log.debug("����ִ�� gps data send to LMS ");
//	      }
//	    }, SKIP_TIME*60*1000, SKIP_TIME*60*1000);
		
		Thread thread=new Thread(new Send2GPSInfoThread(targetConn));
		thread.start();	
		
	}
	
	
	public static void setSkipTime(int skipTime){
		SKIP_TIME=skipTime;
	}
	
	public static void setLastSendTime(){
		lastSendTime=new Date(System.currentTimeMillis());
	}
	public static Date getLastSendTime(){
		if(lastSendTime==null)setLastSendTime();
		return lastSendTime;
	}
	
	/**
	 * ��lms�������³���λ����Ϣ
	 * ��   ��  ��:sendToLMS
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@throws SQLException
	 * ��         ��:@throws MalformedURLException
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void sendToLMS() throws SQLException, MalformedURLException{
		log.debug("��ʼ������ͨgps����");
		if(map!=null&&map.size()>0){
		List<NewVehicleGpsDataPO> valuesList = new ArrayList<NewVehicleGpsDataPO>(map.values());
		if(valuesList!=null&&valuesList.size()>0)AlarmFactory.sendGpsBaseData(valuesList);
		log.debug("������ͨgps����["+valuesList.size()+"]��");
		}
		log.debug("����������ͨgps����");
	}
//	public static void sendToLMS(Connection conn) throws SQLException, MalformedURLException{
//		conn.setAutoCommit(false);
//		List<NewVehicleGpsDataPO> list=TargetDataPOFactory.getNeedSendGpsDataList(conn);
//		if(list!=null&&list.size()>0){
//			AlarmFactory.sendGpsBaseData(list);
//			int minId=list.get(list.size()-1).getId();
////			int maxId=list.get(list.size()-1).getId();
//			TmVehicleGpsIdPO vgPOCon=new TmVehicleGpsIdPO();
//			POFactory.delete(conn, vgPOCon);
//			vgPOCon.setNewVehicleGpsId(minId);
////			vgPOCon.setNewVehicleGpsId(maxId);
//			vgPOCon.setUpdateBy(0);
//			vgPOCon.setUpdateTime(new Date(System.currentTimeMillis()));
//			POFactory.insert(conn, vgPOCon);
//		}
//		conn.commit();
//	}
	
	/**
	 * �ӻ�ȡ�ĳ�������Сʱ����Ϣ�У���ȡ�������µ�����
	 * ��   ��  ��:getSingleCurrentGpsInfoBySimNo
	 * ��������:
	 * ��         ��:@param srcList
	 * ��         ��:@return
	 * ��   ��  ֵ:List<NewVehicleGpsDataPO>
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private static List<NewVehicleGpsDataPO> getSingleCurrentGpsInfoBySimNo(List<NewVehicleGpsDataPO> srcList){
//		if(srcList==null||srcList.size()<1){
//			log.debug("srcList is null");
//			return null;
//		}
//		Map<String,NewVehicleGpsDataPO> map=new ConcurrentHashMap<String, NewVehicleGpsDataPO>();
//		for(NewVehicleGpsDataPO bean:srcList){
//			String license=bean.getEtvdSimNo();
//			Date sysTime=bean.getEtvdSysTime();
//			if(StringUtils.isBlank(license)||sysTime==null)continue;
//			if(map.containsKey(license)){
//				NewVehicleGpsDataPO value=map.get(license);
//				if(value==null||sysTime.getTime()>value.getEtvdSysTime().getTime()){
//					map.put(license, bean);
//				}
//			}else{
//				map.put(license, bean);
//			}
//		}
//	}
	
	public static void inputCurrentGpsInfoBySimNo(NewVehicleGpsDataPO srcBean){
		if(srcBean==null)return;
		String license=srcBean.getEtvdSimNo();
//		Date sysTime=srcBean.getEtvdSysTime();
		Date sysTime=srcBean.getCreateTime();//����create time
		if(StringUtils.isBlank(license)||sysTime==null)return;
		if(map.containsKey(license)){
			NewVehicleGpsDataPO value=map.get(license);
			if(value==null||value.getCreateTime()==null||sysTime.getTime()>value.getCreateTime().getTime()){
				map.put(license, srcBean);
				log.debug("--referesh new DATA to current map:"+srcBean.toXMLString());	
			}
		}else{
			map.put(license, srcBean);
			log.debug("--put new DATA to current map:"+srcBean.toXMLString());	
		}
		
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
class Send2GPSInfoThread implements Runnable{

	private static Logger log=Logger.getLogger(Send2GPSInfoThread.class);
	Connection targetConn;
	
	Send2GPSInfoThread(Connection targetConn){
		this.targetConn=targetConn;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(targetConn==null||targetConn.isClosed()){
				targetConn=DBService.getInstance().getConnection();
			}
			
//			Thread.sleep(10000);
			while(true){
				Double skipTime=RemoteLMSNewClient.getCFGData();

				if(skipTime!=null&&skipTime.doubleValue()>0){
					log.debug("��ȡ�ϱ�Ƶ��1:="+skipTime.doubleValue());
					log.debug("��ȡ�ϱ�Ƶ��2:="+skipTime.intValue());
					SendBaseGPSDataToLMSService.setSkipTime(skipTime.intValue());
					log.debug("��ȡ�ϱ�Ƶ��3:="+skipTime.intValue());
					
					Date lastSendTime=SendBaseGPSDataToLMSService.getLastSendTime();
					log.debug("�ϴ��ϱ�ʱ��:="+lastSendTime);
					log.debug("��ǰʱ��:="+new Date(System.currentTimeMillis()));
					if(new Date(System.currentTimeMillis()).getTime()-lastSendTime.getTime()>=skipTime*60*1000){
						log.debug("ִ���ϱ�:="+skipTime.intValue());
						SendBaseGPSDataToLMSService.sendToLMS();
//						SendBaseGPSDataToLMSService.(targetConn);
						SendBaseGPSDataToLMSService.setLastSendTime();
					}
					
				}

				Thread.sleep(120*1000);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		
		
		
	}
	
	
}