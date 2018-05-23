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
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModObjMidAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModObjMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objMidId=atx.getIntValue("OBJ_MID_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==objMidId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_1000", "处理二级分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setId(objMidId);
		
		TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
		
		if(objMidPOResult==null){
			logger.error(" OBJ_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_2000", "处理二级分类：二级分类不存在", null);
			return 0;
		}
		
		int version=objMidPOResult.getVer();
		objMidPOCon.setVer(version);
		
		TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
		objMidPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		objMidPOValue.setUpdateTime(YBUtility.now());
		objMidPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(objMidPOResult.getFreezeTag())){
				logger.error(" OBJ_MID IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3001", "处理二级分类：二级分类已经是停用状态", null);
				return 0;
			}
			
//			TM_INS_CHECK_OBJ_CLASS_SUB,如果有子类未停用，则不能停用
			TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
			midPOCon.setFreezeTag("0");
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3020", "处理二级分类：存在未停用的子类数据", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_UNV;
			objMidPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(objMidPOResult.getFreezeTag())){
				logger.error(" OBJ_MID IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3002", "处理二级分类：二级分类已经是启用状态", null);
				return 0;
			}
			//检查是否有有效的相同代码的检查项目二级类
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOConTemp=new TmInsCheckObjClassMiddlePO();
			objMidPOConTemp.setStatus("1");
			objMidPOConTemp.setFreezeTag("0");
			objMidPOConTemp.setObjClassCode(objMidPOResult.getObjClassCode());
			
			TmInsCheckObjClassMiddlePO objMidPOResultTemp=POFactory.getByPriKey(conn, objMidPOConTemp);
			
			if(objMidPOResultTemp!=null){
				logger.error(" OBJ_MID SAME OBJ_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3003", "处理二级分类：存在相同编码检查项目二级类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_VAL;
			objMidPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB,如果有子类未停用，则不能停用
			TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
			midPOCon.setStatus("1");
			midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
			
			if(POFactory.getByPriKey(conn, midPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3030", "处理二级分类：存在未删除的子类数据", null);
				return 0;
			}
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_DEL;
			objMidPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_3010", "处理二级分类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, objMidPOCon,objMidPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+objMidPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_MOD_ACTION_ERR_8000", "处理二级分类：数据["+objMidPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "二级分类["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "二级分类["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
