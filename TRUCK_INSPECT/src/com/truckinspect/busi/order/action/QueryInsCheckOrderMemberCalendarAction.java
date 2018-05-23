/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryInsCheckOrderMemberCalendarAction.java
 * �� ������:2018��5��9��-����6:26:30
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
 * ������:QueryInsCheckOrderMemberCalendarAction
 * ������:��ѯ��鵥���Ա��������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��9�� ����6:26:30
 * �޸ı�ע:
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MEMBER_CALENDAR_ACTION_ERR_1000", "��ѯ��鵥���Ա��������������Ϊ��", null);
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
