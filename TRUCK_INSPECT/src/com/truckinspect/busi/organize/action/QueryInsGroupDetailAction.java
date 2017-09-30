/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryDetailInsGroupAction.java
 * �� ������:2017��8��20��-����5:54:18
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;

/**
 * ������:QueryInsGroupDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:54:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsGroupDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String insGroupId=atx.getStringValue("INS_GROUP_ID");
		//check param
		if(StringUtils.isBlank(insGroupId)){
			logger.error(" PARAM INS_GROUP_ID IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_DETAIL_ACTION_ERR_1000", "��ѯ�������ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmInspactGroupPO insGroupPOCon=new TmInspactGroupPO();
		insGroupPOCon.setStatus("1");
		insGroupPOCon.setId(Integer.valueOf(insGroupId));
		insGroupPOCon=POFactory.getByPriKey(conn, insGroupPOCon);
		
		if(insGroupPOCon!=null){
			
			atx.setObjValue("INS_GROUP_BEAN", insGroupPOCon);
		}
		else{
			logger.error("����鲻����");
			atx.setErrorContext("ORGANIZE_INS_GROUP_DETAIL_ACTION_ERR_2000", "��ѯ�������ϸ������鲻����", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
