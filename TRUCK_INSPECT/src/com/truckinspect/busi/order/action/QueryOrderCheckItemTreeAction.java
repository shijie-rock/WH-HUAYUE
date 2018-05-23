/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryOrderCheckItemTreeAction.java
 * 创 建日期:2018年4月8日-下午5:13:17
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * 类名称:QueryOrderCheckItemTreeAction
 * 类描述:查询检查项目树json内容
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年4月8日 下午5:13:17
 * 修改备注:
 * @version 1.0.0
 */
public class QueryOrderCheckItemTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
//		String checkOrderNo=atx.getStringValue("ORDER_NO");//检查单号
		String entTypeCode=atx.getStringValue("ENT_TYPE_CODE","CET_0010");//检查对象类型-车(缺省)，服务器等
	
		Connection conn=atx.getConnection();
		String returnTreeJson=TmInsCheckObjClassPOFactory.getSupMidSubTreeViewStringByEntCheckType(conn,entTypeCode);
		if(StringUtils.isNotBlank(returnTreeJson)){
			atx.setStringValue("TREE_DATA", returnTreeJson);//树形结构
		}
//		if(StringUtils.isNotBlank(checkOrderNo)){
////			TM_INS_CHECK_ORDER_ITEM
//			TmInsCheckOrderItemPO orderItemPOCon=new TmInsCheckOrderItemPO();
//			orderItemPOCon.setStatus("1");
//			orderItemPOCon.setFreezeTag("0");
//			orderItemPOCon.setCheckOrderNo(checkOrderNo);
//			
//			List<TmInsCheckOrderItemPO> itemList=POFactory.select(conn, orderItemPOCon);
//			
//			if(itemList!=null&&itemList.size()>0){
//				atx.setArrayValue("ITEM_LIST", itemList.toArray());//检查单所包含检查项目
//			}
//		}
		return 1;
	}
}
