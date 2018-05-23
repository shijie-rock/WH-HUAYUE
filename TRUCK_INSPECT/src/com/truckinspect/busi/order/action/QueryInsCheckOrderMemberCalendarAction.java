/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsCheckOrderMemberCalendarAction.java
 * 创 建日期:2018年5月9日-下午6:26:30
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * 类名称:QueryInsCheckOrderMemberCalendarAction
 * 类描述:查询检查单检查员任务日历
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月9日 下午6:26:30
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsCheckOrderMemberCalendarAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String startDate=atx.getStringValue("START");
		String endDate=atx.getStringValue("END");
		String memberName=atx.getStringValue("MEMBER_NAME");
		String license=atx.getStringValue("LICENSE");
		
		//check param
		if(StringUtils.isBlank(startDate)||StringUtils.isBlank(endDate)){
			logger.error(" PARAM TRUCK_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MEMBER_CALENDAR_ACTION_ERR_1000", "查询检查单检查员任务日历：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		List<DynaBean> memberTaskList=TmInsCheckOrderPOFactory.queryCheckOrderMemberTaskByMemberName(conn, memberName,license, startDate, endDate);
	
		if(memberTaskList!=null){
			atx.setArrayValue("CALENDAR_TASK_LIST", memberTaskList.toArray());
		}
		
		return 1;
	}

}
