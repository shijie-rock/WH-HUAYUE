/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:EditMemberAction.java
 * �� ������:2017��9��2��-����9:25:12
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
 * ������:EditMemberAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:25:12
 * �޸ı�ע:
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
		String loginTag=atx.getStringValue("IS_LOGIN_SYS","0");//����Ա
		String inspactTag=atx.getStringValue("IS_INSPACTOR","0");//�Ƿ�쳵Ա
		String memberDesc=atx.getStringValue("MEMBER_DESC","");
		String passwordCfg=atx.getStringValue("PASSWORD_CFG");
		String password=atx.getStringValue("PASSWORD");
		String memberCode=atx.getStringValue("MEMBER_CODE");
		
		//check param
		if(StringUtils.isBlank(memberId)||StringUtils.isBlank(memberName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_1000", "�༭�û�������Ϊ��", null);
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
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_2000", "�༭�û����û�������", null);
			return 0;
		}
		
		if(StringUtils.isNotBlank(password)&&!password.equals(passwordCfg)){
			logger.error(" PASSWORD IS NOT EQ PASSWORD_CFG .");
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_3000", "�����û����룺������ȷ�����벻һ��", null);
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
			atx.setErrorContext("ORGANIZE_MEMBER_EDIT_ACTION_ERR_8000", "�༭�û����û�CODE["+memberCode+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_MEMBER_MOD, "", "�༭�û�");
		
		//set return msg
		atx.setStringValue("MSG", "�༭�û����û�CODE["+memberCode+"]�ɹ�");
		return 1;
	}
}
