/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:AgentMobileCheckCodeBean.java
 * 创 建日期:2015年6月5日-下午12:10:01
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Date;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:AgentMobileCheckCodeBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月5日 下午12:10:01
 * 修改备注:
 * @version 1.0.0
 */
public class AgentMobileCheckCodeBean implements DataBean {

	/**
	 * 创建一个新的实例 AgentMobileCheckCodeBean.
	 *
	 */
	private String mobile;
	private String checkCode;
	private Date sendTime;
	private Date checkTime;
	private String checkStatus;//0 未验证 1 已验证
	private String sessionId;
	private int memberId;
	private int agentId;
	private Date expireTime;//失效时间（sendTime+30min）
	private String checkType;//验证类型 手机号码绑定；修改账号 等
	
	/**
	 * mobile
	 *
	 * @return  the mobile
	 * @since   1.0.0
	 */
	
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * checkCode
	 *
	 * @return  the checkCode
	 * @since   1.0.0
	 */
	
	public String getCheckCode() {
		return checkCode;
	}

	/**
	 * @param checkCode the checkCode to set
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * sendTime
	 *
	 * @return  the sendTime
	 * @since   1.0.0
	 */
	
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * checkTime
	 *
	 * @return  the checkTime
	 * @since   1.0.0
	 */
	
	public Date getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * checkStatus
	 *
	 * @return  the checkStatus
	 * @since   1.0.0
	 */
	
	public String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * @param checkStatus the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * sessionId
	 *
	 * @return  the sessionId
	 * @since   1.0.0
	 */
	
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * memberId
	 *
	 * @return  the memberId
	 * @since   1.0.0
	 */
	
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * agentId
	 *
	 * @return  the agentId
	 * @since   1.0.0
	 */
	
	public int getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public AgentMobileCheckCodeBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}

	/**
	 * checkType
	 *
	 * @return  the checkType
	 * @since   1.0.0
	*/
	
	public String getCheckType() {
		return checkType;
	}

	/**
	 * @param checkType the checkType to set
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	/**
	 * expireTime
	 *
	 * @return  the expireTime
	 * @since   1.0.0
	*/
	
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}
