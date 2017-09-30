/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.alarm
 * 文   件  名:AlarmXmlBean.java
 * 创 建日期:2016年9月3日-下午8:26:40
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.alarm;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.cn.gpslms.util.DBConUtil;
import com.infoservice.po.DataBean;

/**
 * 类名称:AlarmXmlBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年9月3日 下午8:26:40
 * 修改备注:
 * @version 1.0.0
 */
public class AlarmXmlBean implements Serializable, DataBean<Object> {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 5271188962887870609L;

	/**
	 * 创建一个新的实例 AlarmXmlBean.
	 *
	 * @param gpsData
	 * @param alarmStatus
	 */
	public AlarmXmlBean(EtVehicleGpsDataPO gpsData, String alarmStatus) {
		super();
		this.gpsData = gpsData;
		this.alarmStatus = alarmStatus;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	private EtVehicleGpsDataPO gpsData;
	private String alarmStatus;
	/**
	 * gpsData
	 *
	 * @return  the gpsData
	 * @since   1.0.0
	 */
	
	public EtVehicleGpsDataPO getGpsData() {
		return gpsData;
	}

	/**
	 * @param gpsData the gpsData to set
	 */
	public void setGpsData(EtVehicleGpsDataPO gpsData) {
		this.gpsData = gpsData;
	}

	/**
	 * alarmStatus
	 *
	 * @return  the alarmStatus
	 * @since   1.0.0
	 */
	
	public String getAlarmStatus() {
		return alarmStatus;
	}

	/**
	 * @param alarmStatus the alarmStatus to set
	 */
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	public String toXMLString() {
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
		sbf.append("<ser:tm_online_track_status_id>"+(StringUtils.isBlank(alarmStatus)?"":alarmStatus)+"</ser:tm_online_track_status_id>");
		sbf.append("<ser:track_date>"+DBConUtil.handleFormatDate(gpsData.getEtvdSysTime())+"</ser:track_date>");
		sbf.append("<ser:truck_name_h>"+gpsData.getEtvdSimNo()+"</ser:truck_name_h>");//TODO
		sbf.append("<ser:carrier_code>"+TargetDataPOFactory.getBillCode(gpsData.getEtvdSimNo())+"</ser:carrier_code>");//TODO
		sbf.append("</ser:Online_Track_Info>");
		return sbf.toString();
	}
	public String toXMLString1() {
		StringBuffer sbf=new StringBuffer();
		sbf.append("<TM_Trackinfor_H>");
		sbf.append("<direction></direction>");
		sbf.append("<direction_degrees>0</direction_degrees>");
//		sbf.append("<ser:direction>"+gpsData.getEtvdDirection()+"</ser:direction>");
//		sbf.append("<ser:direction_degrees>"+gpsData.getEtvdDirection()+"</ser:direction_degrees>");
		sbf.append("<latitude>"+gpsData.getEtvdLatitude()+"</latitude>");
		sbf.append("<longitude>"+gpsData.getEtvdLongitude()+"</longitude>");
		sbf.append("<place_now>"+gpsData.getEtvdRemark()+"</place_now>");
		sbf.append("<spare_fields_head_1></spare_fields_head_1>");
		sbf.append("<spare_fields_head_10></spare_fields_head_10>");
		sbf.append("<spare_fields_head_2></spare_fields_head_2>");
		sbf.append("<spare_fields_head_3></spare_fields_head_3>");
		sbf.append("<spare_fields_head_4></spare_fields_head_4>");
		sbf.append("<spare_fields_head_5></spare_fields_head_5>");
		sbf.append("<spare_fields_head_6></spare_fields_head_6>");
		sbf.append("<spare_fields_head_7></spare_fields_head_7>");
		sbf.append("<spare_fields_head_8></spare_fields_head_8>");
		sbf.append("<spare_fields_head_9></spare_fields_head_9>");
		sbf.append("<speed>"+gpsData.getEtvdSpeed()+"</speed>");
		sbf.append("<tm_online_track_status_id>"+(StringUtils.isBlank(alarmStatus)?"":alarmStatus)+"</tm_online_track_status_id>");
		sbf.append("<track_date>"+DBConUtil.handleFormatDate(gpsData.getEtvdSysTime())+"</track_date>");
		sbf.append("<truck_name_h>"+gpsData.getEtvdSimNo()+"</truck_name_h>");//TODO
		sbf.append("</TM_Trackinfor_H>");
		return sbf.toString();
	}

}
