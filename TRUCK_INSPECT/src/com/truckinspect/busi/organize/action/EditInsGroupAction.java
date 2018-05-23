/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:EditInsGroupAction.java
 * �� ������:2017��8��20��-����5:53:16
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
 * ������:EditInsGroupAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditInsGroupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String insGroupId=atx.getStringValue("INS_GROUP_ID");
		String insGroupName=atx.getStringValue("INS_GROUP_NAME");
		String insGroupDesc=atx.getStringValue("INS_GROUP_DESC","");
		
		//check param
		if(StringUtils.isBlank(insGroupId)||StringUtils.isBlank(insGroupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_1000", "�༭����飺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmInspactGroupPO insGroupPOCon2=new TmInspactGroupPO();
		insGroupPOCon2.setStatus("1");
		insGroupPOCon2.setInsGroupName(insGroupName);
		
		TmInspactGroupPO insGroupPOResult=POFactory.getByPriKey(conn, insGroupPOCon2);
		
		if(insGroupPOResult!=null&&insGroupPOResult.getId().intValue()!=Integer.valueOf(insGroupId)){
			logger.error(" INS_GROUP NAME EXIST .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_3000", "�༭����飺���������["+insGroupName+"]�Ѵ���", null);
			return 0;
		}
		
		//check repeat
		TmInspactGroupPO insGroupPOCon=new TmInspactGroupPO();
		insGroupPOCon.setStatus("1");
		insGroupPOCon.setId(Integer.valueOf(insGroupId));
		
		insGroupPOResult=POFactory.getByPriKey(conn, insGroupPOCon);
		
		if(insGroupPOResult==null){
			logger.error(" INS_GROUP NOT EXIST .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_2000", "�༭����飺����鲻����", null);
			return 0;
		}
		
		int version=insGroupPOResult.getVer();
		insGroupPOCon.setVer(version);

		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInspactGroupPO insGroupPOValue=new TmInspactGroupPO();
		
		insGroupPOValue.setUpdateBy(optMemberId);
		insGroupPOValue.setUpdateTime(YBUtility.now());
		insGroupPOValue.setInsGroupName(insGroupName);
		insGroupPOValue.setInsGroupDesc(insGroupDesc);
		insGroupPOValue.setVer(version+1);
		
		int excuteResult=POFactory.update(conn, insGroupPOCon, insGroupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+insGroupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_8000", "�༭����飺����ID["+insGroupId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_MOD, "", "�༭�����");
		
		//set return msg
		atx.setStringValue("MSG", "�༭�����["+insGroupName+"]�ɹ�");
		return 1;
	}
}
