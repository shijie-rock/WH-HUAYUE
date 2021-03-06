/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response.parser
 * 文   件  名:BillUpdateParser.java
 * 创 建日期:2018年10月19日-下午6:26:26
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
 * 类名称:BillUpdateParser
 * 类描述:处理方式与 BillCreateParser 相同
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午6:26:26
 * 修改备注:
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
		logger.debug("BillUpdateParser begin parse");
		//调用api接口，查询运单，更新本地运单
		//本地存库mina
//		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		String minaMsgId=response.getId();
		String result=response.getResult();//执行结果，Success：成功，Error：失败
		
		String contentJson=response.getContent();
		if(StringUtils.isBlank(contentJson)){
			logger.warn("msg is empty,退出执行线程");
			return 0;
		}
		logger.debug("开始处理内容content:"+contentJson);
		
		String billNo=HyResponseUtil.getBillNoFromResponseContent(contentJson);
		if(StringUtils.isBlank(billNo)){
			logger.warn("billNo is empty,退出执行线程");
			return 0;
		}
		List<ParamBean> paramList1=new ArrayList<ParamBean>();
		ParamBean paramBean=new ParamBean("no",billNo);paramList1.add(paramBean);
		String dataJson=HyLmsSignUtil.getApiRequestContentJson2(paramList1);//json中带有转移符
//		String dataJson=HyLmsSignUtil.getApiRequestContentJson(paramList1);
		
		//本地存api request  
		List<ParamBean> paramList=HyMessageHttpClientFactory.getNewApiParamsBillQuery(dataJson);
		int apiDBId=TmExMsgPOFactory.insertApiReqMsg(minaMsgId, paramList);
		//调用api接口，查询运单，更新请求报文数据（TM_EX_API_REC_MSG），应答报文保存到本地（TM_EX_API_REC_MSG_RESPONSE）。
		String apiResponseJson=HyMessageHttpClientFactory.sendJson(paramList);
		if(StringUtils.isBlank(apiResponseJson)){
			logger.warn("response json is empty,退出执行线程");
			return 0;
		}
		JSONObject resJsonObj=JSONObject.fromObject(apiResponseJson);
//		if(HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(resJsonObj.getString("executeResult"))){
//		}
		String executeResult=resJsonObj.containsKey("executeResult")?resJsonObj.getString("executeResult"):"";
		String errorCode=resJsonObj.containsKey("errorCode")?resJsonObj.getString("errorCode"):"";
		String errorMessage=resJsonObj.containsKey("errorMessage")?resJsonObj.getString("errorMessage"):"";
		String responseData=resJsonObj.containsKey("data")?resJsonObj.getString("data"):"";
		TmExMsgPOFactory.updateApiReqMsg(apiDBId, executeResult, errorCode, errorMessage, responseData,HyLmsClientConstant.TOPIC_BUSI_BILL_UPDATE);
		
		logger.debug("BillUpdateParser end parse");
		
		return 1;
	}

}
