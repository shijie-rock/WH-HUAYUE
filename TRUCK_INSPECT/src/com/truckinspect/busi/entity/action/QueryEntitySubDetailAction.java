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
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;

/**
 * ������:QueryEntitySubDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryEntitySubDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSubId=atx.getIntValue("ENT_SUB_ID",0);
		//check param
		if(entSubId==0){
			logger.error(" PARAM ENTITY_SUB_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_DETAIL_ACTION_ERR_1000", "��ѯ������С����ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_ENTITY_SUB
		TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
		entSubPOCon.setStatus("1");
		entSubPOCon.setId(entSubId);
		entSubPOCon=POFactory.getByPriKey(conn, entSubPOCon);
		
		if(entSubPOCon!=null){
			atx.setObjValue("ENT_SUB_BEAN", entSubPOCon);
		}
		else{
			logger.error("������С�಻����");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_DETAIL_ACTION_ERR_2000", "��ѯ������С����ϸ��������С�಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
