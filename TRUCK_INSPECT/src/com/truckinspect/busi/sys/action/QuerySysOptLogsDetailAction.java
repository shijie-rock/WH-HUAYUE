/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.sys.action
 * ��   ��  ��:QuerySysOptLogsDetailAction.java
 * �� ������:2017��12��7��-����4:32:10
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
 * ������:QuerySysOptLogsDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��7�� ����4:32:10
 * �޸ı�ע:
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
			atx.setErrorContext("SYS_OPT_LOGS_QUERY_DETAIL_ACTION_ERR_1000", "��ѯϵͳ������־��ϸ������Ϊ��", null);
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
			logger.error("ϵͳ������־������");
			atx.setErrorContext("SYS_OPT_LOGS_QUERY_DETAIL_ACTION_ERR_2000", "��ѯϵͳ������־��ϸ��ϵͳ������־������", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
