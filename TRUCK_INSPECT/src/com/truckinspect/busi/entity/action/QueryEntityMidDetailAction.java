/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionDetailAction.java
 * 创 建日期:2017年8月20日-下午5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;

/**
 * 类名称:QueryEntityMidDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:54:18
 * 修改备注:
 * @version 1.0.0
 */
public class QueryEntityMidDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entMidId=atx.getIntValue("ENT_MID_ID",0);
		//check param
		if(entMidId==0){
			logger.error(" PARAM ENTITY_MID_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_DETAIL_ACTION_ERR_1000", "查询检查对象中类明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_ENTITY_MIDDLE
		TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
		entMidPOCon.setStatus("1");
		entMidPOCon.setId(entMidId);
		entMidPOCon=POFactory.getByPriKey(conn, entMidPOCon);
		
		if(entMidPOCon!=null){
			atx.setObjValue("ENT_MID_BEAN", entMidPOCon);
		}
		else{
			logger.error("检查对象中类不存在");
			atx.setErrorContext("BUSI_DATA_ENT_MID_DETAIL_ACTION_ERR_2000", "查询检查对象中类明细：检查对象中类不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
