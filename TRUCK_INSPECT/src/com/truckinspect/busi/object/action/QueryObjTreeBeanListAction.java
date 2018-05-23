/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:QueryObjTreeBeanListAction.java
 * 创 建日期:2018年1月31日-下午10:39:21
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * 类名称:QueryObjTreeBeanListAction
 * 类描述:获取检查项目树状结构，用于前台展示拼接树状结构图
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年1月31日 下午10:39:21
 * 修改备注:
 * @version 1.0.0
 */
public class QueryObjTreeBeanListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckObjClassPOFactory.queryObjectTreeDynaBean(conn, "1".equals(includeStop));
		if(list!=null){
			atx.setArrayValue("OBJECT_LIST", list.toArray());
		}
		TmInsCheckObjClassPOFactory.queryObjectTreeHtml(conn, "1".equals(includeStop));
		return 1;
	}

}
