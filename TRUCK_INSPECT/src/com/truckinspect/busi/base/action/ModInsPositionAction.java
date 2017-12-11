/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:ModInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModInsPositionAction
 * ������:ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModInsPositionAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionId=atx.getStringValue("POSITION_ID");
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(positionId)||"0".equals(positionId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_1000", "������ص㣺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setId(Integer.valueOf(positionId));
		
		TmInsPositionInfoPO positionPOResult=POFactory.getByPriKey(conn, positionPOCon);
		
		if(positionPOResult==null){
			logger.error(" INS_POSITION NOT EXIST .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_2000", "������ص㣺���ص㲻����", null);
			return 0;
		}
		
		int version=positionPOResult.getVer();
		positionPOCon.setVer(version);
		
		TmInsPositionInfoPO positionPOValue=new TmInsPositionInfoPO();
		positionPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		positionPOValue.setUpdateTime(YBUtility.now());
		positionPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(positionPOResult.getFreezeTag())){
				logger.error(" INS_POSITION IS STOPED ALREADY .");
				atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3001", "������ص㣺���ص��Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_UNV;
			positionPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(positionPOResult.getFreezeTag())){
				logger.error(" INS_POSITION IS START ALREADY .");
				atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3002", "������ص㣺���ص��Ѿ�������״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_VAL;
			positionPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_DEL;
			positionPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3010", "������ص㣺��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, positionPOCon,positionPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+positionPOResult.getPositionName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_8000", "������ص㣺����["+positionPOResult.getPositionName()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "���ص�["+positionPOResult.getPositionName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "���ص�["+positionPOResult.getPositionName()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
