/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:QueryMemberDetailAction.java
 * �� ������:2017��9��2��-����9:35:00
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;

/**
 * ������:QueryMemberDetailAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:35:00
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryMemberDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String memberId=atx.getStringValue("MEMBER_ID");
		//check param
		if(StringUtils.isBlank(memberId)){
			logger.error(" PARAM MEMBER_ID IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_DETAIL_ACTION_ERR_1000", "��ѯ�û���ϸ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(Integer.valueOf(memberId));
		memberPOCon=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOCon!=null){
			
			atx.setObjValue("MEMBER_BEAN", memberPOCon);
		}
		else{
			logger.error("�û�������");
			atx.setErrorContext("ORGANIZE_MEMBER_DETAIL_ACTION_ERR_2000", "��ѯ�û���ϸ���û�������", null);
			return 0;
		}
//		atx.setStringValue("MSG", "����ɹ�");
		return 1;
	}

}
