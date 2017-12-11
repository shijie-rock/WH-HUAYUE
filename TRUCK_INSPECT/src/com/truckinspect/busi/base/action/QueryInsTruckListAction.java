/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryMemmberListAction.java
 * �� ������:2017��9��2��-����9:34:29
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.organize.pofactory.TmSysMemberPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:QueryMemberListAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:34:29
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsTruckListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String memberName=atx.getStringValue("MEMBER_NAME");
		String mobile=atx.getStringValue("MOBILE");
		String memberCode=atx.getStringValue("MEMBER_CODE");
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmSysMemberPOFactory.queryMemberDetailList(conn,memberName,mobile,memberCode, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryMemberListAction error:"+e.getMessage());
			atx.setErrorContext("ORGANIZE_MEMBER_LIST_QUERY_ACTION_ERR_9000", "��ѯ�û��б���Ϣ����", e);
			return 0;
		}
		return 1;
	}

}
