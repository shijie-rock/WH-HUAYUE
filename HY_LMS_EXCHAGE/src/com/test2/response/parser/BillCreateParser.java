/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response.parser
 * 文   件  名:BillCreateParser.java
 * 创 建日期:2018年10月19日-下午6:25:10
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
 * 类名称:BillCreateParser
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午6:25:10
 * 修改备注:
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
		//本地存库mina
//		int nimaDBId=TmExMsgPOFactory.insertResponse(response);
		//20190401-异步 发起 api请求。
		String minaMsgId=response.getId();
		String result=response.getResult();//执行结果，Success：成功，Error：失败
//暂时去掉
//		if(!HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(result)){
//			logger.warn("msg is error,退出执行线程");
//			return 0;
//		}
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
		TmExMsgPOFactory.updateApiReqMsg(apiDBId, executeResult, errorCode, errorMessage, responseData,HyLmsClientConstant.TOPIC_BUSI_BILL_CREATE);
		
		logger.debug("BillCreateParser end parse");
		
		return 1;
	}
//遗留问题：
//1：lms.trucking.sales.transportbill.transporttaskbill.create ，运力创建，需要提供接口给 .net。
//应用远程 到万华。
//2：关于：接到mina lms.message.trucking.sales.transportbill.transportconsignmentbill.cancel
//委托单取消业务，不需要发httpclient，需要人为创建一条消息，到：TM_EX_API_REC_MSG，TM_EX_API_REC_MSG_RESPONSE
//便于.net获取该记录后，触发.net的取消业务。
//3：缺少登录失败，重新登录的处理。--目前，放入session open 中。（即在服务端重启之后，session open 之后，会再次发起登录）。
	
}
