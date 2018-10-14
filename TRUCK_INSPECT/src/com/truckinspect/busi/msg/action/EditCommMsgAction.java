/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:EditInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:53:16
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
 * 类名称:EditCommMsgAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditCommMsgAction extends ActionImpl {

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
//		String pubTag=atx.getStringValue("PUBLISH_TAG","0");//是否新增即发布
		String msgId=atx.getStringValue("COMM_MSG_ID");
		
		//check param
		if(StringUtils.isBlank(msgId)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_1000", "编辑公告：参数为空", null);
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
//			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_3000", "编辑公告：公告名称["+insGroupName+"]已存在", null);
//			return 0;
//		}
		
		//check exist
		TmInsCommMsgPO insCommMsgPOCon=new TmInsCommMsgPO();
		insCommMsgPOCon.setStatus("1");
		insCommMsgPOCon.setId(Integer.valueOf(msgId));
		
		TmInsCommMsgPO insCommMsgPOResult=POFactory.getByPriKey(conn, insCommMsgPOCon);
		
		if(insCommMsgPOResult==null){
			logger.error(" INS_COMM_MSG NOT EXIST .");
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_2000", "编辑公告：公告不存在", null);
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
			atx.setErrorContext("MESSAGE_COMM_MSG_EDIT_ACTION_ERR_8000", "编辑公告：数据ID["+msgId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_MOD, "", "编辑公告");
		
		//set return msg
		atx.setStringValue("MSG", "编辑公告["+msgTitle+"]成功");
		return 1;
	}
}
