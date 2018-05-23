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
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;

/**
 * ������:QueryObjectSupDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjectSupDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objId=atx.getStringValue("OBJ_SUP_ID");
		//check param
		if(StringUtils.isBlank(objId)){
			logger.error(" PARAM OBJECT_SUP_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_DETAIL_ACTION_ERR_1000", "��ѯһ��������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setId(Integer.valueOf(objId));
		objSupPOCon=POFactory.getByPriKey(conn, objSupPOCon);
		
		if(objSupPOCon!=null){
			atx.setObjValue("OBJ_SUP_BEAN", objSupPOCon);
		}
		else{
			logger.error("һ�����಻����");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_DETAIL_ACTION_ERR_2000", "��ѯһ��������ϸ��һ�����಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
