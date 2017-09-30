/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.test
 * 文   件  名:CustQrCodeBean.java
 * 创 建日期:2017年8月23日-下午8:49:57
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.io.Serializable;

/**
 * 类名称:CustQrCodeBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月23日 下午8:49:57
 * 修改备注:
 * @version 1.0.0
 */
public class CustQrCodeBean implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 3613868640798282069L;
	private Integer custId;
	private String name;
//	private String custType;
	private String custStatus;
	private Double custBalance;
//	private String codeType;
	private String valiTime;
	private String mobile;
	
	/**
	 * custId
	 *
	 * @return  the custId
	 * @since   1.0.0
	 */
	
	public Integer getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	/**
	 * name
	 *
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * custType
	 *
	 * @return  the custType
	 * @since   1.0.0
	 */
	
//	public String getCustType() {
//		return custType;
//	}
//	/**
//	 * @param custType the custType to set
//	 */
//	public void setCustType(String custType) {
//		this.custType = custType;
//	}
	/**
	 * custStatus
	 *
	 * @return  the custStatus
	 * @since   1.0.0
	 */
	
	public String getCustStatus() {
		return custStatus;
	}
	/**
	 * @param custStatus the custStatus to set
	 */
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	/**
	 * custBalance
	 *
	 * @return  the custBalance
	 * @since   1.0.0
	 */
	
	public Double getCustBalance() {
		return custBalance;
	}
	/**
	 * @param custBalance the custBalance to set
	 */
	public void setCustBalance(Double custBalance) {
		this.custBalance = custBalance;
	}
	/**
	 * codeType
	 *
	 * @return  the codeType
	 * @since   1.0.0
	 */
	
//	public String getCodeType() {
//		return codeType;
//	}
//	/**
//	 * @param codeType the codeType to set
//	 */
//	public void setCodeType(String codeType) {
//		this.codeType = codeType;
//	}
	/**
	 * valiTime
	 *
	 * @return  the valiTime
	 * @since   1.0.0
	 */
	
	public String getValiTime() {
		return valiTime;
	}
	/**
	 * @param valiTime the valiTime to set
	 */
	public void setValiTime(String valiTime) {
		this.valiTime = valiTime;
	}
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


}
