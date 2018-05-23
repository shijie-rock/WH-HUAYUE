/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryInsCheckOrderItemPicDetailAction.java
 * 创 建日期:2018年5月9日-下午5:22:27
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.base.po.TtInsTruckImgPO;
import com.truckinspect.busi.order.po.TmInsCheckOrderItemPO;
import com.truckinspect.busi.order.po.TtInsCheckOrderItemPicPO;

/**
 * 类名称:QueryInsCheckOrderItemPicDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月9日 下午5:22:27
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsCheckOrderItemPicDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String checkItemId=atx.getStringValue("CHECK_ITEM_ID");
		String checkOrderNo=atx.getStringValue("CHECK_ORDER_NO");
		//check param
		if(StringUtils.isBlank(checkItemId)||StringUtils.isBlank(checkOrderNo)){
			logger.error(" PARAM TRUCK_ID IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_PIC_DETAIL_ACTION_ERR_1000", "查询检查项目图片：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		TM_INS_CHECK_ORDER_ITEM
		TmInsCheckOrderItemPO itemPOCon=new TmInsCheckOrderItemPO(); 
		itemPOCon.setStatus("1");
		itemPOCon.setCheckOrderNo(checkOrderNo);
		itemPOCon.setId(Integer.valueOf(checkItemId));
		
		TmInsCheckOrderItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		if(itemPOResult==null){
			logger.error("检查项目不存在");
			atx.setErrorContext("BUSI_DATA_CHECK_ORDER_ITEM_PIC_DETAIL_ACTION_ERR_2000", "查询检查项目图片：检查项目不存在", null);
			return 0;
		}
//		TT_INS_CHECK_ORDER_ITEM_PIC
		TtInsCheckOrderItemPicPO imgPOCon=new TtInsCheckOrderItemPicPO();
		imgPOCon.setStatus("1");
		imgPOCon.setFreezeTag("0");
		imgPOCon.setCheckOrderNo(checkOrderNo);
		imgPOCon.setCheckOrderItemId(itemPOResult.getId());
		
		List<TtInsCheckOrderItemPicPO> imgList=POFactory.select(conn, imgPOCon);
		
		if(imgList!=null&&imgList.size()>0){
			atx.setArrayValue("PIC_LIST", imgList.toArray());
		}
		
		return 1;
	}
}
