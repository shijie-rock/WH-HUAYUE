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
public class TtInsCheckOrderItemParsePO implements DataBean{

	private Integer troubleParseMemberId;
	private Integer recheckMemberId;
	private String checkObjCode;
	private String insPicTitle;
	private String insPicUrl;
	private Date createTime;
	private String troubleParseStatus;
	private Integer createBy;
	private String remark;
	private String checkOrderNo;
	private Date updateTime;
	private Date troubleParseBeginTime;
	private String status;
	private Date troubleParseEndTime;
	private String freezeTag;
	private Integer checkOrderItemId;
	private String insPicDesc;
	private String troubleParseDesc;
	private Integer updateBy;
	private String troubleParseType;
	private Integer id;
	private Integer ver;
	private Integer managerMemberId;
	private Integer insPicId;

	public void setTroubleParseMemberId(Integer troubleParseMemberId){
		this.troubleParseMemberId=troubleParseMemberId;
	}

	public Integer getTroubleParseMemberId(){
		return this.troubleParseMemberId;
	}

	public void setRecheckMemberId(Integer recheckMemberId){
		this.recheckMemberId=recheckMemberId;
	}

	public Integer getRecheckMemberId(){
		return this.recheckMemberId;
	}

	public void setCheckObjCode(String checkObjCode){
		this.checkObjCode=checkObjCode;
	}

	public String getCheckObjCode(){
		return this.checkObjCode;
	}

	public void setInsPicTitle(String insPicTitle){
		this.insPicTitle=insPicTitle;
	}

	public String getInsPicTitle(){
		return this.insPicTitle;
	}

	public void setInsPicUrl(String insPicUrl){
		this.insPicUrl=insPicUrl;
	}

	public String getInsPicUrl(){
		return this.insPicUrl;
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

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setTroubleParseEndTime(Date troubleParseEndTime){
		this.troubleParseEndTime=troubleParseEndTime;
	}

	public Date getTroubleParseEndTime(){
		return this.troubleParseEndTime;
	}

	public void setFreezeTag(String freezeTag){
		this.freezeTag=freezeTag;
	}

	public String getFreezeTag(){
		return this.freezeTag;
	}

	public void setCheckOrderItemId(Integer checkOrderItemId){
		this.checkOrderItemId=checkOrderItemId;
	}

	public Integer getCheckOrderItemId(){
		return this.checkOrderItemId;
	}

	public void setInsPicDesc(String insPicDesc){
		this.insPicDesc=insPicDesc;
	}

	public String getInsPicDesc(){
		return this.insPicDesc;
	}

	public void setTroubleParseDesc(String troubleParseDesc){
		this.troubleParseDesc=troubleParseDesc;
	}

	public String getTroubleParseDesc(){
		return this.troubleParseDesc;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setTroubleParseType(String troubleParseType){
		this.troubleParseType=troubleParseType;
	}

	public String getTroubleParseType(){
		return this.troubleParseType;
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

	public void setManagerMemberId(Integer managerMemberId){
		this.managerMemberId=managerMemberId;
	}

	public Integer getManagerMemberId(){
		return this.managerMemberId;
	}

	public void setInsPicId(Integer insPicId){
		this.insPicId=insPicId;
	}

	public Integer getInsPicId(){
		return this.insPicId;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}