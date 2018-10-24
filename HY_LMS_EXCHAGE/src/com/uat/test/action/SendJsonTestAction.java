/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.uat.test.action
 * 文   件  名:SendJsonTestAction.java
 * 创 建日期:2018年10月24日-下午5:13:06
 * Copyright @ 2018-YouBus.com.cn
 */
package com.uat.test.action;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.test2.msgfactory.HyMessageHttpClientFactory;

/**
 * 类名称:SendJsonTestAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月24日 下午5:13:06
 * 修改备注:
 * @version 1.0.0
 */
public class SendJsonTestAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String jsonRequest=atx.getStringValue("JSON_MSG");
		String msg=null;
		if(StringUtils.isBlank(jsonRequest)){
			msg="请求报文为空";
			atx.setStringValue("MSG", msg);
			return 1;
		}
		
		logger.debug("测试请求报文："+jsonRequest);
		String returnMsg=HyMessageHttpClientFactory.sendJson(jsonRequest);
		logger.debug("测试接收报文："+returnMsg);
		atx.setStringValue("MSG", returnMsg);
		return 1;
	}

}
