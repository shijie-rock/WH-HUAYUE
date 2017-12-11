/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:ModInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModInsPositionAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModInsPositionAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionId=atx.getStringValue("POSITION_ID");
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(positionId)||"0".equals(positionId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_1000", "处理检查地点：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setId(Integer.valueOf(positionId));
		
		TmInsPositionInfoPO positionPOResult=POFactory.getByPriKey(conn, positionPOCon);
		
		if(positionPOResult==null){
			logger.error(" INS_POSITION NOT EXIST .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_2000", "处理检查地点：检查地点不存在", null);
			return 0;
		}
		
		int version=positionPOResult.getVer();
		positionPOCon.setVer(version);
		
		TmInsPositionInfoPO positionPOValue=new TmInsPositionInfoPO();
		positionPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		positionPOValue.setUpdateTime(YBUtility.now());
		positionPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(positionPOResult.getFreezeTag())){
				logger.error(" INS_POSITION IS STOPED ALREADY .");
				atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3001", "处理检查地点：检查地点已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_UNV;
			positionPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(positionPOResult.getFreezeTag())){
				logger.error(" INS_POSITION IS START ALREADY .");
				atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3002", "处理检查地点：检查地点已经是启用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_VAL;
			positionPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_DEL;
			positionPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_3010", "处理检查地点：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, positionPOCon,positionPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+positionPOResult.getPositionName()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BASE_DATA_POSITION_MOD_ACTION_ERR_8000", "处理检查地点：数据["+positionPOResult.getPositionName()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "检查地点["+positionPOResult.getPositionName()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "检查地点["+positionPOResult.getPositionName()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
