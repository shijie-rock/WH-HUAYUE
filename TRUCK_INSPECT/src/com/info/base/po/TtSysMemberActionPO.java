/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-07-20 17:40:50
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.info.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TtSysMemberActionPO implements DataBean{

	private String actionCode;
	private Date createTime;
	private String funCode;
	private Integer createBy;
	private String optionUrl;
	private Date updateTime;
	private String status;
	private Integer memberId;
	private String funName;
	private String actionUrl;
	private Integer updateBy;
	private Integer id;
	private Integer ver;
	private String actionName;
	private String buttonId;
	private String optionName;
	private String optionCode;

	public void setActionCode(String actionCode){
		this.actionCode=actionCode;
	}

	public String getActionCode(){
		return this.actionCode;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setFunCode(String funCode){
		this.funCode=funCode;
	}

	public String getFunCode(){
		return this.funCode;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setOptionUrl(String optionUrl){
		this.optionUrl=optionUrl;
	}

	public String getOptionUrl(){
		return this.optionUrl;
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

	public void setMemberId(Integer memberId){
		this.memberId=memberId;
	}

	public Integer getMemberId(){
		return this.memberId;
	}

	public void setFunName(String funName){
		this.funName=funName;
	}

	public String getFunName(){
		return this.funName;
	}

	public void setActionUrl(String actionUrl){
		this.actionUrl=actionUrl;
	}

	public String getActionUrl(){
		return this.actionUrl;
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

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public void setActionName(String actionName){
		this.actionName=actionName;
	}

	public String getActionName(){
		return this.actionName;
	}

	public void setButtonId(String buttonId){
		this.buttonId=buttonId;
	}

	public String getButtonId(){
		return this.buttonId;
	}

	public void setOptionName(String optionName){
		this.optionName=optionName;
	}

	public String getOptionName(){
		return this.optionName;
	}

	public void setOptionCode(String optionCode){
		this.optionCode=optionCode;
	}

	public String getOptionCode(){
		return this.optionCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}