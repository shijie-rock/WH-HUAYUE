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
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditEntityMidAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditEntityMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entMidId=atx.getIntValue("ENT_MID_ID",0);
		
		String entMidName=atx.getStringValue("ENT_MID_NAME");
//		String entMidCode=atx.getStringValue("ENT_MID_CODE");
		String entSupCode=atx.getStringValue("ENT_SUP_CODE");//一级分类代码
		String entMidDesc=atx.getStringValue("ENT_MID_DESC");
		Integer entMidSort=atx.getIntValue("ENT_MID_SORT",1000);//排序顺序
		
		//check param
		if(entMidId==0||StringUtils.isBlank(entMidName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_EDIT_ACTION_ERR_1000", "编辑检查对象中类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_ENTITY_MIDDLE
		TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
		entMidPOCon.setStatus("1");
		entMidPOCon.setId(entMidId);
		
		TmInsCheckEntityMiddlePO entMidPOResult=POFactory.getByPriKey(conn, entMidPOCon);
		
		if(entMidPOResult==null){
			logger.error(" ENT_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_EDIT_ACTION_ERR_2000", "编辑检查对象中类：检查对象中类不存在", null);
			return 0;
		}
		
		int version=entMidPOResult.getVer();
		entMidPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckEntityMiddlePO entMidPOValue=new TmInsCheckEntityMiddlePO();
		
		entMidPOValue.setUpdateBy(optMemberId);
		entMidPOValue.setUpdateTime(YBUtility.now());
		entMidPOValue.setVer(version+1);
		
		entMidPOValue.setSort(entMidSort);
		entMidPOValue.setCheckEntDesc(entMidDesc);
		entMidPOValue.setCheckEntName(entMidName);
		entMidPOValue.setCheckEntFCode(entSupCode);
		
		int excuteResult=POFactory.update(conn, entMidPOCon, entMidPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entMidId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_EDIT_ACTION_ERR_8000", "编辑检查对象中类：数据ID["+entMidId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTMID_MOD, "", "编辑检查对象中类");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查对象中类["+entMidPOResult.getCheckEntCode()+"]成功");
		return 1;
	}
}
