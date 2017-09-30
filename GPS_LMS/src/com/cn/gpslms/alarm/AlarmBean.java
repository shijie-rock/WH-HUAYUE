/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.alarm
 * ��   ��  ��:AlarmBean.java
 * �� ������:2016��9��3��-����4:44:47
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.alarm;

import java.io.Serializable;
import java.util.Date;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:AlarmBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��9��3�� ����4:44:47
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AlarmBean implements Serializable, DataBean {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -102109974169383445L;

	/**
	 * ����һ���µ�ʵ�� AlarmBean.
	 *
	 */
	public AlarmBean() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}
	
	private Date startTime;
	private String vehicleType;
	private Double speed;
	private String simNo;
	
	/**
	 * startTime
	 *
	 * @return  the startTime
	 * @since   1.0.0
	 */
	
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * vehicleType
	 *
	 * @return  the vehicleType
	 * @since   1.0.0
	 */
	
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * speed
	 *
	 * @return  the speed
	 * @since   1.0.0
	 */
	
	public Double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	/**
	 * simNo
	 *
	 * @return  the simNo
	 * @since   1.0.0
	*/
	
	public String getSimNo() {
		return simNo;
	}

	/**
	 * @param simNo the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

}
