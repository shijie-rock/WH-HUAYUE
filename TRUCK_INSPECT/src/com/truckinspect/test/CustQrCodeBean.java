/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.test
 * ��   ��  ��:CustQrCodeBean.java
 * �� ������:2017��8��23��-����8:49:57
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.io.Serializable;

/**
 * ������:CustQrCodeBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��23�� ����8:49:57
 * �޸ı�ע:
 * @version 1.0.0
 */
public class CustQrCodeBean implements Serializable {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
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
