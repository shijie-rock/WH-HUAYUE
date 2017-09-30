/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.util
 * 文   件  名:VehicleInfoUtil.java
 * 创 建日期:2017年4月9日-下午9:32:27
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.vehicle.bean.VehicleInfoBean;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.comm.remoteclient.RemoteClientFactory;

/**
 * 类名称:VehicleInfoUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月9日 下午9:32:27
 * 修改备注:
 * @version 1.0.0
 */
public class VehicleInfoUtil {
	private static Logger logger=Logger.getLogger(VehicleInfoUtil.class);
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
	
//	{
//	    "code":0,
//	    "message":"",
//	    "data":{
//	        "pk":"aa",
//	        "pageNo":"aa",
//	        "pageSize":"aa",
//	        "autoCount":,
//	        "totalCount":"aa",
//	        "result":[
//	            {
//	                "id":"aa",
//	                "truckid":"aa",
//	                "carnum":"aa",
//	                "carriagetype":"aa",
//	                "carriagetype_id":"aa",
//	                "deleted":"aa",
//	                "drivinglicensebrand":"aa",
//	                "emptydriving":"aa",
//	                "emptydriving_code":"aa",
//	                "fromorgcode":"aa",
//	                "fromtype":"aa",
//	                "gmileage":"aa",
//	                "gpsno":"aa",
//	                "length":"aa",
//	                "name":"aa",
//	                "orgcode":"aa",
//	                "orgcode_code":"aa",
//	                "status":"aa",
//	                "status_code":"aa",
//	                "truckpk":"aa",
//	                "usetype":"aa",
//	                "usefor":"aa",
//	                "usedept":"aa",
//	                "volume":"aa",
//	                "counttime":"aa",
//	                "duration":"aa",
//	                "weight":"aa",
//	            },
//	            ......
//	        ],
//	    }
//	}
	
	public static List<VehicleInfoBean> json2Bean(String jsonResult){
		if(StringUtils.isNotBlank(jsonResult)){
			JSONObject jsonO=JSONObject.fromObject(jsonResult);
			if(jsonO!=null){
				JSONObject jsonData=JSONObject.fromObject(jsonO.get("data"));
				if(jsonData!=null){
					JSONArray jsonArray=JSONArray.fromObject(jsonData.get("result"));
					if(jsonArray!=null){
						
//						JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
//						jsonConfig.setIgnoreDefaultExcludes(true);  //设置默认忽略
//						jsonConfig.setExcludes(new String[]{"ratePersonals", "channelPersonals"});  //只要将所需忽略字段
						
						JsonConfig config = new JsonConfig();  
						Map<String, Object> classMap = new HashMap<String, Object>();  
						config.setClassMap(classMap);  
						config.setRootClass(VehicleInfoBean.class);  
						config.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));  //忽略多余属性
						
						List<VehicleInfoBean> list = JSONArray.toList(jsonArray, new VehicleInfoBean(),config);
						return list;	
					}
				}
			}
		}
		return null;
	}
	
	
	public static List<VehicleInfoBean> queryVehicleDataByCondition(String startDate,String endDate, String vehicleLicense,int iDisplayLength,int currentPageNo){
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("in_date", startDate +" - "+ endDate);
		map.put("carnum",vehicleLicense);
		map.put("pageSize",Integer.valueOf(iDisplayLength));
		map.put("pageNo",Integer.valueOf(currentPageNo));
		
		List<ParamBean> params =new ArrayList<ParamBean>();
		params.add(new ParamBean("method","truck.statistic.truckDaily"));
		params.add(new ParamBean("data",JSONObject.fromObject(map).toString()));
		
		String responseStr=RemoteClientFactory.send(params);
		logger.debug("remote str:="+responseStr);
		List list=json2Bean(responseStr);
		return list;
	}
	
	
	public static List<VehicleInfoBean> queryAllVehicleData(String startDate,String endDate, String vehicleLicense){
		
		List<VehicleInfoBean> returnList=new ArrayList<VehicleInfoBean>();
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("in_date", startDate +" - "+ endDate);
		map.put("carnum",vehicleLicense);
		int currentPageNo=1; //当前页码
		int allPageCount=1; //所有页数
		int pageSize=0; //每页大小
		int allDataCount=0; //全部条数
		while(currentPageNo<=allPageCount){
			if(pageSize>0){
				map.put("pageSize",Integer.valueOf(pageSize));
			}
			map.put("pageNo",Integer.valueOf(currentPageNo));
			
			List<ParamBean> params =new ArrayList<ParamBean>();
			params.add(new ParamBean("method","truck.statistic.truckDaily"));
			params.add(new ParamBean("data",JSONObject.fromObject(map).toString()));
			
			String responseStr=RemoteClientFactory.send(params);

			
			allDataCount=getTotalCount(responseStr);
			pageSize=getPageSize(responseStr);
			allPageCount=allDataCount/pageSize+1;
			
			logger.debug("page_size["+currentPageNo+"] all_page_count["+allPageCount+"] remote str:="+responseStr);
			
			returnList.addAll(json2Bean(responseStr));
			
			currentPageNo++;
			if(returnList.size()>=allDataCount){
				logger.debug("returnList export size ,loop break."); 
				break;//跳出保护
			}
			
			try {
				Thread.sleep(500);//睡眠保护
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnList;
		
	}
	
//	获取总条数
	public static int getTotalCount(String jsonResult){
		if(StringUtils.isNotBlank(jsonResult)){
			JSONObject jsonO=JSONObject.fromObject(jsonResult);
			if(jsonO!=null){
				JSONObject jsonData=JSONObject.fromObject(jsonO.get("data"));
				if(jsonData!=null){
					String totalCountStr=jsonData.getString("totalCount");
					return Integer.valueOf(totalCountStr);
					}
				}
			}
		return 0;
	}
//	获取每页条数
	public static int getPageSize(String jsonResult){
		if(StringUtils.isNotBlank(jsonResult)){
			JSONObject jsonO=JSONObject.fromObject(jsonResult);
			if(jsonO!=null){
				JSONObject jsonData=JSONObject.fromObject(jsonO.get("data"));
				if(jsonData!=null){
					String pageSizeStr=jsonData.getString("pageSize");
					return Integer.valueOf(pageSizeStr);
				}
			}
		}
		return 10;//默认10
	}
}
	class PropertyStrategyWrapper extends PropertySetStrategy {  
	    private PropertySetStrategy original;  
	    public PropertyStrategyWrapper(PropertySetStrategy original) {  
	        this.original = original;  
	    }  
	  
	    @Override  
	    public void setProperty(Object o, String string, Object o1) throws JSONException {  
	        try {  
	            original.setProperty(o, string, o1);  
	  
	        } catch (Exception ex) {  
	        }  
	  
	    }  
	}  

	
	
//	ObjectMapper objectMapper = new ObjectMapper();
//	JsonNode node=null;
//	try {
//		node = objectMapper.readTree(synResponse);
//		JsonNode contents=node.get("SUCCESS");
//		if(1==contents.getIntValue()){
//			log.debug("远程查询成功");
//			if(node.get("RESULT")!=null&&StringUtils.isNotBlank(node.get("RESULT").getTextValue())){
//				log.debug("查询结果为"+node.get("RESULT").getTextValue());
////				JSONObject.fromObject(object)
//				String jsonStr=node.get("RESULT").getTextValue();
//				YBCommonResourceBean returnBean=(YBCommonResourceBean) JSONObject.toBean(JSONObject.fromObject(jsonStr),YBCommonResourceBean.class);
//				System.out.println(returnBean.toXMLString());
//				return returnBean;
	

