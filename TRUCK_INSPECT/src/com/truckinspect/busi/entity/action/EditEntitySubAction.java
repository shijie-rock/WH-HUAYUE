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
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditEntitySubAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditEntitySubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSubId=atx.getIntValue("ENT_SUB_ID",0);
		
		String entSubName=atx.getStringValue("ENT_SUB_NAME");
//		String entSubCode=atx.getStringValue("ENT_SUB_CODE");
		String entMidCode=atx.getStringValue("ENT_MID_CODE");//һ���������
		String entSubDesc=atx.getStringValue("ENT_SUB_DESC");
		Integer entSubSort=atx.getIntValue("ENT_SUB_SORT",1000);//����˳��
		
		//check param
		if(entSubId==0||StringUtils.isBlank(entSubName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_EDIT_ACTION_ERR_1000", "�༭������С�ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_ENTITY_SUB
		TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
		entSubPOCon.setStatus("1");
		entSubPOCon.setId(entSubId);
		
		TmInsCheckEntitySubPO entSubPOResult=POFactory.getByPriKey(conn, entSubPOCon);
		
		if(entSubPOResult==null){
			logger.error(" ENT_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_EDIT_ACTION_ERR_2000", "�༭������С�ࣺ������С�಻����", null);
			return 0;
		}
		
		int version=entSubPOResult.getVer();
		entSubPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckEntitySubPO entSubPOValue=new TmInsCheckEntitySubPO();
		
		entSubPOValue.setUpdateBy(optMemberId);
		entSubPOValue.setUpdateTime(YBUtility.now());
		entSubPOValue.setVer(version+1);
		
		entSubPOValue.setSort(entSubSort);
		entSubPOValue.setCheckEntDesc(entSubDesc);
		entSubPOValue.setCheckEntName(entSubName);
		entSubPOValue.setCheckEntFCode(entMidCode);
		
		int excuteResult=POFactory.update(conn, entSubPOCon, entSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSubId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_EDIT_ACTION_ERR_8000", "�༭������С�ࣺ����ID["+entSubId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUB_MOD, "", "�༭������С��");
		
		//set return msg
		atx.setStringValue("MSG", "�༭������С��["+entSubPOResult.getCheckEntCode()+"]�ɹ�");
		return 1;
	}
}
