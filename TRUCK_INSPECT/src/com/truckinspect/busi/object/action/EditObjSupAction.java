/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:EditInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditObjSupAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditObjSupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSupId=atx.getIntValue("OBJ_SUP_ID",0);
		
		String objSupName=atx.getStringValue("OBJ_SUP_NAME");
		String objSupDesc=atx.getStringValue("OBJ_SUP_DESC");
		Integer objSupSort=atx.getIntValue("OBJ_SUP_SORT",1000);
		
		//check param
		if(objSupId==0||StringUtils.isBlank(objSupName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_1000", "编辑一级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setId(objSupId);
		
		TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
		
		if(objSupPOResult==null){
			logger.error(" OBJ_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_2000", "编辑一级分类：一级分类不存在", null);
			return 0;
		}
		
		int version=objSupPOResult.getVer();
		objSupPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
		
		objSupPOValue.setUpdateBy(optMemberId);
		objSupPOValue.setUpdateTime(YBUtility.now());
		objSupPOValue.setVer(version+1);
		
		objSupPOValue.setSort(objSupSort);
		objSupPOValue.setObjClassDesc(objSupDesc);
		objSupPOValue.setObjClassName(objSupName);
		
		int excuteResult=POFactory.update(conn, objSupPOCon, objSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSupId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_EDIT_ACTION_ERR_8000", "编辑一级分类：数据ID["+objSupId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_MOD, "", "编辑一级分类");
		
		//set return msg
		atx.setStringValue("MSG", "编辑一级分类["+objSupPOResult.getObjClassCode()+"]成功");
		return 1;
	}
}
