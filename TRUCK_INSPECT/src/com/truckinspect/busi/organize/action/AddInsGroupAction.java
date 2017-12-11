/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:AddInsGroupAction.java
 * �� ������:2017��8��20��-����5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddInsGroupAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddInsGroupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String groupName=atx.getStringValue("INS_GROUP_NAME");
		String groupDesc=atx.getStringValue("INS_GROUP_DESC");
		
		//check param
		if(StringUtils.isBlank(groupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_ADD_ACTION_ERR_1000", "��������飺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInspactGroupPO groupPOCon=new TmInspactGroupPO();
		groupPOCon.setStatus("1");
		groupPOCon.setInsGroupName(groupName);
		
		TmInspactGroupPO groupPOResult=POFactory.getByPriKey(conn, groupPOCon);
		if(groupPOResult!=null){
			logger.error(" INS_GROUP EXIST ALREADY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_ADD_ACTION_ERR_2000", "��������飺�����["+groupName+"]�Ѿ�����", null);
			return 0;
		}
		
		groupPOCon.setCreateBy(optMemberId);
		groupPOCon.setCreateTime(YBUtility.now());
		groupPOCon.setFreezeTag("0");
		groupPOCon.setId(POFactory.getIntPriKey(conn, groupPOCon));
		groupPOCon.setInsGroupDesc(groupDesc);
		groupPOCon.setVer(1);
		
		POFactory.insert(conn, groupPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_ADD, "", "���������["+groupName+"]");
		
		//set return msg
		atx.setStringValue("MSG", "���������["+groupName+"]�ɹ�");
		return 1;
	}

}
