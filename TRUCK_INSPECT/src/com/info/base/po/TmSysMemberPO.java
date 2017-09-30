/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-09-02 09:18:50
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.info.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmSysMemberPO implements DataBean{

	private Date createTime;
	private String sysTag;
	private String certNo;
	private String emailConfirmStatus;
	private Integer createBy;
	private String remark;
	private String status;
	private String phone;
	private String memberCode;
	private String onlineStatus;
	private Integer updateBy;
	private String memberRemark;
	private String sex;
	private String memberName;
	private String jobTitelType;
	private String canLoginIns;
	private String canLoginSys;
	private Integer id;
	private String email;
	private String memberType;
	private String password;
	private Date mobileConfirmTime;
	private Date birthday;
	private Date lastLoginTime;
	private String isInspactor;
	private Date updateTime;
	private String freezeTag;
	private Date emialConfirmTime;
	private String mobile;
	private String haveAcc;
	private Integer ver;
	private String openId;
	private String mobileConfirmStatus;

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setSysTag(String sysTag){
		this.sysTag=sysTag;
	}

	public String getSysTag(){
		return this.sysTag;
	}

	public void setCertNo(String certNo){
		this.certNo=certNo;
	}

	public String getCertNo(){
		return this.certNo;
	}

	public void setEmailConfirmStatus(String emailConfirmStatus){
		this.emailConfirmStatus=emailConfirmStatus;
	}

	public String getEmailConfirmStatus(){
		return this.emailConfirmStatus;
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

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setMemberCode(String memberCode){
		this.memberCode=memberCode;
	}

	public String getMemberCode(){
		return this.memberCode;
	}

	public void setOnlineStatus(String onlineStatus){
		this.onlineStatus=onlineStatus;
	}

	public String getOnlineStatus(){
		return this.onlineStatus;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setMemberRemark(String memberRemark){
		this.memberRemark=memberRemark;
	}

	public String getMemberRemark(){
		return this.memberRemark;
	}

	public void setSex(String sex){
		this.sex=sex;
	}

	public String getSex(){
		return this.sex;
	}

	public void setMemberName(String memberName){
		this.memberName=memberName;
	}

	public String getMemberName(){
		return this.memberName;
	}

	public void setJobTitelType(String jobTitelType){
		this.jobTitelType=jobTitelType;
	}

	public String getJobTitelType(){
		return this.jobTitelType;
	}

	public void setCanLoginIns(String canLoginIns){
		this.canLoginIns=canLoginIns;
	}

	public String getCanLoginIns(){
		return this.canLoginIns;
	}

	public void setCanLoginSys(String canLoginSys){
		this.canLoginSys=canLoginSys;
	}

	public String getCanLoginSys(){
		return this.canLoginSys;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setMemberType(String memberType){
		this.memberType=memberType;
	}

	public String getMemberType(){
		return this.memberType;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setMobileConfirmTime(Date mobileConfirmTime){
		this.mobileConfirmTime=mobileConfirmTime;
	}

	public Date getMobileConfirmTime(){
		return this.mobileConfirmTime;
	}

	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime=lastLoginTime;
	}

	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setIsInspactor(String isInspactor){
		this.isInspactor=isInspactor;
	}

	public String getIsInspactor(){
		return this.isInspactor;
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

	public void setEmialConfirmTime(Date emialConfirmTime){
		this.emialConfirmTime=emialConfirmTime;
	}

	public Date getEmialConfirmTime(){
		return this.emialConfirmTime;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setHaveAcc(String haveAcc){
		this.haveAcc=haveAcc;
	}

	public String getHaveAcc(){
		return this.haveAcc;
	}

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public void setOpenId(String openId){
		this.openId=openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	public void setMobileConfirmStatus(String mobileConfirmStatus){
		this.mobileConfirmStatus=mobileConfirmStatus;
	}

	public String getMobileConfirmStatus(){
		return this.mobileConfirmStatus;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}