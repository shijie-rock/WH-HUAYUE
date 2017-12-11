/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.sys.action
 * 文   件  名:QuerySysOptLogsListAction.java
 * 创 建日期:2017年12月7日-上午11:52:42
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.sys.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.sys.pofactory.TbOptLogsPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QuerySysOptLogsListAction
 * 类描述:查询系统操作日志
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年12月7日 上午11:52:42
 * 修改备注:
 * @version 1.0.0
 */
public class QuerySysOptLogsListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String optName=atx.getStringValue("OPT_NAME");
		String optContent=atx.getStringValue("OPT_CONTENT");
		String beginTime=atx.getStringValue("BEGIN_TIME");
		String endTime=atx.getStringValue("END_TIME");
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TbOptLogsPOFactory.querySysOptLogsList(conn, optName, optContent, beginTime, endTime, customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QuerySysOptLogsListAction error:"+e.getMessage());
			atx.setErrorContext("SYS_OPT_LOGS_QUERY_LIST_ACTION_ERR_9000", "查询系统操作日志列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
