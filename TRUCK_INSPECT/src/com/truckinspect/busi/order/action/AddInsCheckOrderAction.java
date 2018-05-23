/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:AddInsCheckOrderAction.java
 * �� ������:2018��3��25��-����6:18:47
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
 * ������:AddInsCheckOrderAction
 * ������:Web����Ӽ�鵥-����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��25�� ����6:18:47
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddInsCheckOrderAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String targetId=atx.getStringValue("CHECK_TARGET_ID");//���Ŀ��id
		String plMemberId=atx.getStringValue("CHECK_PL_MEMBER_ID");//�ƻ������id
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
		String itemList=atx.getStringValue("ITEM_LIST");//CHE_010101ITEM$$���Լ����Ŀ;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$��ͨ��ͷ-���
		
		//check param
		if(StringUtils.isBlank(targetId)||StringUtils.isBlank(plMemberId)||StringUtils.isBlank(itemList)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_1000", "����������鵥������Ϊ��", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_2000", "����������鵥��������ID["+targetId+"]������", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_3000", "����������鵥���ƻ������ID["+plMemberId+"]������", null);
			return 0;
		}
		TmSysMemberPO rCheckMemberPOResult;
		if(StringUtils.isNotBlank(rMemberId)){
			memberPOCon.setId(Integer.valueOf(plMemberId));
			rCheckMemberPOResult=POFactory.getByPriKey(conn, memberPOCon);
			if(rCheckMemberPOResult==null){
				logger.error(" R_CHECK_MEMBER_ID["+rMemberId+"] NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_4000", "����������鵥��ʵ�ʼ����ID["+rMemberId+"]������", null);
				return 0;
			}
		}

		//check plBeingTime<plEndTime
		if(StringUtils.isNotBlank(plBeingTime)&&StringUtils.isNotBlank(plEndTime)){
			try {
				if(DBConUtil.string2Time(plBeingTime).getTime()>DBConUtil.string2Time(plEndTime).getTime()){
					logger.error(" CHECK_PL_BEGIN_TIME > CHECK_PL_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_5000", "����������鵥���ƻ���ʼʱ�����ڼƻ�����ʱ��", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_5001", "����������鵥������ƻ�ʱ���쳣", null);
				return 0;
			}
		}
		
		//check rBeingTime<rEndTime if(rBeingTim,rEndTime exists)
		if(StringUtils.isNotBlank(rBeingTime)&&StringUtils.isNotBlank(rEndTime)){
			try {
				if(DBConUtil.string2Time(rBeingTime).getTime()>DBConUtil.string2Time(rEndTime).getTime()){
					logger.error(" CHECK_R_BEGIN_TIME > CHECK_R_END_TIME");
					atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_6000", "����������鵥��ʵ�ʿ�ʼʱ������ʵ�ʽ���ʱ��", null);
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(" PARSE TIME ERROR ");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_6001", "����������鵥������ʵ��ʱ���쳣", null);
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
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_7000", "����������鵥�����ص���Ϣ["+positionCode+"]������ ", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_8000", "����������鵥����鵥��["+orderNo+"]�Ѿ����ڣ�������", null);
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
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ADD_ACTION_ERR_8001", "����������鵥������ʱ���쳣", null);
				return 0;
		}
		POFactory.insert(conn, orderPOCon);
		
//		TM_INS_CHECK_ORDER_ITEM
		if(StringUtils.isNotBlank(itemList)){
			//CHE_010101ITEM$$���Լ����Ŀ;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$��ͨ��ͷ-���
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
		atx.setStringValue("CHECK_ORDER_NO", orderNo);//�����ŷ���ǰ̨
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_ADD, "", "������鵥");
		
		//set return msg
		atx.setStringValue("MSG", "������鵥["+orderNo+"]�ɹ�");
		return 1;
	}

}



