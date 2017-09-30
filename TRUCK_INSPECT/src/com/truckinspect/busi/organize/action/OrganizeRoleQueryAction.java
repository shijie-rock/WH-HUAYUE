/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:OrganizeRoleQueryAction.java
 * �� ������:2017��7��25��-����8:20:19
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
 * ������:OrganizeRoleQueryAction
 * ������:��Ч��ɫ�б�����������ɫ�������룩��ѯ����ɫ�б���Ϣ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��7��25�� ����8:20:19
 * �޸ı�ע:
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
			
//			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ROLE_ADD, "", "������ɫ");//TEST
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OrganizeRoleQueryAction error:"+e.getMessage());
			atx.setErrorContext("ORGANIZE_ROLE_QUERY_ACTION_ERR_9000", "��ѯ��ɫ�б���Ϣ����", e);
			return 0;
		}
		return 1;
	}

}
