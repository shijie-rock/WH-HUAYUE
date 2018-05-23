/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryBaseDataAutoCompleteAction.java
 * �� ������:2018��5��3��-����11:03:42
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * ������:QueryBaseDataAutoCompleteAction
 * ������:ģ�����룬��ѯ����ˣ����ص㣬��������Ϣ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��3�� ����11:03:42
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryBaseDataAutoCompleteAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String queryType=atx.getStringValue("QUERY_TYPE");//TARGET:�����������糵��;POSITION:���ص�����;CHECKER:���������;
		String name=atx.getStringValue("QUERY_NAME");//����ģ�������߿ո��
		
		if(StringUtils.isBlank(queryType)){
			
			logger.error("���ݲ�ѯ����ģ����ѯ����ѯ���Ͳ���Ϊ��");
			atx.setErrorContext("BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION_ERR_1000", "���ݲ�ѯ����ģ����ѯ����ѯ���Ͳ���Ϊ��", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=null;
		if(StringUtils.isNotBlank(queryType)){
			if("TARGET".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckTargetAutoComplete(conn, name);
			}else if("POSITION".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckPositionAutoComplete(conn, name);
				
			}else if("CHECKER".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckerInfoAutoComplete(conn, name);
			}else{
				logger.error("���ݲ�ѯ����ģ����ѯ����֧�ֲ�ѯ����");
				atx.setErrorContext("BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION_ERR_2000", "���ݲ�ѯ����ģ����ѯ����֧�ֲ�ѯ����", null);
				return 0;
			}
			if(list!=null){
				atx.setArrayValue("RESULT_LIST", list.toArray());
			}
		}
		return 1;
	}

}
