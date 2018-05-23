/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:EditObjByTreeAction.java
 * �� ������:2018��3��6��-����4:46:50
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditObjByTreeAction
 * ������:��״�ṹ�༭��Ŀ����(���ı�㼶�ṹ)
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��6�� ����4:46:50
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditObjByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objId=atx.getIntValue("OBJ_ID",0);
		Integer objSort=atx.getIntValue("OBJ_SORT",1000);
		String objName=atx.getStringValue("OBJ_NAME");
		String objDesc=atx.getStringValue("OBJ_DESC");
		String objLevel=atx.getStringValue("OBJ_LEVEL");
		
		//check param
		if(objId==0||StringUtils.isBlank(objName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_1000", "�༭��Ŀ���ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		if("1".equals(objLevel)){
//			TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setId(objId);
			
			TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
			if(objSupPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_2000", "�༭��Ŀ���ࣺһ������ID["+objId+"]������", null);
				return 0;
			}
			int version=objSupPOResult.getVer();
			objSupPOCon.setVer(version);
			
			TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
			
			objSupPOValue.setUpdateBy(optMemberId);
			objSupPOValue.setUpdateTime(YBUtility.now());
			objSupPOValue.setVer(version+1);
			
			objSupPOValue.setSort(objSort);
			objSupPOValue.setObjClassDesc(objDesc);
			objSupPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objSupPOCon, objSupPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_3000", "�༭��Ŀ���ࣺһ����������ID["+objId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_MOD, "", "�༭��Ŀ���ࣺ�༭һ������");
			//set return msg
			atx.setStringValue("MSG", "�༭��Ŀ���ࣺһ������ID["+objId+"]�ɹ�");
			
		}else if("2".equals(objLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setId(objId);
			
			TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
			if(objMidPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_4000", "�༭��Ŀ���ࣺ��������ID["+objId+"]������", null);
				return 0;
			}
			int version=objMidPOResult.getVer();
			objMidPOCon.setVer(version);
			
			TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
			
			objMidPOValue.setUpdateBy(optMemberId);
			objMidPOValue.setUpdateTime(YBUtility.now());
			objMidPOValue.setVer(version+1);
			
			objMidPOValue.setSort(objSort);
			objMidPOValue.setObjClassDesc(objDesc);
			objMidPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objMidPOCon, objMidPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_5000", "�༭��Ŀ���ࣺ������������ID["+objId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_MOD, "", "�༭��Ŀ���ࣺ�༭��������");
			//set return msg
			atx.setStringValue("MSG", "�༭��Ŀ���ࣺ��������ID["+objId+"]�ɹ�");
			
		}else if("3".equals(objLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
			objSubPOCon.setStatus("1");
			objSubPOCon.setId(objId);
			
			TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
			if(objSubPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_6000", "�༭��Ŀ���ࣺ��������ID["+objId+"]������", null);
				return 0;
			}
			int version=objSubPOResult.getVer();
			objSubPOCon.setVer(version);
			
			TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
			
			objSubPOValue.setUpdateBy(optMemberId);
			objSubPOValue.setUpdateTime(YBUtility.now());
			objSubPOValue.setVer(version+1);
			
			objSubPOValue.setSort(objSort);
			objSubPOValue.setObjClassDesc(objDesc);
			objSubPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objSubPOCon, objSubPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_7000", "�༭��Ŀ���ࣺ������������ID["+objId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_MOD, "", "�༭��Ŀ���ࣺ�༭��������");
			//set return msg
			atx.setStringValue("MSG", "�༭��Ŀ���ࣺ��������ID["+objId+"]�ɹ�");
			
		}else if("4".equals(objLevel)){ //4��TM_INS_CHECK_OBJ_ITEM
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_8000", "�༭��Ŀ���ࣺ�ݲ�֧�ֱ༭��Ŀ", null);
			return 0;
			
		}else{
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_9000", "�༭��Ŀ���ࣺδ֪��������", null);
			return 0;
			
		}
		
		return 1;
	}

}
