/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.msg.action
 * 文   件  名:QueryCommMsgForMainPageAction.java
 * 创 建日期:2018年10月9日-下午6:17:02
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.msg.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.msg.pofactory.TmInsCommMsgPOFactory;

/**
 * 类名称:QueryCommMsgForMainPageAction
 * 类描述:首页查询已发布公告
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月9日 下午6:17:02
 * 修改备注:
 * @version 1.0.0
 */
public class QueryCommMsgForMainPageAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		int dataCount=atx.getIntValue("DATA_COUNT",3);//需要查询的条数
		
		List<DynaBean> list=null;
		Connection conn=atx.getConnection();
		list=TmInsCommMsgPOFactory.queryMainPageCommMsgList(conn, dataCount);
		if(list!=null){
			
			atx.setArrayValue("COMM_MSG_LIST", list.toArray());
		}
		
		return 1;
	}

}
