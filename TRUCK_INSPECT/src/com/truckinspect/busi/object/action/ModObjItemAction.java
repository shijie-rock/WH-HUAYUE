/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:ModObjItemAction.java
 * 创 建日期:2018年3月18日-上午11:17:34
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModObjItemAction
 * 类描述:停用启用检查项目
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月18日 上午11:17:34
 * 修改备注:
 * @version 1.0.0
 */
public class ModObjItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		Integer itemId=atx.getIntValue("OBJ_ITEM_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==itemId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_1000", "处理检查项目：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_ITEM
		TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
		itemPOCon.setStatus("1");
		itemPOCon.setId(itemId);
		
		TmInsCheckObjItemPO objItemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		
		if(objItemPOResult==null){
			logger.error(" OBJ_ITEM NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_2000", "处理检查项目：检查项目不存在", null);
			return 0;
		}
		
		int version=objItemPOResult.getVer();
		itemPOCon.setVer(version);
		
		TmInsCheckObjItemPO objItemPOValue=new TmInsCheckObjItemPO();
		objItemPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objItemPOValue.setUpdateTime(YBUtility.now());
		objItemPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objItemPOResult.getFreezeTag())){
				logger.error(" OBJ_ITEM IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3001", "处理检查项目：检查项目已经是停用状态", null);
				return 0;
			}
			//TM_INS_CHECK_OBJ_ITEM CHELD CHECK
			TmInsCheckObjItemPO itemTempPOCon=new TmInsCheckObjItemPO();
			itemTempPOCon.setStatus("1");
			itemTempPOCon.setFreezeTag("0");
			itemTempPOCon.setCheckObjFCode(objItemPOResult.getCheckObjCode());
			
			if(POFactory.getByPriKey(conn, itemTempPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3020", "处理检查项目：存在未停用的检查项目子类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_UNV;
			objItemPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objItemPOResult.getFreezeTag())){
				logger.error(" OBJ_ITEM IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3002", "处理检查项目：检查项目已经是启用状态", null);
				return 0;
			}
			//检查是否有有效的相同代码的检查项目
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjItemPO itemPOConTemp=new TmInsCheckObjItemPO();
			itemPOConTemp.setStatus("1");
			itemPOConTemp.setFreezeTag("0");
			itemPOConTemp.setCheckObjCode(objItemPOResult.getCheckObjCode());
			
			TmInsCheckObjItemPO objItemPOResultTemp=POFactory.getByPriKey(conn, itemPOConTemp);
			
			if(objItemPOResultTemp!=null){
				logger.error(" OBJ_ITEM SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3003", "处理检查项目：存在相同编码检查项目", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_VAL;
			objItemPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
			//TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO itemPOConTemp=new TmInsCheckObjItemPO();
			itemPOConTemp.setStatus("1");
			itemPOConTemp.setCheckObjFCode(objItemPOResult.getCheckObjCode());
			
			if(POFactory.getByPriKey(conn, itemPOConTemp)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3030", "处理检查项目：存在未删除的检查项目", null);
				return 0;
			}
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJITEM_DEL;
			objItemPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_3010", "处理检查项目：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, itemPOCon,objItemPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objItemPOResult.getCheckObjCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_MOD_ACTION_ERR_8000", "处理检查项目：数据["+objItemPOResult.getCheckObjCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "检查项目["+objItemPOResult.getCheckObjCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "检查项目["+objItemPOResult.getCheckObjCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
