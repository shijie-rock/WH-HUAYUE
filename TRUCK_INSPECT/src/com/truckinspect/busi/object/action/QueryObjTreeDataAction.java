/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:QueryObjTreeDataAction.java
 * 创 建日期:2018年2月4日-下午4:51:39
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * 类名称:QueryObjTreeDataAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年2月4日 下午4:51:39
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjTreeDataAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		Connection conn=atx.getConnection();
		
		String objItemHtml=TmInsCheckObjClassPOFactory.queryObjectTreeHtml(conn,  "1".equals(includeStop));
		if(StringUtils.isNotBlank(objItemHtml)){
			atx.setStringValue("OBJ_ITEM_TREE_DATA", objItemHtml);
		}
		
		return 1;
	}

}
