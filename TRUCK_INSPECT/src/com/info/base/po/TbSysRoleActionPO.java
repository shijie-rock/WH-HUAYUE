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
public class TbSysRoleActionPO implements DataBean{

	private String actionCode;
	private Date createTime;
	private String funCode;
	private String sysTag;
	private Integer createBy;
	private String importTag;
	private String roleCode;
	private Date updateTime;
	private String status;
	private Integer updateBy;
	private Integer id;
	private Integer ver;
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

	public void setSysTag(String sysTag){
		this.sysTag=sysTag;
	}

	public String getSysTag(){
		return this.sysTag;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setImportTag(String importTag){
		this.importTag=importTag;
	}

	public String getImportTag(){
		return this.importTag;
	}

	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}

	public String getRoleCode(){
		return this.roleCode;
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

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
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