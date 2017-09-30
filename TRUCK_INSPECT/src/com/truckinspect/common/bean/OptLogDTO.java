/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.bean
 * 文   件  名:OptLogDTO.java
 * 创 建日期:2017年8月16日-上午11:15:08
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common.bean;

import net.sf.json.JSONObject;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:OptLogDTO
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月16日 上午11:15:08
 * 修改备注:
 * @version 1.0.0
 */
public class OptLogDTO implements DataBean {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private String optTypeCode;
	private String optTypeDesc;
	private String optContent;
	
	
	/**
	 * 创建一个新的实例 OptLogDTO.
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
