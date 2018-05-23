/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionListAction.java
 * 创 建日期:2017年8月20日-下午5:53:55
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.entity.pofactory.TmInsCheckEntityPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QueryEntityMidListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:55
 * 修改备注:
 * @version 1.0.0
 */
public class QueryEntitySubListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entName=atx.getStringValue("ENT_NAME");
		String entCode=atx.getStringValue("ENT_CODE");
		String midCode=atx.getStringValue("MID_CODE");
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmInsCheckEntityPOFactory.queryInsCheckEntitySubList(conn, entCode, entName,midCode, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryEntitySubListAction error:"+e.getMessage());
			atx.setErrorContext("BUSI_DATA_ENT_SUB_QUERY_LIST_ACTION_ERR_9000", "查询检查对象小类列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
