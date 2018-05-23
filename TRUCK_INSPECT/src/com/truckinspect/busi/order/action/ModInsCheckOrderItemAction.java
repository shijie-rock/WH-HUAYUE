/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:ModInsCheckOrderItemAction.java
 * 创 建日期:2018年5月9日-下午4:05:32
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
 * 类名称:ModInsCheckOrderItemAction
 * 类描述:检查单检查项目明细停用启用删除
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月9日 下午4:05:32
 * 修改备注:
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
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");//检查单号
		String checkItemId=atx.getStringValue("CHECK_ORDER_ITEM_ID");//检查明细id
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(checkItemId)||StringUtils.isBlank(checkOrderNo)||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_1000", "处理车辆检查单检查项目明细：参数为空", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_2000", "处理车辆检查单检查项目明细：检查单号["+checkOrderNo+"]不存在", null);
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
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3000", "处理车辆检查单检查项目明细：检查单号["+checkOrderNo+"]检查项目["+checkOrderNo+"]不存在", null);
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
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3001", "处理车辆检查单检查项目明细：车辆检查单明细已经是停用状态", null);
				return 0;
			}
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_UNV;
			itemPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(itemPOResult.getFreezeTag())){
				logger.error(" CHECK_ORDER_ITEM IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3002", "处理车辆检查单检查项目明细：车辆检查单明细已经是启用状态", null);
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
				atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3003", "处理车辆检查单检查项目明细：存在相同的检查项目编号", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_VAL;
			itemPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_WEBCHECKORDERITEM_DEL;
			itemPOValue.setStatus("0");
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_3010", "处理车辆检查单检查项目明细：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, itemPOCon,itemPOValue);//更新 check order item
		
		if(excuteResult<1){
			logger.error(" DATA["+itemPOResult.getCheckOrderNo()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_MOD_ACTION_ERR_8000", "处理车辆检查单检查项目明细：数据["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		//处理检查单主表的CHECK_ITEM_COUNT，CHECK_PASS_COUNT 
		int orderVesion=orderPOResult.getVer();//订单数据版本
		orderPOCon.setVer(orderVesion);
		
		TmInsCheckMainOrderPO orderPOValue=new TmInsCheckMainOrderPO();
		orderPOValue.setUpdateBy(optMemberId);
		orderPOValue.setUpdateTime(YBUtility.now());
		orderPOValue.setVer(version+1);
		
		DynaBean countBean=TmInsCheckOrderPOFactory.queryCheckOrderItemCountByCheckOrderNo(conn, checkOrderNo);
		if(countBean!=null){
			Integer allCount=Long.valueOf(countBean.getLong("ALL_COUNT")).intValue();//总检查项目数
			Integer passCount=Long.valueOf(countBean.getLong("PASS_COUNT")).intValue();//已通过项目数
			
			orderPOValue.setCheckItemCount(allCount);
			orderPOValue.setCheckPassCount(passCount);
			
			String orderStatus=orderPOResult.getCheckOrderStatus();//当前主单检查状态
			String orderResult=orderPOResult.getCheckOrderResult();
			//如果是检查已完成，则重新计算检查结果
			if("COS_0030".equals(orderStatus)||"COS_0040".equals(orderStatus)){ //已检查 or 待整改
				if(allCount==passCount){
					orderResult="COR_0030"; //全部合格
				}else if(passCount==0){
					orderResult="COR_0060"; //全部不合格
				}else {
					orderResult="COR_0020"; //部分合格
				}
				orderPOValue.setCheckOrderResult(orderResult);
			}
			
			logger.debug("UPDATE CHECK ORDER COUNT AND RESULT ");
			excuteResult=POFactory.update(conn, orderPOCon,orderPOValue);//更新 check order
			
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "车辆检查单检查项目明细["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "车辆检查单检查项目明细["+itemPOResult.getCheckOrderNo()+"]["+checkItemId+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}
		
}
