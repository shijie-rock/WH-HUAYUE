/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:QueryInsTruckListAction.java
 * 创 建日期:2017年9月2日-上午9:34:29
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.base.pofactory.TmInsTruckInfoPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:QueryInsTruckListAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:34:29
 * 修改备注:
 * @version 1.0.0
 */
public class QueryInsTruckListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckType=atx.getStringValue("TRUCK_TYPE");
		String truckStatus=atx.getStringValue("TRUCK_STATUS");
		String truckLicense=atx.getStringValue("TRUCK_LICENSE");
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			//需要增加最新检查单
			PageQuery pageQuery=TmInsTruckInfoPOFactory.queryInsTruckList(conn,truckType,truckStatus,truckLicense, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryInsTruckListAction error:"+e.getMessage());
			atx.setErrorContext("BASE_DATA_INS_TRUCK_LIST_QUERY_ACTION_ERR_9000", "查询车辆列表信息出错", e);
			return 0;
		}
		return 1;
	}

}
