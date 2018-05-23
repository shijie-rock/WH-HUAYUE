/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryInsCheckOrderDetailAction.java
 * �� ������:2018��3��25��-����6:22:20
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * ������:QueryInsCheckOrderDetailAction
 * ������:�鿴��鵥��ϸ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��25�� ����6:22:20
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsCheckOrderDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("ORDER_NO");//��鵥��
		
		if(StringUtils.isBlank(checkOrderNo)){
			logger.error(" PARAM ORDER_NO IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_DETAIL_ACTION_ERR_1000", "��ѯ��鵥��ϸ��������鵥��Ϊ��", null);
			return 0;
		}
		
		if(StringUtils.isNotBlank(checkOrderNo)){
			Connection conn=atx.getConnection();
			//main TM_INS_CHECK_MAIN_ORDER
			DynaBean mainBean=TmInsCheckOrderPOFactory.queryInsCheckOrderDetail(conn, checkOrderNo);
			if(mainBean!=null){
				atx.setObjValue("CHECK_ORDER_BEAN", mainBean);
			}else{
				logger.error("��ѯ��鵥��ϸ����鵥��ϸΪ��");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_DETAIL_ACTION_ERR_2000", "��ѯ��鵥��ϸ����鵥��ϸΪ��", null);
				return 0;
			}
			
//			TM_INS_CHECK_ORDER_ITEM
			TmInsCheckOrderItemPO orderItemPOCon=new TmInsCheckOrderItemPO();
			orderItemPOCon.setStatus("1");
			orderItemPOCon.setFreezeTag("0");
			orderItemPOCon.setCheckOrderNo(checkOrderNo);
			
			List<TmInsCheckOrderItemPO> itemList=POFactory.select(conn, orderItemPOCon);
			
			if(itemList!=null&&itemList.size()>0){
				atx.setArrayValue("ITEM_LIST", itemList.toArray());//��鵥�����������Ŀ
			}
		}
		return 1;
	}

}
