/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryRoleDetailAction.java
 * 创 建日期:2017年8月16日-下午2:37:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TbSysRoleActionPO;
import com.info.base.po.TbSysRolePO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;

/**
 * 类名称:QueryRoleDetailAction
 * 类描述:查询角色明细
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月16日 下午2:37:59
 * 修改备注:
 * @version 1.0.0
 */
public class QueryRoleDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String roleCode=atx.getStringValue("ROLE_CODE");
		//check param
		if(StringUtils.isBlank(roleCode)){
			logger.error(" PARAM ROLE_CODE IS EMTPY .");
			atx.setErrorContext("ORGANIZE_ROLE_DETAIL_ACTION_ERR_1000", "查询角色明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleCode(roleCode);
		rolePOCon=POFactory.getByPriKey(conn, rolePOCon);
		
		if(rolePOCon!=null){
			
			atx.setObjValue("ROLE_BEAN", rolePOCon);
			
			TbSysRoleActionPO roleActionPOCon=new TbSysRoleActionPO();
			roleActionPOCon.setStatus("1");
			roleActionPOCon.setRoleCode(roleCode);
			List<TbSysRoleActionPO> list=POFactory.select(conn, roleActionPOCon);
			
			if(list!=null&&list.size()>0){
				atx.setArrayValue("ACTION_LIST", list.toArray());
			}
		}
		else{
			
			logger.error("角色代码对应角色不存在");
			atx.setErrorContext("ORGANIZE_ROLE_DETAIL_ACTION_ERR_2000", "查询角色明细：角色不存在", null);
			return 0;
		}
		
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
