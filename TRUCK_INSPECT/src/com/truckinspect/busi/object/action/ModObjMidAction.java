/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:ModInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModObjMidAction
 * ������:ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModObjMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objMidId=atx.getIntValue("OBJ_MID_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==objMidId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_1000", "����������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setId(objMidId);
		
		TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
		
		if(objMidPOResult==null){
			logger.error(" OBJ_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_2000", "����������ࣺ�������಻����", null);
			return 0;
		}
		
		int version=objMidPOResult.getVer();
		objMidPOCon.setVer(version);
		
		TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
		objMidPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objMidPOValue.setUpdateTime(YBUtility.now());
		objMidPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objMidPOResult.getFreezeTag())){
				logger.error(" OBJ_MID IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3001", "����������ࣺ���������Ѿ���ͣ��״̬", null);
				return 0;
			}
			
//			TM_INS_CHECK_OBJ_CLASS_SUB,���������δͣ�ã�����ͣ��
			TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
			midPOCon.setFreezeTag("0");
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3020", "����������ࣺ����δͣ�õ���������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_UNV;
			objMidPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objMidPOResult.getFreezeTag())){
				logger.error(" OBJ_MID IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3002", "����������ࣺ���������Ѿ�������״̬", null);
				return 0;
			}
			//����Ƿ�����Ч����ͬ����ļ����Ŀ������
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOConTemp=new TmInsCheckObjClassMiddlePO();
			objMidPOConTemp.setStatus("1");
			objMidPOConTemp.setFreezeTag("0");
			objMidPOConTemp.setObjClassCode(objMidPOResult.getObjClassCode());
			
			TmInsCheckObjClassMiddlePO objMidPOResultTemp=POFactory.getByPriKey(conn, objMidPOConTemp);
			
			if(objMidPOResultTemp!=null){
				logger.error(" OBJ_MID SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3003", "����������ࣺ������ͬ��������Ŀ������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_VAL;
			objMidPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB,���������δͣ�ã�����ͣ��
			TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3030", "����������ࣺ����δɾ������������", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_DEL;
			objMidPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3010", "����������ࣺ��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, objMidPOCon,objMidPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objMidPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_8000", "����������ࣺ����["+objMidPOResult.getObjClassCode()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "��������["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "��������["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
