/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response
 * 文   件  名:HyResponseUtil.java
 * 创 建日期:2018年10月19日-下午5:52:28
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseResponseBean;
import com.test2.response.parser.HyResponseParserInter;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * 类名称:HyResponseUtil
 * 类描述:处理接收的mina应答
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月19日 下午5:52:28
 * 修改备注:
 * @version 1.0.0
 */
public class HyResponseUtil {
	
	private static Logger logger=Logger.getLogger(HyResponseUtil.class);
	
	/**
	 * 将接收的应答mina字符串，转为BaseResponseBean 对象
	 * 方   法  名:getResponseBeanFromJson
	 * 方法描述:
	 * 参         数:@param responseJson
	 * 参         数:@return
	 * 返   回  值:BaseResponseBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseResponseBean getResponseBeanFromJson(String responseJson){
		
		JSONObject jsonObj=JSONObject.fromObject(responseJson);
		BaseResponseBean responseBean= (BaseResponseBean)JSONObject.toBean(jsonObj,BaseResponseBean.class);
		return responseBean;
	}
	
	public static void parseReceiveMsg(String responseJson){ //可改为线程方式
		if(StringUtils.isBlank(responseJson)){
			logger.warn("responseJson is empty.");
			return;
		}
		logger.debug("开始处理报文："+responseJson);
		BaseResponseBean resBean=getResponseBeanFromJson(responseJson);
		
		String msgTopic=resBean.getTopic();
		if(HyLmsClientConstant.RESPONSE_PARSER_MAP.containsKey(msgTopic)){
			String parserClassName=HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic);
			try {
				Class parseClass=Class.forName(parserClassName);
				HyResponseParserInter parser=(HyResponseParserInter)parseClass.newInstance();
				parser.parseResponse(resBean);
				logger.debug("结束处理报文："+responseJson);	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName not be class ");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance ");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parser error  ");
			}
			
			
		}else{
			logger.warn("msgTopic ["+msgTopic+"] parserClassName is not exist.");
			return;
		}
	}
	
	/**
	 * 从mina的response消息的content中，提取单号 ：billNo
	 * 方   法  名:getBillNoFromResponseContent
	 * 方法描述:
	 * 参         数:@param contentJson
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getBillNoFromResponseContent(String contentJson){
		JSONObject jsonObj=JSONObject.fromObject(contentJson);
		String billNo=jsonObj.getString("billNo");
		return billNo;
		
	}
	
}
