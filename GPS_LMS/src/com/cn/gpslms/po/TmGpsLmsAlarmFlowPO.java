/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2016-09-04 23:39:37
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.cn.gpslms.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmGpsLmsAlarmFlowPO implements DataBean{

	private Date alarmTime;
	private String address;
	private Integer importBy;
	private String simNo;
	private Date updateTime;
	private Double speed;
	private String etvdLatitude;
	private String status;
	private String etvdLongitude;
	private Date importTime;
	private String alarmTypeCode;
	private Integer updateBy;
	private Integer etvdId;
	private String alarmXml;
	private String alarmTypeDesc;
	private Integer id;

	public void setAlarmTime(Date alarmTime){
		this.alarmTime=alarmTime;
	}

	public Date getAlarmTime(){
		return this.alarmTime;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setImportBy(Integer importBy){
		this.importBy=importBy;
	}

	public Integer getImportBy(){
		return this.importBy;
	}

	public void setSimNo(String simNo){
		this.simNo=simNo;
	}

	public String getSimNo(){
		return this.simNo;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setSpeed(Double speed){
		this.speed=speed;
	}

	public Double getSpeed(){
		return this.speed;
	}

	public void setEtvdLatitude(String etvdLatitude){
		this.etvdLatitude=etvdLatitude;
	}

	public String getEtvdLatitude(){
		return this.etvdLatitude;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setEtvdLongitude(String etvdLongitude){
		this.etvdLongitude=etvdLongitude;
	}

	public String getEtvdLongitude(){
		return this.etvdLongitude;
	}

	public void setImportTime(Date importTime){
		this.importTime=importTime;
	}

	public Date getImportTime(){
		return this.importTime;
	}

	public void setAlarmTypeCode(String alarmTypeCode){
		this.alarmTypeCode=alarmTypeCode;
	}

	public String getAlarmTypeCode(){
		return this.alarmTypeCode;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setEtvdId(Integer etvdId){
		this.etvdId=etvdId;
	}

	public Integer getEtvdId(){
		return this.etvdId;
	}

	public void setAlarmXml(String alarmXml){
		this.alarmXml=alarmXml;
	}

	public String getAlarmXml(){
		return this.alarmXml;
	}

	public void setAlarmTypeDesc(String alarmTypeDesc){
		this.alarmTypeDesc=alarmTypeDesc;
	}

	public String getAlarmTypeDesc(){
		return this.alarmTypeDesc;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}