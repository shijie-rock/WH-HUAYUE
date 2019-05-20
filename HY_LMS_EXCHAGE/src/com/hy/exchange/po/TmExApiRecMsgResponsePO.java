/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2019-05-20 15:35:17
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.hy.exchange.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmExApiRecMsgResponsePO implements DataBean{

	private Date parseTime;
	private String responseData;
	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private Integer createBy;
	private Date updateTime;
	private String topic;
	private String status;
	private String parseStatus;

	public void setParseTime(Date parseTime){
		this.parseTime=parseTime;
	}

	public Date getParseTime(){
		return this.parseTime;
	}

	public void setResponseData(String responseData){
		this.responseData=responseData;
	}

	public String getResponseData(){
		return this.responseData;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setTopic(String topic){
		this.topic=topic;
	}

	public String getTopic(){
		return this.topic;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setParseStatus(String parseStatus){
		this.parseStatus=parseStatus;
	}

	public String getParseStatus(){
		return this.parseStatus;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}