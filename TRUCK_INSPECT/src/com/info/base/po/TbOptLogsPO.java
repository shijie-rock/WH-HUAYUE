/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-08-16 11:43:46
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.info.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TbOptLogsPO implements DataBean{

	private String optName;
	private String optActionDesc;
	private Date createTime;
	private Integer optMemberId;
	private Integer createBy;
	private Date optTime;
	private String remark;
	private Date updateTime;
	private String status;
	private String optContent;
	private String optTypeCode;
	private Integer updateBy;
	private String optTypeDesc;
	private Integer id;
	private Integer ver;
	private String optActionCode;

	public void setOptName(String optName){
		this.optName=optName;
	}

	public String getOptName(){
		return this.optName;
	}

	public void setOptActionDesc(String optActionDesc){
		this.optActionDesc=optActionDesc;
	}

	public String getOptActionDesc(){
		return this.optActionDesc;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setOptMemberId(Integer optMemberId){
		this.optMemberId=optMemberId;
	}

	public Integer getOptMemberId(){
		return this.optMemberId;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setOptTime(Date optTime){
		this.optTime=optTime;
	}

	public Date getOptTime(){
		return this.optTime;
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

	public void setOptContent(String optContent){
		this.optContent=optContent;
	}

	public String getOptContent(){
		return this.optContent;
	}

	public void setOptTypeCode(String optTypeCode){
		this.optTypeCode=optTypeCode;
	}

	public String getOptTypeCode(){
		return this.optTypeCode;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setOptTypeDesc(String optTypeDesc){
		this.optTypeDesc=optTypeDesc;
	}

	public String getOptTypeDesc(){
		return this.optTypeDesc;
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

	public void setOptActionCode(String optActionCode){
		this.optActionCode=optActionCode;
	}

	public String getOptActionCode(){
		return this.optActionCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}