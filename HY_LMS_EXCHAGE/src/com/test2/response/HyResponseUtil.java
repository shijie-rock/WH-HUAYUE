/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response
 * 文   件  名:HyResponseUtil.java
 * 创 建日期:2018年10月19日-下午5:52:28
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.test2.dto.BaseResponseBean;
import com.test2.msgfactory.HyMessageFactory;
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
		logger.debug("resBean:="+resBean.toXMLString());
		String msgTopic=resBean.getTopic();
		String msgId=resBean.getId();
		logger.debug("resBean msgTopic:="+resBean.getTopic());
//		logger.debug("parserClassName :="+HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic));
		
		boolean needSendConfirmTag=true;
		
		//如果是 服务端 发出的 登录，登出的应答消息,服务器报错的消息，则不需要发confirm消息给服务器
		if(HyLmsClientConstant.TOPIC_SYS_LOGIN_RES.equals(msgTopic)||HyLmsClientConstant.TOPIC_SYS_LOGOFF_RES.equals(msgTopic)
				||HyLmsClientConstant.TOPIC_BUSI_MSG_ERROR.equals(msgTopic)){
			needSendConfirmTag=false;
		}
		//20190401-改为只存库，再异步 发起 api请求。
		int nimaDBId=TmExMsgPOFactory.insertNeedParseResponse(resBean);
//		int nimaDBId=TmExMsgPOFactory.insertResponse(resBean);
		logger.debug(" 消息本地存库 ："+responseJson);	
		if(HyLmsClientConstant.RESPONSE_PARSER_MAP.containsKey(msgTopic)){
//			String parserClassName=HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic);
			try {
				//20190401-改为只存库，再异步 发起 api请求。转为 schedule TmExMsgPOFactory parseDBMinaMsg 处理
				/*
				logger.debug("进入应答消息处理：="+parserClassName);
				Class parseClass=Class.forName(parserClassName);
				HyResponseParserInter parser=(HyResponseParserInter)parseClass.newInstance();
				parser.parseResponse(resBean);
				logger.debug("结束处理报文："+responseJson);	
				*/
				
				//发送 成功 confirm 应答给服务端
//				BaseResponseBean clientResBean=HyMessageFactory.createConfirmResponseMsg(msgId, HyLmsClientConstant.MSG_RESULT_SUCCESS, null, null, null);
				if(needSendConfirmTag)
				HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_SUCCESS, null, null, null);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.error("parserClassName not be class "+e.getMessage());
//			} catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.error("parserClassName can't instance "+e.getMessage());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parserClassName can't instance "+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("parser error  "+e.getMessage());
				
				//发送 处理失败 confirm 应答给服务端
//				BaseResponseBean clientResBean=HyMessageFactory.createConfirmResponseMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S999", "具体异常信息", null);
				/* 20190322-处理失败，不再发确认消息
				try {
					if(needSendConfirmTag)
					HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S999", "未定义错误+具体异常信息", null);
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error(" send response error  ");
				}
				*/
			}
			
		}else{
			logger.warn("msgTopic ["+msgTopic+"] parserClassName is not exist.");
			//发送 处理失败 confirm 应答给服务端
			//S101 消息主题不存在
			//消息发送
			try {
				if(needSendConfirmTag)
				HyMessageFactory.sendResponseConfirmMsg(msgId, HyLmsClientConstant.MSG_RESULT_ERROR, "S101", "消息主题不存在", null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" send response error  ");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" send response error  ");
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				logger.error(" send response error  ");
			}
			
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
		if(jsonObj.containsKey("billNo")){
			String billNo=jsonObj.getString("billNo");
			return billNo;
		}
		logger.error("内容不含billNo,无法获取单号");
		return null;
		
	}
	
}
