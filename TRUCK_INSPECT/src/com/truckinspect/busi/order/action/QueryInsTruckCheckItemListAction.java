/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsTruckCheckItemListAction.java
 * 创 建日期:2018年5月3日-下午6:49:40
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
 * 类名称:QueryInsTruckCheckItemListAction
 * 类描述:新增检查单时，新增界面，选中某辆车时（或再次所选车辆与上车辆不同时），
 * 车辆id，及车辆对应的检查项目，刷新检查项目树。
 * 车辆对应的检查项目，
 * 1：优先查询 TT_INS_TRUCK_SUB_OBJ_MAP ，小类对应的检查项目。
 * 2：若没有，则查询 该车辆对应的三级对象TT_INS_TRUCK_MID_ENT_MAP ，对应的三级检查小类 TT_INS_CHECK_ENTITY_OBJ_SUB_MAP，再找对应的检查项目。
 * 3：将三级检查项目小类对应的最终检查项目存入  TM_INS_CHECK_OBJ_ITEM
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月3日 下午6:49:40
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsTruckCheckItemListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckId=atx.getStringValue("TRUCK_ID");
		
		if(StringUtils.isBlank(truckId)||"undefined".equals(truckId)||"null".equals(truckId)){
			logger.error(" PARAM ORDER_NO IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_QUERY_TURCK_CHECK_OBJ_ITEM_ACTION_ERR_1000", "查询车辆检查项目明细：参数车辆ID为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckOrderPOFactory.queryTruckCheckItemByTruckId(conn, Integer.valueOf(truckId));
		if(list==null){
			list=TmInsCheckOrderPOFactory.queryTruckCheckItemTruckEntTypeByTruckId(conn, Integer.valueOf(truckId));
		}
		
		if(list!=null){
			atx.setArrayValue("TRUCK_CHECK_ITEM_LIST", list.toArray());
		}
		
		return 1;
	}

}
