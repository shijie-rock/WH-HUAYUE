/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.msgfactory
 * 文   件  名:HyMessageFactory.java
 * 创 建日期:2018年10月18日-下午3:16:24
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.msgfactory;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.youbus.framework.comm.AppLog;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:HyMessageFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月18日 下午3:16:24
 * 修改备注:
 * @version 1.0.0
 */
public class HyMessageFactory {

	private static Logger appLog=AppLog.getInstance().getDELog();
	
	public static void main(String[] args) {
		
	}
	
	
	/**
	 * 生成一个消息的公共属性
	 * 方   法  名:getNewMessageBean
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:BaseRequestParamBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewMessageBean(){
		BaseRequestParamBean messageBean=new BaseRequestParamBean();
		messageBean.setId(HyLmsSignUtil.getNewMsgId());
		messageBean.setPublishAppKey(HyLmsClientConstant.APP_ID);
		messageBean.setPublishTime(DBConUtil.handleFormatDate(new Date(System.currentTimeMillis())));
		return messageBean;
	}
	
	/**
	 * 生成一个请求消息公共对象
	 * 方   法  名:getNewReqMessageBean
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:BaseRequestParamBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewReqMessageBean(){
		BaseRequestParamBean msgBean=getNewMessageBean();
		msgBean.setMessageType(HyLmsClientConstant.MSG_TYPE_REQ);
		return msgBean;
	}
	
	/**
	 * 生成一个应答消息公共对象
	 * 方   法  名:getNewRspMessageBean
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:BaseRequestParamBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static BaseRequestParamBean getNewRspMessageBean(){
		BaseRequestParamBean msgBean=getNewMessageBean();
		msgBean.setMessageType(HyLmsClientConstant.MSG_TYPE_RSP);
		return msgBean;
	}
	
	/**
	 * 生成登录消息对象
	 * 方   法  名:createSysLoginMsg
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:BaseRequestParamBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static BaseRequestParamBean createSysLoginMsg(){
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_SYS_LOGIN);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	/**
	 * 生成登出消息对象
	 * 方   法  名:createSysLogOffMsg
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:BaseRequestParamBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static BaseRequestParamBean createSysLogOffMsg(){
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_SYS_LOGOFF);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	
	public static BaseRequestParamBean createBillQueryMsg(String billNo){
		
		BaseRequestParamBean msgBean=getNewReqMessageBean();
		msgBean.setContent(null);
		msgBean.setTopic(HyLmsClientConstant.TOPIC_BUSI_BILL_QUERY);	
		String sign=HyLmsSignUtil.getSignFromMsgBean(msgBean);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		msgBean.setSign(sign);
		return msgBean;
	}
	
	
}
