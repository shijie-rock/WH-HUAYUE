/*
* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
* This software is published under the terms of the Infoservice Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2016-11-19 16:54:30
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen

SELECT 
ETVE_CARD,
DECODE(etve_Bill_Party,
 '宁波万华一腾物流有限公司',
 '0000800585',
 '烟台万华合成革集团华悦汽车运输有限公司',
 '0000800001') as carrier_code
FROM et_vehicle
where rec_status='0' AND ETVE_TYPE='TYPE001'
*/
package com.cn.gpslms.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class NewVehiclePO implements DataBean{

	private String etveBodyNo;
	private String etveModel;
	private Date modifyTime;
	private Date createTime;
	private String etveDef6;
	private Integer etveId;
	private String creator;
	private String etveBillParty;
	private String etveDef5;
	private String etveWorkingNo;
	private String etveDesc;
	private String etveEbcuCode;
	private String etveVariety;
	private String etveIsDangerous;
	private String etveAptitude;
	private String etveCard;
	private String etveSupplierCode;
	private String etveType;
	private String etveCompanyBelong;
	private String modifier;
	private Date etveScrapTime;
	private String etveRegisterPlace;
	private String etveDef10;
	private String etveSupplierType;
	private String etveEngineNo;
	private String etveStatus;
	private Integer recStatus;
	private String orgId;
	private Date etveRegisterTime;
	private String etveEtveIdCurrCard;
	private Date etveWorkingExpirationDate;
	private String etveFrameNumber;
	
	private String etveBillNo;
//	 '宁波万华一腾物流有限公司',
//	 '0000800585',
//	 '烟台万华合成革集团华悦汽车运输有限公司',
//	 '0000800001'

	public void setEtveBodyNo(String etveBodyNo){
		this.etveBodyNo=etveBodyNo;
	}

	public String getEtveBodyNo(){
		return this.etveBodyNo;
	}

	public void setEtveModel(String etveModel){
		this.etveModel=etveModel;
	}

	public String getEtveModel(){
		return this.etveModel;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}

	public Date getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setEtveDef6(String etveDef6){
		this.etveDef6=etveDef6;
	}

	public String getEtveDef6(){
		return this.etveDef6;
	}

	public void setEtveId(Integer etveId){
		this.etveId=etveId;
	}

	public Integer getEtveId(){
		return this.etveId;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return this.creator;
	}

	public void setEtveBillParty(String etveBillParty){
		this.etveBillParty=etveBillParty;
	}

	public String getEtveBillParty(){
		return this.etveBillParty;
	}

	public void setEtveDef5(String etveDef5){
		this.etveDef5=etveDef5;
	}

	public String getEtveDef5(){
		return this.etveDef5;
	}

	public void setEtveWorkingNo(String etveWorkingNo){
		this.etveWorkingNo=etveWorkingNo;
	}

	public String getEtveWorkingNo(){
		return this.etveWorkingNo;
	}

	public void setEtveDesc(String etveDesc){
		this.etveDesc=etveDesc;
	}

	public String getEtveDesc(){
		return this.etveDesc;
	}

	public void setEtveEbcuCode(String etveEbcuCode){
		this.etveEbcuCode=etveEbcuCode;
	}

	public String getEtveEbcuCode(){
		return this.etveEbcuCode;
	}

	public void setEtveVariety(String etveVariety){
		this.etveVariety=etveVariety;
	}

	public String getEtveVariety(){
		return this.etveVariety;
	}

	public void setEtveIsDangerous(String etveIsDangerous){
		this.etveIsDangerous=etveIsDangerous;
	}

	public String getEtveIsDangerous(){
		return this.etveIsDangerous;
	}

	public void setEtveAptitude(String etveAptitude){
		this.etveAptitude=etveAptitude;
	}

	public String getEtveAptitude(){
		return this.etveAptitude;
	}

	public void setEtveCard(String etveCard){
		this.etveCard=etveCard;
	}

	public String getEtveCard(){
		return this.etveCard;
	}

	public void setEtveSupplierCode(String etveSupplierCode){
		this.etveSupplierCode=etveSupplierCode;
	}

	public String getEtveSupplierCode(){
		return this.etveSupplierCode;
	}

	public void setEtveType(String etveType){
		this.etveType=etveType;
	}

	public String getEtveType(){
		return this.etveType;
	}

	public void setEtveCompanyBelong(String etveCompanyBelong){
		this.etveCompanyBelong=etveCompanyBelong;
	}

	public String getEtveCompanyBelong(){
		return this.etveCompanyBelong;
	}

	public void setModifier(String modifier){
		this.modifier=modifier;
	}

	public String getModifier(){
		return this.modifier;
	}

	public void setEtveScrapTime(Date etveScrapTime){
		this.etveScrapTime=etveScrapTime;
	}

	public Date getEtveScrapTime(){
		return this.etveScrapTime;
	}

	public void setEtveRegisterPlace(String etveRegisterPlace){
		this.etveRegisterPlace=etveRegisterPlace;
	}

	public String getEtveRegisterPlace(){
		return this.etveRegisterPlace;
	}

	public void setEtveDef10(String etveDef10){
		this.etveDef10=etveDef10;
	}

	public String getEtveDef10(){
		return this.etveDef10;
	}

	public void setEtveSupplierType(String etveSupplierType){
		this.etveSupplierType=etveSupplierType;
	}

	public String getEtveSupplierType(){
		return this.etveSupplierType;
	}

	public void setEtveEngineNo(String etveEngineNo){
		this.etveEngineNo=etveEngineNo;
	}

	public String getEtveEngineNo(){
		return this.etveEngineNo;
	}

	public void setEtveStatus(String etveStatus){
		this.etveStatus=etveStatus;
	}

	public String getEtveStatus(){
		return this.etveStatus;
	}

	public void setRecStatus(Integer recStatus){
		this.recStatus=recStatus;
	}

	public Integer getRecStatus(){
		return this.recStatus;
	}

	public void setOrgId(String orgId){
		this.orgId=orgId;
	}

	public String getOrgId(){
		return this.orgId;
	}

	public void setEtveRegisterTime(Date etveRegisterTime){
		this.etveRegisterTime=etveRegisterTime;
	}

	public Date getEtveRegisterTime(){
		return this.etveRegisterTime;
	}

	public void setEtveEtveIdCurrCard(String etveEtveIdCurrCard){
		this.etveEtveIdCurrCard=etveEtveIdCurrCard;
	}

	public String getEtveEtveIdCurrCard(){
		return this.etveEtveIdCurrCard;
	}

	public void setEtveWorkingExpirationDate(Date etveWorkingExpirationDate){
		this.etveWorkingExpirationDate=etveWorkingExpirationDate;
	}

	public Date getEtveWorkingExpirationDate(){
		return this.etveWorkingExpirationDate;
	}

	public void setEtveFrameNumber(String etveFrameNumber){
		this.etveFrameNumber=etveFrameNumber;
	}

	public String getEtveFrameNumber(){
		return this.etveFrameNumber;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}

	/**
	 * etveBillNo
	 *
	 * @return  the etveBillNo
	 * @since   1.0.0
	*/
	
	public String getEtveBillNo() {
		return etveBillNo;
	}

	/**
	 * @param etveBillNo the etveBillNo to set
	 */
	public void setEtveBillNo(String etveBillNo) {
		this.etveBillNo = etveBillNo;
	}
}