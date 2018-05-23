/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-01-31 11:35:37
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.order.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsCheckOrderItemPO implements DataBean{

	private String positionAddress;
	private Date createTime;
	private String troubleParseStatus;
	private Integer createBy;
	private String remark;
	private String checkObjName;
	private String positionLatitude;
	private String status;
	private String checkObjDesc;
	private Date troubleParseEndTime;
	private String troubleParseResult;
	private String checkOrderStatus;
	private Integer updateBy;
	private Integer id;
	private String troubleDesc;
	private Integer troubleParseMemberId;
	private String checkObjCode;
	private String comlianceDesc;
	private String positionLongitude;
	private String positionName;
	private String positionCode;
	private String objEmergencyLevel;
	private String checkOrderNo;
	private Date updateTime;
	private Date troubleParseBeginTime;
	private String freezeTag;
	private String troubleParseDesc;
	private String checkOrderResult;
	private String troubleParseType;
	private Integer ver;

	public void setPositionAddress(String positionAddress){
		this.positionAddress=positionAddress;
	}

	public String getPositionAddress(){
		return this.positionAddress;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setTroubleParseStatus(String troubleParseStatus){
		this.troubleParseStatus=troubleParseStatus;
	}

	public String getTroubleParseStatus(){
		return this.troubleParseStatus;
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

	public void setCheckObjName(String checkObjName){
		this.checkObjName=checkObjName;
	}

	public String getCheckObjName(){
		return this.checkObjName;
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

	public void setCheckObjDesc(String checkObjDesc){
		this.checkObjDesc=checkObjDesc;
	}

	public String getCheckObjDesc(){
		return this.checkObjDesc;
	}

	public void setTroubleParseEndTime(Date troubleParseEndTime){
		this.troubleParseEndTime=troubleParseEndTime;
	}

	public Date getTroubleParseEndTime(){
		return this.troubleParseEndTime;
	}

	public void setTroubleParseResult(String troubleParseResult){
		this.troubleParseResult=troubleParseResult;
	}

	public String getTroubleParseResult(){
		return this.troubleParseResult;
	}

	public void setCheckOrderStatus(String checkOrderStatus){
		this.checkOrderStatus=checkOrderStatus;
	}

	public String getCheckOrderStatus(){
		return this.checkOrderStatus;
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

	public void setTroubleDesc(String troubleDesc){
		this.troubleDesc=troubleDesc;
	}

	public String getTroubleDesc(){
		return this.troubleDesc;
	}

	public void setTroubleParseMemberId(Integer troubleParseMemberId){
		this.troubleParseMemberId=troubleParseMemberId;
	}

	public Integer getTroubleParseMemberId(){
		return this.troubleParseMemberId;
	}

	public void setCheckObjCode(String checkObjCode){
		this.checkObjCode=checkObjCode;
	}

	public String getCheckObjCode(){
		return this.checkObjCode;
	}

	public void setComlianceDesc(String comlianceDesc){
		this.comlianceDesc=comlianceDesc;
	}

	public String getComlianceDesc(){
		return this.comlianceDesc;
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

	public void setPositionCode(String positionCode){
		this.positionCode=positionCode;
	}

	public String getPositionCode(){
		return this.positionCode;
	}

	public void setObjEmergencyLevel(String objEmergencyLevel){
		this.objEmergencyLevel=objEmergencyLevel;
	}

	public String getObjEmergencyLevel(){
		return this.objEmergencyLevel;
	}

	public void setCheckOrderNo(String checkOrderNo){
		this.checkOrderNo=checkOrderNo;
	}

	public String getCheckOrderNo(){
		return this.checkOrderNo;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setTroubleParseBeginTime(Date troubleParseBeginTime){
		this.troubleParseBeginTime=troubleParseBeginTime;
	}

	public Date getTroubleParseBeginTime(){
		return this.troubleParseBeginTime;
	}

	public void setFreezeTag(String freezeTag){
		this.freezeTag=freezeTag;
	}

	public String getFreezeTag(){
		return this.freezeTag;
	}

	public void setTroubleParseDesc(String troubleParseDesc){
		this.troubleParseDesc=troubleParseDesc;
	}

	public String getTroubleParseDesc(){
		return this.troubleParseDesc;
	}

	public void setCheckOrderResult(String checkOrderResult){
		this.checkOrderResult=checkOrderResult;
	}

	public String getCheckOrderResult(){
		return this.checkOrderResult;
	}

	public void setTroubleParseType(String troubleParseType){
		this.troubleParseType=troubleParseType;
	}

	public String getTroubleParseType(){
		return this.troubleParseType;
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