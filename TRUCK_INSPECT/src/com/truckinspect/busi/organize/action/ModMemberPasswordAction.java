/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:ModMemberPasswordAction.java
 * 创 建日期:2017年11月7日-下午9:52:56
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:ModMemberPasswordAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年11月7日 下午9:52:56
 * 修改备注:
 * @version 1.0.0
 */
public class ModMemberPasswordAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String memberId=atx.getStringValue("MEMBER_ID",null);
		String password=atx.getStringValue("PASSWORD");//PASSWORD
		String passwordCfg=atx.getStringValue("PASSWORD_CFG");//PASSWORD confirm
		
		//check param
		if(StringUtils.isBlank(memberId)||"0".equals(memberId)||StringUtils.isBlank(password)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_1000", "处理用户密码：参数为空", null);
			return 0;
		}
		
		if(!password.equals(passwordCfg)){
			logger.error(" PASSWORD IS NOT EQ PASSWORD_CFG .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_2000", "处理用户密码：新密码与确认密码不一致", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(Integer.valueOf(memberId));
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOResult==null){
			logger.error(" MEMBER NOT EXIST .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_3000", "处理用户密码：用户不存在", null);
			return 0;
		}
		
		int version=memberPOResult.getVer();
		memberPOCon.setVer(version);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		memberPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setVer(version+1);
		memberPOValue.setPassword(YBUtility.getBASE64(password));
		
		int excuteResult=POFactory.update(conn, memberPOCon,memberPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+memberPOResult.getMemberName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_8000", "处理用户密码：数据["+memberPOResult.getMemberName()+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		
		String optTypeDesc="修改密码";
		String optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_PAS;
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "用户["+memberPOResult.getMemberName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "用户["+memberPOResult.getMemberName()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
