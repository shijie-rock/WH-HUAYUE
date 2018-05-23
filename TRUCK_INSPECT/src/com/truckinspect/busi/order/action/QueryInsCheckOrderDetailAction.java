/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsCheckOrderDetailAction.java
 * 创 建日期:2018年3月25日-下午6:22:20
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * 类名称:QueryInsCheckOrderDetailAction
 * 类描述:查看检查单明细
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月25日 下午6:22:20
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsCheckOrderDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("ORDER_NO");//检查单号
		
		if(StringUtils.isBlank(checkOrderNo)){
			logger.error(" PARAM ORDER_NO IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_DETAIL_ACTION_ERR_1000", "查询检查单明细：参数检查单号为空", null);
			return 0;
		}
		
		if(StringUtils.isNotBlank(checkOrderNo)){
			Connection conn=atx.getConnection();
			//main TM_INS_CHECK_MAIN_ORDER
			DynaBean mainBean=TmInsCheckOrderPOFactory.queryInsCheckOrderDetail(conn, checkOrderNo);
			if(mainBean!=null){
				atx.setObjValue("CHECK_ORDER_BEAN", mainBean);
			}else{
				logger.error("查询检查单明细：检查单明细为空");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_DETAIL_ACTION_ERR_2000", "查询检查单明细：检查单明细为空", null);
				return 0;
			}
			
//			TM_INS_CHECK_ORDER_ITEM
			TmInsCheckOrderItemPO orderItemPOCon=new TmInsCheckOrderItemPO();
			orderItemPOCon.setStatus("1");
			orderItemPOCon.setFreezeTag("0");
			orderItemPOCon.setCheckOrderNo(checkOrderNo);
			
			List<TmInsCheckOrderItemPO> itemList=POFactory.select(conn, orderItemPOCon);
			
			if(itemList!=null&&itemList.size()>0){
				atx.setArrayValue("ITEM_LIST", itemList.toArray());//检查单所包含检查项目
			}
		}
		return 1;
	}

}
