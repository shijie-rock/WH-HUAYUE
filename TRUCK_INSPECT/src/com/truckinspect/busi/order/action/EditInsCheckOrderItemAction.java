/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.order.action
 * ��   ��  ��:EditInsCheckOrderItemAction.java
 * �� ������:2018��5��9��-����3:28:04
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.order.po.TmInsCheckMainOrderPO;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:EditInsCheckOrderItemAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��5��9�� ����3:28:04
 * �޸ı�ע:
 * @version 1.0.0
 */
public class EditInsCheckOrderItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String orderNo=atx.getStringValue("CHECK_ORDER_NO");//����
		String itemId=atx.getStringValue("CHECK_ORDER_ITEM_ID");//
		
		String itemComliance=atx.getStringValue("ITEM_COMLIANCE");//��׼
		String itemTroubleDesc=atx.getStringValue("ITEM_TOUBLE_DESC");//��������
		String itemCheckStatus=atx.getStringValue("ITEM_CHECK_STATUS");//��Ŀ���״̬
		String itemCheckResult=atx.getStringValue("ITEM_CHECK_RESULT");//��Ŀ�����
		
		
		//check param
		if(StringUtils.isBlank(itemId)||StringUtils.isBlank(orderNo)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_1000", "�༭������鵥�����Ŀ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);

		//check order no exists
		TmInsCheckMainOrderPO orderPOCon=new TmInsCheckMainOrderPO();
		orderPOCon.setCheckOrderNo(orderNo);
		orderPOCon.setStatus("1");
		orderPOCon.setFreezeTag("0");
		
		TmInsCheckMainOrderPO orderPOResult=POFactory.getByPriKey(conn, orderPOCon);
		if(orderPOResult==null){
			logger.error(" CHECK_ORDER_NO ["+orderNo+"]  NOT EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_2000", "�༭������鵥�����Ŀ����鵥��["+orderNo+"]������", null);
			return 0;
		}
		
		//parse check item 
		//
		TmInsCheckOrderItemPO itemPOCon=new TmInsCheckOrderItemPO();
		itemPOCon.setStatus("1");
		itemPOCon.setFreezeTag("0");
		itemPOCon.setCheckOrderNo(orderNo);
		itemPOCon.setId(Integer.valueOf(itemId));
		
		TmInsCheckOrderItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		if(itemPOResult==null){
			logger.error(" CHECK_ORDER_NO ["+orderNo+"] ITEM ID["+itemId+"] NOT EXIST ");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_3000", "�༭������鵥�����Ŀ����鵥��["+orderNo+"]�����Ŀ["+itemId+"]������", null);
			return 0;
		}
		
		int version=itemPOResult.getVer();
		
		TmInsCheckOrderItemPO itemPOValue=new TmInsCheckOrderItemPO();
		itemPOValue.setUpdateBy(optMemberId);
		itemPOValue.setUpdateTime(YBUtility.now());
		itemPOValue.setVer(version+1);
		
		itemPOValue.setCheckOrderResult(itemCheckResult);
		itemPOValue.setTroubleDesc(itemTroubleDesc);
		itemPOValue.setCheckOrderStatus(itemCheckStatus);
		itemPOValue.setComlianceDesc(itemComliance);
		
		int excuteResult=POFactory.update(conn, itemPOCon, itemPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA NO["+orderNo+"]ID["+itemId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8002", "�༭������鵥�����Ŀ������NO["+orderNo+"]ID["+itemId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		
		//ȱ�٣���Ӧ ��鵥����� ״̬����߼�
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_MOD, "", "�༭������鵥�����Ŀ");
		
		//set return msg
		atx.setStringValue("MSG", "�༭������鵥�����Ŀ["+orderNo+"]["+itemPOResult.getCheckObjCode()+"]�ɹ�");
		return 1;
	}

}
