/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-02-22 13:39:12
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.entity.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsCheckEntitySubPO implements DataBean{

	private String checkEntFCode;
	private String checkEntCode;
	private String checkEntName;
	private Date createTime;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String status;
	private String freezeTag;
	private Integer checkEntLevel;
	private Integer sort;
	private String checkEntDesc;
	private Integer updateBy;
	private String checkEntTypeCode;
	private Integer id;
	private Integer ver;

	public void setCheckEntFCode(String checkEntFCode){
		this.checkEntFCode=checkEntFCode;
	}

	public String getCheckEntFCode(){
		return this.checkEntFCode;
	}

	public void setCheckEntCode(String checkEntCode){
		this.checkEntCode=checkEntCode;
	}

	public String getCheckEntCode(){
		return this.checkEntCode;
	}

	public void setCheckEntName(String checkEntName){
		this.checkEntName=checkEntName;
	}

	public String getCheckEntName(){
		return this.checkEntName;
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

	public void setFreezeTag(String freezeTag){
		this.freezeTag=freezeTag;
	}

	public String getFreezeTag(){
		return this.freezeTag;
	}

	public void setCheckEntLevel(Integer checkEntLevel){
		this.checkEntLevel=checkEntLevel;
	}

	public Integer getCheckEntLevel(){
		return this.checkEntLevel;
	}

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setCheckEntDesc(String checkEntDesc){
		this.checkEntDesc=checkEntDesc;
	}

	public String getCheckEntDesc(){
		return this.checkEntDesc;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setCheckEntTypeCode(String checkEntTypeCode){
		this.checkEntTypeCode=checkEntTypeCode;
	}

	public String getCheckEntTypeCode(){
		return this.checkEntTypeCode;
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