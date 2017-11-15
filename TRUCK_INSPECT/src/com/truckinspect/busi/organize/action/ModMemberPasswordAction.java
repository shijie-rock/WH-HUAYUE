/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:ModMemberPasswordAction.java
 * �� ������:2017��11��7��-����9:52:56
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
 * ������:ModMemberPasswordAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��11��7�� ����9:52:56
 * �޸ı�ע:
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
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_1000", "�����û����룺����Ϊ��", null);
			return 0;
		}
		
		if(!password.equals(passwordCfg)){
			logger.error(" PASSWORD IS NOT EQ PASSWORD_CFG .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_2000", "�����û����룺��������ȷ�����벻һ��", null);
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
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_3000", "�����û����룺�û�������", null);
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
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_PASSWORD_ACTION_ERR_8000", "�����û����룺����["+memberPOResult.getMemberName()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		
		String optTypeDesc="�޸�����";
		String optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_PAS;
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "�û�["+memberPOResult.getMemberName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "�û�["+memberPOResult.getMemberName()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
