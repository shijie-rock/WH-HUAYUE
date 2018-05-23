/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsCheckOrderItemDetailAction.java
 * 创 建日期:2018年5月9日-下午1:02:54
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * 类名称:QueryInsCheckOrderItemDetailAction
 * 类描述:检查单项目详情
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月9日 下午1:02:54
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsCheckOrderItemDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");
		String itemId=atx.getStringValue("CHECK_ORDER_ITEM_ID");
		
		//check param
		if(StringUtils.isBlank(checkOrderNo)||StringUtils.isBlank(itemId)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_DETAIL_ACTION_ERR_1000", "查询检查单项目明细：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		DynaBean bean=TmInsCheckOrderPOFactory.queryInsChckOrerItemDetail(conn, checkOrderNo, Integer.valueOf(itemId));
		if(bean!=null){
			atx.setObjValue("CHECK_ORDER_ITEM_BEAN", bean);
		}
		return 1;
	}

}
