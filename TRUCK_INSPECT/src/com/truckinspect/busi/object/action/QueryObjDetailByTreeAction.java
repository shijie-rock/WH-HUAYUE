/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:QueryObjDetailByTreeAction.java
 * �� ������:2018��3��6��-����2:35:50
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:QueryObjDetailByTreeAction
 * ������:����״ͼ�в�ѯ�����Ŀ����ϸ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��6�� ����2:35:50
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjDetailByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String levelCode=atx.getStringValue("LEVEL");
		String upCode=atx.getStringValue("UP_CODE");
		String nowCode=atx.getStringValue("NOW_CODE");
		
		if(StringUtils.isBlank(levelCode)||StringUtils.isBlank(nowCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION_ERR_1000", "��ѯ�����Ŀ����ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		DynaBean bean=TmInsCheckObjClassPOFactory.queryCheckObjDetailByLevel(conn, levelCode, nowCode, upCode);
		
		if(bean!=null){
			atx.setObjValue("OBJECT_DETAIL_BEAN", bean);
		}
		else{
			logger.error(String.valueOf(levelCode)+"������["+nowCode+"]������");
			atx.setErrorContext("BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION_ERR_2000", "��ѯ�����Ŀ����ϸ��"+String.valueOf(levelCode)+"������["+nowCode+"]������", null);
			return 0;
		}
//		atx
		return 1;
	}

}
