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
public class TsDataDicPO implements DataBean{

	private Integer sort;
	private String typeDesc;
	private Integer updateBy;
	private Date createTime;
	private String subTypeCode;
	private Integer createBy;
	private String codeDesc;
	private Date updateTime;
	private String status;
	private String code;
	private String typeCode;

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setTypeDesc(String typeDesc){
		this.typeDesc=typeDesc;
	}

	public String getTypeDesc(){
		return this.typeDesc;
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

	public void setSubTypeCode(String subTypeCode){
		this.subTypeCode=subTypeCode;
	}

	public String getSubTypeCode(){
		return this.subTypeCode;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setCodeDesc(String codeDesc){
		this.codeDesc=codeDesc;
	}

	public String getCodeDesc(){
		return this.codeDesc;
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

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return this.code;
	}

	public void setTypeCode(String typeCode){
		this.typeCode=typeCode;
	}

	public String getTypeCode(){
		return this.typeCode;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}