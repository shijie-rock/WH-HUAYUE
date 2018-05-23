/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:ModInsCheckOrderItemAction.java
 * �� ������:2018��5��9��-����4:05:32
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:ModInsCheckOrderItemAction
 * ������:��鵥�����Ŀ��ϸͣ������ɾ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��9�� ����4:05:32
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ModInsCheckOrderItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");//��鵥��
		String checkItemId=atx.getStringValue("CHECK_ORDER_ITEM_ID");//�����ϸid
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(checkItemId)||StringUtils.isBlank(checkOrderNo)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_1000", "��������鵥�����Ŀ��ϸ������Ϊ��", null);
			return 0;
		}
		
		
		Connection conn=atx.getConnection();
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);

		//check order no exists
		TmInsCheckMainOrderPO orderPOCon=new TmInsCheckMainOrderPO();
		orderPOCon.setCheckOrderNo(checkOrderNo);
		orderPOCon.setStatus("1");
		orderPOCon.setFreezeTag("0");
		
		TmInsCheckMainOrderPO orderPOResult=POFactory.getByPriKey(conn, orderPOCon);
		if(orderPOResult==null){
			logger.error(" CHECK_ORDER_NO ["+checkOrderNo+"]  NOT EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_2000", "��������鵥�����Ŀ��ϸ����鵥��["+checkOrderNo+"]������", null);
			return 0;
		}
		
		//parse check item 
		TmInsCheckOrderItemPO itemPOCon=new TmInsCheckOrderItemPO();
		itemPOCon.setStatus("1");
//		itemPOCon.setFreezeTag("0");
		itemPOCon.setCheckOrderNo(checkOrderNo);
		itemPOCon.setId(Integer.valueOf(checkItemId));
		
		TmInsCheckOrderItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		if(itemPOResult==null){
			logger.error(" CHECK_ORDER_NO ["+checkOrderNo+"] ITEM ID["+checkItemId+"] NOT EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3000", "��������鵥�����Ŀ��ϸ����鵥��["+checkOrderNo+"]�����Ŀ["+checkOrderNo+"]������", null);
			return 0;
		}

		int version=itemPOResult.getVer();
		
		itemPOCon.setVer(version);
		
		TmInsCheckOrderItemPO itemPOValue=new TmInsCheckOrderItemPO();
		itemPOValue.setUpdateBy(optMemberId);
		itemPOValue.setUpdateTime(YBUtility.now());
		itemPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(itemPOResult.getFreezeTag())){
				logger.error(" CHECK_ORDER_ITEM IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3001", "��������鵥�����Ŀ��ϸ��������鵥��ϸ�Ѿ���ͣ��״̬", null);
				return 0;
			}
			//BUSI
			optTypeDesc="ͣ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_UNV;
			itemPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(itemPOResult.getFreezeTag())){
				logger.error(" CHECK_ORDER_ITEM IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3002", "��������鵥�����Ŀ��ϸ��������鵥��ϸ�Ѿ�������״̬", null);
				return 0;
			}
			
			TmInsCheckOrderItemPO checkOrderItemPOConTemp=new TmInsCheckOrderItemPO();
			checkOrderItemPOConTemp.setStatus("1");
			checkOrderItemPOConTemp.setFreezeTag("0");
			checkOrderItemPOConTemp.setCheckOrderNo(checkOrderNo);
			checkOrderItemPOConTemp.setCheckObjCode(itemPOResult.getCheckObjCode());
			
			TmInsCheckOrderItemPO checkOrderItemPOResultTemp=POFactory.getByPriKey(conn, checkOrderItemPOConTemp);
			
			if(checkOrderItemPOResultTemp!=null){
				logger.error(" CHECK_ORDER_ITEM SAME OBJ_CHECK_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3003", "��������鵥�����Ŀ��ϸ��������ͬ�ļ����Ŀ���", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="����";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_VAL;
			itemPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="ɾ��";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_DEL;
			itemPOValue.setStatus("0");
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3010", "��������鵥�����Ŀ��ϸ����֧�ֲ�������["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, itemPOCon,itemPOValue);//���� check order item
		
		if(excuteResult<1){
			logger.error(" DATA["+itemPOResult.getCheckOrderNo()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_8000", "��������鵥�����Ŀ��ϸ������["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		//�����鵥�����CHECK_ITEM_COUNT��CHECK_PASS_COUNT 
		int orderVesion=orderPOResult.getVer();//�������ݰ汾
		orderPOCon.setVer(orderVesion);
		
		TmInsCheckMainOrderPO orderPOValue=new TmInsCheckMainOrderPO();
		orderPOValue.setUpdateBy(optMemberId);
		orderPOValue.setUpdateTime(YBUtility.now());
		orderPOValue.setVer(version+1);
		
		DynaBean countBean=TmInsCheckOrderPOFactory.queryCheckOrderItemCountByCheckOrderNo(conn, checkOrderNo);
		if(countBean!=null){
			Integer allCount=Long.valueOf(countBean.getLong("ALL_COUNT")).intValue();//�ܼ����Ŀ��
			Integer passCount=Long.valueOf(countBean.getLong("PASS_COUNT")).intValue();//��ͨ����Ŀ��
			
			orderPOValue.setCheckItemCount(allCount);
			orderPOValue.setCheckPassCount(passCount);
			
			String orderStatus=orderPOResult.getCheckOrderStatus();//��ǰ�������״̬
			String orderResult=orderPOResult.getCheckOrderResult();
			//����Ǽ������ɣ������¼�������
			if("COS_0030".equals(orderStatus)||"COS_0040".equals(orderStatus)){ //�Ѽ�� or ������
				if(allCount==passCount){
					orderResult="COR_0030"; //ȫ���ϸ�
				}else if(passCount==0){
					orderResult="COR_0060"; //ȫ�����ϸ�
				}else {
					orderResult="COR_0020"; //���ֺϸ�
				}
				orderPOValue.setCheckOrderResult(orderResult);
			}
			
			logger.debug("UPDATE CHECK ORDER COUNT AND RESULT ");
			excuteResult=POFactory.update(conn, orderPOCon,orderPOValue);//���� check order
			
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "������鵥�����Ŀ��ϸ["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "������鵥�����Ŀ��ϸ["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]"+optTypeDesc+"����ɹ���");
		return 1;
		
	}
		
}
