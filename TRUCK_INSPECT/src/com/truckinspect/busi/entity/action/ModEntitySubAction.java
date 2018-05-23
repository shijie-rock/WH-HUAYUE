/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:ModInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TtInsTruckMidEntMapPO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModEntitySubAction
 * ������:ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModEntitySubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSubId=atx.getIntValue("ENT_SUB_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==entSubId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_1000", "���������С�ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_ENTITY_SUB
		TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
		entSubPOCon.setStatus("1");
		entSubPOCon.setId(entSubId);
		
		TmInsCheckEntitySubPO entSubPOResult=POFactory.getByPriKey(conn, entSubPOCon);
		
		if(entSubPOResult==null){
			logger.error(" ENT_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_2000", "���������С�ࣺ������С�಻����", null);
			return 0;
		}
		
		int version=entSubPOResult.getVer();
		entSubPOCon.setVer(version);
		
		TmInsCheckEntitySubPO entSubPOValue=new TmInsCheckEntitySubPO();
		entSubPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		entSubPOValue.setUpdateTime(YBUtility.now());
		entSubPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(entSubPOResult.getFreezeTag())){
				logger.error(" ENT_SUB IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3001", "���������С�ࣺ������С���Ѿ���ͣ��״̬", null);
				return 0;
			}
			//У�鳵����Ӧ����С���
//			TODO
//			TT_INS_TRUCK_MID_ENT_MAP
			TtInsTruckMidEntMapPO truckEntMapPOCon=new TtInsTruckMidEntMapPO();
			truckEntMapPOCon.setStatus("1");
			truckEntMapPOCon.setFreezeTag("0");
			truckEntMapPOCon.setCheckEntSubCode(entSubPOResult.getCheckEntCode());
			
			TtInsTruckMidEntMapPO truckEntMapPOTemp=POFactory.getByPriKey(conn,truckEntMapPOCon);
			
			if(truckEntMapPOTemp!=null){
				logger.error(" ENT_SUB EXIST TRUCK_MAP .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3004", "���������С�ࣺ���ڳ�����Ӧ������С��", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_UNV;
			entSubPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(entSubPOResult.getFreezeTag())){
				logger.error(" ENT_SUB IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3002", "���������С�ࣺ������С���Ѿ�������״̬", null);
				return 0;
			}
			
			//����Ƿ�����Ч����ͬ����ļ�����С��
//			TM_INS_CHECK_ENTITY_SUB
			TmInsCheckEntitySubPO entSubPOConTemp=new TmInsCheckEntitySubPO();
			entSubPOConTemp.setStatus("1");
			entSubPOConTemp.setFreezeTag("0");
			entSubPOConTemp.setCheckEntCode(entSubPOResult.getCheckEntCode());
			
			TmInsCheckEntitySubPO entSubPOResultTemp=POFactory.getByPriKey(conn, entSubPOConTemp);
			
			if(entSubPOResultTemp!=null){
				logger.error(" ENT_SUB SAME ENT_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3003", "���������С�ࣺ������ͬ���������С��", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_VAL;
			entSubPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//У�鳵����Ӧ����С���
//			TODO
//			TT_INS_TRUCK_MID_ENT_MAP
			TtInsTruckMidEntMapPO truckEntMapPOCon=new TtInsTruckMidEntMapPO();
			truckEntMapPOCon.setStatus("1");
			truckEntMapPOCon.setFreezeTag("0");
			truckEntMapPOCon.setCheckEntSubCode(entSubPOResult.getCheckEntCode());
			
			TtInsTruckMidEntMapPO truckEntMapPOTemp=POFactory.getByPriKey(conn,truckEntMapPOCon);
			
			if(truckEntMapPOTemp!=null){
				logger.error(" ENT_SUB EXIST TRUCK_MAP .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3005", "���������С�ࣺ���ڳ�����Ӧ������С��", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_DEL;
			entSubPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3010", "���������С�ࣺ��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, entSubPOCon,entSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSubPOResult.getCheckEntCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_8000", "���������С�ࣺ����["+entSubPOResult.getCheckEntCode()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "������С��["+entSubPOResult.getCheckEntCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "������С��["+entSubPOResult.getCheckEntCode()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
