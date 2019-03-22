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
public class TmExApiRecMsgResponsePO implements DataBean{

	private String responseData;
	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private Integer createBy;
	private Date updateTime;
	private String status;
	
	private Date parseTime;
	private String topic;
	private String parseStatus;

	/**
	 * parseTime
	 *
	 * @return  the parseTime
	 * @since   1.0.0
	 */
	
	public Date getParseTime() {
		return parseTime;
	}

	/**
	 * @param parseTime the parseTime to set
	 */
	public void setParseTime(Date parseTime) {
		this.parseTime = parseTime;
	}

	/**
	 * topic
	 *
	 * @return  the topic
	 * @since   1.0.0
	 */
	
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * parseStatus
	 *
	 * @return  the parseStatus
	 * @since   1.0.0
	 */
	
	public String getParseStatus() {
		return parseStatus;
	}

	/**
	 * @param parseStatus the parseStatus to set
	 */
	public void setParseStatus(String parseStatus) {
		this.parseStatus = parseStatus;
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

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}