/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:AddInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:AddObjSubAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddObjSubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objSubName=atx.getStringValue("OBJ_SUB_NAME");
		String objSubCode=atx.getStringValue("OBJ_SUB_CODE");
		String objMidCode=atx.getStringValue("OBJ_MID_CODE");//二级分类代码
		String objSubDesc=atx.getStringValue("OBJ_SUB_DESC");
		Integer objSubSort=atx.getIntValue("OBJ_SUB_SORT",1000);//排序顺序
		
		//check param
		if(StringUtils.isBlank(objSubName)||StringUtils.isBlank(objSubCode)||StringUtils.isBlank(objSubDesc)||StringUtils.isBlank(objMidCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_1000", "新增三级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setObjClassCode(objSubCode);
		objSubPOCon.setFreezeTag("0");
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		if(objSubPOResult!=null){
			logger.error(" OBJECT SUB EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_2000", "新增三级分类：三级分类代码["+objSubCode+"]已经存在", null);
			return 0;
		}
		objSubPOCon.setObjClassDesc(objSubDesc);
		objSubPOCon.setObjClassLevel(3);
		objSubPOCon.setObjClassName(objSubName);
		objSubPOCon.setSort(objSubSort);
		objSubPOCon.setObjClassFCode(objMidCode);
		
		objSubPOCon.setCreateBy(optMemberId);
		objSubPOCon.setCreateTime(YBUtility.now());
		objSubPOCon.setFreezeTag("0");
		objSubPOCon.setId(POFactory.getIntPriKey(conn, objSubPOCon));
		objSubPOCon.setVer(1);
		
		POFactory.insert(conn, objSubPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_ADD, "", "新增三级分类");
		
		//set return msg
		atx.setStringValue("MSG", "新增三级分类["+objSubCode+"]成功");
		return 1;
	}

}
