/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-12-03 17:03:44
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsPositionInfoPO implements DataBean{

	private String positionAddress;
	private String positionDesc;
	private Date createTime;
	private String positionLongitude;
	private String positionName;
	private Integer createBy;
	private String positionCode;
	private String remark;
	private Date updateTime;
	private String positionLatitude;
	private String status;
	private String freezeTag;
	private Integer updateBy;
	private Integer id;
	private Integer ver;

	public void setPositionAddress(String positionAddress){
		this.positionAddress=positionAddress;
	}

	public String getPositionAddress(){
		return this.positionAddress;
	}

	public void setPositionDesc(String positionDesc){
		this.positionDesc=positionDesc;
	}

	public String getPositionDesc(){
		return this.positionDesc;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setPositionLongitude(String positionLongitude){
		this.positionLongitude=positionLongitude;
	}

	public String getPositionLongitude(){
		return this.positionLongitude;
	}

	public void setPositionName(String positionName){
		this.positionName=positionName;
	}

	public String getPositionName(){
		return this.positionName;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setPositionCode(String positionCode){
		this.positionCode=positionCode;
	}

	public String getPositionCode(){
		return this.positionCode;
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

	public void setPositionLatitude(String positionLatitude){
		this.positionLatitude=positionLatitude;
	}

	public String getPositionLatitude(){
		return this.positionLatitude;
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

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}