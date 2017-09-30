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
public class TmSysActionPO implements DataBean{

	private Integer sort;
	private String actionUrl;
	private String actionCode;
	private Integer updateBy;
	private Date createTime;
	private String funCode;
	private Integer id;
	private Integer createBy;
	private String actionName;
	private String actionDesc;
	private Date updateTime;
	private String status;

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setActionUrl(String actionUrl){
		this.actionUrl=actionUrl;
	}

	public String getActionUrl(){
		return this.actionUrl;
	}

	public void setActionCode(String actionCode){
		this.actionCode=actionCode;
	}

	public String getActionCode(){
		return this.actionCode;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
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

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setActionName(String actionName){
		this.actionName=actionName;
	}

	public String getActionName(){
		return this.actionName;
	}

	public void setActionDesc(String actionDesc){
		this.actionDesc=actionDesc;
	}

	public String getActionDesc(){
		return this.actionDesc;
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

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}