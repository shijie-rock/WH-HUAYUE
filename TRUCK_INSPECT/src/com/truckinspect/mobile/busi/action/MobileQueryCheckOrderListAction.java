/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.mobile.busi.action
 * 文   件  名:MobileQueryCheckOrderListAction.java
 * 创 建日期:2018年10月10日-下午8:20:33
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.mobile.busi.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * 类名称:MobileQueryCheckOrderListAction
 * 类描述:移动端查询检查单列表
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月10日 下午8:20:33
 * 修改备注:
 * @version 1.0.0
 */
public class MobileQueryCheckOrderListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkMemberId=atx.getStringValue("CHECK_MEMBER_ID");//检查人id，前台传入
		int pageNo=atx.getIntValue("PAGE",1);//要查询的页面数,默认查询第一页
		
		String queryType=atx.getStringValue("QUERY_TYPE");//1:未检,2：检中,3：已检
		
		//check param
		if(StringUtils.isBlank(queryType)||StringUtils.isBlank(checkMemberId)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MOBILE_QUERY_CHECK_ORDER_LIST_ACTION_ERR_1000", "移动端查询检查单列表：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckOrderPOFactory.mobileQueryCheckOrderList(conn, Integer.valueOf(checkMemberId), queryType, pageNo);
		
		if(list!=null&&list.size()>0){
			atx.setArrayValue("CHECK_ORDER_LIST", list.toArray());
		}
		
		return 1;
	}

}
