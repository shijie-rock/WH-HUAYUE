/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.msgfactory
 * ��   ��  ��:HyMessageFactory.java
 * �� ������:2018��10��18��-����3:16:24
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.msgfactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.test2.dto.BaseResponseBean;
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
	private static BaseResponseBean getNewRspMessageBean(){
		BaseResponseBean messageBean=new BaseResponseBean();
		messageBean.setId(HyLmsSignUtil.getNewMsgId());
		messageBean.setPublishAppKey(HyLmsClientConstant.APP_ID);
		messageBean.setPublishTime(DBConUtil.handleFormatDate(new Date(System.currentTimeMillis())));
		messageBean.setMessageType(HyLmsClientConstant.MSG_TYPE_RSP);
		return messageBean;
	}
	
	/**
	 * 
	 * ��   ��  ��:createConfirmResponseMsg
	 * ��������:
	 * ��         ��:@param requestMsgId :����ҪӦ�������id������˷�������Ϣid����
	 * ��         ��:@param result ִ�н����Success���ɹ���Error��ʧ��
	 * ��         ��:@param errCode ������� ������Ϣ
	 * ��         ��:@param errMsg
	 * ��         ��:@param content
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseResponseBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static BaseResponseBean createConfirmResponseMsg(String requestMsgId,String result,String errCode,String errMsg,String content){
		BaseResponseBean messageBean=getNewRspMessageBean();
		messageBean.setContent(content);
		messageBean.setErrorCode(errCode);
		messageBean.setErrorMessage(errMsg);
		messageBean.setRequestMessageId(requestMsgId);
		messageBean.setResult(result);
		messageBean.setTopic(HyLmsClientConstant.TOPIC_BUSI_MSG_CONFIRM);
		
		String sign=HyLmsSignUtil.getSignFromMsgBean(messageBean);
		
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		messageBean.setSign(sign);
		
		return messageBean;
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
	
	/**
	 * �����˷���Ӧ���ִ
	 * ��   ��  ��:sendResponseConfirmMsg
	 * ��������:
	 * ��         ��:@param requestMsgId
	 * ��         ��:@param result
	 * ��         ��:@param errCode
	 * ��         ��:@param errMsg
	 * ��         ��:@param content
	 * ��         ��:@throws IllegalAccessException
	 * ��         ��:@throws IllegalArgumentException
	 * ��         ��:@throws InvocationTargetException
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void sendResponseConfirmMsg(String requestMsgId,String result,String errCode,String errMsg,String content) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		IoSession session=ClientHelper.getInstance().getSESSION_CHANNAL();
		//��װӦ����Ϣ
		BaseResponseBean clientResBean=HyMessageFactory.createConfirmResponseMsg(requestMsgId, result, errCode, errMsg, content);
		appLog.debug("׼������ confirm Ӧ��:json="+JSONObject.fromObject(clientResBean).toString());
		//��Ϣ����
		if(session!=null){
			session.write(HyLmsSignUtil.getRequestBeanJson(clientResBean));
			appLog.debug("confirm Ӧ���ѷ���");
			//��Ϣ���
			TmExMsgPOFactory.insertResponse(clientResBean);
			appLog.debug("confirm Ӧ���¼�����ݿ�");
		}

	}
}
