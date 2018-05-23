/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-02-28 18:32:52
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TtInsTruckSubObjMapPO implements DataBean{

	private Integer truckId;
	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private Integer createBy;
	private String remark;
	private Integer ver;
	private String objClassSubCode;
	private Date updateTime;
	private String status;
	private String freezeTag;

	public void setTruckId(Integer truckId){
		this.truckId=truckId;
	}

	public Integer getTruckId(){
		return this.truckId;
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

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public void setObjClassSubCode(String objClassSubCode){
		this.objClassSubCode=objClassSubCode;
	}

	public String getObjClassSubCode(){
		return this.objClassSubCode;
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

	public void setFreezeTag(String freezeTag){
		this.freezeTag=freezeTag;
	}

	public String getFreezeTag(){
		return this.freezeTag;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}