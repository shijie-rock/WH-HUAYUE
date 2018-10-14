/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:EditInsCheckOrderAction.java
 * 创 建日期:2018年5月8日-上午9:57:32
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjItemPOFactory;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:EditInsCheckOrderAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月8日 上午9:57:32
 * 修改备注:
 * @version 1.0.0
 */
public class EditInsCheckOrderAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		String orderNo=atx.getStringValue("CHECK_ORDER_NO");//单号
		
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
		if(StringUtils.isBlank(orderNo)||StringUtils.isBlank(targetId)||StringUtils.isBlank(plMemberId)||StringUtils.isBlank(itemList)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_1000", "编辑车辆检查单：参数为空", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_2000", "编辑车辆检查单：检查对象ID["+targetId+"]不存在", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_3000", "编辑车辆检查单：计划检查人ID["+plMemberId+"]不存在", null);
			return 0;
		}
		TmSysMemberPO rCheckMemberPOResult;
		if(StringUtils.isNotBlank(rMemberId)){
			memberPOCon.setId(Integer.valueOf(plMemberId));
			rCheckMemberPOResult=POFactory.getByPriKey(conn, memberPOCon);
			if(rCheckMemberPOResult==null){
				logger.error(" R_CHECK_MEMBER_ID["+rMemberId+"] NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_4000", "编辑车辆检查单：实际检查人ID["+rMemberId+"]不存在", null);
				return 0;
			}
		}

		//check plBeingTime<plEndTime
		if(StringUtils.isNotBlank(plBeingTime)&&StringUtils.isNotBlank(plEndTime)){
			try {
				if(DBConUtil.string2Time(plBeingTime).getTime()>DBConUtil.string2Time(plEndTime).getTime()){
					logger.error(" CHECK_PL_BEGIN_TIME > CHECK_PL_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_5000", "编辑车辆检查单：计划开始时间晚于计划结束时间", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_5001", "编辑车辆检查单：处理计划时间异常", null);
				return 0;
			}
		}
		
		//check rBeingTime<rEndTime if(rBeingTim,rEndTime exists)
		if(StringUtils.isNotBlank(rBeingTime)&&StringUtils.isNotBlank(rEndTime)){
			try {
				if(DBConUtil.string2Time(rBeingTime).getTime()>DBConUtil.string2Time(rEndTime).getTime()){
					logger.error(" CHECK_R_BEGIN_TIME > CHECK_R_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_6000", "编辑车辆检查单：实际开始时间晚于实际结束时间", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_6001", "编辑车辆检查单：处理实际时间异常", null);
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
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_7000", "编辑车辆检查单：检查地点信息["+positionCode+"]不存在 ", null);
				return 0;
			}
		}
		//check order no exists
		
		TmInsCheckMainOrderPO orderPOCon=new TmInsCheckMainOrderPO();
		orderPOCon.setCheckOrderNo(orderNo);
		orderPOCon.setStatus("1");
		orderPOCon.setFreezeTag("0");
		
		TmInsCheckMainOrderPO orderPOResult=POFactory.getByPriKey(conn, orderPOCon);
		if(orderPOResult==null){
			logger.error(" CHECK_ORDER_NO ["+orderNo+"]  NOT EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8000", "编辑车辆检查单：检查单号["+orderNo+"]不存在", null);
			return 0;
		}
		
		int version=orderPOResult.getVer();
		orderPOCon.setVer(version);
		
		TmInsCheckMainOrderPO orderPOValue=new TmInsCheckMainOrderPO();
		
		orderPOValue.setUpdateBy(optMemberId);
		orderPOValue.setUpdateTime(YBUtility.now());
		orderPOValue.setVer(version+1);
		
		if(StringUtils.isNotBlank(itemCount)){
			orderPOValue.setCheckItemCount(Integer.valueOf(itemCount));
		}else{
			orderPOValue.setCheckItemCount(0);
		}
		if(StringUtils.isNotBlank(orderDesc)){
			orderPOValue.setCheckOrderDesc(orderDesc);
		}
		if(StringUtils.isNotBlank(checkStatus)){
			orderPOValue.setCheckOrderStatus(checkStatus);
		}else{
			orderPOValue.setCheckOrderStatus(TruckInsCommonCanstant.BASE_DATA_CHECK_ORDER_STATUS_UNCHECK);
		}
		
		if(StringUtils.isNotBlank(checkResult)){
			orderPOValue.setCheckOrderResult(checkResult);
		}else{
			orderPOValue.setCheckOrderResult(TruckInsCommonCanstant.BASE_DATA_CHECK_ORDER_RESULT_UNDONE);
		}
		if(StringUtils.isNotBlank(passCount)){
			orderPOValue.setCheckPassCount(Integer.valueOf(passCount));
		}else{
			orderPOValue.setCheckPassCount(0);
		}

		if(StringUtils.isNotBlank(plMemberId)){
			orderPOValue.setCheckPlMemberId(Integer.valueOf(plMemberId));
		}
		
		if(StringUtils.isNotBlank(rMemberId)){
			orderPOValue.setCheckRMemberId(Integer.valueOf(rMemberId));
		}
		
		orderPOValue.setCheckTargetCode(truckPOResult.getTruckLicense());
		orderPOValue.setCheckTargetId(Integer.valueOf(targetId));
		orderPOValue.setCheckTargetName(truckPOResult.getTruckLicense());
		
		orderPOValue.setCreateBy(optMemberId);
		orderPOValue.setCreateTime(YBUtility.now());
		orderPOValue.setFreezeTag("0");
		orderPOValue.setStatus("1");
		orderPOValue.setId(POFactory.getIntPriKey(conn, orderPOCon));
		if(StringUtils.isNotBlank(positionAddDesc))
			orderPOValue.setPositionAddress(positionAddDesc);
		
		orderPOValue.setPositionCode(positionCode);
		orderPOValue.setPositionName(positionName);
		
		if(positionPOResult!=null){
			orderPOValue.setPositionLatitude(positionPOResult.getPositionLatitude());
			orderPOValue.setPositionLongitude(positionPOResult.getPositionLongitude());
		}
		
		try {
			if(StringUtils.isNotBlank(plBeingTime)){
				orderPOValue.setCheckPlBeginTime(DBConUtil.string2Time(plBeingTime));
			}
			if(StringUtils.isNotBlank(plEndTime)){
				orderPOValue.setCheckPlEndTime(DBConUtil.string2Time(plEndTime));
			}
			if(StringUtils.isNotBlank(rBeingTime)){
				orderPOValue.setCheckRBeginTime(DBConUtil.string2Time(rBeingTime));
			}
			if(StringUtils.isNotBlank(rEndTime)){
				orderPOValue.setCheckREndTime(DBConUtil.string2Time(rEndTime));
			}
				
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8001", "编辑车辆检查单：处理时间异常", null);
				return 0;
		}
		
//		int excuteResult=POFactory.update(conn, orderPOCon, orderPOValue);
//		
//		if(excuteResult<1){
//			logger.error(" DATA["+orderNo+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
//			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8002", "编辑车辆检查单：数据NO["+orderNo+"]已变更，无法完成操作，请稍后重试", null);
//			return 0;
//		}
		
//		TM_INS_CHECK_ORDER_ITEM
		if(StringUtils.isNotBlank(itemList)){
			//
			orderPOCon.setVer(version+1);
			orderPOCon=POFactory.getByPriKey(conn, orderPOCon);
			
			//parse check item 
			//
			TmInsCheckOrderItemPO itemPOCon=new TmInsCheckOrderItemPO();
			itemPOCon.setStatus("1");
			itemPOCon.setFreezeTag("0");
			itemPOCon.setCheckOrderNo(orderNo);
			
			TmInsCheckOrderItemPO itemPOUpdateValue=new TmInsCheckOrderItemPO();
			itemPOUpdateValue.setUpdateBy(optMemberId);
			itemPOUpdateValue.setUpdateTime(YBUtility.now());
			itemPOUpdateValue.setPositionAddress(orderPOCon.getPositionAddress());
			itemPOUpdateValue.setPositionCode(orderPOCon.getPositionCode());
			itemPOUpdateValue.setPositionLatitude(orderPOCon.getPositionLatitude());
			itemPOUpdateValue.setPositionLongitude(orderPOCon.getPositionLongitude());
			itemPOUpdateValue.setPositionName(orderPOCon.getPositionName());
			
			//CHE_010101ITEM$$测试检查项目;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$普通车头-检查
			String[] itemCodeArray=itemList.split(";;");
			TmInsCheckOrderItemPO itemPOInsertValue=new TmInsCheckOrderItemPO();
			itemPOInsertValue.setCreateBy(optMemberId);
			itemPOInsertValue.setCreateTime(YBUtility.now());
			itemPOInsertValue.setStatus("1");
			itemPOInsertValue.setFreezeTag("0");
			itemPOInsertValue.setVer(1);
			itemPOInsertValue.setCheckOrderNo(orderNo);
//			itemPOCon.setCheckOrderResult(checkOrderResult);
			itemPOInsertValue.setCheckOrderStatus(TruckInsCommonCanstant.BASE_DATA_CHECK_ITEM_STATUS_UNDONE);
			
			itemPOInsertValue.setPositionAddress(orderPOCon.getPositionAddress());
			itemPOInsertValue.setPositionCode(orderPOCon.getPositionCode());
			itemPOInsertValue.setPositionLatitude(orderPOCon.getPositionLatitude());
			itemPOInsertValue.setPositionLongitude(orderPOCon.getPositionLongitude());
			itemPOInsertValue.setPositionName(orderPOCon.getPositionName());
			
//			TM_INS_CHECK_OBJ_ITEM
			TmInsCheckObjItemPO objItemPOCon=new TmInsCheckObjItemPO();
			objItemPOCon.setStatus("1");
			objItemPOCon.setFreezeTag("0");
	
			TmInsCheckObjItemPO objItemPOResult;
			List<Integer> idList=new ArrayList<Integer>();//存有效的check item id
			
			StringBuffer sbf=new StringBuffer("");//检测项目摘要
			
			for(String itemCodeSplitStr:itemCodeArray){
				logger.debug("itemCodeSplitStr:="+itemCodeSplitStr);
				String[] itemCodeName=itemCodeSplitStr.split("[$][$]");
				if(itemCodeName==null||itemCodeName.length!=2){
					logger.error("ITEM_CODE_NAME NOT INVALID ["+itemCodeName+"]");
				}else{
					String itemCode=itemCodeName[0];
					String itemName=itemCodeName[1];
					
					sbf.append(itemName).append(",");
					
					itemPOCon.setCheckObjCode(itemCode);
//					itemPOCon.setCheckObjName(itemName);
					//查询检查项目详情
					objItemPOCon.setCheckObjCode(itemCode);
					objItemPOResult=POFactory.getByPriKey(conn, objItemPOCon);
					if(objItemPOResult!=null){
						itemPOInsertValue.setCheckObjDesc(objItemPOResult.getCheckObjDesc());
						itemPOInsertValue.setComlianceDesc(objItemPOResult.getComlianceDesc());
						itemPOInsertValue.setObjEmergencyLevel(objItemPOResult.getObjEmergencyLevel());
						itemPOInsertValue.setCheckObjCode(itemCode);
						itemPOInsertValue.setCheckObjName(itemName);
						
						itemPOUpdateValue.setCheckObjDesc(objItemPOResult.getCheckObjDesc());
						itemPOUpdateValue.setComlianceDesc(objItemPOResult.getComlianceDesc());
						itemPOUpdateValue.setObjEmergencyLevel(objItemPOResult.getObjEmergencyLevel());
					}
					
					TmInsCheckOrderItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
					if(itemPOResult!=null){
						//update
						itemPOCon.setId(itemPOResult.getId());
						itemPOUpdateValue.setVer(itemPOResult.getVer()+1);
						POFactory.update(conn, itemPOCon, itemPOUpdateValue);
						idList.add(itemPOResult.getId());
					}else{
						//insert
						itemPOInsertValue.setId(POFactory.getIntPriKey(conn, itemPOInsertValue));
						POFactory.insert(conn, itemPOInsertValue);
						idList.add(itemPOInsertValue.getId());
					}
				}
				
				if(StringUtils.isNotBlank(sbf.toString())){ //20181012-增加摘要
					String itemSummary=sbf.toString();
					if(itemSummary.endsWith(",")){
						itemSummary=itemSummary.substring(0,itemSummary.length()-1);
					}
					orderPOCon.setCheckItemSummary(itemSummary);
				}
			}
			
			int excuteResult=POFactory.update(conn, orderPOCon, orderPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+orderNo+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8002", "编辑车辆检查单：数据NO["+orderNo+"]已变更，无法完成操作，请稍后重试", null);
				return 0;
			}
			
			String idStr=null;
			if(idList!=null){
				idStr=StringUtils.join(idList.toArray(),","); 
			}
			//处理需要删除的check item
			TmInsCheckObjItemPOFactory.updateNeedDeleteCheckOrderItem(conn, orderNo, idStr, optMemberId);
			
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_MOD, "", "编辑车辆检查单");
		
		//set return msg
		atx.setStringValue("MSG", "编辑车辆检查单["+orderNo+"]成功");
		return 1;
	}

}
