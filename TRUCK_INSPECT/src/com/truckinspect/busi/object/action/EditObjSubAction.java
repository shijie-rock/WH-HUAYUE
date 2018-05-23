/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:EditInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditObjSubAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditObjSubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSubId=atx.getIntValue("OBJ_SUB_ID",0);
		
		String objSubName=atx.getStringValue("OBJ_SUB_NAME");
//		String objSubCode=atx.getStringValue("OBJ_SUB_CODE");
		String objMidCode=atx.getStringValue("OBJ_MID_CODE");//�����������
		String objSubDesc=atx.getStringValue("OBJ_SUB_DESC");
		Integer objSubSort=atx.getIntValue("OBJ_SUB_SORT",1000);//����˳��
		
		//check param
		if(objSubId==0||StringUtils.isBlank(objSubName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_EDIT_ACTION_ERR_1000", "�༭�������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setId(objSubId);
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		
		if(objSubPOResult==null){
			logger.error(" OBJ_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_EDIT_ACTION_ERR_2000", "�༭�������ࣺ�������಻����", null);
			return 0;
		}
		
		int version=objSubPOResult.getVer();
		objSubPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
		
		objSubPOValue.setUpdateBy(optMemberId);
		objSubPOValue.setUpdateTime(YBUtility.now());
		objSubPOValue.setVer(version+1);
		
		objSubPOValue.setSort(objSubSort);
		objSubPOValue.setObjClassDesc(objSubDesc);
		objSubPOValue.setObjClassName(objSubName);
		objSubPOValue.setObjClassFCode(objMidCode);
		
		int excuteResult=POFactory.update(conn, objSubPOCon, objSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSubId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_EDIT_ACTION_ERR_8000", "�༭�������ࣺ����ID["+objSubId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_MOD, "", "�༭��������");
		
		//set return msg
		atx.setStringValue("MSG", "�༭��������["+objSubPOResult.getObjClassCode()+"]�ɹ�");
		return 1;
	}
}
