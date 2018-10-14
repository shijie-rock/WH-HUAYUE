/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2018-09-29 12:11:12
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.truckinspect.busi.msg.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TmInsCommMsgPO implements DataBean{

	private Date createTime;
	private String publishTag;
	private String commMsgContent;
	private Integer createBy;
	private String remark;
	private Date updateTime;
	private String status;
	private String freezeTag;
	private String commMsgTitle;
	private Integer publishMemId;
	private Integer updateBy;
	private String commMsgSubTitle;
	private Integer id;
	private Integer ver;
	private Date publishTime;
	private String comMsgLevel;

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setPublishTag(String publishTag){
		this.publishTag=publishTag;
	}

	public String getPublishTag(){
		return this.publishTag;
	}

	public void setCommMsgContent(String commMsgContent){
		this.commMsgContent=commMsgContent;
	}

	public String getCommMsgContent(){
		return this.commMsgContent;
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

	public void setCommMsgTitle(String commMsgTitle){
		this.commMsgTitle=commMsgTitle;
	}

	public String getCommMsgTitle(){
		return this.commMsgTitle;
	}

	public void setPublishMemId(Integer publishMemId){
		this.publishMemId=publishMemId;
	}

	public Integer getPublishMemId(){
		return this.publishMemId;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setCommMsgSubTitle(String commMsgSubTitle){
		this.commMsgSubTitle=commMsgSubTitle;
	}

	public String getCommMsgSubTitle(){
		return this.commMsgSubTitle;
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

	public void setPublishTime(Date publishTime){
		this.publishTime=publishTime;
	}

	public Date getPublishTime(){
		return this.publishTime;
	}

	public void setComMsgLevel(String comMsgLevel){
		this.comMsgLevel=comMsgLevel;
	}

	public String getComMsgLevel(){
		return this.comMsgLevel;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}