/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.sys.action
 * 文   件  名:GetNowTimeFromServerAction.java
 * 创 建日期:2018年5月22日-下午4:38:38
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;

/**
 * 类名称:GetNowTimeFromServerAction
 * 类描述:获取服务器时间
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月22日 下午4:38:38
 * 修改备注:
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
