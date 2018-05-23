/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:ModObjItemAction.java
 * �� ������:2018��3��18��-����11:17:34
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModObjItemAction
 * ������:ͣ�����ü����Ŀ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��18�� ����11:17:34
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModObjItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		Integer itemId=atx.getIntValue("OBJ_ITEM_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==itemId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_1000", "��������Ŀ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_ITEM
		TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
		itemPOCon.setStatus("1");
		itemPOCon.setId(itemId);
		
		TmInsCheckObjItemPO objItemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		
		if(objItemPOResult==null){
			logger.error(" OBJ_ITEM NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_2000", "��������Ŀ�������Ŀ������", null);
			return 0;
		}
		
		int version=objItemPOResult.getVer();
		itemPOCon.setVer(version);
		
		TmInsCheckObjItemPO objItemPOValue=new TmInsCheckObjItemPO();
		objItemPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objItemPOValue.setUpdateTime(YBUtility.now());
		objItemPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objItemPOResult.getFreezeTag())){
				logger.error(" OBJ_ITEM IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3001", "��������Ŀ�������Ŀ�Ѿ���ͣ��״̬", null);
				return 0;
			}
			//TM_INS_CHECK_OBJ_ITEM CHELD CHECK
			TmInsCheckObjItemPO itemTempPOCon=new TmInsCheckObjItemPO();
			itemTempPOCon.setStatus("1");
			itemTempPOCon.setFreezeTag("0");
			itemTempPOCon.setCheckObjFCode(objItemPOResult.getCheckObjCode());
			
			if(POFactory.getByPriKey(conn, itemTempPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3020", "��������Ŀ������δͣ�õļ����Ŀ����", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_UNV;
			objItemPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objItemPOResult.getFreezeTag())){
				logger.error(" OBJ_ITEM IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3002", "��������Ŀ�������Ŀ�Ѿ�������״̬", null);
				return 0;
			}
			//����Ƿ�����Ч����ͬ����ļ����Ŀ
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjItemPO itemPOConTemp=new TmInsCheckObjItemPO();
			itemPOConTemp.setStatus("1");
			itemPOConTemp.setFreezeTag("0");
			itemPOConTemp.setCheckObjCode(objItemPOResult.getCheckObjCode());
			
			TmInsCheckObjItemPO objItemPOResultTemp=POFactory.getByPriKey(conn, itemPOConTemp);
			
			if(objItemPOResultTemp!=null){
				logger.error(" OBJ_ITEM SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3003", "��������Ŀ��������ͬ��������Ŀ", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_VAL;
			objItemPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOConTemp=new TmInsCheckObjItemPO();
			itemPOConTemp.setStatus("1");
			itemPOConTemp.setCheckObjFCode(objItemPOResult.getCheckObjCode());
			
			if(POFactory.getByPriKey(conn, itemPOConTemp)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3030", "��������Ŀ������δɾ���ļ����Ŀ", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_DEL;
			objItemPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3010", "��������Ŀ����֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, itemPOCon,objItemPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objItemPOResult.getCheckObjCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_8000", "��������Ŀ������["+objItemPOResult.getCheckObjCode()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "�����Ŀ["+objItemPOResult.getCheckObjCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "�����Ŀ["+objItemPOResult.getCheckObjCode()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
