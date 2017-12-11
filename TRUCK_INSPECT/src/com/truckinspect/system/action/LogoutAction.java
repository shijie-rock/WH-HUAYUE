/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.system.action
 * ��   ��  ��:LogoutAction.java
 * �� ������:2017��12��5��-����3:52:41
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.system.action;

import java.sql.Connection;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBCommonContant;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:LogoutAction
 * ������:�û�ϵͳ�ǳ�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��5�� ����3:52:41
 * �޸ı�ע:
 * @version 1.0.0
 */
public class LogoutAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		//���µ�¼��Ϣ
		Integer optMemberId=YBUtility.getMemberId(atx);
		if(optMemberId==null){
			logger.error(" ���û���Ϣ�ǳ� .");
			return 1;
		}
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		memberPOValue.setUpdateBy(optMemberId);
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setOnlineStatus("0");
		
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(optMemberId);
		
		Connection conn=atx.getConnection();
		POFactory.update(conn, memberPOCon, memberPOValue);
		
		// ���û���Ϣ���SESSION
		atx.setStringValue(YBCommonContant.SESSION_ID, null);
		atx.setStringValue(YBCommonContant.SESSION_USER_ID, null); //TM_SYS_MEMBER id
		atx.setStringValue(YBCommonContant.SESSION_USER_NAME,null); //TM_SYS_MEMBER MEMBER_NAME
		atx.setStringValue(YBCommonContant.SESSION_USER_CODE,null); //TM_SYS_MEMBER MEMBER_CODE
		atx.setObjValue(YBCommonContant.SESSION_LOGIN_USER,null); //TM_SYS_MEMBER PO
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_LOGIN, "", "�û�["+optMemberName+"]�ǳ�ϵͳ");
		
		//set return msg
		atx.setStringValue("MSG", "�û�["+optMemberName+"]�ǳ��ɹ�");
		
		return 1;
	}

}
