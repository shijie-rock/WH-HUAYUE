/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:ModInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:53:32
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
 * 类名称:ModCommMsgAction
 * 类描述:公告停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
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
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_1000", "处理公告：参数为空", null);
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
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_2000", "处理公告：公告不存在", null);
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
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3001", "处理公告：公告已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_UNV;
			insCommMsgPOValue.setFreezeTag("1"); //不清除用户权限
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS START ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3002", "处理公告：公告已经是启用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_VAL;
			insCommMsgPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_DEL;
			insCommMsgPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else if("pub".equals(optType)){//发布
			
			if("1".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS STOPED ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3003", "处理公告：公告已经是停用状态，请先启用再进行发布操作", null);
				return 0;
			}
//			if("1".equals(insCommMsgPOResult.getPublishTag())){
//				logger.error(" COMM_MSG IS PUBLISH ALREADY .");
//				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3004", "处理公告：公告已经是发布状态，无法再次发布", null);
//				return 0;
//			}
			optTypeDesc="发布";//多次发布可提升公告显示排序
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_SED;
			insCommMsgPOValue.setPublishTag("1");
			insCommMsgPOValue.setPublishMemId(optMemberId);
			insCommMsgPOValue.setPublishTime(YBUtility.now());
			
		}
		else if("cal".equals(optType)){//取消发布
			
			if("1".equals(insCommMsgPOResult.getFreezeTag())){
				logger.error(" COMM_MSG IS STOPED ALREADY .");
				atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3004", "处理公告：公告已经是停用状态，请先启用再进行取消发布操作", null);
				return 0;
			}
			
			optTypeDesc="取消发布";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_COMM_MSG_CAL;
			insCommMsgPOValue.setPublishTag("0");
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_3010", "处理公告：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, insCommMsgPOCon,insCommMsgPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+insCommMsgPOResult.getCommMsgTitle()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("MESSAGE_COMM_MSG_MOD_ACTION_ERR_8000", "处理公告：数据["+insCommMsgPOResult.getCommMsgTitle()+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "公告["+insCommMsgPOResult.getCommMsgTitle()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "公告["+insCommMsgPOResult.getCommMsgTitle()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
