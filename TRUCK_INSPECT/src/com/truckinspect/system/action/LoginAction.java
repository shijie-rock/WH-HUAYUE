/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.system.action
 * ��   ��  ��:LoginAction.java
 * �� ������:2017��12��5��-����3:50:58
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
 * ������:LoginAction
 * ������:�û�ϵͳ��¼
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��5�� ����3:50:58:��ʱ1,1
 * �޸ı�ע:
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
			atx.setErrorContext("LOGIN_ACTION_ERR_1000", "ϵͳ��¼���û��������벻��Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setMemberCode(name);
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOResult==null){
			logger.error(" EMP NOT EXIST .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2000", "ϵͳ��¼���û�������", null);
			return 0;
		}
		
		if("1".equals(memberPOResult.getFreezeTag())){
			logger.error(" EMP HAS BEEN FREEZEN .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2001", "ϵͳ��¼���û��ѱ�����", null);
			return 0;
		}
		
		if(!DBConUtil.MD5Encode(pass).equals(memberPOResult.getPassword())){
			logger.error(" EMP PASSWORD IS ERROR .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2002", "ϵͳ��¼���������", null);
			return 0;
		}
		if(!"1".equals(memberPOResult.getCanLoginSys())){
			logger.error(" EMP CANNOT LOGIN .");
			atx.setErrorContext("LOGIN_ACTION_ERR_2003", "ϵͳ��¼���û����ܵ�¼ϵͳ", null);
			return 0;
		}
		
		// ��֤ͨ�������û���Ϣ����SESSION
		String sessionId=atx.getStringValue(FrameworkConstant.SESSION_ID);
		atx.setStringValue(YBCommonContant.SESSION_ID, sessionId);
		atx.setStringValue(YBCommonContant.SESSION_USER_ID, String.valueOf(memberPOResult.getId())); //TM_SYS_MEMBER id
		atx.setStringValue(YBCommonContant.SESSION_USER_NAME,memberPOResult.getMemberName()); //TM_SYS_MEMBER MEMBER_NAME
		atx.setStringValue(YBCommonContant.SESSION_USER_CODE,memberPOResult.getMemberCode()); //TM_SYS_MEMBER MEMBER_CODE
		atx.setObjValue(YBCommonContant.SESSION_LOGIN_USER,memberPOResult); //TM_SYS_MEMBER PO
		
		//���µ�¼��Ϣ
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
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_LOGIN, "", "�û�["+memberPOResult.getMemberName()+"]��¼ϵͳ");
		
		//set return msg
		atx.setStringValue("MSG", "�û�["+optMemberName+"]��¼�ɹ�");
		
		return 1;
	}

}
