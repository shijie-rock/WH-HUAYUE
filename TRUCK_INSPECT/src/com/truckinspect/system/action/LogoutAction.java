/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.system.action
 * 文   件  名:LogoutAction.java
 * 创 建日期:2017年12月5日-下午3:52:41
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.system.action;

import java.sql.Connection;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBCommonContant;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:LogoutAction
 * 类描述:用户系统登出
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年12月5日 下午3:52:41
 * 修改备注:
 * @version 1.0.0
 */
public class LogoutAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		//更新登录信息
		Integer optMemberId=YBUtility.getMemberId(atx);
		if(optMemberId==null){
			logger.error(" 无用户信息登出 .");
			return 1;
		}
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		memberPOValue.setUpdateBy(optMemberId);
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setOnlineStatus("0");
		
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(optMemberId);
		
		Connection conn=atx.getConnection();
		POFactory.update(conn, memberPOCon, memberPOValue);
		
		// 将用户信息清除SESSION
		atx.setStringValue(YBCommonContant.SESSION_ID, null);
		atx.setStringValue(YBCommonContant.SESSION_USER_ID, null); //TM_SYS_MEMBER id
		atx.setStringValue(YBCommonContant.SESSION_USER_NAME,null); //TM_SYS_MEMBER MEMBER_NAME
		atx.setStringValue(YBCommonContant.SESSION_USER_CODE,null); //TM_SYS_MEMBER MEMBER_CODE
		atx.setObjValue(YBCommonContant.SESSION_LOGIN_USER,null); //TM_SYS_MEMBER PO
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_LOGIN, "", "用户["+optMemberName+"]登出系统");
		
		//set return msg
		atx.setStringValue("MSG", "用户["+optMemberName+"]登出成功");
		
		return 1;
	}

}
