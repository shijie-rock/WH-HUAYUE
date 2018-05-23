/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:QueryObjectItemDetailAction.java
 * �� ������:2018��3��13��-����10:07:54
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjItemPOFactory;

/**
 * ������:QueryObjectItemDetailAction
 * ������:��ѯ�����Ŀ��ϸ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��13�� ����10:07:54
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjectItemDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer itemId=atx.getIntValue("OBJ_ITEM_ID",0);
		
		if(itemId==0){
			logger.error(" PARAM OBJECT_ITEM_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_DETAIL_ACTION_ERR_1000", "��ѯ�����Ŀ��ϸ������Ϊ��", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		
		DynaBean itemBean=TmInsCheckObjItemPOFactory.queryObjItemDetail(conn, itemId);
		if(itemBean!=null){
			atx.setObjValue("OBJ_ITEM_BEAN", itemBean);
		}
		else{
			logger.error("�����Ŀ������");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_DETAIL_ACTION_ERR_2000", "��ѯ�����Ŀ��ϸ��ά����Ŀ������", null);
			return 0;
		}
		
		return 1;
	}

}
