/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:EditInsGroupAction.java
 * 创 建日期:2017年8月20日-下午5:53:16
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
 * 类名称:EditInsGroupAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditInsGroupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String insGroupId=atx.getStringValue("INS_GROUP_ID");
		String insGroupName=atx.getStringValue("INS_GROUP_NAME");
		String insGroupDesc=atx.getStringValue("INS_GROUP_DESC","");
		
		//check param
		if(StringUtils.isBlank(insGroupId)||StringUtils.isBlank(insGroupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_1000", "编辑检查组：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmInspactGroupPO insGroupPOCon2=new TmInspactGroupPO();
		insGroupPOCon2.setStatus("1");
		insGroupPOCon2.setInsGroupName(insGroupName);
		
		TmInspactGroupPO insGroupPOResult=POFactory.getByPriKey(conn, insGroupPOCon2);
		
		if(insGroupPOResult!=null&&insGroupPOResult.getId().intValue()!=Integer.valueOf(insGroupId)){
			logger.error(" INS_GROUP NAME EXIST .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_3000", "编辑检查组：检查组名称["+insGroupName+"]已存在", null);
			return 0;
		}
		
		//check repeat
		TmInspactGroupPO insGroupPOCon=new TmInspactGroupPO();
		insGroupPOCon.setStatus("1");
		insGroupPOCon.setId(Integer.valueOf(insGroupId));
		
		insGroupPOResult=POFactory.getByPriKey(conn, insGroupPOCon);
		
		if(insGroupPOResult==null){
			logger.error(" INS_GROUP NOT EXIST .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_2000", "编辑检查组：检查组不存在", null);
			return 0;
		}
		
		int version=insGroupPOResult.getVer();
		insGroupPOCon.setVer(version);

		//add role
		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInspactGroupPO insGroupPOValue=new TmInspactGroupPO();
		
		insGroupPOValue.setUpdateBy(optMemberId);
		insGroupPOValue.setUpdateTime(YBUtility.now());
		insGroupPOValue.setInsGroupName(insGroupName);
		insGroupPOValue.setInsGroupDesc(insGroupDesc);
		insGroupPOValue.setVer(version+1);
		
		int excuteResult=POFactory.update(conn, insGroupPOCon, insGroupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+insGroupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("ORGANIZE_INS_GROUP_EDIT_ACTION_ERR_8000", "编辑检查组：数据ID["+insGroupId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSGROUP_MOD, "", "编辑检查组");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查组["+insGroupName+"]成功");
		return 1;
	}
}
