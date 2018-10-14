/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:EditInsGroupAction.java
 * �� ������:2017��8��20��-����5:53:16
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
 * ������:EditCommMsgAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditCommMsgAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String msgTitle=atx.getStringValue("COMM_MSG_TITLE");//�������
		String msgSubTitle=atx.getStringValue("COMM_MSG_SUB_TITLE");//����˵��
		String msgContent=atx.getStringValue("COMM_MSG_CONTENT");//��������
		String msgLevel=atx.getStringValue("COMM_MSG_LEVEL");//���漶��
//		String pubTag=atx.getStringValue("PUBLISH_TAG","0");//�Ƿ�����������
		String msgId=atx.getStringValue("COMM_MSG_ID");
		
		//check param
		if(StringUtils.isBlank(msgId)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_1000", "�༭���棺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		TmInsCommMsgPO insCommMsgPOCon2=new TmInsCommMsgPO();
//		insCommMsgPOCon2.setStatus("1");
//		insCommMsgPOCon2.setInsGroupName(insGroupName);
//		
//		TmInsCommMsgPO insCommMsgPOResult=POFactory.getByPriKey(conn, insCommMsgPOCon2);
//		
//		if(insCommMsgPOResult!=null&&insCommMsgPOResult.getId().intValue()!=Integer.valueOf(insGroupId)){
//			logger.error(" INS_GROUP NAME EXIST .");
//			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_3000", "�༭���棺��������["+insGroupName+"]�Ѵ���", null);
//			return 0;
//		}
		
		//check exist
		TmInsCommMsgPO insCommMsgPOCon=new TmInsCommMsgPO();
		insCommMsgPOCon.setStatus("1");
		insCommMsgPOCon.setId(Integer.valueOf(msgId));
		
		TmInsCommMsgPO insCommMsgPOResult=POFactory.getByPriKey(conn, insCommMsgPOCon);
		
		if(insCommMsgPOResult==null){
			logger.error(" INS_COMM_MSG NOT EXIST .");
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_2000", "�༭���棺���治����", null);
			return 0;
		}
		
		int version=insCommMsgPOResult.getVer();
		insCommMsgPOCon.setVer(version);

		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCommMsgPO insCommMsgPOValue=new TmInsCommMsgPO();
		
		insCommMsgPOValue.setVer(version+1);
		insCommMsgPOValue.setUpdateBy(optMemberId);
		insCommMsgPOValue.setUpdateTime(YBUtility.now());
		insCommMsgPOValue.setCommMsgTitle(msgTitle);
		insCommMsgPOValue.setCommMsgSubTitle(msgSubTitle);
		insCommMsgPOValue.setCommMsgContent(msgContent);
		insCommMsgPOValue.setComMsgLevel(msgLevel);
		
		int excuteResult=POFactory.update(conn, insCommMsgPOCon, insCommMsgPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+msgId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_8000", "�༭���棺����ID["+msgId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_MOD, "", "�༭����");
		
		//set return msg
		atx.setStringValue("MSG", "�༭����["+msgTitle+"]�ɹ�");
		return 1;
	}
}
