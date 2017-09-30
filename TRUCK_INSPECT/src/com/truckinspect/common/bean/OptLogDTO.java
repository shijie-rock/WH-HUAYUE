/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.common.bean
 * ��   ��  ��:OptLogDTO.java
 * �� ������:2017��8��16��-����11:15:08
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common.bean;

import net.sf.json.JSONObject;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:OptLogDTO
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��16�� ����11:15:08
 * �޸ı�ע:
 * @version 1.0.0
 */
public class OptLogDTO implements DataBean {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private String optTypeCode;
	private String optTypeDesc;
	private String optContent;
	
	
	/**
	 * ����һ���µ�ʵ�� OptLogDTO.
	 *
	 * @param optTypeCode
	 * @param optTypeDesc
	 * @param optContent
	 */
	public OptLogDTO(String optTypeCode, String optTypeDesc, String optContent) {
		super();
		this.optTypeCode = optTypeCode;
		this.optTypeDesc = optTypeDesc;
		this.optContent = optContent;
	}

	/**
	 * optTypeCode
	 *
	 * @return  the optTypeCode
	 * @since   1.0.0
	 */
	
	public String getOptTypeCode() {
		return optTypeCode;
	}

	/**
	 * @param optTypeCode the optTypeCode to set
	 */
	public void setOptTypeCode(String optTypeCode) {
		this.optTypeCode = optTypeCode;
	}

	/**
	 * optTypeDesc
	 *
	 * @return  the optTypeDesc
	 * @since   1.0.0
	 */
	
	public String getOptTypeDesc() {
		return optTypeDesc;
	}

	/**
	 * @param optTypeDesc the optTypeDesc to set
	 */
	public void setOptTypeDesc(String optTypeDesc) {
		this.optTypeDesc = optTypeDesc;
	}

	/**
	 * optContent
	 *
	 * @return  the optContent
	 * @since   1.0.0
	 */
	
	public String getOptContent() {
		return optContent;
	}

	/**
	 * @param optContent the optContent to set
	 */
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	private static final long serialVersionUID = -8903962981743124766L;

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}
	
	public String toJsonString(){
		return JSONObject.fromObject(this).toString();
	}

}
