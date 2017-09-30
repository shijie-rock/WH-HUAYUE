/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.util
 * 文   件  名:GPSUtil.java
 * 创 建日期:2016年8月28日-下午12:52:38
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
 * 类名称:GPSUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月28日 下午12:52:38
 * 修改备注:
 * @version 1.0.0
 */
public class GPSUtil {

	/**
	 * 创建一个新的实例 GPSUtil.
	 *
	 */
	public GPSUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 包装webservice 请求xml
	 * 方   法  名:packWSXml
	 * 方法描述:
	 * 参         数:@param alarmBean
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
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
	 * 包装webservice 请求xml
	 * 方   法  名:packWSXml
	 * 方法描述:
	 * 参         数:@param alarmBeanList
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
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
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
		System.out.println(new GPSUtil().getClass().getSimpleName());
	}

}
