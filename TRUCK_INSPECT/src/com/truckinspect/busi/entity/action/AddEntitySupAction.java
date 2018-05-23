/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:AddInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:AddEntitySupAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddEntitySupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entSupName=atx.getStringValue("ENT_SUP_NAME");
		String entSupCode=atx.getStringValue("ENT_SUP_CODE");
		String entSupDesc=atx.getStringValue("ENT_SUP_DESC");
		String entType=atx.getStringValue("ENT_TYPE");
		Integer entSupSort=atx.getIntValue("ENT_SUP_SORT",1000);//排序顺序
		
		//check param
		if(StringUtils.isBlank(entSupName)||StringUtils.isBlank(entSupCode)||StringUtils.isBlank(entSupDesc)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_ADD_ACTION_ERR_1000", "新增检查对象大类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setCheckEntCode(entSupCode);
		entSupPOCon.setFreezeTag("0");
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		if(entSupPOResult!=null){
			logger.error(" ENTITY SUP EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_ADD_ACTION_ERR_2000", "新增检查对象大类：检查对象大类代码["+entSupCode+"]已经存在", null);
			return 0;
		}
		entSupPOCon.setCheckEntDesc(entSupDesc);
		entSupPOCon.setCheckEntLevel(1);
		entSupPOCon.setCheckEntName(entSupName);
		entSupPOCon.setCheckEntTypeCode(entType);
		entSupPOCon.setSort(entSupSort);
		
		entSupPOCon.setCreateBy(optMemberId);
		entSupPOCon.setCreateTime(YBUtility.now());
		entSupPOCon.setFreezeTag("0");
		entSupPOCon.setId(POFactory.getIntPriKey(conn, entSupPOCon));
		entSupPOCon.setVer(1);
		
		POFactory.insert(conn, entSupPOCon);
		
		//TM_INS_CHECK_OBJ_CLASS_SUPPER
		//@20180223 自动增加 一级项目类
		//entSupCode
		String msg="";
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setCheckEntSupCode(entSupCode);
		
		TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
		if(objSupPOResult!=null){
			logger.warn(" OBJECT SUP EXIST ALREADY ,NO NEED ADD .");
		}else{
			objSupPOCon.setObjClassCode(entSupCode);
			
			objSupPOCon.setObjClassDesc(entSupDesc);
			objSupPOCon.setObjClassLevel(1);
			objSupPOCon.setObjClassName(entSupName);
			objSupPOCon.setSort(entSupSort);
			
			objSupPOCon.setCreateBy(optMemberId);
			objSupPOCon.setCreateTime(YBUtility.now());
			objSupPOCon.setFreezeTag("0");
			objSupPOCon.setId(POFactory.getIntPriKey(conn, objSupPOCon));
			objSupPOCon.setVer(1);
			objSupPOCon.setRemark("自动增加项目一级分类");
			
			POFactory.insert(conn, objSupPOCon);
			
			logger.warn(" OBJECT SUP AUTO ADD .");
			msg=",并自动增加项目一级分类";
		}
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUP_ADD, "", "新增检查对象大类"+msg);
		
		//set return msg
		atx.setStringValue("MSG", "新增检查对象大类["+entSupCode+"]成功");
		return 1;
	}

}
