/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2019-05-20 14:55:56
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.hy.exchange.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmExMinaBeatLogPO implements DataBean{

	private Date recordTime;
	private String beatConnStatus;
	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String status;

	public void setRecordTime(Date recordTime){
		this.recordTime=recordTime;
	}

	public Date getRecordTime(){
		return this.recordTime;
	}

	public void setBeatConnStatus(String beatConnStatus){
		this.beatConnStatus=beatConnStatus;
	}

	public String getBeatConnStatus(){
		return this.beatConnStatus;
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

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return this.remark;
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