/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:AddInsPositionAction.java
 * �� ������:2017��8��20��-����5:52:59
 * Copyright @ 2017-YouBus.com.cn
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
 * ������:AddObjMidAction
 * ������:����Ŀ��״ͼ������Ŀ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddObjByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objName=atx.getStringValue("OBJ_NAME");//��Ŀ����
		String objCode=atx.getStringValue("OBJ_CODE");//��Ŀ����
		String objUpCode=atx.getStringValue("OBJ_UP_CODE");//�ϼ�����
		String objDesc=atx.getStringValue("OBJ_DESC");//��Ŀ˵��
		Integer objSort=atx.getIntValue("OBJ_SORT",1000);//����˳��
		String addLevel=atx.getStringValue("ADD_LEVEL");//Ҫ���ӵļ��𣬾������ĸ���;
		//1��TM_INS_CHECK_OBJ_CLASS_SUPPER
		//2��TM_INS_CHECK_OBJ_CLASS_MIDDLE
		//3��TM_INS_CHECK_OBJ_CLASS_SUB
		//4��TM_INS_CHECK_OBJ_ITEM
		
		//check param
		if(StringUtils.isBlank(objUpCode)||StringUtils.isBlank(objCode)||StringUtils.isBlank(objName)||StringUtils.isBlank(addLevel)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_1000", "�������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		if("1".equals(addLevel)){
//			TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
			if(objSupPOResult!=null){
				logger.error(" OBJECT SUP EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_ADD_ACTION_ERR_2000", "����һ�����ࣺһ���������["+objCode+"]�Ѿ�����", null);
				return 0;
			}
			objSupPOCon.setObjClassDesc(objDesc);
			objSupPOCon.setObjClassLevel(1);
			objSupPOCon.setObjClassName(objName);
			objSupPOCon.setSort(objSort);
			
			objSupPOCon.setCreateBy(optMemberId);
			objSupPOCon.setCreateTime(YBUtility.now());
			objSupPOCon.setFreezeTag("0");
			objSupPOCon.setId(POFactory.getIntPriKey(conn, objSupPOCon));
			objSupPOCon.setVer(1);
			
			POFactory.insert(conn, objSupPOCon);
			
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_ADD, "", "����һ������");
			//set return msg
			atx.setStringValue("MSG", "����һ������["+objCode+"]�ɹ�");
			
		}else if("2".equals(addLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
			if(objMidPOResult!=null){
				logger.error(" OBJECT MID EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_ADD_ACTION_ERR_2000", "�����������ࣺ�����������["+objCode+"]�Ѿ�����", null);
				return 0;
			}
			objMidPOCon.setObjClassDesc(objDesc);
			objMidPOCon.setObjClassLevel(2);
			objMidPOCon.setObjClassName(objName);
			objMidPOCon.setSort(objSort);
			objMidPOCon.setObjClassFCode(objUpCode);
			
			objMidPOCon.setCreateBy(optMemberId);
			objMidPOCon.setCreateTime(YBUtility.now());
			objMidPOCon.setFreezeTag("0");
			objMidPOCon.setId(POFactory.getIntPriKey(conn, objMidPOCon));
			objMidPOCon.setVer(1);
			
			POFactory.insert(conn, objMidPOCon);
			
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_ADD, "", "������������");
			
			//set return msg
			atx.setStringValue("MSG", "������������["+objCode+"]�ɹ�");
			
		}else if("3".equals(addLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
			objSubPOCon.setStatus("1");
			objSubPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
			if(objSubPOResult!=null){
				logger.error(" OBJECT SUB EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_2000", "�����������ࣺ�����������["+objCode+"]�Ѿ�����", null);
				return 0;
			}
			objSubPOCon.setObjClassDesc(objDesc);
			objSubPOCon.setObjClassLevel(3);
			objSubPOCon.setObjClassName(objName);
			objSubPOCon.setSort(objSort);
			objSubPOCon.setObjClassFCode(objUpCode);
			
			objSubPOCon.setCreateBy(optMemberId);
			objSubPOCon.setCreateTime(YBUtility.now());
			objSubPOCon.setFreezeTag("0");
			objSubPOCon.setId(POFactory.getIntPriKey(conn, objSubPOCon));
			objSubPOCon.setVer(1);
			
			POFactory.insert(conn, objSubPOCon);
			
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_ADD, "", "������������");
			//set return msg
			atx.setStringValue("MSG", "������������["+objCode+"]�ɹ�");
			
		}else if("4".equals(addLevel)){ //4��TM_INS_CHECK_OBJ_ITEM
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_2000", "�������ࣺ�ݲ�֧��������Ŀ", null);
			return 0;
			
		}else{
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_3000", "�������ࣺδ֪��������", null);
			return 0;
			
		}
		return 1;
	}

}
