/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-01-31 11:35:37
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.object.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TtInsCheckObjItemAddressPO implements DataBean{

	private String checkObjCode;
	private Date createTime;
	private String imgPositionLeft;
	private String remark;
	private Integer createBy;
	private String imgPositionTop;
	private Date updateTime;
	private String status;
	private String freezeTag;
	private Integer updateBy;
	private String objAddressDesc;
	private Integer id;
	private Integer ver;
	private String objAddressName;

	public void setCheckObjCode(String checkObjCode){
		this.checkObjCode=checkObjCode;
	}

	public String getCheckObjCode(){
		return this.checkObjCode;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setImgPositionLeft(String imgPositionLeft){
		this.imgPositionLeft=imgPositionLeft;
	}

	public String getImgPositionLeft(){
		return this.imgPositionLeft;
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

	public void setImgPositionTop(String imgPositionTop){
		this.imgPositionTop=imgPositionTop;
	}

	public String getImgPositionTop(){
		return this.imgPositionTop;
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

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setObjAddressDesc(String objAddressDesc){
		this.objAddressDesc=objAddressDesc;
	}

	public String getObjAddressDesc(){
		return this.objAddressDesc;
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

	public void setObjAddressName(String objAddressName){
		this.objAddressName=objAddressName;
	}

	public String getObjAddressName(){
		return this.objAddressName;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}