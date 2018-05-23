/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryOrderCheckItemTreeAction.java
 * �� ������:2018��4��8��-����5:13:17
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:QueryOrderCheckItemTreeAction
 * ������:��ѯ�����Ŀ��json����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��4��8�� ����5:13:17
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryOrderCheckItemTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
//		String checkOrderNo=atx.getStringValue("ORDER_NO");//��鵥��
		String entTypeCode=atx.getStringValue("ENT_TYPE_CODE","CET_0010");//����������-��(ȱʡ)����������
	
		Connection conn=atx.getConnection();
		String returnTreeJson=TmInsCheckObjClassPOFactory.getSupMidSubTreeViewStringByEntCheckType(conn,entTypeCode);
		if(StringUtils.isNotBlank(returnTreeJson)){
			atx.setStringValue("TREE_DATA", returnTreeJson);//���νṹ
		}
//		if(StringUtils.isNotBlank(checkOrderNo)){
////			TM_INS_CHECK_ORDER_ITEM
//			TmInsCheckOrderItemPO orderItemPOCon=new TmInsCheckOrderItemPO();
//			orderItemPOCon.setStatus("1");
//			orderItemPOCon.setFreezeTag("0");
//			orderItemPOCon.setCheckOrderNo(checkOrderNo);
//			
//			List<TmInsCheckOrderItemPO> itemList=POFactory.select(conn, orderItemPOCon);
//			
//			if(itemList!=null&&itemList.size()>0){
//				atx.setArrayValue("ITEM_LIST", itemList.toArray());//��鵥�����������Ŀ
//			}
//		}
		return 1;
	}
}
