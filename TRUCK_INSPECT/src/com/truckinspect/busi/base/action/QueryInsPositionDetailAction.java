/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionDetailAction.java
 * 创 建日期:2017年8月20日-下午5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;

/**
 * 类名称:QueryInsPositionDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:54:18
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsPositionDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionId=atx.getStringValue("POSITION_ID");
		//check param
		if(StringUtils.isBlank(positionId)){
			logger.error(" PARAM INS_POSITION_ID IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_DETAIL_ACTION_ERR_1000", "查询检查地点明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_POSITION_INFO
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setId(Integer.valueOf(positionId));
		positionPOCon=POFactory.getByPriKey(conn, positionPOCon);
		
		if(positionPOCon!=null){
			
			atx.setObjValue("INS_POSITION_BEAN", positionPOCon);
		}
		else{
			logger.error("检查地点不存在");
			atx.setErrorContext("BASE_DATA_POSITION_DETAIL_ACTION_ERR_2000", "查询检查地点明细：检查地点不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
