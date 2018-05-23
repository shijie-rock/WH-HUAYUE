/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:QueryObjectItemDetailAction.java
 * 创 建日期:2018年3月13日-上午10:07:54
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjItemPOFactory;

/**
 * 类名称:QueryObjectItemDetailAction
 * 类描述:查询检查项目明细
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月13日 上午10:07:54
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjectItemDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer itemId=atx.getIntValue("OBJ_ITEM_ID",0);
		
		if(itemId==0){
			logger.error(" PARAM OBJECT_ITEM_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_DETAIL_ACTION_ERR_1000", "查询检查项目明细：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		
		DynaBean itemBean=TmInsCheckObjItemPOFactory.queryObjItemDetail(conn, itemId);
		if(itemBean!=null){
			atx.setObjValue("OBJ_ITEM_BEAN", itemBean);
		}
		else{
			logger.error("检查项目不存在");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_DETAIL_ACTION_ERR_2000", "查询检查项目明细：维修项目不存在", null);
			return 0;
		}
		
		return 1;
	}

}
