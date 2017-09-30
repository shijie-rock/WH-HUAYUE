/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-08-20 17:07:58
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.organize.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInspactGroupPO implements DataBean{

	private String insGroupDesc;
	private String insGroupName;
	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private Integer createBy;
	private String remark;
	private Integer ver;
	private Date updateTime;
	private String status;
	private String insGroupCode;
	private String freezeTag;

	public void setInsGroupDesc(String insGroupDesc){
		this.insGroupDesc=insGroupDesc;
	}

	public String getInsGroupDesc(){
		return this.insGroupDesc;
	}

	public void setInsGroupName(String insGroupName){
		this.insGroupName=insGroupName;
	}

	public String getInsGroupName(){
		return this.insGroupName;
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

	public void setInsGroupCode(String insGroupCode){
		this.insGroupCode=insGroupCode;
	}

	public String getInsGroupCode(){
		return this.insGroupCode;
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