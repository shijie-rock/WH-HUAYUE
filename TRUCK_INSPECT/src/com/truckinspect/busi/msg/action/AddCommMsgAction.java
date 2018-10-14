/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.organize.action
 * ��   ��  ��:AddInsGroupAction.java
 * �� ������:2017��8��20��-����5:52:59
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
 * ������:AddCommMsgAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddCommMsgAction extends ActionImpl {

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
		String pubTag=atx.getStringValue("PUBLISH_TAG","0");//�Ƿ�����������
		
		//check param
		if(StringUtils.isBlank(msgTitle)||StringUtils.isBlank(msgContent)||StringUtils.isBlank(msgLevel)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_ADD_ACTION_ERR_1000", "�������棺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCommMsgPO commMsgPOCon=new TmInsCommMsgPO();
		commMsgPOCon.setStatus("1");
		commMsgPOCon.setCommMsgTitle(msgTitle);
		commMsgPOCon.setCommMsgSubTitle(msgSubTitle);
		commMsgPOCon.setCommMsgContent(msgContent);
		commMsgPOCon.setComMsgLevel(msgLevel);
		
//		TmInsCommMsgPO groupPOResult=POFactory.getByPriKey(conn, commMsgPOCon);
//		if(groupPOResult!=null){
//			logger.error(" INS_GROUP EXIST ALREADY .");
//			atx.setErrorContext("MESSAGE_COMM_MSG_ADD_ACTION_ERR_2000", "�������棺����["+groupName+"]�Ѿ�����", null);
//			return 0;
//		}
//		
		commMsgPOCon.setCreateBy(optMemberId);
		commMsgPOCon.setCreateTime(YBUtility.now());
		commMsgPOCon.setFreezeTag("0");
		commMsgPOCon.setId(POFactory.getIntPriKey(conn, commMsgPOCon));
		commMsgPOCon.setVer(1);
		
		if("1".equals(pubTag)){
			commMsgPOCon.setPublishMemId(optMemberId);
			commMsgPOCon.setPublishTag("1");
			commMsgPOCon.setPublishTime(YBUtility.now());
		}else{
			commMsgPOCon.setPublishTag("0");
		}
		
		POFactory.insert(conn, commMsgPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_ADD, "", "����"+("1".equals(pubTag)?"������":"")+"����["+msgTitle+"]");
		
		//set return msg
		atx.setStringValue("MSG", "����"+("1".equals(pubTag)?"������":"")+"����["+msgTitle+"]�ɹ�");
		return 1;
	}

}
