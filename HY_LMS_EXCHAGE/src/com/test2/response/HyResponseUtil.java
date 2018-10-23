/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response
 * ��   ��  ��:HyResponseUtil.java
 * �� ������:2018��10��19��-����5:52:28
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseResponseBean;
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
		
		String msgTopic=resBean.getTopic();
		if(HyLmsClientConstant.RESPONSE_PARSER_MAP.containsKey(msgTopic)){
			String parserClassName=HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic);
			try {
				Class parseClass=Class.forName(parserClassName);
				HyResponseParserInter parser=(HyResponseParserInter)parseClass.newInstance();
				parser.parseResponse(resBean);
				logger.debug("���������ģ�"+responseJson);	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName not be class ");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance ");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parser error  ");
			}
			
			
		}else{
			logger.warn("msgTopic ["+msgTopic+"] parserClassName is not exist.");
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
		String billNo=jsonObj.getString("billNo");
		return billNo;
		
	}
	
}
