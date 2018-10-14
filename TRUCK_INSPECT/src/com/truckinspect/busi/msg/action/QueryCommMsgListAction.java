/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryInsGroupListAction.java
 * 创 建日期:2017年8月20日-下午5:53:55
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.msg.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.msg.pofactory.TmInsCommMsgPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QueryInsGroupListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:55
 * 修改备注:
 * @version 1.0.0
 */
public class QueryCommMsgListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String msgContent=atx.getStringValue("COMM_MSG_CONTENT");
		String msgLevel=atx.getStringValue("COM_MSG_LEVEL");
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmInsCommMsgPOFactory.queryCommMsgList(conn,msgLevel,msgContent, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryCommMsgListAction error:"+e.getMessage());
			atx.setErrorContext("MESSAGE_COMM_MSG_QUERY_LIST_ACTION_ERR_9000", "查询公告列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
