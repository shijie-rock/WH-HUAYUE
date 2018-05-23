/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:EditInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditEntitySupAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditEntitySupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSupId=atx.getIntValue("ENT_SUP_ID",0);
		
		String entSupName=atx.getStringValue("ENT_SUP_NAME");
		String entSupDesc=atx.getStringValue("ENT_SUP_DESC");
		String entType=atx.getStringValue("ENT_TYPE");
		Integer entSupSort=atx.getIntValue("ENT_SUP_SORT",1000);
		
		//check param
		if(entSupId==0||StringUtils.isBlank(entSupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_1000", "�༭��������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setId(entSupId);
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		
		if(entSupPOResult==null){
			logger.error(" ENT_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_2000", "�༭��������ࣺ��������಻����", null);
			return 0;
		}
		
		int version=entSupPOResult.getVer();
		entSupPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckEntitySupperPO entSupPOValue=new TmInsCheckEntitySupperPO();
		
		entSupPOValue.setUpdateBy(optMemberId);
		entSupPOValue.setUpdateTime(YBUtility.now());
		entSupPOValue.setVer(version+1);
		
		entSupPOValue.setSort(entSupSort);
		entSupPOValue.setCheckEntDesc(entSupDesc);
		entSupPOValue.setCheckEntName(entSupName);
		entSupPOValue.setCheckEntTypeCode(entType);
		
		int excuteResult=POFactory.update(conn, entSupPOCon, entSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_8000", "�༭��������ࣺ����ID["+entSupId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUP_MOD, "", "�༭���������");
		
		//set return msg
		atx.setStringValue("MSG", "�༭���������["+entSupPOResult.getCheckEntCode()+"]�ɹ�");
		return 1;
	}
}
