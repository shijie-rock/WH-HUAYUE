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
public class TbSysRolePO implements DataBean{

	private String roleDesc;
	private Integer updateBy;
	private Date createTime;
	private String sysTag;
	private Integer ver;
	private Integer createBy;
	private Date updateTime;
	private String roleCode;
	private String roleName;
	private String importTag;
	private String status;
	private String freezeTag;
	

	public void setRoleDesc(String roleDesc){
		this.roleDesc=roleDesc;
	}

	public String getRoleDesc(){
		return this.roleDesc;
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

	public void setSysTag(String sysTag){
		this.sysTag=sysTag;
	}

	public String getSysTag(){
		return this.sysTag;
	}

	public void setVer(Integer ver){
		this.ver=ver;
	}

	public Integer getVer(){
		return this.ver;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}

	public String getRoleCode(){
		return this.roleCode;
	}

	public void setRoleName(String roleName){
		this.roleName=roleName;
	}

	public String getRoleName(){
		return this.roleName;
	}

	public void setImportTag(String importTag){
		this.importTag=importTag;
	}

	public String getImportTag(){
		return this.importTag;
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
}