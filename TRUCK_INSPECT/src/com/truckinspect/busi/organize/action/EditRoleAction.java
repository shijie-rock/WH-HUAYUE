/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:EditRoleAction.java
 * �� ������:2017��8��19��-����3:00:49
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TbSysRolePO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.pofactory.TbSysRolePOFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditRoleAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��19�� ����3:00:49
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditRoleAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String roleCode=atx.getStringValue("ROLE_CODE");
		String roleName=atx.getStringValue("ROLE_NAME");
		String roleDesc=atx.getStringValue("ROLE_DESC","");
		String actionCodeStr=atx.getStringValue("ROLE_ACTION_STR");
		
		//check param
		if(StringUtils.isBlank(roleCode)||StringUtils.isBlank(roleName)||StringUtils.isBlank(actionCodeStr)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_1000", "�༭��ɫ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		��ͬ��ɫ���Ʋ������
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleName(roleName);
		
		TbSysRolePO rolePOConResult=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOConResult!=null&&rolePOConResult.getRoleCode().equals(roleCode)){
			logger.error(" ROLE NAME EXIST .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_3000", "�༭��ɫ����ɫ����["+roleName+"]�Ѵ���", null);
			return 0;
		}
		
		//check repeat
		rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleCode(roleCode);
		
		TbSysRolePO rolePOResult=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOResult==null){
			logger.error(" ROLE NOT EXIST .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_2000", "�༭��ɫ����ɫ������", null);
			return 0;
		}
		
		int version=rolePOResult.getVer();
		rolePOCon.setVer(version);

		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		int excuteResult=TbSysRolePOFactory.editRole(conn, roleName, roleDesc, actionCodeStr, optMemberId,rolePOCon);
		
		if(excuteResult<1){
			logger.error(" DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_8000", "�༭��ɫ������["+roleCode+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ROLE_MOD, "", "�༭��ɫ");
		
		//set return msg
		atx.setStringValue("MSG", "�༭��ɫ["+roleCode+"]�ɹ�");
		return 1;
	}

}
