/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:ModInsGroupAction.java
 * �� ������:2017��8��20��-����5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModInsGroupAction
 * ������:�����ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModInsGroupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String insGroupId=atx.getStringValue("INS_GROUP_ID",null);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(insGroupId)||"0".equals(insGroupId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_1000", "�������飺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check exist
		TmInspactGroupPO insGroupPOCon=new TmInspactGroupPO();
		insGroupPOCon.setStatus("1");
		insGroupPOCon.setId(Integer.valueOf(insGroupId));
		
		TmInspactGroupPO insGroupPOResult=POFactory.getByPriKey(conn, insGroupPOCon);
		
		if(insGroupPOResult==null){
			logger.error(" INS_GROUP NOT EXIST .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_2000", "�������飺����鲻����", null);
			return 0;
		}
		
		int version=insGroupPOResult.getVer();
		insGroupPOCon.setVer(version);
		
		TmInspactGroupPO insGroupPOValue=new TmInspactGroupPO();
		insGroupPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		insGroupPOValue.setUpdateTime(YBUtility.now());
		insGroupPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(insGroupPOResult.getFreezeTag())){
				logger.error(" INS_GROUP IS STOPED ALREADY .");
				atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_3001", "�������飺������Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSGROUP_UNV;
			insGroupPOValue.setFreezeTag("1"); //������û�Ȩ��
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(insGroupPOResult.getFreezeTag())){
				logger.error(" INS_GROUP IS START ALREADY .");
				atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_3002", "�������飺������Ѿ�������״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSGROUP_VAL;
			insGroupPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSGROUP_DEL;
			insGroupPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_3010", "�������飺��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, insGroupPOCon,insGroupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+insGroupPOResult.getInsGroupName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_MOD_ACTION_ERR_8000", "�������飺����["+insGroupPOResult.getInsGroupName()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "�����["+insGroupPOResult.getInsGroupName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "�����["+insGroupPOResult.getInsGroupName()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
