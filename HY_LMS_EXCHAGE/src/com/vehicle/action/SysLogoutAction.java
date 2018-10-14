/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.action
 * 文   件  名:SysLogoutAction.java
 * 创 建日期:2017年4月16日-下午9:02:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.action;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;

/**
 * 类名称:SysLogoutAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月16日 下午9:02:18
 * 修改备注:
 * @version 1.0.0
 */
public class SysLogoutAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		atx.setStringValue("session_username",null);
		
		atx.setStringValue("MSG", "登录成功");
		return 1;
		
	}

}
