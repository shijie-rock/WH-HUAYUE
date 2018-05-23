/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:EditInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditEntitySupAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditEntitySupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSupId=atx.getIntValue("ENT_SUP_ID",0);
		
		String entSupName=atx.getStringValue("ENT_SUP_NAME");
		String entSupDesc=atx.getStringValue("ENT_SUP_DESC");
		String entType=atx.getStringValue("ENT_TYPE");
		Integer entSupSort=atx.getIntValue("ENT_SUP_SORT",1000);
		
		//check param
		if(entSupId==0||StringUtils.isBlank(entSupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_1000", "编辑检查对象大类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setId(entSupId);
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		
		if(entSupPOResult==null){
			logger.error(" ENT_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_2000", "编辑检查对象大类：检查对象大类不存在", null);
			return 0;
		}
		
		int version=entSupPOResult.getVer();
		entSupPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckEntitySupperPO entSupPOValue=new TmInsCheckEntitySupperPO();
		
		entSupPOValue.setUpdateBy(optMemberId);
		entSupPOValue.setUpdateTime(YBUtility.now());
		entSupPOValue.setVer(version+1);
		
		entSupPOValue.setSort(entSupSort);
		entSupPOValue.setCheckEntDesc(entSupDesc);
		entSupPOValue.setCheckEntName(entSupName);
		entSupPOValue.setCheckEntTypeCode(entType);
		
		int excuteResult=POFactory.update(conn, entSupPOCon, entSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_EDIT_ACTION_ERR_8000", "编辑检查对象大类：数据ID["+entSupId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUP_MOD, "", "编辑检查对象大类");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查对象大类["+entSupPOResult.getCheckEntCode()+"]成功");
		return 1;
	}
}
