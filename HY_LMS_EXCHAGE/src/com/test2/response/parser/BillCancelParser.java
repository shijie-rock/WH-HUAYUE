/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:BillCancelParser.java
 * �� ������:2018��10��19��-����6:26:44
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseResponseBean;
import com.test2.response.HyResponseUtil;

/**
 * ������:BillCancelParser
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:26:44
 * �޸ı�ע:
 * @version 1.0.0
 */
public class BillCancelParser implements HyResponseParserInter {
	private static Logger logger=Logger.getLogger(BillCancelParser.class);
	/* (non-Javadoc)
	 * @see com.test2.response.parser.HyResponseParserInter#parseResponse(com.test2.dto.BaseResponseBean)
	 */
	@Override
	public int parseResponse(BaseResponseBean response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("BillCancelParser begin parse");
		//ȡ�����ض���
		
		//���ش��mina
//		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//ִ�н����Success���ɹ���Error��ʧ��
		
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
		
		TmExMsgPOFactory.addRecMsgResponseContent(billNo, HyLmsClientConstant.TOPIC_BUSI_BILL_CANCEL);
		
		logger.debug("BillCancelParser end parse");
		
		return 1;
	}

}
