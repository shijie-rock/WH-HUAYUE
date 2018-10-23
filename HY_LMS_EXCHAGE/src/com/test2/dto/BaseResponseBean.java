/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.dto
 * 文   件  名:BaseResponseBean.java
 * 创 建日期:2018年10月19日-下午6:12:47
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.dto;

import java.io.Serializable;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:BaseResponseBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午6:12:47
 * 修改备注:
 * @version 1.0.0
 */
public class BaseResponseBean implements DataBean, Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -2864191337174302768L;

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	private String id;
	private String topic;
	private String publishAppKey;
	private String publishTime;//yyyy-MM-dd HH:mi:ss
	private String messageType;//消息类型，Request 请求消息，Response 响应消息
	private String requestMessageId;//请求消息ID
	private String result;//执行结果，Success：成功，Error：失败
	
	private String errorCode;//错误编码
	private String errorMessage;//错误信息
	private String sign;//签名
	private String content;//消息内容，json字符串
	
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
	 * requestMessageId
	 *
	 * @return  the requestMessageId
	 * @since   1.0.0
	 */
	
	public String getRequestMessageId() {
		return requestMessageId;
	}



	/**
	 * @param requestMessageId the requestMessageId to set
	 */
	public void setRequestMessageId(String requestMessageId) {
		this.requestMessageId = requestMessageId;
	}



	/**
	 * result
	 *
	 * @return  the result
	 * @since   1.0.0
	 */
	
	public String getResult() {
		return result;
	}



	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * errorCode
	 *
	 * @return  the errorCode
	 * @since   1.0.0
	 */
	
	public String getErrorCode() {
		return errorCode;
	}



	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	/**
	 * errorMessage
	 *
	 * @return  the errorMessage
	 * @since   1.0.0
	 */
	
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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



	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}

}
