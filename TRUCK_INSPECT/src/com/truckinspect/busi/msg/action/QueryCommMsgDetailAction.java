/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryDetailInsGroupAction.java
 * �� ������:2017��8��20��-����5:54:18
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
 * ������:QueryCommMsgDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
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
			atx.setErrorContext("MESSAGE_COMM_MSG_DETAIL_ACTION_ERR_1000", "��ѯ������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		DynaBean bean=TmInsCommMsgPOFactory.queryCommMsgDetail(conn, Integer.valueOf(insCommMsgId));
		
		if(bean!=null){
			
			atx.setObjValue("COMM_MSG_BEAN", bean);
		}
		else{
			logger.error("���治����");
			atx.setErrorContext("MESSAGE_COMM_MSG_DETAIL_ACTION_ERR_2000", "��ѯ������ϸ�����治����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
