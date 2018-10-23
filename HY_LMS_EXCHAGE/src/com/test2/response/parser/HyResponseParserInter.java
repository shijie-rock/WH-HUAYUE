/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response.parser
 * 文   件  名:HyResponseParserInter.java
 * 创 建日期:2018年10月19日-下午6:00:00
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response.parser;

import com.test2.dto.BaseResponseBean;

/**
 * 类名称:HyResponseParserInter
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午6:00:00
 * 修改备注:
 * @version 1.0.0
 */
public interface HyResponseParserInter {

	public int parseResponse(BaseResponseBean response)throws Exception;
	
}
