/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.remoteclient
 * ��   ��  ��:ParamBean.java
 * �� ������:2015��6��3��-����11:17:02
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.httpclient;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:ParamBean
 * ������:���ڰ�װ�������bean
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��3�� ����11:17:02
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ParamBean implements DataBean {
	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 5585723020016605325L;
	private String paramName;
	private String paramValue;
	/**
	 * ����һ���µ�ʵ�� ParamBean.
	 *
	 */
	public ParamBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����һ���µ�ʵ�� ParamBean.
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public ParamBean(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}
	
	/**
	 * paramName
	 *
	 * @return  the paramName
	 * @since   1.0.0
	 */
	
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * paramValue
	 *
	 * @return  the paramValue
	 * @since   1.0.0
	 */
	
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
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
