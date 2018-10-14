/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.msg.action
 * ��   ��  ��:QueryCommMsgForMainPageAction.java
 * �� ������:2018��10��9��-����6:17:02
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
 * ������:QueryCommMsgForMainPageAction
 * ������:��ҳ��ѯ�ѷ�������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��9�� ����6:17:02
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryCommMsgForMainPageAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		int dataCount=atx.getIntValue("DATA_COUNT",3);//��Ҫ��ѯ������
		
		List<DynaBean> list=null;
		Connection conn=atx.getConnection();
		list=TmInsCommMsgPOFactory.queryMainPageCommMsgList(conn, dataCount);
		if(list!=null){
			
			atx.setArrayValue("COMM_MSG_LIST", list.toArray());
		}
		
		return 1;
	}

}
