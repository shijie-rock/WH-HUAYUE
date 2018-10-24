/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.msgfactory
 * 文   件  名:HyMessageHttpClientFactory.java
 * 创 建日期:2018年10月21日-下午6:08:39
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.msgfactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.youbus.framework.comm.AppLog;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.comm.remoteclient.SSLClient;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:HyMessageHttpClientFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月21日 下午6:08:39
 * 修改备注:
 * @version 1.0.0
 */
public class HyMessageHttpClientFactory {
	private static Logger appLog=AppLog.getInstance().getDELog();
	
	
	/**pb=new ParamBean();paramList.add(pb);
	 * 生产公共api请求参数表
	 * 方   法  名:getNewApiParams
	 * 方法描述:
	 * 参         数:@param topicName
	 * 参         数:@return
	 * 返   回  值:List<ParamBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static List<ParamBean> getNewApiParams(String topicName,String dataJson){
		
		List<ParamBean> paramList=new ArrayList<ParamBean>();
		ParamBean pb;
		pb=new ParamBean("serviceCode",topicName);paramList.add(pb);
		pb=new ParamBean("appKey",HyLmsClientConstant.APP_ID);paramList.add(pb);
		pb=new ParamBean("requestTime",DBConUtil.handleFormatDate(new Date(System.currentTimeMillis())));paramList.add(pb);
		pb=new ParamBean("data",dataJson);paramList.add(pb);
		String sign=HyLmsSignUtil.getSign(paramList);
		if(StringUtils.isBlank(sign)){
			appLog.error("SIGN IS NULL");
			return null;
		}
		pb=new ParamBean("sign",sign);paramList.add(pb);
		return paramList;
	}
	/**
	 * 消息主题：运单查询:请求参数
	 * 方   法  名:getNewApiParamsBillQuery
	 * 方法描述:
	 * 参         数:@param dataJson
	 * 参         数:@return
	 * 返   回  值:List<ParamBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<ParamBean> getNewApiParamsBillQuery(String dataJson){
		return getNewApiParams(HyLmsClientConstant.TOPIC_BUSI_BILL_QUERY, dataJson);
		
	}
	
	/**
	 * 消息主题：运力创建:请求参数
	 * 方   法  名:getNewApiParamsTaskCreate
	 * 方法描述:
	 * 参         数:@param dataJson
	 * 参         数:@return
	 * 返   回  值:List<ParamBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<ParamBean> getNewApiParamsTaskCreate(String dataJson){
		return getNewApiParams(HyLmsClientConstant.TOPIC_BUSI_TASK_CREATE, dataJson);
		
	}
	
	/**
	 * post 方式
	 * 方   法  名:send
	 * 方法描述:
	 * 参         数:@param params
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String send(List<ParamBean> params){
		String serverUrl=HyLmsClientConstant.API_SERVER_URL;
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
//		params.add(new ParamBean("timestamp",DBConUtil.handleFormatDate(new Date(System.currentTimeMillis()))));
//		params.add(new ParamBean("app_key",CommonConstant.KEY));
//		String sign=SignUtil.getSign(params);
//		params.add(new ParamBean("sign",sign));
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
            appLog.debug("接收到api回执 :=" + sf);
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
	 * post json 方式
	 * 方   法  名:sendJson
	 * 方法描述:
	 * 参         数:@param params
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String sendJson(List<ParamBean> params){
		String serverUrl=HyLmsClientConstant.API_SERVER_URL;
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
		String paramJson=HyLmsSignUtil.getApiRequestJson(params);
		post= new HttpPost(serverUrl); //
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
		post.setConfig(requestConfig);
		post.addHeader("Connection", "close");  
//		post.addRequestHeader("connection","keep-alive");//test
		post.addHeader("Content-Type", "application/json");  //提报中文
	    post.setEntity(new StringEntity(paramJson,Consts.UTF_8));
		response =client.execute(post);
		  String sf="";
		  HttpEntity respEntity=response.getEntity();
          if (respEntity != null) {  
            System.out.println("contentEncoding:" + respEntity.getContentEncoding());  
            sf=EntityUtils.toString(respEntity,Consts.UTF_8);
            appLog.debug("接收到api回执 :=" + sf);
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
	 * 直接发送json报文
	 * 方   法  名:sendJson
	 * 方法描述:
	 * 参         数:@param jsonRequest
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String sendJson(String jsonRequest){
		String serverUrl=HyLmsClientConstant.API_SERVER_URL;
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
//		String paramJson=HyLmsSignUtil.getApiRequestJson(params);
		post= new HttpPost(serverUrl); //
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
		post.setConfig(requestConfig);
		post.addHeader("Connection", "close");  
//		post.addRequestHeader("connection","keep-alive");//test
		post.addHeader("Content-Type", "application/json");  //提报中文
	    post.setEntity(new StringEntity(jsonRequest,Consts.UTF_8));
		response =client.execute(post);
		  String sf="";
		  HttpEntity respEntity=response.getEntity();
          if (respEntity != null) {  
            System.out.println("contentEncoding:" + respEntity.getContentEncoding());  
            sf=EntityUtils.toString(respEntity,Consts.UTF_8);
            appLog.debug("接收到api回执 :=" + sf);
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
	
	private static List<NameValuePair>  parseParam2NameValuePair(List<ParamBean> params){
		if(params==null||params.size()<1)return null;
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		for(ParamBean bean:params){
			list.add(new BasicNameValuePair(bean.getParamName(), bean.getParamValue()));
		}
		return list;
	}
	
}
