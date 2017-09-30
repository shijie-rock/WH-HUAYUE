/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.action
 * 文   件  名:SysLoginAction.java
 * 创 建日期:2017年4月16日-下午6:37:28
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.action;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.vehicle.common.CommonConstant;

/**
 * 类名称:SysLoginAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月16日 下午6:37:28
 * 修改备注:
 * @version 1.0.0
 */
public class SysLoginAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String name=atx.getStringValue("name");
		String pass=atx.getStringValue("pass");
		
		if(!CommonConstant.NAME.equals(name)){
			atx.setErrorContext("SYS_LOGIN_ACTION_0010", "用户名不存在",null);
			return 0;
		}
		if(!CommonConstant.PASS.equals(pass)){
			atx.setErrorContext("SYS_LOGIN_ACTION_0020", "密码错误",null);
			return 0;
		}
		
		atx.setStringValue("session_username", name);
		
		atx.setStringValue("MSG", "登录成功");
		return 1;
	}

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
