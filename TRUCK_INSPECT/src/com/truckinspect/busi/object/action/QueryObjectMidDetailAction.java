/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionDetailAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;

/**
 * ������:QueryObjectMidDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjectMidDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objMidId=atx.getIntValue("OBJ_MID_ID",0);
		//check param
		if(objMidId==0){
			logger.error(" PARAM OBJECT_MID_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_DETAIL_ACTION_ERR_1000", "��ѯ����������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setId(objMidId);
		objMidPOCon=POFactory.getByPriKey(conn, objMidPOCon);
		
		if(objMidPOCon!=null){
			atx.setObjValue("OBJ_MID_BEAN", objMidPOCon);
		}
		else{
			logger.error("�������಻����");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_DETAIL_ACTION_ERR_2000", "��ѯ����������ϸ���������಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
