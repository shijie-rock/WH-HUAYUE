/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-02-22 13:37:29
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.object.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsCheckObjClassMiddlePO implements DataBean{

	private String objClassDesc;
	private Integer objClassLevel;
	private Date createTime;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String status;
	private String freezeTag;
	private String objClassTypeCode;
	private String objClassName;
	private Integer sort;
	private Integer updateBy;
	private String objClassCode;
	private Integer id;
	private Integer ver;
	private String checkEntMidCode;
	private String objClassFCode;

	public void setObjClassDesc(String objClassDesc){
		this.objClassDesc=objClassDesc;
	}

	public String getObjClassDesc(){
		return this.objClassDesc;
	}

	public void setObjClassLevel(Integer objClassLevel){
		this.objClassLevel=objClassLevel;
	}

	public Integer getObjClassLevel(){
		return this.objClassLevel;
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

	public void setObjClassTypeCode(String objClassTypeCode){
		this.objClassTypeCode=objClassTypeCode;
	}

	public String getObjClassTypeCode(){
		return this.objClassTypeCode;
	}

	public void setObjClassName(String objClassName){
		this.objClassName=objClassName;
	}

	public String getObjClassName(){
		return this.objClassName;
	}

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setObjClassCode(String objClassCode){
		this.objClassCode=objClassCode;
	}

	public String getObjClassCode(){
		return this.objClassCode;
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

	public void setCheckEntMidCode(String checkEntMidCode){
		this.checkEntMidCode=checkEntMidCode;
	}

	public String getCheckEntMidCode(){
		return this.checkEntMidCode;
	}

	public void setObjClassFCode(String objClassFCode){
		this.objClassFCode=objClassFCode;
	}

	public String getObjClassFCode(){
		return this.objClassFCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}