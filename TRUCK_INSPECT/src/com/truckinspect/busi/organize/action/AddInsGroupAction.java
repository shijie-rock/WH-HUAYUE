/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:AddInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
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
 * 类名称:AddInsGroupAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddInsGroupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String groupName=atx.getStringValue("INS_GROUP_NAME");
		String groupDesc=atx.getStringValue("INS_GROUP_DESC");
		
		//check param
		if(StringUtils.isBlank(groupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_ADD_ACTION_ERR_1000", "新增检查组：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInspactGroupPO groupPOCon=new TmInspactGroupPO();
		groupPOCon.setStatus("1");
		groupPOCon.setInsGroupName(groupName);
		
		TmInspactGroupPO groupPOResult=POFactory.getByPriKey(conn, groupPOCon);
		if(groupPOResult!=null){
			logger.error(" INS_GROUP EXIST ALREADY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_ADD_ACTION_ERR_2000", "新增检查组：检查组["+groupName+"]已经存在", null);
			return 0;
		}
		
		groupPOCon.setCreateBy(optMemberId);
		groupPOCon.setCreateTime(YBUtility.now());
		groupPOCon.setFreezeTag("0");
		groupPOCon.setId(POFactory.getIntPriKey(conn, groupPOCon));
		groupPOCon.setInsGroupDesc(groupDesc);
		groupPOCon.setVer(1);
		
		POFactory.insert(conn, groupPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_ADD, "", "新增检查组["+groupName+"]");
		
		//set return msg
		atx.setStringValue("MSG", "新增检查组["+groupName+"]成功");
		return 1;
	}

}
