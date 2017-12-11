/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-12-09 23:05:19
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.base.po;

import java.util.Date;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsTruckInfoPO implements DataBean{

	private Date confirmExpDate;
	private Date createTime;
	private String truckDesc;
	private String vin;
	private Integer mainPicId;
	private Integer createBy;
	private String remark;
	private String modelCode;
	private String brandName;
	private String status;
	private String mainPicUrl;
	private Float truckWeight;
	private Integer updateBy;
	private Float truckHeight;
	private Integer id;
	private Date confirmBeginDate;
	private Integer driverId;
	private String color;
	private String truckDangerLevel;
	private String truckLicense;
	private String seriesName;
	private String confirmNo;
	private String truckBelongType;
	private Float truckLength;
	private Date updateTime;
	private String modelName;
	private String truckStatus;
	private String truckType;
	private Date licenseDate;
	private Date makeDate;
	private String seriesCode;
	private String brandCode;
	
	/**
	 * freezeTag
	 *
	 * @return  the freezeTag
	 * @since   1.0.0
	 */
	
	public String getFreezeTag() {
		return freezeTag;
	}

	/**
	 * @param freezeTag the freezeTag to set
	 */
	public void setFreezeTag(String freezeTag) {
		this.freezeTag = freezeTag;
	}

	/**
	 * ver
	 *
	 * @return  the ver
	 * @since   1.0.0
	 */
	
	public Integer getVer() {
		return ver;
	}

	/**
	 * @param ver the ver to set
	 */
	public void setVer(Integer ver) {
		this.ver = ver;
	}

	private String freezeTag;
	private Integer ver;

	public void setConfirmExpDate(Date confirmExpDate){
		this.confirmExpDate=confirmExpDate;
	}

	public Date getConfirmExpDate(){
		return this.confirmExpDate;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setTruckDesc(String truckDesc){
		this.truckDesc=truckDesc;
	}

	public String getTruckDesc(){
		return this.truckDesc;
	}

	public void setVin(String vin){
		this.vin=vin;
	}

	public String getVin(){
		return this.vin;
	}

	public void setMainPicId(Integer mainPicId){
		this.mainPicId=mainPicId;
	}

	public Integer getMainPicId(){
		return this.mainPicId;
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

	public void setModelCode(String modelCode){
		this.modelCode=modelCode;
	}

	public String getModelCode(){
		return this.modelCode;
	}

	public void setBrandName(String brandName){
		this.brandName=brandName;
	}

	public String getBrandName(){
		return this.brandName;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setMainPicUrl(String mainPicUrl){
		this.mainPicUrl=mainPicUrl;
	}

	public String getMainPicUrl(){
		return this.mainPicUrl;
	}

	public void setTruckWeight(Float truckWeight){
		this.truckWeight=truckWeight;
	}

	public Float getTruckWeight(){
		return this.truckWeight;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setTruckHeight(Float truckHeight){
		this.truckHeight=truckHeight;
	}

	public Float getTruckHeight(){
		return this.truckHeight;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setConfirmBeginDate(Date confirmBeginDate){
		this.confirmBeginDate=confirmBeginDate;
	}

	public Date getConfirmBeginDate(){
		return this.confirmBeginDate;
	}

	public void setDriverId(Integer driverId){
		this.driverId=driverId;
	}

	public Integer getDriverId(){
		return this.driverId;
	}

	public void setColor(String color){
		this.color=color;
	}

	public String getColor(){
		return this.color;
	}

	public void setTruckDangerLevel(String truckDangerLevel){
		this.truckDangerLevel=truckDangerLevel;
	}

	public String getTruckDangerLevel(){
		return this.truckDangerLevel;
	}

	public void setTruckLicense(String truckLicense){
		this.truckLicense=truckLicense;
	}

	public String getTruckLicense(){
		return this.truckLicense;
	}

	public void setSeriesName(String seriesName){
		this.seriesName=seriesName;
	}

	public String getSeriesName(){
		return this.seriesName;
	}

	public void setConfirmNo(String confirmNo){
		this.confirmNo=confirmNo;
	}

	public String getConfirmNo(){
		return this.confirmNo;
	}

	public void setTruckBelongType(String truckBelongType){
		this.truckBelongType=truckBelongType;
	}

	public String getTruckBelongType(){
		return this.truckBelongType;
	}

	public void setTruckLength(Float truckLength){
		this.truckLength=truckLength;
	}

	public Float getTruckLength(){
		return this.truckLength;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setModelName(String modelName){
		this.modelName=modelName;
	}

	public String getModelName(){
		return this.modelName;
	}

	public void setTruckType(String truckType){
		this.truckType=truckType;
	}

	public String getTruckType(){
		return this.truckType;
	}

	public void setLicenseDate(Date licenseDate){
		this.licenseDate=licenseDate;
	}

	public Date getLicenseDate(){
		return this.licenseDate;
	}

	public void setMakeDate(Date makeDate){
		this.makeDate=makeDate;
	}

	public Date getMakeDate(){
		return this.makeDate;
	}

	public void setSeriesCode(String seriesCode){
		this.seriesCode=seriesCode;
	}

	public String getSeriesCode(){
		return this.seriesCode;
	}

	public void setBrandCode(String brandCode){
		this.brandCode=brandCode;
	}

	public String getBrandCode(){
		return this.brandCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}

	/**
	 * truckStatus
	 *
	 * @return  the truckStatus
	 * @since   1.0.0
	*/
	
	public String getTruckStatus() {
		return truckStatus;
	}

	/**
	 * @param truckStatus the truckStatus to set
	 */
	public void setTruckStatus(String truckStatus) {
		this.truckStatus = truckStatus;
	}
}