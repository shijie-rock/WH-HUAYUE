/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:AgentMobileCheckCodeBean.java
 * �� ������:2015��6��5��-����12:10:01
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Date;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:AgentMobileCheckCodeBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��5�� ����12:10:01
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AgentMobileCheckCodeBean implements DataBean {

	/**
	 * ����һ���µ�ʵ�� AgentMobileCheckCodeBean.
	 *
	 */
	private String mobile;
	private String checkCode;
	private Date sendTime;
	private Date checkTime;
	private String checkStatus;//0 δ��֤ 1 ����֤
	private String sessionId;
	private int memberId;
	private int agentId;
	private Date expireTime;//ʧЧʱ�䣨sendTime+30min��
	private String checkType;//��֤���� �ֻ�����󶨣��޸��˺� ��
	
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
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
