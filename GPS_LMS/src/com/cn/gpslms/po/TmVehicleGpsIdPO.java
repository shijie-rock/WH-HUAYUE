/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2016-09-03 22:31:50
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.cn.gpslms.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmVehicleGpsIdPO implements DataBean{

	private Integer newVehicleGpsId;
	private Integer importBy;
	private Date updateTime;
	private Date importTime;
	private String status;
	private Integer updateBy;

	public void setNewVehicleGpsId(Integer newVehicleGpsId){
		this.newVehicleGpsId=newVehicleGpsId;
	}

	public Integer getNewVehicleGpsId(){
		return this.newVehicleGpsId;
	}

	public void setImportBy(Integer importBy){
		this.importBy=importBy;
	}

	public Integer getImportBy(){
		return this.importBy;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setImportTime(Date importTime){
		this.importTime=importTime;
	}

	public Date getImportTime(){
		return this.importTime;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}