/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionDetailAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;

/**
 * ������:QueryEntitySupDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryEntitySupDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entId=atx.getStringValue("ENT_SUP_ID");
		//check param
		if(StringUtils.isBlank(entId)){
			logger.error(" PARAM ENTITY_SUP_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_DETAIL_ACTION_ERR_1000", "��ѯ�����������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setId(Integer.valueOf(entId));
		entSupPOCon=POFactory.getByPriKey(conn, entSupPOCon);
		
		if(entSupPOCon!=null){
			atx.setObjValue("ENT_SUP_BEAN", entSupPOCon);
		}
		else{
			logger.error("��������಻����");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_DETAIL_ACTION_ERR_2000", "��ѯ�����������ϸ����������಻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
