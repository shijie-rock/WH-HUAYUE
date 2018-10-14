/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.mobile.busi.action
 * ��   ��  ��:MobileQueryCheckOrderListAction.java
 * �� ������:2018��10��10��-����8:20:33
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.mobile.busi.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * ������:MobileQueryCheckOrderListAction
 * ������:�ƶ��˲�ѯ��鵥�б�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��10�� ����8:20:33
 * �޸ı�ע:
 * @version 1.0.0
 */
public class MobileQueryCheckOrderListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkMemberId=atx.getStringValue("CHECK_MEMBER_ID");//�����id��ǰ̨����
		int pageNo=atx.getIntValue("PAGE",1);//Ҫ��ѯ��ҳ����,Ĭ�ϲ�ѯ��һҳ
		
		String queryType=atx.getStringValue("QUERY_TYPE");//1:δ��,2������,3���Ѽ�
		
		//check param
		if(StringUtils.isBlank(queryType)||StringUtils.isBlank(checkMemberId)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MOBILE_QUERY_CHECK_ORDER_LIST_ACTION_ERR_1000", "�ƶ��˲�ѯ��鵥�б�����Ϊ��", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckOrderPOFactory.mobileQueryCheckOrderList(conn, Integer.valueOf(checkMemberId), queryType, pageNo);
		
		if(list!=null&&list.size()>0){
			atx.setArrayValue("CHECK_ORDER_LIST", list.toArray());
		}
		
		return 1;
	}

}
