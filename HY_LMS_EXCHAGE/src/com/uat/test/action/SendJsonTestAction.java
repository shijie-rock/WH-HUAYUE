/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.uat.test.action
 * ��   ��  ��:SendJsonTestAction.java
 * �� ������:2018��10��24��-����5:13:06
 * Copyright @ 2018-YouBus.com.cn
 */
package com.uat.test.action;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.test2.msgfactory.HyMessageHttpClientFactory;

/**
 * ������:SendJsonTestAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��24�� ����5:13:06
 * �޸ı�ע:
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
			msg="������Ϊ��";
			atx.setStringValue("MSG", msg);
			return 1;
		}
		
		logger.debug("���������ģ�"+jsonRequest);
		String returnMsg=HyMessageHttpClientFactory.sendJson(jsonRequest);
		logger.debug("���Խ��ձ��ģ�"+returnMsg);
		atx.setStringValue("MSG", returnMsg);
		return 1;
	}

}
