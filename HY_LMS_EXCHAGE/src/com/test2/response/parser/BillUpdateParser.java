/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:BillUpdateParser.java
 * �� ������:2018��10��19��-����6:26:26
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.dto.BaseResponseBean;

/**
 * ������:BillUpdateParser
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:26:26
 * �޸ı�ע:
 * @version 1.0.0
 */
public class BillUpdateParser implements HyResponseParserInter {
	private static Logger logger=Logger.getLogger(BillUpdateParser.class);
	/* (non-Javadoc)
	 * @see com.test2.response.parser.HyResponseParserInter#parseResponse(com.test2.dto.BaseResponseBean)
	 */
	@Override
	public int parseResponse(BaseResponseBean response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("begin parse");
		//����api�ӿڣ���ѯ�˵������±����˵�
		//���ش��mina
		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//ִ�н����Success���ɹ���Error��ʧ��
		
		return 0;
	}

}
