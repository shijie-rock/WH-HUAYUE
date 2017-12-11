/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionListAction.java
 * 创 建日期:2017年8月20日-下午5:53:55
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.base.pofactory.TmInsPositionInfoPOFactory;
import com.truckinspect.busi.organize.pofactory.TmInspactGroupPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QueryInsPositionListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:55
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsPositionListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionName=atx.getStringValue("POSITION_NAME");
		String positionCode=atx.getStringValue("POSITION_CODE");
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmInsPositionInfoPOFactory.queryInsPositionList(conn, positionCode, positionName, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryInsPositionListAction error:"+e.getMessage());
			atx.setErrorContext("BASE_DATA_POSITION_QUERY_LIST_ACTION_ERR_9000", "查询检查地点列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
