/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:ErrorParser.java
 * �� ������:2018��10��19��-����6:26:10
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import org.apache.log4j.Logger;

import com.test2.dto.BaseResponseBean;

/**
 * ������:ErrorParser
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:26:10
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ErrorParser implements HyResponseParserInter {
	private static Logger logger=Logger.getLogger(ErrorParser.class);
	/* (non-Javadoc)
	 * @see com.test2.response.parser.HyResponseParserInter#parseResponse(com.test2.dto.BaseResponseBean)
	 */
	@Override
	public int parseResponse(BaseResponseBean response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("ErrorParser begin parse");
		
		//���ش��mina
//		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//ִ�н����Success���ɹ���Error��ʧ��
		
		logger.debug("ErrorParser end parse");
		
		return 1;
	}

}
