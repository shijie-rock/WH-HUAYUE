/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:EditMemberAction.java
 * 创 建日期:2017年9月2日-上午9:25:12
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
 * 类名称:EditMemberAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:25:12
 * 修改备注:
 * @version 1.0.0
 */
public class EditMemberAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String memberId=atx.getStringValue("MEMBER_ID");
		String memberName=atx.getStringValue("MEMBER_NAME");
		String memberMobile=atx.getStringValue("MEMBER_MOBILE");
		String memberEmail=atx.getStringValue("MEMBER_EMAIL");
		String memberJob=atx.getStringValue("MEMBER_JOB");
		String memberSex=atx.getStringValue("MEMBER_SEX");
		String memberCertNo=atx.getStringValue("MEMBER_CERT_NO");
		String memberBirthday=atx.getStringValue("MEMBER_BIRTH_DAY");
		String loginTag=atx.getStringValue("IS_LOGIN_SYS","0");//操作员
		String inspactTag=atx.getStringValue("IS_INSPACTOR","0");//是否检车员
		String memberDesc=atx.getStringValue("MEMBER_DESC","");
		String passwordCfg=atx.getStringValue("PASSWORD_CFG");
		String password=atx.getStringValue("PASSWORD");
		String memberCode=atx.getStringValue("MEMBER_CODE");
		
		//check param
		if(StringUtils.isBlank(memberId)||StringUtils.isBlank(memberName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_1000", "编辑用户：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(Integer.valueOf(memberId));
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOResult==null){
			logger.error(" MEMBER NOT EXIST .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_2000", "编辑用户：用户不存在", null);
			return 0;
		}
		
		if(StringUtils.isNotBlank(password)&&!password.equals(passwordCfg)){
			logger.error(" PASSWORD IS NOT EQ PASSWORD_CFG .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_3000", "处理用户密码：密码与确认密码不一致", null);
			return 0;
		}
		
		int version=memberPOResult.getVer();
		memberPOCon.setVer(version);

		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		
		memberPOValue.setUpdateBy(optMemberId);
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setVer(version+1);
		memberPOValue.setMemberName(memberName);
		memberPOValue.setMemberRemark(memberDesc);
		memberPOValue.setMobile(memberMobile);
		memberPOValue.setEmail(memberEmail);
		memberPOValue.setJobTitelType(memberJob);
		memberPOValue.setSex(memberSex);
		memberPOValue.setCertNo(memberCertNo);
		memberPOValue.setBirthday(DBConUtil.string2TimeYMD(memberBirthday));
		memberPOValue.setSysTag(loginTag);
		memberPOValue.setIsInspactor(inspactTag);
		memberPOValue.setCanLoginIns(inspactTag);
		
		if(StringUtils.isNotBlank(password))
		memberPOValue.setPassword(DBConUtil.MD5Encode(password));
		
		int excuteResult=POFactory.update(conn, memberPOCon, memberPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+memberId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_8000", "编辑用户：用户CODE["+memberCode+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_MEMBER_MOD, "", "编辑用户");
		
		//set return msg
		atx.setStringValue("MSG", "编辑用户：用户CODE["+memberCode+"]成功");
		return 1;
	}
}
