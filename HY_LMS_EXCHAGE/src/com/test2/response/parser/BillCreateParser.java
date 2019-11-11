/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:BillCreateParser.java
 * �� ������:2018��10��19��-����6:25:10
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseResponseBean;
import com.test2.msgfactory.HyMessageHttpClientFactory;
import com.test2.response.HyResponseUtil;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * ������:BillCreateParser
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:25:10
 * �޸ı�ע:
 * @version 1.0.0
 */
public class BillCreateParser implements HyResponseParserInter {
	private static Logger logger=Logger.getLogger(BillCreateParser.class);
	/* (non-Javadoc)
	 * @see com.test2.response.parser.HyResponseParserInter#parseResponse(com.test2.dto.BaseResponseBean)
	 */
	@Override
	public int parseResponse(BaseResponseBean response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("BillCreateParser begin parse");
		//���ش��mina
//		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		//20190401-�첽 ���� api����
		String minaMsgId=response.getId();
		String result=response.getResult();//ִ�н����Success���ɹ���Error��ʧ��
//��ʱȥ��
//		if(!HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(result)){
//			logger.warn("msg is error,�˳�ִ���߳�");
//			return 0;
//		}
		String contentJson=response.getContent();
		if(StringUtils.isBlank(contentJson)){
			logger.warn("msg is empty,�˳�ִ���߳�");
			return 0;
		}
		logger.debug("��ʼ��������content:"+contentJson);
		
		String billNo=HyResponseUtil.getBillNoFromResponseContent(contentJson);
		if(StringUtils.isBlank(billNo)){
			logger.warn("billNo is empty,�˳�ִ���߳�");
			return 0;
		}
		List<ParamBean> paramList1=new ArrayList<ParamBean>();
		ParamBean paramBean=new ParamBean("no",billNo);paramList1.add(paramBean);
		String dataJson=HyLmsSignUtil.getApiRequestContentJson2(paramList1);//json�д���ת�Ʒ�
//		String dataJson=HyLmsSignUtil.getApiRequestContentJson(paramList1);
		
		//���ش�api request  
		List<ParamBean> paramList=HyMessageHttpClientFactory.getNewApiParamsBillQuery(dataJson);
		int apiDBId=TmExMsgPOFactory.insertApiReqMsg(minaMsgId, paramList);
		//����api�ӿڣ���ѯ�˵����������������ݣ�TM_EX_API_REC_MSG����Ӧ���ı��浽���أ�TM_EX_API_REC_MSG_RESPONSE����
		String apiResponseJson=HyMessageHttpClientFactory.sendJson(paramList);
		if(StringUtils.isBlank(apiResponseJson)){
			logger.warn("response json is empty,�˳�ִ���߳�");
			return 0;
		}
		JSONObject resJsonObj=JSONObject.fromObject(apiResponseJson);
//		if(HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(resJsonObj.getString("executeResult"))){
//		}
		String executeResult=resJsonObj.containsKey("executeResult")?resJsonObj.getString("executeResult"):"";
		String errorCode=resJsonObj.containsKey("errorCode")?resJsonObj.getString("errorCode"):"";
		String errorMessage=resJsonObj.containsKey("errorMessage")?resJsonObj.getString("errorMessage"):"";
		String responseData=resJsonObj.containsKey("data")?resJsonObj.getString("data"):"";
		TmExMsgPOFactory.updateApiReqMsg(apiDBId, executeResult, errorCode, errorMessage, responseData,HyLmsClientConstant.TOPIC_BUSI_BILL_CREATE);
		
		logger.debug("BillCreateParser end parse");
		
		return 1;
	}
//�������⣺
//1��lms.trucking.sales.transportbill.transporttaskbill.create ��������������Ҫ�ṩ�ӿڸ� .net��
//Ӧ��Զ�� ���򻪡�
//2�����ڣ��ӵ�mina lms.message.trucking.sales.transportbill.transportconsignmentbill.cancel
//ί�е�ȡ��ҵ�񣬲���Ҫ��httpclient����Ҫ��Ϊ����һ����Ϣ������TM_EX_API_REC_MSG��TM_EX_API_REC_MSG_RESPONSE
//����.net��ȡ�ü�¼�󣬴���.net��ȡ��ҵ��
//3��ȱ�ٵ�¼ʧ�ܣ����µ�¼�Ĵ���--Ŀǰ������session open �С������ڷ��������֮��session open ֮�󣬻��ٴη����¼����
	
}
