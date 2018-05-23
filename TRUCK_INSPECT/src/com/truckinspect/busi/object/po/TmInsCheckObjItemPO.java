/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-02-28 23:21:22
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.object.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsCheckObjItemPO implements DataBean{

	private String objCheckWay;
	private String objCheckBef;
	private String objCheckAft;
	private String nodePath;
	private String objCheckReceive;
	private Date createTime;
	private String objClassCodeSup;
	private Integer objCheckPhoto;
	private String remark;
	private Integer createBy;
	private String checkObjName;
	private String checkObjDesc;
	private String status;
	private Integer sort;
	private Integer updateBy;
	private Integer id;
	private Integer checkObjLevel;
	private String objClassCodeMid;
	private String objCheckHaf;
	private String comlianceDesc;
	private String checkObjCode;
	private String objEmergencyLevel;
	private Date updateTime;
	private String freezeTag;
	private String checkObjFCode;
	private String objClassTypeCode;
	private String objClassCodeSub;
	private Integer objCheckFrequency;
	private Integer ver;

	public void setObjCheckWay(String objCheckWay){
		this.objCheckWay=objCheckWay;
	}

	public String getObjCheckWay(){
		return this.objCheckWay;
	}

	public void setObjCheckBef(String objCheckBef){
		this.objCheckBef=objCheckBef;
	}

	public String getObjCheckBef(){
		return this.objCheckBef;
	}

	public void setObjCheckAft(String objCheckAft){
		this.objCheckAft=objCheckAft;
	}

	public String getObjCheckAft(){
		return this.objCheckAft;
	}

	public void setNodePath(String nodePath){
		this.nodePath=nodePath;
	}

	public String getNodePath(){
		return this.nodePath;
	}

	public void setObjCheckReceive(String objCheckReceive){
		this.objCheckReceive=objCheckReceive;
	}

	public String getObjCheckReceive(){
		return this.objCheckReceive;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setObjClassCodeSup(String objClassCodeSup){
		this.objClassCodeSup=objClassCodeSup;
	}

	public String getObjClassCodeSup(){
		return this.objClassCodeSup;
	}

	public void setObjCheckPhoto(Integer objCheckPhoto){
		this.objCheckPhoto=objCheckPhoto;
	}

	public Integer getObjCheckPhoto(){
		return this.objCheckPhoto;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setCheckObjName(String checkObjName){
		this.checkObjName=checkObjName;
	}

	public String getCheckObjName(){
		return this.checkObjName;
	}

	public void setCheckObjDesc(String checkObjDesc){
		this.checkObjDesc=checkObjDesc;
	}

	public String getCheckObjDesc(){
		return this.checkObjDesc;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
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

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCheckObjLevel(Integer checkObjLevel){
		this.checkObjLevel=checkObjLevel;
	}

	public Integer getCheckObjLevel(){
		return this.checkObjLevel;
	}

	public void setObjClassCodeMid(String objClassCodeMid){
		this.objClassCodeMid=objClassCodeMid;
	}

	public String getObjClassCodeMid(){
		return this.objClassCodeMid;
	}

	public void setObjCheckHaf(String objCheckHaf){
		this.objCheckHaf=objCheckHaf;
	}

	public String getObjCheckHaf(){
		return this.objCheckHaf;
	}

	public void setComlianceDesc(String comlianceDesc){
		this.comlianceDesc=comlianceDesc;
	}

	public String getComlianceDesc(){
		return this.comlianceDesc;
	}

	public void setCheckObjCode(String checkObjCode){
		this.checkObjCode=checkObjCode;
	}

	public String getCheckObjCode(){
		return this.checkObjCode;
	}

	public void setObjEmergencyLevel(String objEmergencyLevel){
		this.objEmergencyLevel=objEmergencyLevel;
	}

	public String getObjEmergencyLevel(){
		return this.objEmergencyLevel;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setFreezeTag(String freezeTag){
		this.freezeTag=freezeTag;
	}

	public String getFreezeTag(){
		return this.freezeTag;
	}

	public void setCheckObjFCode(String checkObjFCode){
		this.checkObjFCode=checkObjFCode;
	}

	public String getCheckObjFCode(){
		return this.checkObjFCode;
	}

	public void setObjClassTypeCode(String objClassTypeCode){
		this.objClassTypeCode=objClassTypeCode;
	}

	public String getObjClassTypeCode(){
		return this.objClassTypeCode;
	}

	public void setObjClassCodeSub(String objClassCodeSub){
		this.objClassCodeSub=objClassCodeSub;
	}

	public String getObjClassCodeSub(){
		return this.objClassCodeSub;
	}

	public void setObjCheckFrequency(Integer objCheckFrequency){
		this.objCheckFrequency=objCheckFrequency;
	}

	public Integer getObjCheckFrequency(){
		return this.objCheckFrequency;
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