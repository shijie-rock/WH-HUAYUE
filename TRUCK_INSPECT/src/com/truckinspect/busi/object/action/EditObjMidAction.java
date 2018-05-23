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
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditObjMidAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditObjMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objMidId=atx.getIntValue("OBJ_MID_ID",0);
		
		String objMidName=atx.getStringValue("OBJ_MID_NAME");
//		String objMidCode=atx.getStringValue("OBJ_MID_CODE");
		String objSupCode=atx.getStringValue("OBJ_SUP_CODE");//一级分类代码
		String objMidDesc=atx.getStringValue("OBJ_MID_DESC");
		Integer objMidSort=atx.getIntValue("OBJ_MID_SORT",1000);//排序顺序
		
		//check param
		if(objMidId==0||StringUtils.isBlank(objMidName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_EDIT_ACTION_ERR_1000", "编辑二级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setId(objMidId);
		
		TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
		
		if(objMidPOResult==null){
			logger.error(" OBJ_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_EDIT_ACTION_ERR_2000", "编辑二级分类：二级分类不存在", null);
			return 0;
		}
		
		int version=objMidPOResult.getVer();
		objMidPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
		
		objMidPOValue.setUpdateBy(optMemberId);
		objMidPOValue.setUpdateTime(YBUtility.now());
		objMidPOValue.setVer(version+1);
		
		objMidPOValue.setSort(objMidSort);
		objMidPOValue.setObjClassDesc(objMidDesc);
		objMidPOValue.setObjClassName(objMidName);
		objMidPOValue.setObjClassFCode(objSupCode);
		
		int excuteResult=POFactory.update(conn, objMidPOCon, objMidPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objMidId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_EDIT_ACTION_ERR_8000", "编辑二级分类：数据ID["+objMidId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_MOD, "", "编辑二级分类");
		
		//set return msg
		atx.setStringValue("MSG", "编辑二级分类["+objMidPOResult.getObjClassCode()+"]成功");
		return 1;
	}
}
