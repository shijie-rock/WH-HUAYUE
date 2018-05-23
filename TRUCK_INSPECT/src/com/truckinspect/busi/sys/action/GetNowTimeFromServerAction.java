/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.sys.action
 * ��   ��  ��:GetNowTimeFromServerAction.java
 * �� ������:2018��5��22��-����4:38:38
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;

/**
 * ������:GetNowTimeFromServerAction
 * ������:��ȡ������ʱ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��22�� ����4:38:38
 * �޸ı�ע:
 * @version 1.0.0
 */
public class GetNowTimeFromServerAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
		atx.setStringValue("NOW_TIME", nowTime);
		return 1;
	}

}
