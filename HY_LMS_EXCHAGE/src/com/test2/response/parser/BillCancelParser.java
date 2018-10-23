/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response.parser
 * 文   件  名:BillCancelParser.java
 * 创 建日期:2018年10月19日-下午6:26:44
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.dto.BaseResponseBean;

/**
 * 类名称:BillCancelParser
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午6:26:44
 * 修改备注:
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
		logger.debug("begin parse");
		//取消本地订单
		
		//本地存库mina
		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//执行结果，Success：成功，Error：失败
		
		
		return 0;
	}

}
