/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsPositionDetailAction.java
 * 创 建日期:2017年8月20日-下午5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;

/**
 * 类名称:QueryObjectSupDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:54:18
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjectSupDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objId=atx.getStringValue("OBJ_SUP_ID");
		//check param
		if(StringUtils.isBlank(objId)){
			logger.error(" PARAM OBJECT_SUP_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_DETAIL_ACTION_ERR_1000", "查询一级分类明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setId(Integer.valueOf(objId));
		objSupPOCon=POFactory.getByPriKey(conn, objSupPOCon);
		
		if(objSupPOCon!=null){
			atx.setObjValue("OBJ_SUP_BEAN", objSupPOCon);
		}
		else{
			logger.error("一级分类不存在");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_DETAIL_ACTION_ERR_2000", "查询一级分类明细：一级分类不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
