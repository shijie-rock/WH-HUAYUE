/*
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* CreateDate : 2017-07-20 17:46:35
* CreateBy   : rock
* Comment    : generate by com.infoservice.po.POGen
*/

package com.info.base.po;

import java.util.Date;
import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

@SuppressWarnings("serial")
public class TbAppInfoPO implements DataBean{

	private Date lastStartTime;
	private String appName;
	private String appSite;
	private Date createTime;
	private String appContext;
	private Date onlineTime;
	private String appCode;
	private String localCity;
	private String isCurrent;
	private Integer createBy;
	private String appDesc;
	private Date updateTime;
	private String status;
	private String noPrefix;
	private String appIp;
	private String version;
	private Integer updateBy;
	private String appType;
	private String runStatus;
	private Integer id;
	private String localCityCode;
	private String protocol;
	private String serverUrl;
	private String fileServerPrifixUrl;//FILE_SERVER_PRIFIX_URL 文件服务器地址前缀

	public void setLastStartTime(Date lastStartTime){
		this.lastStartTime=lastStartTime;
	}

	public Date getLastStartTime(){
		return this.lastStartTime;
	}

	public void setAppName(String appName){
		this.appName=appName;
	}

	public String getAppName(){
		return this.appName;
	}

	public void setAppSite(String appSite){
		this.appSite=appSite;
	}

	public String getAppSite(){
		return this.appSite;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setAppContext(String appContext){
		this.appContext=appContext;
	}

	public String getAppContext(){
		return this.appContext;
	}

	public void setOnlineTime(Date onlineTime){
		this.onlineTime=onlineTime;
	}

	public Date getOnlineTime(){
		return this.onlineTime;
	}

	public void setAppCode(String appCode){
		this.appCode=appCode;
	}

	public String getAppCode(){
		return this.appCode;
	}

	public void setLocalCity(String localCity){
		this.localCity=localCity;
	}

	public String getLocalCity(){
		return this.localCity;
	}

	public void setIsCurrent(String isCurrent){
		this.isCurrent=isCurrent;
	}

	public String getIsCurrent(){
		return this.isCurrent;
	}

	public void setCreateBy(Integer createBy){
		this.createBy=createBy;
	}

	public Integer getCreateBy(){
		return this.createBy;
	}

	public void setAppDesc(String appDesc){
		this.appDesc=appDesc;
	}

	public String getAppDesc(){
		return this.appDesc;
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

	public void setNoPrefix(String noPrefix){
		this.noPrefix=noPrefix;
	}

	public String getNoPrefix(){
		return this.noPrefix;
	}

	public void setAppIp(String appIp){
		this.appIp=appIp;
	}

	public String getAppIp(){
		return this.appIp;
	}

	public void setVersion(String version){
		this.version=version;
	}

	public String getVersion(){
		return this.version;
	}

	public void setUpdateBy(Integer updateBy){
		this.updateBy=updateBy;
	}

	public Integer getUpdateBy(){
		return this.updateBy;
	}

	public void setAppType(String appType){
		this.appType=appType;
	}

	public String getAppType(){
		return this.appType;
	}

	public void setRunStatus(String runStatus){
		this.runStatus=runStatus;
	}

	public String getRunStatus(){
		return this.runStatus;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setLocalCityCode(String localCityCode){
		this.localCityCode=localCityCode;
	}

	public String getLocalCityCode(){
		return this.localCityCode;
	}

	public void setProtocol(String protocol){
		this.protocol=protocol;
	}

	public String getProtocol(){
		return this.protocol;
	}

	public String toXMLString(){
		return POFactoryUtil.beanToXmlString(this);
	}

	/**
	 * serverUrl
	 *
	 * @return  the serverUrl
	 * @since   1.0.0
	*/
	
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * @param serverUrl the serverUrl to set
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * fileServerPrifixUrl
	 *
	 * @return  the fileServerPrifixUrl
	 * @since   1.0.0
	*/
	
	public String getFileServerPrifixUrl() {
		return fileServerPrifixUrl;
	}

	/**
	 * @param fileServerPrifixUrl the fileServerPrifixUrl to set
	 */
	public void setFileServerPrifixUrl(String fileServerPrifixUrl) {
		this.fileServerPrifixUrl = fileServerPrifixUrl;
	}
}