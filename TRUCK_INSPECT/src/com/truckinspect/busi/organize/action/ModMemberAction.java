/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:ModMemberAction.java
 * 创 建日期:2017年9月2日-上午9:34:04
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
 * 类名称:ModMemberAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:34:04
 * 修改备注:
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
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_1000", "处理用户：参数为空", null);
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
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_2000", "处理用户：用户不存在", null);
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
				atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3001", "处理用户：用户已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_UNV;
			memberPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(memberPOResult.getFreezeTag())){
				logger.error(" MEMBER IS START ALREADY .");
				atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3002", "处理用户：用户已经是启用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_VAL;
			memberPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_DEL;
			memberPOValue.setStatus("0");
			//clear employee  MEMBER 
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_3010", "处理用户：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, memberPOCon,memberPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+memberPOResult.getMemberName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_MEMBER_MOD_ACTION_ERR_8000", "处理用户：数据["+memberPOResult.getMemberName()+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "用户["+memberPOResult.getMemberName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "用户["+memberPOResult.getMemberName()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
