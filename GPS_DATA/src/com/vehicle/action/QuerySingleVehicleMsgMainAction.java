/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.action
 * 文   件  名:QuerySingleVehicleMsgMainAction.java
 * 创 建日期:2017年4月13日-下午11:09:54
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.vehicle.bean.VehicleInfoBean;
import com.vehicle.util.VehicleInfoUtil;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.comm.remoteclient.RemoteClientFactory;

/**
 * 类名称:QuerySingleVehicleMsgMainAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月13日 下午11:09:54
 * 修改备注:
 * @version 1.0.0
 */
public class QuerySingleVehicleMsgMainAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
//		VehicleInfoUtil.json2Bean(jsonResult)
		String vehicleLicense=atx.getStringValue("vehicle_license");
		String startDate=atx.getStringValue("startDate");
		String endDate=atx.getStringValue("endDate");
		
		Map<String,Object> map = new HashMap<String,Object>(); 
//		map.put("in_date", "17-04-01 00:00 - 17-04-08 17:21");
//		map.put("carnum", "鲁FW1505");
		map.put("in_date", startDate +" - "+ endDate);
		map.put("carnum",vehicleLicense);
		
		List<ParamBean> params =new ArrayList<ParamBean>();
		params.add(new ParamBean("method","truck.statistic.truckDaily"));
		params.add(new ParamBean("data",JSONObject.fromObject(map).toString()));
		String responseStr=RemoteClientFactory.send(params);
		logger.debug("remote str:="+responseStr);
		List<VehicleInfoBean> list=VehicleInfoUtil.json2Bean(responseStr);
		if(list!=null&&list.size()>0){
			logger.debug(JSONObject.fromObject(list.get(0)).toString());
			System.out.println(JSONObject.fromObject(list.get(0)).toString());
			atx.setObjValue("VEHICLE_INFO", list.get(0));
		}else{
			logger.debug("无查询结果");
			atx.setStringValue("MSG", "无查询结果");
		}
		return 1;
	}

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
