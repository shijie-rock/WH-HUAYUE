/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:AddInsCheckOrderAction.java
 * 创 建日期:2018年3月25日-下午6:18:47
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:AddInsCheckOrderAction
 * 类描述:Web端添加检查单-车辆
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月25日 下午6:18:47
 * 修改备注:
 * @version 1.0.0
 */
public class AddInsCheckOrderAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String targetId=atx.getStringValue("CHECK_TARGET_ID");//检查目标id
		String plMemberId=atx.getStringValue("CHECK_PL_MEMBER_ID");//计划检查人id
		String rMemberId=atx.getStringValue("CHECK_R_MEMBER_ID");
		String plBeingTime=atx.getStringValue("CHECK_PL_BEGIN_TIME");
		String plEndTime=atx.getStringValue("CHECK_PL_END_TIME");
		String rBeingTime=atx.getStringValue("CHECK_R_BEGIN_TIME");
		String rEndTime=atx.getStringValue("CHECK_R_END_TIME");
		String positionCode=atx.getStringValue("POSITION_CODE");
		String positionName=atx.getStringValue("POSITION_NAME");
		String positionAddDesc=atx.getStringValue("POSITION_ADDRESS");
		String itemCount=atx.getStringValue("CHECK_ITEM_COUNT");
		String passCount=atx.getStringValue("CHECK_PASS_COUNT");
		String checkStatus=atx.getStringValue("CHECK_ORDER_STATUS");
		String checkResult=atx.getStringValue("CHECK_ORDER_RESULT");
		String orderDesc=atx.getStringValue("CHECK_ORDER_DESC");
		String itemList=atx.getStringValue("ITEM_LIST");//CHE_010101ITEM$$测试检查项目;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$普通车头-检查
		
		//check param
		if(StringUtils.isBlank(targetId)||StringUtils.isBlank(plMemberId)||StringUtils.isBlank(itemList)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_1000", "新增车辆检查单：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		//check targetId exists
//		TM_INS_TRUCK_INFO
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO();
		truckPOCon.setStatus("1");
		truckPOCon.setFreezeTag("0");
		truckPOCon.setId(Integer.valueOf(targetId));
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult==null){
			logger.error(" TRAGET ID["+targetId+"] NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_2000", "新增车辆检查单：检查对象ID["+targetId+"]不存在", null);
			return 0;
		}
		//check plMemberId,rMemberId exists
//		TM_SYS_MEMBER
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setFreezeTag("0");
		
		memberPOCon.setId(Integer.valueOf(plMemberId));
		TmSysMemberPO memberPOResult=POFactory.getByPriKey(conn, memberPOCon);
		if(memberPOResult==null){
			logger.error(" PL_CHECK_MEMBER_ID["+plMemberId+"] NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_3000", "新增车辆检查单：计划检查人ID["+plMemberId+"]不存在", null);
			return 0;
		}
		TmSysMemberPO rCheckMemberPOResult;
		if(StringUtils.isNotBlank(rMemberId)){
			memberPOCon.setId(Integer.valueOf(plMemberId));
			rCheckMemberPOResult=POFactory.getByPriKey(conn, memberPOCon);
			if(rCheckMemberPOResult==null){
				logger.error(" R_CHECK_MEMBER_ID["+rMemberId+"] NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_4000", "新增车辆检查单：实际检查人ID["+rMemberId+"]不存在", null);
				return 0;
			}
		}

		//check plBeingTime<plEndTime
		if(StringUtils.isNotBlank(plBeingTime)&&StringUtils.isNotBlank(plEndTime)){
			try {
				if(DBConUtil.string2Time(plBeingTime).getTime()>DBConUtil.string2Time(plEndTime).getTime()){
					logger.error(" CHECK_PL_BEGIN_TIME > CHECK_PL_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_5000", "新增车辆检查单：计划开始时间晚于计划结束时间", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_5001", "新增车辆检查单：处理计划时间异常", null);
				return 0;
			}
		}
		
		//check rBeingTime<rEndTime if(rBeingTim,rEndTime exists)
		if(StringUtils.isNotBlank(rBeingTime)&&StringUtils.isNotBlank(rEndTime)){
			try {
				if(DBConUtil.string2Time(rBeingTime).getTime()>DBConUtil.string2Time(rEndTime).getTime()){
					logger.error(" CHECK_R_BEGIN_TIME > CHECK_R_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_6000", "新增车辆检查单：实际开始时间晚于实际结束时间", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_6001", "新增车辆检查单：处理实际时间异常", null);
				return 0;
			}
		}
		
//		TM_INS_POSITION_INFO
		TmInsPositionInfoPO positionPOResult=null;
		if(StringUtils.isNotBlank(positionCode)){
			TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
			positionPOCon.setStatus("1");
			positionPOCon.setFreezeTag("0");
			positionPOCon.setPositionCode(positionCode);
			
			positionPOResult=POFactory.getByPriKey(conn, positionPOCon);
			
			if(positionPOResult==null){
				logger.error(" POSITIION CODE ["+positionCode+"] NOT EXIST ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_7000", "新增车辆检查单：检查地点信息["+positionCode+"]不存在 ", null);
				return 0;
			}
		}

//		TM_INS_CHECK_MAIN_ORDER
		//CREATE ORDER_NO
		String orderNo=YBUtility.getWEBCheckOrderNo();
		TmInsCheckMainOrderPO orderPOCon=new TmInsCheckMainOrderPO();
		orderPOCon.setCheckOrderNo(orderNo);
		
		if(POFactory.getByPriKey(conn, orderPOCon)!=null){
			
			logger.error(" CHECK_ORDER_NO ["+orderNo+"]  EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_8000", "新增车辆检查单：检查单号["+orderNo+"]已经存在，请重试", null);
			return 0;
		}
		if(StringUtils.isNotBlank(itemCount)){
			orderPOCon.setCheckItemCount(Integer.valueOf(itemCount));
		}else{
			orderPOCon.setCheckItemCount(0);
		}
		if(StringUtils.isNotBlank(orderDesc)){
			orderPOCon.setCheckOrderDesc(orderDesc);
		}
		if(StringUtils.isNotBlank(checkStatus)){
			orderPOCon.setCheckOrderStatus(checkStatus);
		}else{
			orderPOCon.setCheckOrderStatus(TruckInsCommonCanstant.BASE_DATA_CHECK_ORDER_STATUS_UNCHECK);
		}
		
		if(StringUtils.isNotBlank(checkResult)){
			orderPOCon.setCheckOrderResult(checkResult);
		}else{
			orderPOCon.setCheckOrderResult(TruckInsCommonCanstant.BASE_DATA_CHECK_ORDER_RESULT_UNDONE);
		}
		if(StringUtils.isNotBlank(passCount)){
			orderPOCon.setCheckPassCount(Integer.valueOf(passCount));
		}else{
			orderPOCon.setCheckPassCount(0);
		}

		if(StringUtils.isNotBlank(plMemberId)){
			orderPOCon.setCheckPlMemberId(Integer.valueOf(plMemberId));
		}
		
		if(StringUtils.isNotBlank(rMemberId)){
			orderPOCon.setCheckRMemberId(Integer.valueOf(rMemberId));
		}
		
		orderPOCon.setCheckTargetCode(truckPOResult.getTruckLicense());
		orderPOCon.setCheckTargetId(Integer.valueOf(targetId));
		orderPOCon.setCheckTargetName(truckPOResult.getTruckLicense());
		
		orderPOCon.setCreateBy(optMemberId);
		orderPOCon.setCreateTime(YBUtility.now());
		orderPOCon.setFreezeTag("0");
		orderPOCon.setStatus("1");
		orderPOCon.setId(POFactory.getIntPriKey(conn, orderPOCon));
		if(StringUtils.isNotBlank(positionAddDesc))
		orderPOCon.setPositionAddress(positionAddDesc);
		
		orderPOCon.setPositionCode(positionCode);
		orderPOCon.setPositionName(positionName);
		
		if(positionPOResult!=null){
			orderPOCon.setPositionLatitude(positionPOResult.getPositionLatitude());
			orderPOCon.setPositionLongitude(positionPOResult.getPositionLongitude());
		}
		
		orderPOCon.setVer(1);
		
		try {
			if(StringUtils.isNotBlank(plBeingTime)){
				orderPOCon.setCheckPlBeginTime(DBConUtil.string2Time(plBeingTime));
			}
			if(StringUtils.isNotBlank(plEndTime)){
				orderPOCon.setCheckPlEndTime(DBConUtil.string2Time(plEndTime));
			}
			if(StringUtils.isNotBlank(rBeingTime)){
				orderPOCon.setCheckRBeginTime(DBConUtil.string2Time(rBeingTime));
			}
			if(StringUtils.isNotBlank(rEndTime)){
				orderPOCon.setCheckREndTime(DBConUtil.string2Time(rEndTime));
			}
				
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_8001", "新增车辆检查单：处理时间异常", null);
				return 0;
		}
		POFactory.insert(conn, orderPOCon);
		
//		TM_INS_CHECK_ORDER_ITEM
		if(StringUtils.isNotBlank(itemList)){
			//CHE_010101ITEM$$测试检查项目;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$普通车头-检查
			String[] itemCodeArray=itemList.split(";;");
			
			TmInsCheckOrderItemPO itemPOCon=new TmInsCheckOrderItemPO();
			itemPOCon.setCreateBy(optMemberId);
			itemPOCon.setCreateTime(YBUtility.now());
			itemPOCon.setStatus("1");
			itemPOCon.setFreezeTag("0");
			itemPOCon.setVer(1);
			itemPOCon.setCheckOrderNo(orderNo);
//			itemPOCon.setCheckOrderResult(checkOrderResult);
			itemPOCon.setCheckOrderStatus(TruckInsCommonCanstant.BASE_DATA_CHECK_ITEM_STATUS_UNDONE);
			
			itemPOCon.setPositionAddress(orderPOCon.getPositionAddress());
			itemPOCon.setPositionCode(orderPOCon.getPositionCode());
			itemPOCon.setPositionLatitude(orderPOCon.getPositionLatitude());
			itemPOCon.setPositionLongitude(orderPOCon.getPositionLongitude());
			itemPOCon.setPositionName(orderPOCon.getPositionName());
			
//			TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO objItemPOCon=new TmInsCheckObjItemPO();
			objItemPOCon.setStatus("1");
			objItemPOCon.setFreezeTag("0");
	
			TmInsCheckObjItemPO objItemPOResult;
			
			for(String itemCodeSplitStr:itemCodeArray){
				logger.debug("itemCodeSplitStr:="+itemCodeSplitStr);
				String[] itemCodeName=itemCodeSplitStr.split("[$][$]");
				if(itemCodeName==null||itemCodeName.length!=2){
					logger.error("ITEM_CODE_NAME NOT INVALID ["+itemCodeName+"]");
				}else{
					String itemCode=itemCodeName[0];
					String itemName=itemCodeName[1];
					
					itemPOCon.setCheckObjCode(itemCode);
					itemPOCon.setCheckObjName(itemName);
					
					objItemPOCon.setCheckObjCode(itemCode);
					
					objItemPOResult=POFactory.getByPriKey(conn, objItemPOCon);
					if(objItemPOResult!=null){
						itemPOCon.setCheckObjDesc(objItemPOResult.getCheckObjDesc());
						itemPOCon.setComlianceDesc(objItemPOResult.getComlianceDesc());
						itemPOCon.setObjEmergencyLevel(objItemPOResult.getObjEmergencyLevel());
					}
					itemPOCon.setId(POFactory.getIntPriKey(conn, itemPOCon));
					POFactory.insert(conn, itemPOCon);
				}
			}
		}
		atx.setStringValue("CHECK_ORDER_NO", orderNo);//订单号返回前台
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_ADD, "", "新增检查单");
		
		//set return msg
		atx.setStringValue("MSG", "新增检查单["+orderNo+"]成功");
		return 1;
	}

}



