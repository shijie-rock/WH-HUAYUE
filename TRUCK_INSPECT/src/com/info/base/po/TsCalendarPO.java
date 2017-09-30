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
public class TsCalendarPO implements DataBean{

	private Date createTime;
	private Integer year;
	private Integer createBy;
	private Integer dayCount;
	private String remark;
	private Date updateTime;
	private Integer weekNum;
	private String status;
	private String isWorkDay;
	private Integer day;
	private Integer updateBy;
	private Date date;
	private Integer id;
	private Integer weekCount;
	private Integer month;

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setYear(Integer year){
		this.year=year;
	}

	public Integer getYear(){
		return this.year;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setDayCount(Integer dayCount){
		this.dayCount=dayCount;
	}

	public Integer getDayCount(){
		return this.dayCount;
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

	public void setWeekNum(Integer weekNum){
		this.weekNum=weekNum;
	}

	public Integer getWeekNum(){
		return this.weekNum;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setIsWorkDay(String isWorkDay){
		this.isWorkDay=isWorkDay;
	}

	public String getIsWorkDay(){
		return this.isWorkDay;
	}

	public void setDay(Integer day){
		this.day=day;
	}

	public Integer getDay(){
		return this.day;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setDate(Date date){
		this.date=date;
	}

	public Date getDate(){
		return this.date;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setWeekCount(Integer weekCount){
		this.weekCount=weekCount;
	}

	public Integer getWeekCount(){
		return this.weekCount;
	}

	public void setMonth(Integer month){
		this.month=month;
	}

	public Integer getMonth(){
		return this.month;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}
}