/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryDetailInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;

/**
 * 类名称:QueryInsGroupDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:54:18
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsGroupDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String insGroupId=atx.getStringValue("INS_GROUP_ID");
		//check param
		if(StringUtils.isBlank(insGroupId)){
			logger.error(" PARAM INS_GROUP_ID IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_DETAIL_ACTION_ERR_1000", "查询检查组明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmInspactGroupPO insGroupPOCon=new TmInspactGroupPO();
		insGroupPOCon.setStatus("1");
		insGroupPOCon.setId(Integer.valueOf(insGroupId));
		insGroupPOCon=POFactory.getByPriKey(conn, insGroupPOCon);
		
		if(insGroupPOCon!=null){
			
			atx.setObjValue("INS_GROUP_BEAN", insGroupPOCon);
		}
		else{
			logger.error("检查组不存在");
			atx.setErrorContext("ORGANIZE_INS_GROUP_DETAIL_ACTION_ERR_2000", "查询检查组明细：检查组不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
