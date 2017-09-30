/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:ModRoleAction.java
 * 创 建日期:2017年8月18日-下午5:11:41
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TbSysRoleActionPO;
import com.info.base.po.TbSysRolePO;
import com.info.base.po.TtSysMemberActionPO;
import com.info.base.po.TtSysMemberRolePO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModRoleAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月18日 下午5:11:41
 * 修改备注:
 * @version 1.0.0
 */
public class ModRoleAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String roleCode=atx.getStringValue("ROLE_CODE");
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(roleCode)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_1000", "处理角色：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check exist
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleCode(roleCode);
		
		TbSysRolePO rolePOResult=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOResult==null){
			logger.error(" ROLE NOT EXIST .");
			atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_2000", "处理角色：角色不存在", null);
			return 0;
		}
		
		int version=rolePOResult.getVer();
		rolePOCon.setVer(version);
		
		TbSysRolePO rolePOValue=new TbSysRolePO();
		rolePOValue.setUpdateBy(YBUtility.getMemberId(atx));
		rolePOValue.setUpdateTime(YBUtility.now());
		rolePOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(rolePOResult.getFreezeTag())){
				logger.error(" ROLE IS STOPED ALREADY .");
				atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_3001", "处理角色：角色已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ROLE_UNV;
			rolePOValue.setFreezeTag("1"); //不清除用户权限
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(rolePOResult.getFreezeTag())){
				logger.error(" ROLE IS START ALREADY .");
				atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_3002", "处理角色：角色已经是启用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ROLE_VAL;
			rolePOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ROLE_DEL;
			rolePOValue.setStatus("0");
			//clear employee role ，action
			TtSysMemberRolePO memberRolePOCon=new TtSysMemberRolePO();
			memberRolePOCon.setStatus("1");
			memberRolePOCon.setRoleCode(roleCode);
			
			//member role
			TtSysMemberRolePO memberRolePOValue=new TtSysMemberRolePO();
			memberRolePOValue.setUpdateBy(YBUtility.getMemberId(atx));
			memberRolePOValue.setUpdateTime(YBUtility.now());
			memberRolePOValue.setStatus("0");
			
			POFactory.update(conn, memberRolePOCon, memberRolePOValue);
			
			TbSysRoleActionPO roleActionPOCon=new TbSysRoleActionPO();
			roleActionPOCon.setStatus("1");
			roleActionPOCon.setRoleCode(roleCode);
			
			List<TbSysRoleActionPO> roleActionList=POFactory.select(conn, roleActionPOCon);
			if(roleActionList!=null&&roleActionList.size()>0){
				TtSysMemberActionPO memberActionPOCon=new TtSysMemberActionPO();
				memberActionPOCon.setStatus("1");
				//member action
				TtSysMemberActionPO memberActionPOValue=new TtSysMemberActionPO();
				memberActionPOValue.setUpdateBy(YBUtility.getMemberId(atx));
				memberActionPOValue.setUpdateTime(YBUtility.now());
				memberActionPOValue.setStatus("0");
				
				for(TbSysRoleActionPO roleActionBean:roleActionList){
					if(StringUtils.isBlank(roleActionBean.getActionCode())){
						continue;
					}
					memberActionPOCon.setActionCode(roleActionBean.getActionCode());
					POFactory.update(conn, memberActionPOCon, memberActionPOValue);
				}
			}
			
//			role action
			TbSysRoleActionPO roleActionPOValue=new TbSysRoleActionPO();
			roleActionPOValue.setUpdateBy(YBUtility.getMemberId(atx));
			roleActionPOValue.setUpdateTime(YBUtility.now());
			roleActionPOValue.setStatus("0");
			
			POFactory.update(conn, roleActionPOCon, roleActionPOValue);
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_3010", "处理角色：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, rolePOCon,rolePOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_8000", "处理角色：数据["+roleCode+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_ROLE_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "角色["+roleCode+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "角色["+roleCode+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}




