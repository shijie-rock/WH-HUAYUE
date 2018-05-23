/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryInsCheckOrderItemListAction.java
 * �� ������:2018��5��8��-����4:19:23
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:QueryInsCheckOrderItemListAction
 * ������:��ѯ��鵥�����Ŀ�б�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��8�� ����4:19:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsCheckOrderItemListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");//��鵥��
		String itemCode=atx.getStringValue("ITEM_CODE");//
		String targetName=atx.getStringValue("TARGET_NAME");//���Ŀ�����ƣ�����
		String pBeginDate=atx.getStringValue("PLAN_BEGIN_DATE");//
		String pEndDate=atx.getStringValue("PLAN_END_DATE");//
		String rBeginDate=atx.getStringValue("CHECK_BEGIN_DATE");//
		String rEndDate=atx.getStringValue("CHECK_END_DATE");//
		String itemName=atx.getStringValue("ITEM_NAME");//
		String checkResult=atx.getStringValue("CHECK_ITEM_RESULT");//CHECK_ITEM_RESULT �����ֵ�
		String checkStatus=atx.getStringValue("CHECK_ITEM_STATUS");//CHECK_ITEM_STATUS �����ֵ�
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=TmInsCheckOrderPOFactory.queryInsCheckOrderItemList(conn, checkOrderNo,itemCode,targetName, pBeginDate, pEndDate, rBeginDate, rEndDate, itemName, checkResult, checkStatus, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryInsCheckOrderListAction error:"+e.getMessage());
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_QUERY_LIST_ACTION_ERR_9000", "��ѯ��鵥�����Ŀ��ϸ�б���Ϣ����", e);
			return 0;
		}
		
		return 1;
	}

}
