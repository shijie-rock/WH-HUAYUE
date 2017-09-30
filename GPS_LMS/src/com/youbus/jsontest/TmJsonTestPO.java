/**
 * 项目名称:JSON_TEST
 * 包         名:com.youbus.jsontest
 * 文   件  名:TmJsonTestPO.java
 * 创 建日期:2015年8月5日-下午3:03:30
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.jsontest;

import java.util.Date;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:TmJsonTestPO
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年8月5日 下午3:03:30
 * 修改备注:
 * @version 1.0.0
 */
public class TmJsonTestPO implements DataBean {

	/**
	 * 创建一个新的实例 TmJsonTestPO.
	 *
	 */
	public TmJsonTestPO() {
		// TODO Auto-generated constructor stub
	}

	private Date createTime;
	private Integer createBy;
	private Integer updateBy;
	private Date updateTime;
	private String status;
	private String remark;
	private Integer id;
	
	private String time;
	private String rssi;
	private String wiffId;
	/**
	 * createTime
	 *
	 * @return  the createTime
	 * @since   1.0.0
	 */
	
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * createBy
	 *
	 * @return  the createBy
	 * @since   1.0.0
	 */
	
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	/**
	 * updateBy
	 *
	 * @return  the updateBy
	 * @since   1.0.0
	 */
	
	public Integer getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * updateTime
	 *
	 * @return  the updateTime
	 * @since   1.0.0
	 */
	
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * remark
	 *
	 * @return  the remark
	 * @since   1.0.0
	 */
	
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * id
	 *
	 * @return  the id
	 * @since   1.0.0
	 */
	
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * time
	 *
	 * @return  the time
	 * @since   1.0.0
	 */
	
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * rssi
	 *
	 * @return  the rssi
	 * @since   1.0.0
	 */
	
	public String getRssi() {
		return rssi;
	}

	/**
	 * @param rssi the rssi to set
	 */
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	/**
	 * wiffId
	 *
	 * @return  the wiffId
	 * @since   1.0.0
	 */
	
	public String getWiffId() {
		return wiffId;
	}

	/**
	 * @param wiffId the wiffId to set
	 */
	public void setWiffId(String wiffId) {
		this.wiffId = wiffId;
	}

	/**
	 * mac
	 *
	 * @return  the mac
	 * @since   1.0.0
	 */
	
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	private String mac;

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}

}
