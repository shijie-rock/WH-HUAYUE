/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.sys.action
 * 文   件  名:QuerySysOptLogsDetailAction.java
 * 创 建日期:2017年12月7日-下午4:32:10
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.sys.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TbOptLogsPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;

/**
 * 类名称:QuerySysOptLogsDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年12月7日 下午4:32:10
 * 修改备注:
 * @version 1.0.0
 */
public class QuerySysOptLogsDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String logsId=atx.getStringValue("LOGS_ID");
		//check param
		if(StringUtils.isBlank(logsId)){
			logger.error(" PARAM INS_POSITION_ID IS EMTPY .");
			atx.setErrorContext("SYS_OPT_LOGS_QUERY_DETAIL_ACTION_ERR_1000", "查询系统操作日志明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TB_OPT_LOGS
		TbOptLogsPO logsPOCon=new TbOptLogsPO();
		logsPOCon.setStatus("1");
		logsPOCon.setId(Integer.valueOf(logsId));
		logsPOCon=POFactory.getByPriKey(conn, logsPOCon);
		
		if(logsPOCon!=null){
			atx.setObjValue("OPT_LOGS_BEAN", logsPOCon);
		}
		else{
			logger.error("系统操作日志不存在");
			atx.setErrorContext("SYS_OPT_LOGS_QUERY_DETAIL_ACTION_ERR_2000", "查询系统操作日志明细：系统操作日志不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
