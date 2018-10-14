/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:AddInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
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
 * 类名称:AddCommMsgAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddCommMsgAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String msgTitle=atx.getStringValue("COMM_MSG_TITLE");//公告标题
		String msgSubTitle=atx.getStringValue("COMM_MSG_SUB_TITLE");//公告说明
		String msgContent=atx.getStringValue("COMM_MSG_CONTENT");//公告内容
		String msgLevel=atx.getStringValue("COMM_MSG_LEVEL");//公告级别
		String pubTag=atx.getStringValue("PUBLISH_TAG","0");//是否新增即发布
		
		//check param
		if(StringUtils.isBlank(msgTitle)||StringUtils.isBlank(msgContent)||StringUtils.isBlank(msgLevel)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_ADD_ACTION_ERR_1000", "新增公告：参数为空", null);
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
//			atx.setErrorContext("MESSAGE_COMM_MSG_ADD_ACTION_ERR_2000", "新增公告：公告["+groupName+"]已经存在", null);
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
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_ADD, "", "新增"+("1".equals(pubTag)?"并发布":"")+"公告["+msgTitle+"]");
		
		//set return msg
		atx.setStringValue("MSG", "新增"+("1".equals(pubTag)?"并发布":"")+"公告["+msgTitle+"]成功");
		return 1;
	}

}
