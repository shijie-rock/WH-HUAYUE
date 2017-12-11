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
public class TtInsTruckImgPO implements DataBean{

	private Integer thunmbPicId;
	private String picDesc;
	private Date createTime;
	private String srcPicUrl;
	private String picTitle;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String thunmbPicUrl;
	private String status;
	private Integer truckId;
	private Integer sort;
	private Integer srcPicId;
	private Integer updateBy;
	private Integer id;
	
	private String freezeTag;
	private Integer ver;

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

	public void setThunmbPicId(Integer thunmbPicId){
		this.thunmbPicId=thunmbPicId;
	}

	public Integer getThunmbPicId(){
		return this.thunmbPicId;
	}

	public void setPicDesc(String picDesc){
		this.picDesc=picDesc;
	}

	public String getPicDesc(){
		return this.picDesc;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setSrcPicUrl(String srcPicUrl){
		this.srcPicUrl=srcPicUrl;
	}

	public String getSrcPicUrl(){
		return this.srcPicUrl;
	}

	public void setPicTitle(String picTitle){
		this.picTitle=picTitle;
	}

	public String getPicTitle(){
		return this.picTitle;
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

	public void setThunmbPicUrl(String thunmbPicUrl){
		this.thunmbPicUrl=thunmbPicUrl;
	}

	public String getThunmbPicUrl(){
		return this.thunmbPicUrl;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setTruckId(Integer truckId){
		this.truckId=truckId;
	}

	public Integer getTruckId(){
		return this.truckId;
	}

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setSrcPicId(Integer srcPicId){
		this.srcPicId=srcPicId;
	}

	public Integer getSrcPicId(){
		return this.srcPicId;
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

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}