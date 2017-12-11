/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.system.action
 * 文   件  名:LoginAction.java
 * 创 建日期:2017年12月5日-下午3:50:58
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.system.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBCommonContant;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:LoginAction
 * 类描述:用户系统登录
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年12月5日 下午3:50:58:暂时1,1
 * 修改备注:
 * @version 1.0.0
 */
public class LoginAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String pass=atx.getStringValue("PASS");
		String name=atx.getStringValue("NAME");
		
		if(StringUtils.isBlank(pass)||StringUtils.isBlank(name)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("LOGIN_ACTION_ERR_1000", "系统登录：用户名和密码不能为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setMemberCode(name);
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOResult==null){
			logger.error(" EMP NOT EXIST .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2000", "系统登录：用户不存在", null);
			return 0;
		}
		
		if("1".equals(memberPOResult.getFreezeTag())){
			logger.error(" EMP HAS BEEN FREEZEN .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2001", "系统登录：用户已被冻结", null);
			return 0;
		}
		
		if(!DBConUtil.MD5Encode(pass).equals(memberPOResult.getPassword())){
			logger.error(" EMP PASSWORD IS ERROR .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2002", "系统登录：密码错误", null);
			return 0;
		}
		if(!"1".equals(memberPOResult.getCanLoginSys())){
			logger.error(" EMP CANNOT LOGIN .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2003", "系统登录：用户不能登录系统", null);
			return 0;
		}
		
		// 验证通过，将用户信息放入SESSION
		String sessionId=atx.getStringValue(FrameworkConstant.SESSION_ID);
		atx.setStringValue(YBCommonContant.SESSION_ID, sessionId);
		atx.setStringValue(YBCommonContant.SESSION_USER_ID, String.valueOf(memberPOResult.getId())); //TM_SYS_MEMBER id
		atx.setStringValue(YBCommonContant.SESSION_USER_NAME,memberPOResult.getMemberName()); //TM_SYS_MEMBER MEMBER_NAME
		atx.setStringValue(YBCommonContant.SESSION_USER_CODE,memberPOResult.getMemberCode()); //TM_SYS_MEMBER MEMBER_CODE
		atx.setObjValue(YBCommonContant.SESSION_LOGIN_USER,memberPOResult); //TM_SYS_MEMBER PO
		
		//更新登录信息
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		memberPOValue.setUpdateBy(optMemberId);
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setLastLoginTime(YBUtility.now());
		memberPOValue.setOnlineStatus("1");
		
		memberPOCon.setId(memberPOResult.getId());
		
		POFactory.update(conn, memberPOCon, memberPOValue);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_LOGIN, "", "用户["+memberPOResult.getMemberName()+"]登录系统");
		
		//set return msg
		atx.setStringValue("MSG", "用户["+optMemberName+"]登录成功");
		
		return 1;
	}

}
