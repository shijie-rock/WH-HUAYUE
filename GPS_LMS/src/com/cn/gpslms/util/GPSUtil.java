/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.util
 * ��   ��  ��:GPSUtil.java
 * �� ������:2016��8��28��-����12:52:38
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cn.gpslms.alarm.AlarmXmlBean;
import com.cn.gpslms.common.CommonConstant;
import com.cn.gpslms.po.NewVehicleGpsDataPO;
import com.cn.gpslms.pofactory.TargetDataPOFactory;

/**
 * ������:GPSUtil
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��8��28�� ����12:52:38
 * �޸ı�ע:
 * @version 1.0.0
 */
public class GPSUtil {

	/**
	 * ����һ���µ�ʵ�� GPSUtil.
	 *
	 */
	public GPSUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * ��װwebservice ����xml
	 * ��   ��  ��:packWSXml
	 * ��������:
	 * ��         ��:@param alarmBean
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String packWSAlarmXml(AlarmXmlBean alarmBean){
		StringBuffer sbf=new StringBuffer();
		sbf.append(CommonConstant.WBS_SOAP_XML_START);
		sbf.append(CommonConstant.WBS_SOAP_HEAD_XML);
		sbf.append(CommonConstant.WBS_SOAP_BODY_START);
		sbf.append("<ser:L3_LMS_IF11_WS_01>");
		sbf.append(alarmBean.toXMLString());
		sbf.append("</ser:L3_LMS_IF11_WS_01>");
		sbf.append(CommonConstant.WBS_SOAP_BODY_END);
		sbf.append(CommonConstant.WBS_SOAP_XML_END);
		return sbf.toString();
	}
	/**
	 * ��װwebservice ����xml
	 * ��   ��  ��:packWSXml
	 * ��������:
	 * ��         ��:@param alarmBeanList
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String packWSAlarmXml(List<AlarmXmlBean> alarmBeanList){
		StringBuffer sbf=new StringBuffer();
		sbf.append(CommonConstant.WBS_SOAP_XML_START);
		sbf.append(CommonConstant.WBS_SOAP_HEAD_XML);
		sbf.append(CommonConstant.WBS_SOAP_BODY_START);
		sbf.append("<ser:L3_LMS_IF11_WS_01>");
		for(AlarmXmlBean alarmBean:alarmBeanList)sbf.append(alarmBean.toXMLString());
		sbf.append("</ser:L3_LMS_IF11_WS_01>");
		sbf.append(CommonConstant.WBS_SOAP_BODY_END);
		sbf.append(CommonConstant.WBS_SOAP_XML_END);
		return sbf.toString();
	}
	
	
	public static String packWSXml(NewVehicleGpsDataPO gpsBean){
		StringBuffer sbf=new StringBuffer();
		sbf.append(CommonConstant.WBS_SOAP_XML_START);
		sbf.append(CommonConstant.WBS_SOAP_HEAD_XML);
		sbf.append(CommonConstant.WBS_SOAP_BODY_START);
		sbf.append("<ser:L3_LMS_IF11_WS_01>");
		sbf.append(gpsNewData2XML(gpsBean));
		sbf.append("</ser:L3_LMS_IF11_WS_01>");
		sbf.append(CommonConstant.WBS_SOAP_BODY_END);
		sbf.append(CommonConstant.WBS_SOAP_XML_END);
		return sbf.toString();
	}
	
	
	public static String packWSXml(List<NewVehicleGpsDataPO> gpsList){
		StringBuffer sbf=new StringBuffer();
		sbf.append(CommonConstant.WBS_SOAP_XML_START);
		sbf.append(CommonConstant.WBS_SOAP_HEAD_XML);
		sbf.append(CommonConstant.WBS_SOAP_BODY_START);
		sbf.append("<ser:L3_LMS_IF11_WS_01>");
		for(NewVehicleGpsDataPO gpsBean:gpsList)sbf.append(gpsNewData2XML(gpsBean));
		sbf.append("</ser:L3_LMS_IF11_WS_01>");
		sbf.append(CommonConstant.WBS_SOAP_BODY_END);
		sbf.append(CommonConstant.WBS_SOAP_XML_END);
		return sbf.toString();
	}

	private static String gpsNewData2XML(NewVehicleGpsDataPO gpsData){
		StringBuffer sbf=new StringBuffer();
		sbf.append("<ser:Online_Track_Info>");
		sbf.append("<ser:direction></ser:direction>");
		sbf.append("<ser:direction_degrees>0</ser:direction_degrees>");
//		sbf.append("<ser:direction>"+gpsData.getEtvdDirection()+"</ser:direction>");
//		sbf.append("<ser:direction_degrees>"+gpsData.getEtvdDirection()+"</ser:direction_degrees>");
		sbf.append("<ser:latitude>"+gpsData.getEtvdLatitude()+"</ser:latitude>");
		sbf.append("<ser:longitude>"+gpsData.getEtvdLongitude()+"</ser:longitude>");
		sbf.append("<ser:place_now>"+gpsData.getEtvdRemark()+"</ser:place_now>");
		sbf.append("<ser:spare_fields_head_1></ser:spare_fields_head_1>");
		sbf.append("<ser:spare_fields_head_10></ser:spare_fields_head_10>");
		sbf.append("<ser:spare_fields_head_2></ser:spare_fields_head_2>");
		sbf.append("<ser:spare_fields_head_3></ser:spare_fields_head_3>");
		sbf.append("<ser:spare_fields_head_4></ser:spare_fields_head_4>");
		sbf.append("<ser:spare_fields_head_5></ser:spare_fields_head_5>");
		sbf.append("<ser:spare_fields_head_6></ser:spare_fields_head_6>");
		sbf.append("<ser:spare_fields_head_7></ser:spare_fields_head_7>");
		sbf.append("<ser:spare_fields_head_8></ser:spare_fields_head_8>");
		sbf.append("<ser:spare_fields_head_9></ser:spare_fields_head_9>");
		sbf.append("<ser:speed>"+gpsData.getEtvdSpeed()+"</ser:speed>");
		sbf.append("<ser:tm_online_track_status_id></ser:tm_online_track_status_id>");
		sbf.append("<ser:track_date>"+DBConUtil.handleFormatDate(gpsData.getEtvdSysTime())+"</ser:track_date>");
		sbf.append("<ser:truck_name_h>"+gpsData.getEtvdSimNo()+"</ser:truck_name_h>");//TODO
		sbf.append("<ser:carrier_code>"+TargetDataPOFactory.getBillCode(gpsData.getEtvdSimNo())+"</ser:carrier_code>");//TODO
		sbf.append("</ser:Online_Track_Info>");
		return sbf.toString();
		
	}
	
	
	public static void logMap(Map map,String logTitle,Logger log){
		System.out.println(logTitle+":begin");
		log.debug(logTitle+":begin");
		if(map!=null){
			Set entrySet=map.entrySet();
			Iterator<Entry> iter=entrySet.iterator();
			while(iter.hasNext()){
				Entry entry=iter.next();
				System.out.println("key:="+entry.getKey()+"::value:="+entry.getValue());
				log.debug("key:="+entry.getKey()+"::value:="+entry.getValue());
			}
		}
		System.out.println(logTitle+":end");	
		log.debug(logTitle+":end");
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
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
		System.out.println(new GPSUtil().getClass().getSimpleName());
	}

}
