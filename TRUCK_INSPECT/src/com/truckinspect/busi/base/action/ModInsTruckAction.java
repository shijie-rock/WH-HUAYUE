/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:ModMemberAction.java
 * 创 建日期:2017年9月2日-上午9:34:04
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModInsTruckAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:34:04
 * 修改备注:
 * @version 1.0.0
 */
public class ModInsTruckAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckId=atx.getStringValue("TRUCK_ID",null);
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(truckId)||"0".equals(truckId)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_1000", "处理车辆：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check exist
//		TM_INS_TRUCK_INFO
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO();
		truckPOCon.setStatus("1");
		truckPOCon.setId(Integer.valueOf(truckId));
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		
		if(truckPOResult==null){
			logger.error(" TRUCK NOT EXIST .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_2000", "处理车辆：车辆不存在", null);
			return 0;
		}
		
		int version=truckPOResult.getVer();
		truckPOCon.setVer(version);
		
		TmInsTruckInfoPO truckPOValue=new TmInsTruckInfoPO();
		truckPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		truckPOValue.setUpdateTime(YBUtility.now());
		truckPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(truckPOResult.getFreezeTag())){
				logger.error(" TRUCK IS STOPED ALREADY .");
				atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_3001", "处理车辆：车辆已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_UNV;
			truckPOValue.setFreezeTag("1"); 
			
			
		}else if("start".equals(optType)){
			
			if("0".equals(truckPOResult.getFreezeTag())){
				logger.error(" TRUCK IS START ALREADY .");
				atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_3002", "处理车辆：车辆已经是启用状态", null);
				return 0;
			}
			//same license ?
			TmInsTruckInfoPO truckPOCoTemp=new TmInsTruckInfoPO();
			truckPOCoTemp.setStatus("1");
			truckPOCoTemp.setFreezeTag("0");
			truckPOCoTemp.setTruckLicense(truckPOResult.getTruckLicense());
			
			TmInsTruckInfoPO truckPOResultTemp=POFactory.getByPriKey(conn, truckPOCoTemp);
			if(truckPOResultTemp!=null){
				logger.error(" EXIST SAME LICENSE TRUCK .");
				atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_3003", "处理车辆：有相同车牌车辆处于启用状态", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_VAL;
			truckPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_MEMBER_DEL;
			truckPOValue.setStatus("0");
			//clear employee  MEMBER 
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_3010", "处理车辆：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, truckPOCon,truckPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+truckPOResult.getTruckLicense()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_MOD_ACTION_ERR_8000", "处理车辆：数据["+truckPOResult.getTruckLicense()+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "车辆["+truckPOResult.getTruckLicense()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "车辆["+truckPOResult.getTruckLicense()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}

}
