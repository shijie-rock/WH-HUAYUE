/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:OrganizeRoleQueryAction.java
 * 创 建日期:2017年7月25日-下午8:20:19
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.organize.pofactory.TbSysRolePOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:OrganizeRoleQueryAction
 * 类描述:有效角色列表，按条件（角色名，代码）查询，角色列表信息
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年7月25日 下午8:20:19
 * 修改备注:
 * @version 1.0.0
 */
public class OrganizeRoleQueryAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String roleName=atx.getStringValue("ROLE_NAME");
		String roleCode=atx.getStringValue("ROLE_CODE");
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TbSysRolePOFactory.queryRoleList(conn, roleName, roleCode, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
//			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ROLE_ADD, "", "新增角色");//TEST
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OrganizeRoleQueryAction error:"+e.getMessage());
			atx.setErrorContext("ORGANIZE_ROLE_QUERY_ACTION_ERR_9000", "查询角色列表信息出错", e);
			return 0;
		}
		return 1;
	}

}
