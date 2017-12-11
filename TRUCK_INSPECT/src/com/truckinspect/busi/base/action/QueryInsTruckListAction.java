/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryMemmberListAction.java
 * 创 建日期:2017年9月2日-上午9:34:29
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
 * 类名称:QueryMemberListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:34:29
 * 修改备注:
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
			atx.setErrorContext("ORGANIZE_MEMBER_LIST_QUERY_ACTION_ERR_9000", "查询用户列表信息出错", e);
			return 0;
		}
		return 1;
	}

}
