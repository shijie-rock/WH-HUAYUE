/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.msgfactory
 * ��   ��  ��:HyMessageFactory.java
 * �� ������:2018��10��18��-����3:16:24
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.msgfactory;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.youbus.framework.comm.AppLog;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:HyMessageFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��18�� ����3:16:24
 * �޸ı�ע:
 * @version 1.0.0
 */
public class HyMessageFactory {

	private static Logger appLog=AppLog.getInstance().getDELog();
	
	public static void main(String[] args) {
		
	}
	
	
	/**
	 * ����һ����Ϣ�Ĺ�������
	 * ��   ��  ��:getNewMessageBean
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseRequestParamBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewMessageBean(){
		BaseRequestParamBean messageBean=new BaseRequestParamBean();
		messageBean.setId(HyLmsSignUtil.getNewMsgId());
		messageBean.setPublishAppKey(HyLmsClientConstant.APP_ID);
		messageBean.setPublishTime(DBConUtil.handleFormatDate(new Date(System.currentTimeMillis())));
		return messageBean;
	}
	
	/**
	 * ����һ��������Ϣ��������
	 * ��   ��  ��:getNewReqMessageBean
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseRequestParamBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewReqMessageBean(){
		BaseRequestParamBean msgBean=getNewMessageBean();
		msgBean.setMessageType(HyLmsClientConstant.MSG_TYPE_REQ);
		return msgBean;
	}
	
	/**
	 * ����һ��Ӧ����Ϣ��������
	 * ��   ��  ��:getNewRspMessageBean
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseRequestParamBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewRspMessageBean(){
		BaseRequestParamBean msgBean=getNewMessageBean();
		msgBean.setMessageType(HyLmsClientConstant.MSG_TYPE_RSP);
		return msgBean;
	}
	
	/**
	 * ���ɵ�¼��Ϣ����
	 * ��   ��  ��:createSysLoginMsg
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseRequestParamBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static BaseRequestParamBean createSysLoginMsg(){
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_SYS_LOGIN);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	/**
	 * ���ɵǳ���Ϣ����
	 * ��   ��  ��:createSysLogOffMsg
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseRequestParamBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static BaseRequestParamBean createSysLogOffMsg(){
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_SYS_LOGOFF);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	
	public static BaseRequestParamBean createBillQueryMsg(String billNo){
		
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_BUSI_BILL_QUERY);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	
	
}
