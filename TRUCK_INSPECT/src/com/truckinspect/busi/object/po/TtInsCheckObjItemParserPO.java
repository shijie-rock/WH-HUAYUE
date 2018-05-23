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
public class TtInsCheckObjItemParserPO implements DataBean{

	private String checkObjCode;
	private Date createTime;
	private String objClassCodeSup;
	private Integer parserMemberId;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String status;
	private String freezeTag;
	private String checkMemberType;
	private Integer updateBy;
	private String objClassCodeSub;
	private Integer id;
	private Integer ver;
	private String objClassCodeMid;

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

	public void setObjClassCodeSup(String objClassCodeSup){
		this.objClassCodeSup=objClassCodeSup;
	}

	public String getObjClassCodeSup(){
		return this.objClassCodeSup;
	}

	public void setParserMemberId(Integer parserMemberId){
		this.parserMemberId=parserMemberId;
	}

	public Integer getParserMemberId(){
		return this.parserMemberId;
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

	public void setCheckMemberType(String checkMemberType){
		this.checkMemberType=checkMemberType;
	}

	public String getCheckMemberType(){
		return this.checkMemberType;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setObjClassCodeSub(String objClassCodeSub){
		this.objClassCodeSub=objClassCodeSub;
	}

	public String getObjClassCodeSub(){
		return this.objClassCodeSub;
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

	public void setObjClassCodeMid(String objClassCodeMid){
		this.objClassCodeMid=objClassCodeMid;
	}

	public String getObjClassCodeMid(){
		return this.objClassCodeMid;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}