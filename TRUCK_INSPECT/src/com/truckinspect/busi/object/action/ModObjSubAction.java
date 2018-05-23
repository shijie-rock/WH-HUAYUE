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
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModObjSubAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModObjSubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objSubId=atx.getIntValue("OBJ_SUB_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==objSubId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_1000", "处理三级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setId(objSubId);
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		
		if(objSubPOResult==null){
			logger.error(" OBJ_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_2000", "处理三级分类：三级分类不存在", null);
			return 0;
		}
		
		int version=objSubPOResult.getVer();
		objSubPOCon.setVer(version);
		
		TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
		objSubPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objSubPOValue.setUpdateTime(YBUtility.now());
		objSubPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objSubPOResult.getFreezeTag())){
				logger.error(" OBJ_SUB IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3001", "处理三级分类：三级分类已经是停用状态", null);
				return 0;
			}
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
			itemPOCon.setStatus("1");
			itemPOCon.setFreezeTag("0");
			itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, itemPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3020", "处理三级分类：存在未停用的检查项目", null);
				return 0;
			}
			
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_UNV;
			objSubPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objSubPOResult.getFreezeTag())){
				logger.error(" OBJ_SUB IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3002", "处理三级分类：三级分类已经是启用状态", null);
				return 0;
			}
			//检查是否有有效的相同代码的检查项目三级类
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOConTemp=new TmInsCheckObjClassSubPO();
			objSubPOConTemp.setStatus("1");
			objSubPOConTemp.setFreezeTag("0");
			objSubPOConTemp.setObjClassCode(objSubPOResult.getObjClassCode());
			
			TmInsCheckObjClassSubPO objSubPOResultTemp=POFactory.getByPriKey(conn, objSubPOConTemp);
			
			if(objSubPOResultTemp!=null){
				logger.error(" OBJ_SUB SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3003", "处理三级分类：存在相同编码检查项目三级类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_VAL;
			objSubPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
			itemPOCon.setStatus("1");
			itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, itemPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3030", "处理三级分类：存在未删除的检查项目", null);
				return 0;
			}
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_DEL;
			objSubPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_3010", "处理三级分类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, objSubPOCon,objSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objSubPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_MOD_ACTION_ERR_8000", "处理三级分类：数据["+objSubPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "三级分类["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "三级分类["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
