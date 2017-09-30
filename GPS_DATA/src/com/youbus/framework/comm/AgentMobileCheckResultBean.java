/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:AgentMobileCheckResultBean.java
 * �� ������:2015��6��5��-����3:35:23
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:AgentMobileCheckResultBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��5�� ����3:35:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AgentMobileCheckResultBean implements DataBean {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 66135871499378928L;

	private int returnCode;//��֤ ��֤�뷵�ؽ������
	private String returnMsg;//��֤ ��֤�뷵�ؽ��˵��
	
	/**
	 * ����һ���µ�ʵ�� AgentMobileCheckResultBean.
	 *
	 */
	public AgentMobileCheckResultBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * returnCode
	 *
	 * @return  the returnCode
	 * @since   1.0.0
	 */
	
	public int getReturnCode() {
		return returnCode;
	}

	
	/**
	 * ����һ���µ�ʵ�� AgentMobileCheckResultBean.
	 *
	 * @param returnCode
	 * @param returnMsg
	 */
	public AgentMobileCheckResultBean(int returnCode, String returnMsg) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * returnMsg
	 *
	 * @return  the returnMsg
	 * @since   1.0.0
	 */
	
	public String getReturnMsg() {
		return returnMsg;
	}

	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
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

}
