/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:ModInsCheckOrderAction.java
 * �� ������:2018��5��8��-����2:53:00
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModInsCheckOrderAction
 * ������:ͣ�á����á�ɾ���ȼ�鵥����������ӱ�������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��8�� ����2:53:00
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModInsCheckOrderAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");//��鵥��
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(checkOrderNo)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_1000", "��������鵥������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		//check exist
//		TM_INS_CHECK_MAIN_ORDER
		TmInsCheckMainOrderPO checkOrderPOCon=new TmInsCheckMainOrderPO();
		checkOrderPOCon.setStatus("1");
		checkOrderPOCon.setCheckOrderNo(checkOrderNo);
		
		TmInsCheckMainOrderPO checkOrderPOResult=POFactory.getByPriKey(conn, checkOrderPOCon);
		
		if(checkOrderPOResult==null){
			logger.error(" CHECK_ORDER NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_2000", "��������鵥��������鵥������", null);
			return 0;
		}
		
		int version=checkOrderPOResult.getVer();
		checkOrderPOCon.setVer(version);
		
		TmInsCheckMainOrderPO checkOrderPOValue=new TmInsCheckMainOrderPO();
		checkOrderPOValue.setUpdateBy(optMemberId);
		checkOrderPOValue.setUpdateTime(YBUtility.now());
		checkOrderPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(checkOrderPOResult.getFreezeTag())){
				logger.error(" CHECK_ORDER IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_3001", "��������鵥��������鵥�Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_UNV;
			checkOrderPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(checkOrderPOResult.getFreezeTag())){
				logger.error(" CHECK_ORDER IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_3002", "��������鵥��������鵥�Ѿ�������״̬", null);
				return 0;
			}
			
			TmInsCheckMainOrderPO checkOrderPOConTemp=new TmInsCheckMainOrderPO();
			checkOrderPOConTemp.setStatus("1");
			checkOrderPOConTemp.setFreezeTag("0");
			checkOrderPOConTemp.setCheckOrderNo(checkOrderNo);
			
			TmInsCheckMainOrderPO checkOrderPOResultTemp=POFactory.getByPriKey(conn, checkOrderPOConTemp);
			
			if(checkOrderPOResultTemp!=null){
				logger.error(" CHECK_ORDER SAME ORDER_NO EXIST .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_3003", "��������鵥��������ͬ���ŵĳ�����鵥", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_VAL;
			checkOrderPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDER_DEL;
			checkOrderPOValue.setStatus("0");
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_3010", "��������鵥����֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, checkOrderPOCon,checkOrderPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+checkOrderPOResult.getCheckOrderNo()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_MOD_ACTION_ERR_8000", "��������鵥������["+checkOrderPOResult.getCheckOrderNo()+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "������鵥["+checkOrderPOResult.getCheckOrderNo()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "������鵥["+checkOrderPOResult.getCheckOrderNo()+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}

}
