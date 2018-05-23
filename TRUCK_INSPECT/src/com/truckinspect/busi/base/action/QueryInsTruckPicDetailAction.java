/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsTruckPicDetailAction.java
 * 创 建日期:2018年3月5日-下午4:22:11
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.base.po.TtInsTruckImgPO;

/**
 * 类名称:QueryInsTruckPicDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月5日 下午4:22:11
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsTruckPicDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckId=atx.getStringValue("TRUCK_ID");
		//check param
		if(StringUtils.isBlank(truckId)||"0".equals(truckId)){
			logger.error(" PARAM TRUCK_ID IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_PIC_DETAIL_ACTION_ERR_1000", "查询车辆图片：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		TM_INS_TRUCK_INFO
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO(); 
		truckPOCon.setStatus("1");
		truckPOCon.setId(Integer.valueOf(truckId));
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult==null){
			logger.error("车辆不存在");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_PIC_DETAIL_ACTION_ERR_2000", "查询车辆图片：车辆不存在", null);
			return 0;
		}
//		TT_INS_TRUCK_IMG
		TtInsTruckImgPO imgPOCon=new TtInsTruckImgPO();
		imgPOCon.setStatus("1");
		imgPOCon.setFreezeTag("0");
		imgPOCon.setTruckId(truckPOResult.getId());
		
		List<TtInsTruckImgPO> imgList=POFactory.select(conn, imgPOCon);
		
		if(imgList!=null&&imgList.size()>0){
			atx.setArrayValue("PIC_LIST", imgList.toArray());
		}
		
		return 1;
	}

}
