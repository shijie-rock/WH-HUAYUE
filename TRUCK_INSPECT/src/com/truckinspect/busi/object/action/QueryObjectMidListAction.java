/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionListAction.java
 * 创 建日期:2017年8月20日-下午5:53:55
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
 * 类名称:QueryObjectMidListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:55
 * 修改备注:
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
			atx.setErrorContext("BUSI_DATA_OBJ_MID_QUERY_LIST_ACTION_ERR_9000", "查询项目二级分类列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
