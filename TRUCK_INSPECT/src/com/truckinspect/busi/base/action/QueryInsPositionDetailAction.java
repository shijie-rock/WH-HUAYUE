/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsPositionDetailAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;

/**
 * ������:QueryInsPositionDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsPositionDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionId=atx.getStringValue("POSITION_ID");
		//check param
		if(StringUtils.isBlank(positionId)){
			logger.error(" PARAM INS_POSITION_ID IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_DETAIL_ACTION_ERR_1000", "��ѯ���ص���ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_POSITION_INFO
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setId(Integer.valueOf(positionId));
		positionPOCon=POFactory.getByPriKey(conn, positionPOCon);
		
		if(positionPOCon!=null){
			
			atx.setObjValue("INS_POSITION_BEAN", positionPOCon);
		}
		else{
			logger.error("���ص㲻����");
			atx.setErrorContext("BASE_DATA_POSITION_DETAIL_ACTION_ERR_2000", "��ѯ���ص���ϸ�����ص㲻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
