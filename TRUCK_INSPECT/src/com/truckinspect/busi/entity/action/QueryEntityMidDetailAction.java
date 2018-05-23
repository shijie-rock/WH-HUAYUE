/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionDetailAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;

/**
 * ������:QueryEntityMidDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryEntityMidDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entMidId=atx.getIntValue("ENT_MID_ID",0);
		//check param
		if(entMidId==0){
			logger.error(" PARAM ENTITY_MID_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_DETAIL_ACTION_ERR_1000", "��ѯ������������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_ENTITY_MIDDLE
		TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
		entMidPOCon.setStatus("1");
		entMidPOCon.setId(entMidId);
		entMidPOCon=POFactory.getByPriKey(conn, entMidPOCon);
		
		if(entMidPOCon!=null){
			atx.setObjValue("ENT_MID_BEAN", entMidPOCon);
		}
		else{
			logger.error("���������಻����");
			atx.setErrorContext("BUSI_DATA_ENT_MID_DETAIL_ACTION_ERR_2000", "��ѯ������������ϸ�����������಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
