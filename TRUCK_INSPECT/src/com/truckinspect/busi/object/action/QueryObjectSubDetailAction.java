/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionDetailAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;

/**
 * ������:QueryObjectSubDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjectSubDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSubId=atx.getIntValue("OBJ_SUB_ID",0);
		//check param
		if(objSubId==0){
			logger.error(" PARAM OBJECT_SUB_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_DETAIL_ACTION_ERR_1000", "��ѯ����������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objMidPOCon=new TmInsCheckObjClassSubPO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setId(objSubId);
		objMidPOCon=POFactory.getByPriKey(conn, objMidPOCon);
		
		if(objMidPOCon!=null){
			atx.setObjValue("OBJ_SUB_BEAN", objMidPOCon);
		}
		else{
			logger.error("�������಻����");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_DETAIL_ACTION_ERR_2000", "��ѯ����������ϸ���������಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
