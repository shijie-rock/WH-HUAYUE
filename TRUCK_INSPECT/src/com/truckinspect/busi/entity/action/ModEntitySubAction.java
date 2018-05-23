/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:ModInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TtInsTruckMidEntMapPO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModEntitySubAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModEntitySubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSubId=atx.getIntValue("ENT_SUB_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==entSubId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_1000", "处理检查对象小类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_ENTITY_SUB
		TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
		entSubPOCon.setStatus("1");
		entSubPOCon.setId(entSubId);
		
		TmInsCheckEntitySubPO entSubPOResult=POFactory.getByPriKey(conn, entSubPOCon);
		
		if(entSubPOResult==null){
			logger.error(" ENT_SUB NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_2000", "处理检查对象小类：检查对象小类不存在", null);
			return 0;
		}
		
		int version=entSubPOResult.getVer();
		entSubPOCon.setVer(version);
		
		TmInsCheckEntitySubPO entSubPOValue=new TmInsCheckEntitySubPO();
		entSubPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		entSubPOValue.setUpdateTime(YBUtility.now());
		entSubPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(entSubPOResult.getFreezeTag())){
				logger.error(" ENT_SUB IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3001", "处理检查对象小类：检查对象小类已经是停用状态", null);
				return 0;
			}
			//校验车辆对应对象小类表
//			TODO
//			TT_INS_TRUCK_MID_ENT_MAP
			TtInsTruckMidEntMapPO truckEntMapPOCon=new TtInsTruckMidEntMapPO();
			truckEntMapPOCon.setStatus("1");
			truckEntMapPOCon.setFreezeTag("0");
			truckEntMapPOCon.setCheckEntSubCode(entSubPOResult.getCheckEntCode());
			
			TtInsTruckMidEntMapPO truckEntMapPOTemp=POFactory.getByPriKey(conn,truckEntMapPOCon);
			
			if(truckEntMapPOTemp!=null){
				logger.error(" ENT_SUB EXIST TRUCK_MAP .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3004", "处理检查对象小类：存在车辆对应检查对象小类", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_UNV;
			entSubPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(entSubPOResult.getFreezeTag())){
				logger.error(" ENT_SUB IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3002", "处理检查对象小类：检查对象小类已经是启用状态", null);
				return 0;
			}
			
			//检查是否有有效的相同代码的检查对象小类
//			TM_INS_CHECK_ENTITY_SUB
			TmInsCheckEntitySubPO entSubPOConTemp=new TmInsCheckEntitySubPO();
			entSubPOConTemp.setStatus("1");
			entSubPOConTemp.setFreezeTag("0");
			entSubPOConTemp.setCheckEntCode(entSubPOResult.getCheckEntCode());
			
			TmInsCheckEntitySubPO entSubPOResultTemp=POFactory.getByPriKey(conn, entSubPOConTemp);
			
			if(entSubPOResultTemp!=null){
				logger.error(" ENT_SUB SAME ENT_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3003", "处理检查对象小类：存在相同编码检查对象小类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_VAL;
			entSubPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//校验车辆对应对象小类表
//			TODO
//			TT_INS_TRUCK_MID_ENT_MAP
			TtInsTruckMidEntMapPO truckEntMapPOCon=new TtInsTruckMidEntMapPO();
			truckEntMapPOCon.setStatus("1");
			truckEntMapPOCon.setFreezeTag("0");
			truckEntMapPOCon.setCheckEntSubCode(entSubPOResult.getCheckEntCode());
			
			TtInsTruckMidEntMapPO truckEntMapPOTemp=POFactory.getByPriKey(conn,truckEntMapPOCon);
			
			if(truckEntMapPOTemp!=null){
				logger.error(" ENT_SUB EXIST TRUCK_MAP .");
				atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3005", "处理检查对象小类：存在车辆对应检查对象小类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUB_DEL;
			entSubPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_3010", "处理检查对象小类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, entSubPOCon,entSubPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSubPOResult.getCheckEntCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_MOD_ACTION_ERR_8000", "处理检查对象小类：数据["+entSubPOResult.getCheckEntCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "检查对象小类["+entSubPOResult.getCheckEntCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "检查对象小类["+entSubPOResult.getCheckEntCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
