/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionListAction.java
 * �� ������:2017��8��20��-����5:53:55
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:QueryObjectMidListAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:55
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjectMidListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objName=atx.getStringValue("OBJ_NAME");
		String objCode=atx.getStringValue("OBJ_CODE");
		String supCode=atx.getStringValue("SUP_CODE");
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmInsCheckObjClassPOFactory.queryInsCheckObjectClassMidList(conn, objCode, objName,supCode, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryObjectMidListAction error:"+e.getMessage());
			atx.setErrorContext("BUSI_DATA_OBJ_MID_QUERY_LIST_ACTION_ERR_9000", "��ѯ��Ŀ���������б���Ϣ����", e);
			return 0;
		}
		
		return 1;
	}

}
