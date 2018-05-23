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
public class TtInsCheckEntityObjSubMapPO implements DataBean{

	private Integer updateBy;
	private Date createTime;
	private Integer id;
	private String checkEntSubCode;
	private Integer createBy;
	private Integer ver;
	private String remark;
	private String objClassSubCode;
	private Date updateTime;
	private String status;
	private String freezeTag;

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

	public void setCheckEntSubCode(String checkEntSubCode){
		this.checkEntSubCode=checkEntSubCode;
	}

	public String getCheckEntSubCode(){
		return this.checkEntSubCode;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return this.remark;
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