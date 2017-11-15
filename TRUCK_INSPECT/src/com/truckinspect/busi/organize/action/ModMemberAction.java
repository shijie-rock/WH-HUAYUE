/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:ModMemberAction.java
 * �� ������:2017��9��2��-����9:34:04
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModMemberAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:34:04
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModMemberAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String memberId=atx.getStringValue("MEMBER_ID",null);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(memberId)||"0".equals(memberId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_1000", "�����û�������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check exist
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(Integer.valueOf(memberId));
		
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOResult==null){
			logger.error(" MEMBER NOT EXIST .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_2000", "�����û����û�������", null);
			return 0;
		}
		
		int version=memberPOResult.getVer();
		memberPOCon.setVer(version);
		
		TmSysMemberPO memberPOValue=new TmSysMemberPO();
		memberPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		memberPOValue.setUpdateTime(YBUtility.now());
		memberPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(memberPOResult.getFreezeTag())){
				logger.error(" MEMBER IS STOPED ALREADY .");
				atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3001", "�����û����û��Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_UNV;
			memberPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(memberPOResult.getFreezeTag())){
				logger.error(" MEMBER IS START ALREADY .");
				atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3002", "�����û����û��Ѿ�������״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_VAL;
			memberPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_DEL;
			memberPOValue.setStatus("0");
			//clear employee  MEMBER 
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3010", "�����û�����֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, memberPOCon,memberPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+memberPOResult.getMemberName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_8000", "�����û�������["+memberPOResult.getMemberName()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "�û�["+memberPOResult.getMemberName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "�û�["+memberPOResult.getMemberName()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
