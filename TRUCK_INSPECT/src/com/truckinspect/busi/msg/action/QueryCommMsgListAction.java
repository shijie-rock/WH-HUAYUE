/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryInsGroupListAction.java
 * �� ������:2017��8��20��-����5:53:55
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
 * ������:QueryInsGroupListAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:55
 * �޸ı�ע:
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
			atx.setErrorContext("MESSAGE_COMM_MSG_QUERY_LIST_ACTION_ERR_9000", "��ѯ�����б���Ϣ����", e);
			return 0;
		}
		
		return 1;
	}

}