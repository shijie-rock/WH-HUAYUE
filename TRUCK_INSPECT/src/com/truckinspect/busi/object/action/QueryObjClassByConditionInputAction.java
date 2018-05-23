/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:QueryObjClassByConditionInputAction.java
 * 创 建日期:2018年3月20日-下午12:08:09
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * 类名称:QueryObjClassByConditionInputAction
 * 类描述:根据条件查询联想输入，查询大类，中类，小类
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月20日 下午12:08:09
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjClassByConditionInputAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String queryType=atx.getStringValue("QUERY_TYPE");//
		//SUP：根据supcode supName，查询 sup
		//MID：根据midcode midName 查询 sup mid
		//SUB：根据subcode subName 查询sup mid sub
		
		Integer rows=atx.getIntValue("maxRows",10);//最查询多少条
		
		String supName=atx.getStringValue("SUP_NAME");
		String supCode=atx.getStringValue("SUP_CODE");
		
		String midName=atx.getStringValue("MID_NAME");
		String midCode=atx.getStringValue("MID_CODE");
		
		String subName=atx.getStringValue("SUB_NAME");
		String subCode=atx.getStringValue("SUB_CODE");
		
		if(StringUtils.isBlank(queryType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("QUERY_OBJ_CLASS_CODE_BY_CONDITIION_ACTION_ERR_1000", "查询检查项目类别：参数为空", null);
			return 0;
		}
		List<DynaBean> resultList=null;
		Connection conn=atx.getConnection();
		if("SUP".equals(queryType)){
			logger.debug(" QUERY SUP BY SUP .");
			resultList=TmInsCheckObjClassPOFactory.querySupClassBySupCodeAndSupName(conn, supCode, supName, rows);
			
		}else if("MID".equals(queryType)){
			logger.debug(" QUERY MID BY MID .");
			resultList=TmInsCheckObjClassPOFactory.querySupMidClassByMidCodeAndMidName(conn, midCode, midName,supCode, supName, rows);
		}else if("SUB".equals(queryType)){
			logger.debug(" QUERY SUB BY SUB .");
			resultList=TmInsCheckObjClassPOFactory.querySupMidSubClassBySubCodeAndSubName(conn, subCode, subName,midCode, midName,supCode, supName, rows);
		}else{
			logger.error(" NOT SUPPORT QUERY_TYPE ["+queryType+"] .");
			atx.setErrorContext("QUERY_OBJ_CLASS_CODE_BY_CONDITIION_ACTION_ERR_2000", "查询检查项目类别：不支持查询类型", null);
			return 0;
		}
		
		if(resultList!=null){
			atx.setArrayValue("OBJ_CLASS_DATA", resultList.toArray());
		}
		
		return 1;
	}

}
