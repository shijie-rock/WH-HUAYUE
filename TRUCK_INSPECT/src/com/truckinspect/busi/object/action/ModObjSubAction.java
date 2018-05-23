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
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModObjSubAction
 * ������:ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModObjSubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSubId=atx.getIntValue("OBJ_SUB_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==objSubId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_1000", "�����������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setId(objSubId);
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		
		if(objSubPOResult==null){
			logger.error(" OBJ_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_2000", "�����������ࣺ�������಻����", null);
			return 0;
		}
		
		int version=objSubPOResult.getVer();
		objSubPOCon.setVer(version);
		
		TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
		objSubPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objSubPOValue.setUpdateTime(YBUtility.now());
		objSubPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objSubPOResult.getFreezeTag())){
				logger.error(" OBJ_SUB IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3001", "�����������ࣺ���������Ѿ���ͣ��״̬", null);
				return 0;
			}
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
			itemPOCon.setStatus("1");
			itemPOCon.setFreezeTag("0");
			itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, itemPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3020", "�����������ࣺ����δͣ�õļ����Ŀ", null);
				return 0;
			}
			
			
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_UNV;
			objSubPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objSubPOResult.getFreezeTag())){
				logger.error(" OBJ_SUB IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3002", "�����������ࣺ���������Ѿ�������״̬", null);
				return 0;
			}
			//����Ƿ�����Ч����ͬ����ļ����Ŀ������
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOConTemp=new TmInsCheckObjClassSubPO();
			objSubPOConTemp.setStatus("1");
			objSubPOConTemp.setFreezeTag("0");
			objSubPOConTemp.setObjClassCode(objSubPOResult.getObjClassCode());
			
			TmInsCheckObjClassSubPO objSubPOResultTemp=POFactory.getByPriKey(conn, objSubPOConTemp);
			
			if(objSubPOResultTemp!=null){
				logger.error(" OBJ_SUB SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3003", "�����������ࣺ������ͬ��������Ŀ������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_VAL;
			objSubPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
			itemPOCon.setStatus("1");
			itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, itemPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3030", "�����������ࣺ����δɾ���ļ����Ŀ", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_DEL;
			objSubPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3010", "�����������ࣺ��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, objSubPOCon,objSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSubPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_8000", "�����������ࣺ����["+objSubPOResult.getObjClassCode()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "��������["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "��������["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
