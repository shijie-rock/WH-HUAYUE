/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response.parser
 * ��   ��  ��:HyResponseParserInter.java
 * �� ������:2018��10��19��-����6:00:00
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import com.test2.dto.BaseResponseBean;

/**
 * ������:HyResponseParserInter
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��19�� ����6:00:00
 * �޸ı�ע:
 * @version 1.0.0
 */
public interface HyResponseParserInter {

	public int parseResponse(BaseResponseBean response)throws Exception;
	
}
