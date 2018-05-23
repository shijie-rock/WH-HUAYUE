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
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModEntitySupAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModEntitySupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entSupId=atx.getIntValue("ENT_SUP_ID",0);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==entSupId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_1000", "处理检查对象大类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setId(entSupId);
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		
		if(entSupPOResult==null){
			logger.error(" ENT_SUP NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_2000", "处理检查对象大类：检查对象大类不存在", null);
			return 0;
		}
		
		int version=entSupPOResult.getVer();
		entSupPOCon.setVer(version);
		
		TmInsCheckEntitySupperPO entSupPOValue=new TmInsCheckEntitySupperPO();
		entSupPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		entSupPOValue.setUpdateTime(YBUtility.now());
		entSupPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(entSupPOResult.getFreezeTag())){
				logger.error(" ENT_SUP IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3001", "处理检查对象大类：检查对象大类已经是停用状态", null);
				return 0;
			}
			
			//TM_INS_CHECK_ENTITY_MIDDLE
			TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
			entMidPOCon.setStatus("1");
			entMidPOCon.setFreezeTag("0");
			entMidPOCon.setCheckEntFCode(entSupPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entMidPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3020", "处理检查对象大类：存在未停用的检查对象中类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_UNV;
			entSupPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(entSupPOResult.getFreezeTag())){
				logger.error(" ENT_SUP IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3002", "处理检查对象大类：检查对象大类已经是启用状态", null);
				return 0;
			}
			
			//检查是否有有效的相同代码的检查对象大类
//			TM_INS_CHECK_ENTITY_SUPPER
			TmInsCheckEntitySupperPO entSupPOConTemp=new TmInsCheckEntitySupperPO();
			entSupPOConTemp.setStatus("1");
			entSupPOConTemp.setFreezeTag("0");
			entSupPOConTemp.setCheckEntCode(entSupPOResult.getCheckEntCode());
			
			TmInsCheckEntitySupperPO entSupPOResultTemp=POFactory.getByPriKey(conn, entSupPOConTemp);
			
			if(entSupPOResultTemp!=null){
				logger.error(" ENT_MID SAME ENT_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3003", "处理检查对象大类：存在相同编码检查对象大类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_VAL;
			entSupPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//TM_INS_CHECK_ENTITY_MIDDLE
			TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
			entMidPOCon.setStatus("1");
			entMidPOCon.setCheckEntFCode(entSupPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entMidPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3030", "处理检查对象大类：存在未删除的检查对象中类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTSUP_DEL;
			entSupPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_3010", "处理检查对象大类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, entSupPOCon,entSupPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entSupPOResult.getCheckEntCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_SUP_MOD_ACTION_ERR_8000", "处理检查对象大类：数据["+entSupPOResult.getCheckEntCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "检查对象大类["+entSupPOResult.getCheckEntCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "检查对象大类["+entSupPOResult.getCheckEntCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
