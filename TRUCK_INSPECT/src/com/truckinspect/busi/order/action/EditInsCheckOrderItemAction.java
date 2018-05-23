/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:EditInsCheckOrderItemAction.java
 * 创 建日期:2018年5月9日-下午3:28:04
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
 * 类名称:EditInsCheckOrderItemAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月9日 下午3:28:04
 * 修改备注:
 * @version 1.0.0
 */
public class EditInsCheckOrderItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String orderNo=atx.getStringValue("CHECK_ORDER_NO");//单号
		String itemId=atx.getStringValue("CHECK_ORDER_ITEM_ID");//
		
		String itemComliance=atx.getStringValue("ITEM_COMLIANCE");//标准
		String itemTroubleDesc=atx.getStringValue("ITEM_TOUBLE_DESC");//问题描述
		String itemCheckStatus=atx.getStringValue("ITEM_CHECK_STATUS");//项目检查状态
		String itemCheckResult=atx.getStringValue("ITEM_CHECK_RESULT");//项目检查结果
		
		
		//check param
		if(StringUtils.isBlank(itemId)||StringUtils.isBlank(orderNo)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_1000", "编辑车辆检查单检查项目：参数为空", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_2000", "编辑车辆检查单检查项目：检查单号["+orderNo+"]不存在", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_EDIT_ACTION_ERR_3000", "编辑车辆检查单检查项目：检查单号["+orderNo+"]检查项目["+itemId+"]不存在", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_EDIT_ACTION_ERR_8002", "编辑车辆检查单检查项目：数据NO["+orderNo+"]ID["+itemId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		
		//缺少，相应 检查单主表的 状态变更逻辑
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_MOD, "", "编辑车辆检查单检查项目");
		
		//set return msg
		atx.setStringValue("MSG", "编辑车辆检查单检查项目["+orderNo+"]["+itemPOResult.getCheckObjCode()+"]成功");
		return 1;
	}

}
