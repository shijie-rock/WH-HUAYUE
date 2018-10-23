/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-10-22 16:48:30
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.hy.exchange.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmExApiRecMsgPO implements DataBean{

	private String sign;
	private String errCode;
	private Date parseTime;
	private String errMsg;
	private Date createTime;
	private Integer createBy;
	private String reqData;
	private Date updateTime;
	private String status;
	private String reqTime;
	private String parseStatus;
	private Integer updateBy;
	private String refMinaMsgId;
	private String appKey;
	private Integer id;
	private String executeResult;
	private String serviceCode;

	public void setSign(String sign){
		this.sign=sign;
	}

	public String getSign(){
		return this.sign;
	}

	public void setErrCode(String errCode){
		this.errCode=errCode;
	}

	public String getErrCode(){
		return this.errCode;
	}

	public void setParseTime(Date parseTime){
		this.parseTime=parseTime;
	}

	public Date getParseTime(){
		return this.parseTime;
	}

	public void setErrMsg(String errMsg){
		this.errMsg=errMsg;
	}

	public String getErrMsg(){
		return this.errMsg;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setReqData(String reqData){
		this.reqData=reqData;
	}

	public String getReqData(){
		return this.reqData;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setReqTime(String reqTime){
		this.reqTime=reqTime;
	}

	public String getReqTime(){
		return this.reqTime;
	}

	public void setParseStatus(String parseStatus){
		this.parseStatus=parseStatus;
	}

	public String getParseStatus(){
		return this.parseStatus;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setRefMinaMsgId(String refMinaMsgId){
		this.refMinaMsgId=refMinaMsgId;
	}

	public String getRefMinaMsgId(){
		return this.refMinaMsgId;
	}

	public void setAppKey(String appKey){
		this.appKey=appKey;
	}

	public String getAppKey(){
		return this.appKey;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setExecuteResult(String executeResult){
		this.executeResult=executeResult;
	}

	public String getExecuteResult(){
		return this.executeResult;
	}

	public void setServiceCode(String serviceCode){
		this.serviceCode=serviceCode;
	}

	public String getServiceCode(){
		return this.serviceCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}