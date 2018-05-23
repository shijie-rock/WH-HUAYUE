/**
 * ��Ŀ�� ��:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryMemberDetailAction.java
 * �� ������:2017��9��2��-����9:35:00
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.base.po.TtInsTruckMidEntMapPO;
import com.truckinspect.busi.base.po.TtInsTruckSubObjMapPO;
import com.truckinspect.busi.entity.pofactory.TmInsCheckEntityPOFactory;

/**
 * ������:QueryInsTruckDetailAction
 * ������:��ѯ����ͼƬ�б�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:35:00
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsTruckDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckId=atx.getStringValue("TRUCK_ID");
		//check param
		if(StringUtils.isBlank(truckId)){
			logger.error(" PARAM TRUCK_ID IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_DETAIL_ACTION_ERR_1000", "��ѯ������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		TM_INS_TRUCK_INFO
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO(); 
		truckPOCon.setStatus("1");
		truckPOCon.setId(Integer.valueOf(truckId));
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult==null){
			logger.error("����������");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_DETAIL_ACTION_ERR_2000", "��ѯ������ϸ������������", null);
			return 0;
		}
		
		atx.setObjValue("INS_TRUCK_BEAN", truckPOResult);
		
		//TT_INS_TRUCK_MID_ENT_MAP
		TtInsTruckMidEntMapPO entMapPOCon=new TtInsTruckMidEntMapPO();
		entMapPOCon.setStatus("1");
		entMapPOCon.setFreezeTag("0");
		entMapPOCon.setTruckId(truckPOResult.getId());
		List<TtInsTruckMidEntMapPO> entList=POFactory.select(conn, entMapPOCon);
		
		if(entList!=null&&entList.size()>0){
			atx.setArrayValue("ENT_MAP_LIST", entList.toArray());
		}
		
		
		//TT_INS_TRUCK_SUB_OBJ_MAP
		TtInsTruckSubObjMapPO subMapPOCon=new TtInsTruckSubObjMapPO();
		subMapPOCon.setStatus("1");
		subMapPOCon.setFreezeTag("0");
		subMapPOCon.setTruckId(truckPOResult.getId());
		List<TtInsTruckSubObjMapPO> subList=POFactory.select(conn, subMapPOCon);
		
		if(subList==null||subList.size()<1){
			subList=TmInsCheckEntityPOFactory.querySubObjBySubEnt(conn, truckPOResult.getId());
		}
		
		if(subList!=null&&subList.size()>0){
			atx.setArrayValue("OBJ_MAP_LIST", subList.toArray());
		}

		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
