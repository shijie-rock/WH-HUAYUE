/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:AddMemberAction.java
 * �� ������:2017��9��2��-����9:24:53
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:AddMemberAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:24:53
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddMemberAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String memberCode=atx.getStringValue("MEMBER_CODE");
		String memberName=atx.getStringValue("MEMBER_NAME");
		String memberDesc=atx.getStringValue("MEMBER_DESC");
		String memberMobile=atx.getStringValue("MEMBER_MOBILE");
		String memberEmail=atx.getStringValue("MEMBER_EMAIL");
		String memberJob=atx.getStringValue("MEMBER_JOB");
		String memberSex=atx.getStringValue("MEMBER_SEX");
		String memberCertNo=atx.getStringValue("MEMBER_CERT_NO");
		String memberBirthday=atx.getStringValue("MEMBER_BIRTH_DAY");
		String isInspactor=atx.getStringValue("IS_INSPACTOR");
		String isLoginSys=atx.getStringValue("IS_LOGIN_SYS");
		String password=atx.getStringValue("PASSWORD");//need md5 check
		String passwordCfg=atx.getStringValue("PASSWORD_CFG");
		
		//check param
		if(StringUtils.isBlank(memberCode)||StringUtils.isBlank(memberName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_MEMBER_ADD_ACTION_ERR_1000", "����Ա��������Ϊ��", null);
			return 0;
		}
		
		if(StringUtils.isNotBlank(password)){//PASS IS NOT EMPTY
			if(!password.equals(passwordCfg)){
				logger.error(" PASSWORD NOT EQ PASSWORD_CFG .");
				atx.setErrorContext("ORGANIZE_INS_MEMBER_ADD_ACTION_ERR_1010", "����Ա����������ȷ�����벻һ��", null);
				return 0;
			}
		}else{//PASS IS EMPTY
			if(StringUtils.isNotBlank(passwordCfg)){
				logger.error(" PASSWORD_CFG IS NOT EMPTY.");
				atx.setErrorContext("ORGANIZE_INS_MEMBER_ADD_ACTION_ERR_1020", "����Ա����ȷ�����벻Ϊ��", null);
				return 0;
			}
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setMemberCode(memberCode);
		memberPOCon.setStatus("1");
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		if(memberPOResult!=null){
			logger.error(" MEMBER CODE EXIST ALREADY .");
			atx.setErrorContext("ORGANIZE_MEMBER_ADD_ACTION_ERR_2000", "����Ա����Ա������["+memberCode+"]�Ѿ�����", null);
			return 0;
		}
		
		memberPOCon.setCreateBy(optMemberId);
		memberPOCon.setCreateTime(YBUtility.now());
		memberPOCon.setFreezeTag("0");
		memberPOCon.setId(POFactory.getIntPriKey(conn, memberPOCon));
		memberPOCon.setVer(1);
		if(StringUtils.isNotBlank(memberBirthday))
		memberPOCon.setBirthday(DBConUtil.string2TimeYMD(memberBirthday));
		memberPOCon.setIsInspactor("1".equals(isInspactor)?"1":"0");
		memberPOCon.setCanLoginIns(memberPOCon.getIsInspactor());
		memberPOCon.setCanLoginSys("1".equals(isLoginSys)?"1":"0");
		memberPOCon.setCertNo(memberCertNo);
		memberPOCon.setEmail(memberEmail);
		
		if(StringUtils.isNotBlank(memberJob))
		memberPOCon.setJobTitelType(memberJob);
		
		memberPOCon.setMobile(memberMobile);
		memberPOCon.setSex(memberSex);
		
		if(StringUtils.isNotBlank(password)){
			memberPOCon.setPassword(DBConUtil.MD5Encode(password));
			memberPOCon.setHaveAcc("1");
		}else{
			memberPOCon.setHaveAcc("0");
		}
		
		memberPOCon.setMemberName(memberName);
		memberPOCon.setMemberRemark(memberDesc);

		POFactory.insert(conn, memberPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_MEMBER_ADD, "", "����Ա��");
		
		//set return msg
		atx.setStringValue("MSG", "����Ա��["+memberName+"]�ɹ�");
		return 1;
	}


}
