/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:ModInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModObjSupAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModObjSupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSupId=atx.getIntValue("OBJ_SUP_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==objSupId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_1000", "处理一级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setId(objSupId);
		
		TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
		
		if(objSupPOResult==null){
			logger.error(" OBJ_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_2000", "处理一级分类：一级分类不存在", null);
			return 0;
		}
		
		int version=objSupPOResult.getVer();
		objSupPOCon.setVer(version);
		
		TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
		objSupPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objSupPOValue.setUpdateTime(YBUtility.now());
		objSupPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objSupPOResult.getFreezeTag())){
				logger.error(" OBJ_SUP IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3001", "处理一级分类：一级分类已经是停用状态", null);
				return 0;
			}
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE,如果有子类未停用，则不能停用
			TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
			midPOCon.setFreezeTag("0");
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objSupPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3020", "处理一级分类：存在未停用的子类数据", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUP_UNV;
			objSupPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(objSupPOResult.getFreezeTag())){
				logger.error(" OBJ_SUP IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3002", "处理一级分类：一级分类已经是启用状态", null);
				return 0;
			}
			
			//检查是否有有效的相同代码的检查项目一级类
//			TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOConTemp=new TmInsCheckObjClassSupperPO();
			objSupPOConTemp.setStatus("1");
			objSupPOConTemp.setFreezeTag("0");
			objSupPOConTemp.setObjClassCode(objSupPOResult.getObjClassCode());
			
			TmInsCheckObjClassSupperPO objSupPOResultTemp=POFactory.getByPriKey(conn, objSupPOConTemp);
			
			if(objSupPOResultTemp!=null){
				logger.error(" OBJ_SUP SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3003", "处理一级分类：存在相同编码检查项目一级类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUP_VAL;
			objSupPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE,如果有子类未停用，则不能停用
			TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objSupPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3030", "处理一级分类：存在未删除的子类数据", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUP_DEL;
			objSupPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_3010", "处理一级分类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, objSupPOCon,objSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSupPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_MOD_ACTION_ERR_8000", "处理一级分类：数据["+objSupPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "一级分类["+objSupPOResult.getObjClassCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "一级分类["+objSupPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
