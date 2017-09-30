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
public class TmSysOptionPO implements DataBean{

	private String actionCode;
	private String optionDesc;
	private Date createTime;
	private Integer createBy;
	private String optionUrl;
	private Date updateTime;
	private String status;
	private Integer updateBy;
	private Integer id;
	private String buttonId;
	private String frActionId;
	private String optionName;
	private String optionCode;

	public void setActionCode(String actionCode){
		this.actionCode=actionCode;
	}

	public String getActionCode(){
		return this.actionCode;
	}

	public void setOptionDesc(String optionDesc){
		this.optionDesc=optionDesc;
	}

	public String getOptionDesc(){
		return this.optionDesc;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
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

	public void setButtonId(String buttonId){
		this.buttonId=buttonId;
	}

	public String getButtonId(){
		return this.buttonId;
	}

	public void setFrActionId(String frActionId){
		this.frActionId=frActionId;
	}

	public String getFrActionId(){
		return this.frActionId;
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