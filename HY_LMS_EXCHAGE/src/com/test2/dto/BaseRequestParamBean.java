/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.dto
 * ��   ��  ��:BaseRequestParamBean.java
 * �� ������:2018��10��17��-����2:53:14
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.dto;

import java.io.Serializable;

import com.infoservice.po.DataBean;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:BaseRequestParamBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��17�� ����2:53:14
 * �޸ı�ע:
 * @version 1.0.0
 */
public class BaseRequestParamBean implements DataBean,Serializable {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -4120153504204589372L;
	
	private String id;
	private String topic;
	private String publishAppKey;
	private String publishTime;//yyyy-MM-dd HH:mi:ss
	private String messageType;//��Ϣ���ͣ�Request ������Ϣ��Response ��Ӧ��Ϣ
	private String sign;//ǩ��
	private String content;//��Ϣ���ݣ�json�ַ���
	/**
	 * id
	 *
	 * @return  the id
	 * @since   1.0.0
	 */
	
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * topic
	 *
	 * @return  the topic
	 * @since   1.0.0
	 */
	
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * publishAppKey
	 *
	 * @return  the publishAppKey
	 * @since   1.0.0
	 */
	
	public String getPublishAppKey() {
		return publishAppKey;
	}
	/**
	 * @param publishAppKey the publishAppKey to set
	 */
	public void setPublishAppKey(String publishAppKey) {
		this.publishAppKey = publishAppKey;
	}
	/**
	 * publishTime
	 *
	 * @return  the publishTime
	 * @since   1.0.0
	 */
	
	public String getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * messageType
	 *
	 * @return  the messageType
	 * @since   1.0.0
	 */
	
	public String getMessageType() {
		return messageType;
	}
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	/**
	 * sign
	 *
	 * @return  the sign
	 * @since   1.0.0
	 */
	
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * content
	 *
	 * @return  the content
	 * @since   1.0.0
	 */
	
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
