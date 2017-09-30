/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:AddRoleAction.java
 * �� ������:2017��8��15��-����11:37:29
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.busi.organize.pofactory.TbSysRolePOFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddRoleAction
 * ������:�½���ɫ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��15�� ����11:37:29
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddRoleAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String roleCode=atx.getStringValue("ROLE_CODE");
		String roleName=atx.getStringValue("ROLE_NAME");
		String roleDesc=atx.getStringValue("ROLE_DESC");
		String actionCodeStr=atx.getStringValue("ROLE_ACTION_STR");
		
		//check param
		if(StringUtils.isBlank(roleCode)||StringUtils.isBlank(roleName)||StringUtils.isBlank(actionCodeStr)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_ROLE_ADD_ACTION_ERR_1000", "������ɫ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
		if(TbSysRolePOFactory.roleIsExist(conn, roleCode)){
			logger.error(" ROLE EXIST ALREADY .");
			atx.setErrorContext("ORGANIZE_ROLE_ADD_ACTION_ERR_2000", "������ɫ����ɫ�����Ѿ�����", null);
			return 0;
		}
		
		//
//		��ͬ��ɫ���Ʋ������
		if(TbSysRolePOFactory.roleNameIsExist(conn, roleName)){
			logger.error(" ROLE NAME EXIST ALREADY .");
			atx.setErrorContext("ORGANIZE_ROLE_ADD_ACTION_ERR_3000", "������ɫ����ɫ�����Ѿ�����", null);
			return 0;
		}
		
		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		int excuteResult=TbSysRolePOFactory.addRole(conn, roleCode, roleName, roleDesc, actionCodeStr, optMemberId, optMemberName);
		if(excuteResult<1){
			atx.setErrorContext("ORGANIZE_ROLE_ADD_ACTION_ERR_8000", "������ɫ������ʧ��", null);
			return 0;
		}
		
		//build optInfo
//		atx.setObjValue("OPT_LOG_BEAN", new OptLogDTO(TruckInsCommonCanstant.OPT_TYPE_ROLE_ADD, "", "������ɫ"));
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ROLE_ADD, "", "������ɫ");
		
		//set return msg
		atx.setStringValue("MSG", "������ɫ["+roleCode+"]�ɹ�");
		return 1;
	}

}
