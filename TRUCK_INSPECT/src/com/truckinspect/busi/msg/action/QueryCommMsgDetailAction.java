/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryDetailInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.msg.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.msg.pofactory.TmInsCommMsgPOFactory;

/**
 * 类名称:QueryCommMsgDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:54:18
 * 修改备注:
 * @version 1.0.0
 */
public class QueryCommMsgDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String insCommMsgId=atx.getStringValue("COMM_MSG_ID");
		//check param
		if(StringUtils.isBlank(insCommMsgId)){
			logger.error(" PARAM INS_COMM_MSG_ID IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_DETAIL_ACTION_ERR_1000", "查询公告明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		DynaBean bean=TmInsCommMsgPOFactory.queryCommMsgDetail(conn, Integer.valueOf(insCommMsgId));
		
		if(bean!=null){
			
			atx.setObjValue("COMM_MSG_BEAN", bean);
		}
		else{
			logger.error("公告不存在");
			atx.setErrorContext("MESSAGE_COMM_MSG_DETAIL_ACTION_ERR_2000", "查询公告明细：公告不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
