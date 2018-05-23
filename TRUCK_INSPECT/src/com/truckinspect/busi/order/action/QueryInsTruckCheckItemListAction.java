/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:QueryInsTruckCheckItemListAction.java
 * �� ������:2018��5��3��-����6:49:40
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
 * ������:QueryInsTruckCheckItemListAction
 * ������:������鵥ʱ���������棬ѡ��ĳ����ʱ�����ٴ���ѡ�������ϳ�����ͬʱ����
 * ����id����������Ӧ�ļ����Ŀ��ˢ�¼����Ŀ����
 * ������Ӧ�ļ����Ŀ��
 * 1�����Ȳ�ѯ TT_INS_TRUCK_SUB_OBJ_MAP ��С���Ӧ�ļ����Ŀ��
 * 2����û�У����ѯ �ó�����Ӧ����������TT_INS_TRUCK_MID_ENT_MAP ����Ӧ���������С�� TT_INS_CHECK_ENTITY_OBJ_SUB_MAP�����Ҷ�Ӧ�ļ����Ŀ��
 * 3�������������ĿС���Ӧ�����ռ����Ŀ����  TM_INS_CHECK_OBJ_ITEM
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��3�� ����6:49:40
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsTruckCheckItemListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckId=atx.getStringValue("TRUCK_ID");
		
		if(StringUtils.isBlank(truckId)||"undefined".equals(truckId)||"null".equals(truckId)){
			logger.error(" PARAM ORDER_NO IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_QUERY_TURCK_CHECK_OBJ_ITEM_ACTION_ERR_1000", "��ѯ���������Ŀ��ϸ����������IDΪ��", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckOrderPOFactory.queryTruckCheckItemByTruckId(conn, Integer.valueOf(truckId));
		if(list==null){
			list=TmInsCheckOrderPOFactory.queryTruckCheckItemTruckEntTypeByTruckId(conn, Integer.valueOf(truckId));
		}
		
		if(list!=null){
			atx.setArrayValue("TRUCK_CHECK_ITEM_LIST", list.toArray());
		}
		
		return 1;
	}

}
