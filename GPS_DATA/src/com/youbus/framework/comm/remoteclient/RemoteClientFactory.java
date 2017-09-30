/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.remoteclient
 * 文   件  名:RemoteClientFactory.java
 * 创 建日期:2015年6月3日-上午10:15:42
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.remoteclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;



//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.vehicle.bean.VehicleInfoBean;
import com.vehicle.common.CommonConstant;
import com.vehicle.util.SignUtil;
import com.vehicle.util.VehicleInfoUtil;
import com.youbus.framework.util.DBConUtil;

/**向远程服务器发请求
 * 类名称:RemoteClientFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月3日 上午10:15:42
 * 修改备注: change 2.0.0 change to HttpClient4.3@9-23 by Rock
 * @version 1.0.0
 */
public class RemoteClientFactory {
	
	private static Logger log = LogManager.getLogger(RemoteClientFactory.class);

	/**
	 * 创建一个新的实例 RemoteClientFactory.
	 *
	 */
	public RemoteClientFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 向远程服务器发送接口 :http://localhost:8088/AUTH_CENTER/remote?aaa=bbb&....
	 * 方   法  名:send
	 * 方法描述:
	 * 参         数:@param serverUrl: http://localhost:8088/AUTH_CENTER
	 * 参         数:@param params :List<ParamBean> ,仅包含 method，data；剩下的参数由本方法补充：timestamp ：2010-02-08 20:23:30；app_key；sign
	 * 参         数:@return
	 * 返   回  值:String 返回json格式String
	 * 创   建  人:rock 
	 * @exception
	 * @since  1.0.0
	 * @change 2.0.0 change to HttpClient4.3@9-23 by Rock
	 * 
	 */
	public static String send(List<ParamBean> params){
		String serverUrl=CommonConstant.REQ_SERVER_URL;
		HttpPost post =null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
	    CloseableHttpClient client = null; 
	    CloseableHttpResponse response =null;
		try {
		System.out.println("serverUrl :="+serverUrl);
		if(serverUrl.startsWith("https")){
			client=SSLClient.createSSLClientDefault();
		}else{
			client=httpClientBuilder.build();  
		}
		
		params.add(new ParamBean("timestamp",DBConUtil.handleFormatDate(new Date(System.currentTimeMillis()))));
		params.add(new ParamBean("app_key",CommonConstant.KEY));
		String sign=SignUtil.getSign(params);
		params.add(new ParamBean("sign",sign));
		
		List<NameValuePair> paramList=parseParam2NameValuePair(params);
		
		post= new HttpPost(serverUrl); //
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
		post.setConfig(requestConfig);
		post.addHeader("Connection", "close");  
//		post.addRequestHeader("connection","keep-alive");//test
//		String charSet=post.getRequestCharSet();
		//String charSet="utf-8";
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
	    post.setEntity(entity);
//		post.addHeader("Content-Type","application/x-www-form-urlencoded");  //提报中文
		response =client.execute(post);
		  String sf="";
		  HttpEntity respEntity=response.getEntity();
          if (respEntity != null) {  
            System.out.println("contentEncoding:" + respEntity.getContentEncoding());  

            sf=EntityUtils.toString(respEntity,Consts.UTF_8);
            log.debug("return :=" + sf);
            System.out.println("response content:" +sf );  
           }
//			jacksonTest(decryptStr);
			System.out.println("response return status:="+response.getStatusLine().getStatusCode());
//			if()
			return sf;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{  
			if(post!=null)
			post.releaseConnection(); 
			if(response!=null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(client!=null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			client.getHttpConnectionManager().closeIdleConnections(0);    
		}  
		
		
		return null;
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
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("in_date", "17-04-01 00:00 - 17-04-08 17:21");
		map.put("carnum", "鲁FW1505");
		
		List<ParamBean> params =new ArrayList<ParamBean>();
		params.add(new ParamBean("method","truck.statistic.truckDaily"));
		params.add(new ParamBean("data",JSONObject.fromObject(map).toString()));
		String responseStr=send(params);
		List<VehicleInfoBean> list=VehicleInfoUtil.json2Bean(responseStr);
		if(list!=null){
			System.out.println(JSONObject.fromObject(list.get(0)).toString());
		}
	}
	
	private static String parseReqStrFromParamBeans(List<ParamBean> params){
		if(!DBConUtil.listNotNULL(params))return null;
		StringBuffer sf=new StringBuffer();
		int i=0;
		String value=null;
		for(ParamBean temp:params){
			value=temp.getParamValue();
			if(i!=0)
				sf.append("&").append(temp.getParamName()).append("=").append(value==null?"":value);
			else 
				sf.append(temp.getParamName()).append("=").append(value==null?"":value);
			i++;
		}
		System.out.println("params :="+ sf.toString());
		return sf.toString();
		
	}
	
	private static List<NameValuePair>  parseParam2NameValuePair(List<ParamBean> params){
		if(params==null||params.size()<1)return null;
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		for(ParamBean bean:params){
			list.add(new BasicNameValuePair(bean.getParamName(), bean.getParamValue()));
		}
		return list;
	}

}
