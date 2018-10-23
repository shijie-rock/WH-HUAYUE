/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:LoginParser.java
 * �� ������:2018��10��19��-����6:25:42
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseResponseBean;

/**
 * ������:LoginParser
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:25:42
 * �޸ı�ע:
 * @version 1.0.0
 */
public class LoginParser implements HyResponseParserInter {
	private static Logger logger=Logger.getLogger(LoginParser.class);
	/* (non-Javadoc)
	 * @see com.test2.response.parser.HyResponseParserInter#parseResponse(com.test2.dto.BaseResponseBean)
	 */
	@Override
	public int parseResponse(BaseResponseBean response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("begin parse");
		
		//���ش��mina
		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//ִ�н����Success���ɹ���Error��ʧ��
		if(!HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(result)){
			logger.warn("msg is error,�˳�ִ���߳�");
			return 0;
		}else{
			//��¼�ɹ���������Ŀ����������Ҫ�ٷ��͵�¼mina�ӿ�
			HyLmsClientConstant.IS_SYS_LOGIN_IN=true;
		}
		
		return 1;
	}

}
