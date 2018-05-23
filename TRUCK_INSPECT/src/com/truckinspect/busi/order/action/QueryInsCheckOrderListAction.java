/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsCheckOrderListAction.java
 * 创 建日期:2018年3月25日-下午6:22:06
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QueryInsCheckOrderListAction
 * 类描述:查看检查单列表
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月25日 下午6:22:06
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsCheckOrderListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");//检查单号
		String address=atx.getStringValue("ADDRESS");//检查地址
		String targetName=atx.getStringValue("TARGET_NAME");//检查目标名称，车牌
		String pBeginDate=atx.getStringValue("PLAN_BEGIN_DATE");//
		String pEndDate=atx.getStringValue("PLAN_END_DATE");//
		String rBeginDate=atx.getStringValue("CHECK_BEGIN_DATE");//
		String rEndDate=atx.getStringValue("CHECK_END_DATE");//
		String checkOptName=atx.getStringValue("CHECK_OPT_NAME");//
		String checkResult=atx.getStringValue("CHECK_RESULT");//CHECK_ORDER_RESULT 数据字典
		String checkStatus=atx.getStringValue("CHECK_STATUS");//CHECK_ORDER_STATUS 数据字典
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			//test
//			TmInsCheckObjClassPOFactory.getSupMidSubTreeViewStringByEntCheckType(conn, "CET_0010");
//			TmInsCheckObjClassPOFactory.getSameUpCodeItemListMap(TmInsCheckObjClassPOFactory.queryOneSubClassNodeCheckObjList(conn, "CHE_01", "CHE_0101", "CHE_010101"));
			
			PageQuery pageQuery=TmInsCheckOrderPOFactory.queryInsCheckOrderList(conn, checkOrderNo,address,targetName, pBeginDate, pEndDate, rBeginDate, rEndDate, checkOptName, checkResult, checkStatus, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryInsCheckOrderListAction error:"+e.getMessage());
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_QUERY_LIST_ACTION_ERR_9000", "查询检查单列表信息出错", e);
			return 0;
		}
		
		return 1;
	}

}
