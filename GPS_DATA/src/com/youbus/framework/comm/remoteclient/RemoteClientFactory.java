/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.remoteclient
 * ��   ��  ��:RemoteClientFactory.java
 * �� ������:2015��6��3��-����10:15:42
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

/**��Զ�̷�����������
 * ������:RemoteClientFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��3�� ����10:15:42
 * �޸ı�ע: change 2.0.0 change to HttpClient4.3@9-23 by Rock
 * @version 1.0.0
 */
public class RemoteClientFactory {
	
	private static Logger log = LogManager.getLogger(RemoteClientFactory.class);

	/**
	 * ����һ���µ�ʵ�� RemoteClientFactory.
	 *
	 */
	public RemoteClientFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��Զ�̷��������ͽӿ� :http://localhost:8088/AUTH_CENTER/remote?aaa=bbb&....
	 * ��   ��  ��:send
	 * ��������:
	 * ��         ��:@param serverUrl: http://localhost:8088/AUTH_CENTER
	 * ��         ��:@param params :List<ParamBean> ,������ method��data��ʣ�µĲ����ɱ��������䣺timestamp ��2010-02-08 20:23:30��app_key��sign
	 * ��         ��:@return
	 * ��   ��  ֵ:String ����json��ʽString
	 * ��   ��  ��:rock 
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
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//��������ʹ��䳬ʱʱ��
		post.setConfig(requestConfig);
		post.addHeader("Connection", "close");  
//		post.addRequestHeader("connection","keep-alive");//test
//		String charSet=post.getRequestCharSet();
		//String charSet="utf-8";
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
	    post.setEntity(entity);
//		post.addHeader("Content-Type","application/x-www-form-urlencoded");  //�ᱨ����
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
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("in_date", "17-04-01 00:00 - 17-04-08 17:21");
		map.put("carnum", "³FW1505");
		
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
