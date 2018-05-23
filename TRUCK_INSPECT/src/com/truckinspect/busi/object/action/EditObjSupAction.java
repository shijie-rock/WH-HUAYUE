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
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditObjSupAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditObjSupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSupId=atx.getIntValue("OBJ_SUP_ID",0);
		
		String objSupName=atx.getStringValue("OBJ_SUP_NAME");
		String objSupDesc=atx.getStringValue("OBJ_SUP_DESC");
		Integer objSupSort=atx.getIntValue("OBJ_SUP_SORT",1000);
		
		//check param
		if(objSupId==0||StringUtils.isBlank(objSupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_1000", "�༭һ�����ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setId(objSupId);
		
		TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
		
		if(objSupPOResult==null){
			logger.error(" OBJ_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_2000", "�༭һ�����ࣺһ�����಻����", null);
			return 0;
		}
		
		int version=objSupPOResult.getVer();
		objSupPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
		
		objSupPOValue.setUpdateBy(optMemberId);
		objSupPOValue.setUpdateTime(YBUtility.now());
		objSupPOValue.setVer(version+1);
		
		objSupPOValue.setSort(objSupSort);
		objSupPOValue.setObjClassDesc(objSupDesc);
		objSupPOValue.setObjClassName(objSupName);
		
		int excuteResult=POFactory.update(conn, objSupPOCon, objSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_8000", "�༭һ�����ࣺ����ID["+objSupId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_MOD, "", "�༭һ������");
		
		//set return msg
		atx.setStringValue("MSG", "�༭һ������["+objSupPOResult.getObjClassCode()+"]�ɹ�");
		return 1;
	}
}
