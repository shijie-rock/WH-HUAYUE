/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-01-07 20:20:16
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.cn.gpslms.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmPlAlarmBeanPO implements DataBean{

	private Date importTime;
	private Integer updateBy;
	private String simNo;
	private Integer importBy;
	private Double speed;
	private Date updateTime;
	private Date startTime;
	private String status;
	private Integer id;

	public void setImportTime(Date importTime){
		this.importTime=importTime;
	}

	public Date getImportTime(){
		return this.importTime;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setSimNo(String simNo){
		this.simNo=simNo;
	}

	public String getSimNo(){
		return this.simNo;
	}

	public void setImportBy(Integer importBy){
		this.importBy=importBy;
	}

	public Integer getImportBy(){
		return this.importBy;
	}

	public void setSpeed(Double speed){
		this.speed=speed;
	}

	public Double getSpeed(){
		return this.speed;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getStartTime(){
		return this.startTime;
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
}