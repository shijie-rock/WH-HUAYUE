/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:EditRoleAction.java
 * 创 建日期:2017年8月19日-下午3:00:49
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
 * 类名称:EditRoleAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月19日 下午3:00:49
 * 修改备注:
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
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_1000", "编辑角色：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		相同角色名称不能添加
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleName(roleName);
		
		TbSysRolePO rolePOConResult=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOConResult!=null&&rolePOConResult.getRoleCode().equals(roleCode)){
			logger.error(" ROLE NAME EXIST .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_3000", "编辑角色：角色名称["+roleName+"]已存在", null);
			return 0;
		}
		
		//check repeat
		rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleCode(roleCode);
		
		TbSysRolePO rolePOResult=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOResult==null){
			logger.error(" ROLE NOT EXIST .");
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_2000", "编辑角色：角色不存在", null);
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
			atx.setErrorContext("ORGANIZE_ROLE_EDIT_ACTION_ERR_8000", "编辑角色：数据["+roleCode+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ROLE_MOD, "", "编辑角色");
		
		//set return msg
		atx.setStringValue("MSG", "编辑角色["+roleCode+"]成功");
		return 1;
	}

}
