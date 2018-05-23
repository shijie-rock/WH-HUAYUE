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
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModEntitySupAction
 * ������:ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModEntitySupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSupId=atx.getIntValue("ENT_SUP_ID",0);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==entSupId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_1000", "�����������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setId(entSupId);
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		
		if(entSupPOResult==null){
			logger.error(" ENT_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_2000", "�����������ࣺ��������಻����", null);
			return 0;
		}
		
		int version=entSupPOResult.getVer();
		entSupPOCon.setVer(version);
		
		TmInsCheckEntitySupperPO entSupPOValue=new TmInsCheckEntitySupperPO();
		entSupPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		entSupPOValue.setUpdateTime(YBUtility.now());
		entSupPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(entSupPOResult.getFreezeTag())){
				logger.error(" ENT_SUP IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3001", "�����������ࣺ����������Ѿ���ͣ��״̬", null);
				return 0;
			}
			
			//TM_INS_CHECK_ENTITY_MIDDLE
			TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
			entMidPOCon.setStatus("1");
			entMidPOCon.setFreezeTag("0");
			entMidPOCon.setCheckEntFCode(entSupPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entMidPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3020", "�����������ࣺ����δͣ�õļ���������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_UNV;
			entSupPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(entSupPOResult.getFreezeTag())){
				logger.error(" ENT_SUP IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3002", "�����������ࣺ����������Ѿ�������״̬", null);
				return 0;
			}
			
			//����Ƿ�����Ч����ͬ����ļ��������
//			TM_INS_CHECK_ENTITY_SUPPER
			TmInsCheckEntitySupperPO entSupPOConTemp=new TmInsCheckEntitySupperPO();
			entSupPOConTemp.setStatus("1");
			entSupPOConTemp.setFreezeTag("0");
			entSupPOConTemp.setCheckEntCode(entSupPOResult.getCheckEntCode());
			
			TmInsCheckEntitySupperPO entSupPOResultTemp=POFactory.getByPriKey(conn, entSupPOConTemp);
			
			if(entSupPOResultTemp!=null){
				logger.error(" ENT_MID SAME ENT_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3003", "�����������ࣺ������ͬ������������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_VAL;
			entSupPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//TM_INS_CHECK_ENTITY_MIDDLE
			TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
			entMidPOCon.setStatus("1");
			entMidPOCon.setCheckEntFCode(entSupPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entMidPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3030", "�����������ࣺ����δɾ���ļ���������", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_DEL;
			entSupPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3010", "�����������ࣺ��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, entSupPOCon,entSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSupPOResult.getCheckEntCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_8000", "�����������ࣺ����["+entSupPOResult.getCheckEntCode()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "���������["+entSupPOResult.getCheckEntCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "���������["+entSupPOResult.getCheckEntCode()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
