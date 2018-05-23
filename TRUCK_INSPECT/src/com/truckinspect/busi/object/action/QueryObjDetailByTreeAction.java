/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:QueryObjDetailByTreeAction.java
 * 创 建日期:2018年3月6日-下午2:35:50
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * 类名称:QueryObjDetailByTreeAction
 * 类描述:在树状图中查询检查项目类明细
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月6日 下午2:35:50
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjDetailByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String levelCode=atx.getStringValue("LEVEL");
		String upCode=atx.getStringValue("UP_CODE");
		String nowCode=atx.getStringValue("NOW_CODE");
		
		if(StringUtils.isBlank(levelCode)||StringUtils.isBlank(nowCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION_ERR_1000", "查询检查项目类明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		DynaBean bean=TmInsCheckObjClassPOFactory.queryCheckObjDetailByLevel(conn, levelCode, nowCode, upCode);
		
		if(bean!=null){
			atx.setObjValue("OBJECT_DETAIL_BEAN", bean);
		}
		else{
			logger.error(String.valueOf(levelCode)+"级分类["+nowCode+"]不存在");
			atx.setErrorContext("BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION_ERR_2000", "查询检查项目类明细："+String.valueOf(levelCode)+"级分类["+nowCode+"]不存在", null);
			return 0;
		}
//		atx
		return 1;
	}

}
