/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response
 * ��   ��  ��:HyResponseUtil.java
 * �� ������:2018��10��19��-����5:52:28
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
import com.test2.msgfactory.HyMessageFactory;
import com.test2.response.parser.HyResponseParserInter;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * ������:HyResponseUtil
 * ������:������յ�minaӦ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����5:52:28
 * �޸ı�ע:
 * @version 1.0.0
 */
public class HyResponseUtil {
	
	private static Logger logger=Logger.getLogger(HyResponseUtil.class);
	
	/**
	 * �����յ�Ӧ��mina�ַ�����תΪBaseResponseBean ����
	 * ��   ��  ��:getResponseBeanFromJson
	 * ��������:
	 * ��         ��:@param responseJson
	 * ��         ��:@return
	 * ��   ��  ֵ:BaseResponseBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseResponseBean getResponseBeanFromJson(String responseJson){
		
		JSONObject jsonObj=JSONObject.fromObject(responseJson);
		BaseResponseBean responseBean= (BaseResponseBean)JSONObject.toBean(jsonObj,BaseResponseBean.class);
		return responseBean;
	}
	
	public static void parseReceiveMsg(String responseJson){ //�ɸ�Ϊ�̷߳�ʽ
		if(StringUtils.isBlank(responseJson)){
			logger.warn("responseJson is empty.");
			return;
		}
		logger.debug("��ʼ�����ģ�"+responseJson);
		BaseResponseBean resBean=getResponseBeanFromJson(responseJson);
		logger.debug("resBean:="+resBean.toXMLString());
		String msgTopic=resBean.getTopic();
		String msgId=resBean.getId();
		logger.debug("resBean msgTopic:="+resBean.getTopic());
//		logger.debug("parserClassName :="+HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic));
		
		boolean needSendConfirmTag=true;
		
		//����� ����� ������ ��¼���ǳ���Ӧ����Ϣ,�������������Ϣ������Ҫ��confirm��Ϣ��������
		if(HyLmsClientConstant.TOPIC_SYS_LOGIN_RES.equals(msgTopic)||HyLmsClientConstant.TOPIC_SYS_LOGOFF_RES.equals(msgTopic)
				||HyLmsClientConstant.TOPIC_BUSI_MSG_ERROR.equals(msgTopic)){
			needSendConfirmTag=false;
		}
		//20190401-��Ϊֻ��⣬���첽 ���� api����
		int nimaDBId=TmExMsgPOFactory.insertNeedParseResponse(resBean);
//		int nimaDBId=TmExMsgPOFactory.insertResponse(resBean);
		logger.debug(" ��Ϣ���ش�� ��"+responseJson);	
		if(HyLmsClientConstant.RESPONSE_PARSER_MAP.containsKey(msgTopic)){
//			String parserClassName=HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic);
			try {
				//20190401-��Ϊֻ��⣬���첽 ���� api����תΪ schedule TmExMsgPOFactory parseDBMinaMsg ����
				/*
				logger.debug("����Ӧ����Ϣ����="+parserClassName);
				Class parseClass=Class.forName(parserClassName);
				HyResponseParserInter parser=(HyResponseParserInter)parseClass.newInstance();
				parser.parseResponse(resBean);
				logger.debug("���������ģ�"+responseJson);	
				*/
				
				//���� �ɹ� confirm Ӧ��������
//				BaseResponseBean clientResBean=HyMessageFactory.createConfirmResponseMsg(msgId, HyLmsClientConstant.MSG_RESULT_SUCCESS, null, null, null);
				if(needSendConfirmTag)
				HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_SUCCESS, null, null, null);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.error("parserClassName not be class "+e.getMessage());
//			} catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.error("parserClassName can't instance "+e.getMessage());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance "+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parser error  "+e.getMessage());
				
				//���� ����ʧ�� confirm Ӧ��������
//				BaseResponseBean clientResBean=HyMessageFactory.createConfirmResponseMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S999", "�����쳣��Ϣ", null);
				/* 20190322-����ʧ�ܣ����ٷ�ȷ����Ϣ
				try {
					if(needSendConfirmTag)
					HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S999", "δ�������+�����쳣��Ϣ", null);
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				}
				*/
			}
			
		}else{
			logger.warn("msgTopic ["+msgTopic+"] parserClassName is not exist.");
			//���� ����ʧ�� confirm Ӧ��������
			//S101 ��Ϣ���ⲻ����
			//��Ϣ����
			try {
				if(needSendConfirmTag)
				HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S101", "��Ϣ���ⲻ����", null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" send response error  ");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" send response error  ");
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				logger.error(" send response error  ");
			}
			
			return;
		}
	}
	
	/**
	 * ��mina��response��Ϣ��content�У���ȡ���� ��billNo
	 * ��   ��  ��:getBillNoFromResponseContent
	 * ��������:
	 * ��         ��:@param contentJson
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getBillNoFromResponseContent(String contentJson){
		JSONObject jsonObj=JSONObject.fromObject(contentJson);
		if(jsonObj.containsKey("billNo")){
			String billNo=jsonObj.getString("billNo");
			return billNo;
		}
		logger.error("���ݲ���billNo,�޷���ȡ����");
		return null;
		
	}
	
}
