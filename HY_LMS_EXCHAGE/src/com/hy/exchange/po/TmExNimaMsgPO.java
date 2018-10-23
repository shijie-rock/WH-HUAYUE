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
public class TmExNimaMsgPO implements DataBean{

	private String pubAppKey;
	private String sign;
	private String errCode;
	private Date parseTime;
	private String msgId;
	private String errMsg;
	private Date createTime;
	private Integer createBy;
	private String msgType;
	private Date updateTime;
	private String status;
	private String topic;
	private String parseStatus;
	private String pubTime;
	private String result;
	private String reqMsgId;
	private Integer updateBy;
	private String content;
	private Integer id;

	public void setPubAppKey(String pubAppKey){
		this.pubAppKey=pubAppKey;
	}

	public String getPubAppKey(){
		return this.pubAppKey;
	}

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

	public void setMsgId(String msgId){
		this.msgId=msgId;
	}

	public String getMsgId(){
		return this.msgId;
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

	public void setMsgType(String msgType){
		this.msgType=msgType;
	}

	public String getMsgType(){
		return this.msgType;
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

	public void setTopic(String topic){
		this.topic=topic;
	}

	public String getTopic(){
		return this.topic;
	}

	public void setParseStatus(String parseStatus){
		this.parseStatus=parseStatus;
	}

	public String getParseStatus(){
		return this.parseStatus;
	}

	public void setPubTime(String pubTime){
		this.pubTime=pubTime;
	}

	public String getPubTime(){
		return this.pubTime;
	}

	public void setResult(String result){
		this.result=result;
	}

	public String getResult(){
		return this.result;
	}

	public void setReqMsgId(String reqMsgId){
		this.reqMsgId=reqMsgId;
	}

	public String getReqMsgId(){
		return this.reqMsgId;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return this.content;
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