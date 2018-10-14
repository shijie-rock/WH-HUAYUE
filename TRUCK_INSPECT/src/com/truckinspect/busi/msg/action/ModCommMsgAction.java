/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:ModInsGroupAction.java
 * �� ������:2017��8��20��-����5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.msg.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.msg.po.TmInsCommMsgPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModCommMsgAction
 * ������:����ͣ�á����á�ɾ���Ȳ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModCommMsgAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String insCommMsgId=atx.getStringValue("COMM_MSG_ID",null);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(insCommMsgId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_1000", "�����棺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check exist
		TmInsCommMsgPO insCommMsgPOCon=new TmInsCommMsgPO();
		insCommMsgPOCon.setStatus("1");
		insCommMsgPOCon.setId(Integer.valueOf(insCommMsgId));
		
		TmInsCommMsgPO insCommMsgPOResult=POFactory.getByPriKey(conn, insCommMsgPOCon);
		
		if(insCommMsgPOResult==null){
			logger.error(" COMM_MSG NOT EXIST .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_2000", "�����棺���治����", null);
			return 0;
		}
		
		int version=insCommMsgPOResult.getVer();
		insCommMsgPOCon.setVer(version);
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCommMsgPO insCommMsgPOValue=new TmInsCommMsgPO();
		insCommMsgPOValue.setUpdateBy(optMemberId);
		insCommMsgPOValue.setUpdateTime(YBUtility.now());
		insCommMsgPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS STOPED ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3001", "�����棺�����Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_UNV;
			insCommMsgPOValue.setFreezeTag("1"); //������û�Ȩ��
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS START ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3002", "�����棺�����Ѿ�������״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_VAL;
			insCommMsgPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_DEL;
			insCommMsgPOValue.setStatus("0");
			//clear employee  ins_group ��clear ins_group check item
			
		}else if("pub".equals(optType)){//����
			
			if("1".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS STOPED ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3003", "�����棺�����Ѿ���ͣ��״̬�����������ٽ��з�������", null);
				return 0;
			}
//			if("1".equals(insCommMsgPOResult.getPublishTag())){
//				logger.error(" COMM_MSG IS PUBLISH ALREADY .");
//				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3004", "�����棺�����Ѿ��Ƿ���״̬���޷��ٴη���", null);
//				return 0;
//			}
			optTypeDesc="����";//��η���������������ʾ����
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_SED;
			insCommMsgPOValue.setPublishTag("1");
			insCommMsgPOValue.setPublishMemId(optMemberId);
			insCommMsgPOValue.setPublishTime(YBUtility.now());
			
		}
		else if("cal".equals(optType)){//ȡ������
			
			if("1".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS STOPED ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3004", "�����棺�����Ѿ���ͣ��״̬�����������ٽ���ȡ����������", null);
				return 0;
			}
			
			optTypeDesc="ȡ������";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_CAL;
			insCommMsgPOValue.setPublishTag("0");
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3010", "�����棺��֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, insCommMsgPOCon,insCommMsgPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+insCommMsgPOResult.getCommMsgTitle()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_8000", "�����棺����["+insCommMsgPOResult.getCommMsgTitle()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "����["+insCommMsgPOResult.getCommMsgTitle()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "����["+insCommMsgPOResult.getCommMsgTitle()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
