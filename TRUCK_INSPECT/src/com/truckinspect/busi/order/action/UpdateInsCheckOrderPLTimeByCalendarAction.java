/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:UpdateInsCheckOrderPLTimeByCalendarAction.java
 * �� ������:2018��5��10��-����10:27:25
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:UpdateInsCheckOrderPLTimeByCalendarAction
 * ������:�������������ϸ��¼�鵥�ƻ���ʼ�ͽ���ʱ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��10�� ����10:27:25
 * �޸ı�ע:
 * @version 1.0.0
 */
public class UpdateInsCheckOrderPLTimeByCalendarAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderId=atx.getStringValue("CHECK_ORDER_ID");
		String newBegin=atx.getStringValue("NEW_START");
		String newEnd=atx.getStringValue("NEW_END");
		
		//check param
		if(StringUtils.isBlank(checkOrderId)||StringUtils.isBlank(newBegin)||StringUtils.isBlank(newEnd)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION_ERR_1000", "����������������ʼʱ�䣺����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check order exist
//		TM_INS_CHECK_MAIN_ORDER
		TmInsCheckMainOrderPO orderPOCon=new TmInsCheckMainOrderPO();
		orderPOCon.setStatus("1");
		orderPOCon.setFreezeTag("0");
		orderPOCon.setId(Integer.valueOf(checkOrderId));
		
		TmInsCheckMainOrderPO orderResult=POFactory.getByPriKey(conn, orderPOCon);
		if(orderResult==null){
			logger.error(" CHECK ORDER IS EMTPY ID["+checkOrderId+"] .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION_ERR_2000", "����������������ʼʱ�䣺��鵥ID["+checkOrderId+"]������", null);
			return 0;
		}
		
		String checkStatus=orderResult.getCheckOrderStatus();
//		check order status
		if(!TruckInsCommonCanstant.BASE_DATA_CHECK_ORDER_STATUS_UNCHECK.equals(checkStatus)){
			logger.error(" CHECK ORDER ID["+checkOrderId+"] ,STATUS NOT SUPPORT.");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION_ERR_3000", "����������������ʼʱ�䣺��鵥δ���ڴ����״̬���޷��϶�", null);
			return 0;
		}
		
		int version=orderResult.getVer();
		orderPOCon.setVer(version);
		

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		 
		try {
			Date checkPlBeginTime = DBConUtil.string2Time(newBegin);
			Date checkPlEndTime=DBConUtil.string2Time(newEnd);
			
			TmInsCheckMainOrderPO orderPOValue=new TmInsCheckMainOrderPO();
			
			orderPOValue.setUpdateBy(optMemberId);
			orderPOValue.setUpdateTime(YBUtility.now());
			orderPOValue.setVer(version+1);
			orderPOValue.setCheckPlBeginTime(checkPlBeginTime);
			orderPOValue.setCheckPlEndTime(checkPlEndTime);
			
			int excuteResult=POFactory.update(conn, orderPOCon, orderPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA ["+checkOrderId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION_ERR_8000", "����������������ʼʱ�䣺����ID["+checkOrderId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_PLB, "", "��������ʼʱ��");
			//set return msg
			atx.setStringValue("MSG", "����������������ʼʱ��ɹ�");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(" CHECK ORDER ID["+checkOrderId+"] ,PARSE TIME ERROR .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION_ERR_4000", "����������������ʼʱ�䣺��鵥ʱ�䴦��ʧ��", null);
			return 0;
		}
		
		return 1;
	}

}
