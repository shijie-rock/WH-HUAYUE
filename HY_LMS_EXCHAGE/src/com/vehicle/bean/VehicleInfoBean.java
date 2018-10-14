/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.bean
 * 文   件  名:VehicleInfoBean.java
 * 创 建日期:2017年4月9日-下午9:26:23
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.bean;

import java.io.Serializable;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:VehicleInfoBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月9日 下午9:26:23
 * 修改备注:
 * @version 1.0.0
 */
public class VehicleInfoBean implements DataBean {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 4443142458830189013L;
	
	private String id;
	private String truckid;
	private String carnum;
	private String carriagetype;
	private String carriagetype_id;
	private String deleted;
	private String drivinglicensebrand;
	private String emptydriving;
	private String emptydriving_code;
	private String fromorgcode;
	private String fromtype;
	private String gmileage;//里程
	private String gpsno;
	private String length;
	private String name;
	private String orgcode;
	private String orgcode_code;
	private String status;
	private String status_code;
	private String truckpk;
	private String usetype;
	private String usefor;
	private String usedept;
	private String volume;
	private String counttime;
	private String duration; //秒
	private String weight;
	/**
	 * id
	 *
	 * @return  the id
	 * @since   1.0.0
	 */
	
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * truckid
	 *
	 * @return  the truckid
	 * @since   1.0.0
	 */
	
	public String getTruckid() {
		return truckid;
	}
	/**
	 * @param truckid the truckid to set
	 */
	public void setTruckid(String truckid) {
		this.truckid = truckid;
	}
	/**
	 * carnum
	 *
	 * @return  the carnum
	 * @since   1.0.0
	 */
	
	public String getCarnum() {
		return carnum;
	}
	/**
	 * @param carnum the carnum to set
	 */
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	/**
	 * carriagetype
	 *
	 * @return  the carriagetype
	 * @since   1.0.0
	 */
	
	public String getCarriagetype() {
		return carriagetype;
	}
	/**
	 * @param carriagetype the carriagetype to set
	 */
	public void setCarriagetype(String carriagetype) {
		this.carriagetype = carriagetype;
	}
	/**
	 * carriagetype_id
	 *
	 * @return  the carriagetype_id
	 * @since   1.0.0
	 */
	
	public String getCarriagetype_id() {
		return carriagetype_id;
	}
	/**
	 * @param carriagetype_id the carriagetype_id to set
	 */
	public void setCarriagetype_id(String carriagetype_id) {
		this.carriagetype_id = carriagetype_id;
	}
	/**
	 * deleted
	 *
	 * @return  the deleted
	 * @since   1.0.0
	 */
	
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * drivinglicensebrand
	 *
	 * @return  the drivinglicensebrand
	 * @since   1.0.0
	 */
	
	public String getDrivinglicensebrand() {
		return drivinglicensebrand;
	}
	/**
	 * @param drivinglicensebrand the drivinglicensebrand to set
	 */
	public void setDrivinglicensebrand(String drivinglicensebrand) {
		this.drivinglicensebrand = drivinglicensebrand;
	}
	/**
	 * emptydriving
	 *
	 * @return  the emptydriving
	 * @since   1.0.0
	 */
	
	public String getEmptydriving() {
		return emptydriving;
	}
	/**
	 * @param emptydriving the emptydriving to set
	 */
	public void setEmptydriving(String emptydriving) {
		this.emptydriving = emptydriving;
	}
	/**
	 * emptydriving_code
	 *
	 * @return  the emptydriving_code
	 * @since   1.0.0
	 */
	
	public String getEmptydriving_code() {
		return emptydriving_code;
	}
	/**
	 * @param emptydriving_code the emptydriving_code to set
	 */
	public void setEmptydriving_code(String emptydriving_code) {
		this.emptydriving_code = emptydriving_code;
	}
	/**
	 * fromorgcode
	 *
	 * @return  the fromorgcode
	 * @since   1.0.0
	 */
	
	public String getFromorgcode() {
		return fromorgcode;
	}
	/**
	 * @param fromorgcode the fromorgcode to set
	 */
	public void setFromorgcode(String fromorgcode) {
		this.fromorgcode = fromorgcode;
	}
	/**
	 * fromtype
	 *
	 * @return  the fromtype
	 * @since   1.0.0
	 */
	
	public String getFromtype() {
		return fromtype;
	}
	/**
	 * @param fromtype the fromtype to set
	 */
	public void setFromtype(String fromtype) {
		this.fromtype = fromtype;
	}
	/**
	 * gmileage
	 *
	 * @return  the gmileage
	 * @since   1.0.0
	 */
	
	public String getGmileage() {
		return gmileage;
	}
	/**
	 * @param gmileage the gmileage to set
	 */
	public void setGmileage(String gmileage) {
		this.gmileage = gmileage;
	}
	/**
	 * gpsno
	 *
	 * @return  the gpsno
	 * @since   1.0.0
	 */
	
	public String getGpsno() {
		return gpsno;
	}
	/**
	 * @param gpsno the gpsno to set
	 */
	public void setGpsno(String gpsno) {
		this.gpsno = gpsno;
	}
	/**
	 * length
	 *
	 * @return  the length
	 * @since   1.0.0
	 */
	
	public String getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * name
	 *
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * orgcode
	 *
	 * @return  the orgcode
	 * @since   1.0.0
	 */
	
	public String getOrgcode() {
		return orgcode;
	}
	/**
	 * @param orgcode the orgcode to set
	 */
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	/**
	 * orgcode_code
	 *
	 * @return  the orgcode_code
	 * @since   1.0.0
	 */
	
	public String getOrgcode_code() {
		return orgcode_code;
	}
	/**
	 * @param orgcode_code the orgcode_code to set
	 */
	public void setOrgcode_code(String orgcode_code) {
		this.orgcode_code = orgcode_code;
	}
	/**
	 * status
	 *
	 * @return  the status
	 * @since   1.0.0
	 */
	
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * status_code
	 *
	 * @return  the status_code
	 * @since   1.0.0
	 */
	
	public String getStatus_code() {
		return status_code;
	}
	/**
	 * @param status_code the status_code to set
	 */
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	/**
	 * truckpk
	 *
	 * @return  the truckpk
	 * @since   1.0.0
	 */
	
	public String getTruckpk() {
		return truckpk;
	}
	/**
	 * @param truckpk the truckpk to set
	 */
	public void setTruckpk(String truckpk) {
		this.truckpk = truckpk;
	}
	/**
	 * usetype
	 *
	 * @return  the usetype
	 * @since   1.0.0
	 */
	
	public String getUsetype() {
		return usetype;
	}
	/**
	 * @param usetype the usetype to set
	 */
	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}
	/**
	 * usefor
	 *
	 * @return  the usefor
	 * @since   1.0.0
	 */
	
	public String getUsefor() {
		return usefor;
	}
	/**
	 * @param usefor the usefor to set
	 */
	public void setUsefor(String usefor) {
		this.usefor = usefor;
	}
	/**
	 * usedept
	 *
	 * @return  the usedept
	 * @since   1.0.0
	 */
	
	public String getUsedept() {
		return usedept;
	}
	/**
	 * @param usedept the usedept to set
	 */
	public void setUsedept(String usedept) {
		this.usedept = usedept;
	}
	/**
	 * volume
	 *
	 * @return  the volume
	 * @since   1.0.0
	 */
	
	public String getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	/**
	 * counttime
	 *
	 * @return  the counttime
	 * @since   1.0.0
	 */
	
	public String getCounttime() {
		return counttime;
	}
	/**
	 * @param counttime the counttime to set
	 */
	public void setCounttime(String counttime) {
		this.counttime = counttime;
	}
	/**
	 * duration
	 *
	 * @return  the duration
	 * @since   1.0.0
	 */
	
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * weight
	 *
	 * @return  the weight
	 * @since   1.0.0
	 */
	
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}
	
	
	
	

}
