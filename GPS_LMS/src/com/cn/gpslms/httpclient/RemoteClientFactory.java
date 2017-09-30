/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.remoteclient
 * ��   ��  ��:RemoteClientFactory.java
 * �� ������:2015��6��3��-����10:15:42
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.httpclient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cn.gpslms.util.DBConUtil;

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
	 * ��         ��:@param remoteChannel :remote
	 * ��         ��:@param params :List<ParamBean>
	 * ��         ��:@return
	 * ��   ��  ֵ:String ����json��ʽString
	 * ��   ��  ��:rock 
	 * @exception
	 * @since  1.0.0
	 * @change 2.0.0 change to HttpClient4.3@9-23 by Rock
	 * 
	 */
	public static String send(String serverUrl,String remoteChannel,String remoteAction,List<ParamBean> params){
//		HttpClient client=null;
//		PostMethod post =null;
		
//		HttpPost post =null;
		HttpGet post =null;
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    credsProvider.setCredentials(new AuthScope("aexdev.whchem.com", 8091), new UsernamePasswordCredentials("user_lms_external", "ABCD1234"));
//	    CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
	    HttpClientContext context = HttpClientContext.create();
	    context.setCredentialsProvider(credsProvider);
//	    context.setAuthSchemeRegistry(authRegistry);
//	    context.setAuthCache(authCache);
//	    HttpGet httpget = new HttpGet("http://somehost/");
	    
	    CloseableHttpClient client = null; 
	    CloseableHttpResponse response =null;
		try {
		System.out.println("serverUrl :="+serverUrl);
//		MsgEncryptService encrypt=MyEncryptServiceImp.getInstance();
		
//		params.add(new ParamBean("action",remoteAction));
		
		
		String encryptReqStr=(parseReqStrFromParamBeans(params));
//		String encryptReqStr=encrypt.encrypt(parseReqStrFromParamBeans(params));
		System.out.println("encryptReqStr :="+encryptReqStr);
		if(serverUrl.startsWith("https")){
			client=SSLClient.createSSLClientDefault();
		}else{
			client=httpClientBuilder.build();  
		}
		
//	    if (hasCredential) {
//	        AuthScope authScope=new AuthScope(AuthScope.ANY_HOST,AuthScope.ANY_PORT);
//	        UsernamePasswordCredentials usernamePasswordCredentials=new UsernamePasswordCredentials(USERNAME, PASSWORD);
//	        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//	        credentialsProvider.setCredentials(authScope, usernamePasswordCredentials);
//	        httpClient.setCredentialsProvider(credentialsProvider);
//	      }
		
		NameValuePair[] values = new NameValuePair[1];
		values[0]=new BasicNameValuePair("req",encryptReqStr);
//		values[1]=new NameValuePair("token",token);
		List<NameValuePair> paramList=Arrays.asList(values);
		post= new HttpGet("https://aexdev.whchem.com:8091/dir/wsdl?p=ic/489fdc3469a535caa0da0e4b2d4e851f"); //��Ҫ�����ݿ��ȡ //TB_MEMBER_APP_MAP
//		post= new HttpPost(serverUrl+"/"+remoteChannel); //��Ҫ�����ݿ��ȡ //TB_MEMBER_APP_MAP
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//��������ʹ��䳬ʱʱ��
		post.setConfig(requestConfig);
		post.addHeader("Connection", "close");  
//		post.addRequestHeader("connection","keep-alive");//test
//		String charSet=post.getRequestCharSet();
		String charSet="utf-8";
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
//	    post.setEntity(entity);
	    
//		post.addHeader("Content-Type","application/x-www-form-urlencoded");  //�ᱨ����
		response =client.execute(post,context);
//			BufferedReader bReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),charSet==null?"utf-8":charSet));
//			String temp;
//			StringBuffer sf=new StringBuffer();
//			while ((temp = bReader.readLine()) != null) {
//				System.out.println(temp);
//				sf.append(temp);
//		 	}
		  String sf="";
		  HttpEntity respEntity=response.getEntity();
          if (respEntity != null) {  
            System.out.println("contentEncoding:" + respEntity.getContentEncoding());  

            sf=EntityUtils.toString(respEntity,Consts.UTF_8);
            System.out.println("response content:" +sf );  
           }
			String decryptStr=(sf.toString());
			System.out.println("response decrypt:="+decryptStr);
//			System.out.println("-------------jackson test ----------");
//			jacksonTest(decryptStr);
			System.out.println("response return status:="+response.getStatusLine().getStatusCode());
//			if()
			return decryptStr;
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
		send("https://aexdev.whchem.com:8091/dir/wsdl?p=ic/489fdc3469a535caa0da0e4b2d4e851f", null, null, null);
		
		
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
//	CloseableHttpClient https CredentialsProvider credsProvider = new BasicCredentialsProvider();
//	http://bbs.csdn.net/topics/391844932?list=lz
////1	
//	CloseableHttpClient httpclient = <...>
//	 
//	HttpHost targetHost = new HttpHost("localhost", 80, "http");
//	CredentialsProvider credsProvider = new BasicCredentialsProvider();
//	credsProvider.setCredentials(
//	        new AuthScope(targetHost.getHostName(), targetHost.getPort()),
//	        new UsernamePasswordCredentials("username", "password"));
//	 
//	// Create AuthCache instance
//	AuthCache authCache = new BasicAuthCache();
//	// Generate BASIC scheme object and add it to the local auth cache
//	BasicScheme basicAuth = new BasicScheme();
//	authCache.put(targetHost, basicAuth);
//	 
//	// Add AuthCache to the execution context
//	HttpClientContext context = HttpClientContext.create();
//	context.setCredentialsProvider(credsProvider);
//	context.setAuthCache(authCache);
//	 
//	HttpGet httpget = new HttpGet("/");
//	for (int i = 0; i < 3; i++) {
//	    CloseableHttpResponse response = httpclient.execute(
//	            targetHost, httpget, context);
//	    try {
//	        HttpEntity entity = response.getEntity();
//	 
//	    } finally {
//	        response.close();
//	    }
//	}
////2	
//	/������֤����������֤��Χ
//    CredentialsProvider credsProvider = new BasicCredentialsProvider();
//    credsProvider.setCredentials(new AuthScope(HsbcSmsUtilConfiguration.getPROXY_ADDRESS(), HsbcSmsUtilConfiguration.getPROXY_PORT()),//���Է��ʵķ�Χ
//            new UsernamePasswordCredentials(HsbcSmsUtilConfiguration.getPROXY_USERNAME(), HsbcSmsUtilConfiguration.getPROXY_PASSWORD()));//�û���������
//
//    CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
//
//    String responseText = "";
//    CloseableHttpResponse response = null;
//
//    // ���ʵ�Ŀ��վ�㣬�˿ں�Э��
//    HttpHost targetHost = new HttpHost(HsbcSmsUtilConfiguration.getURI(), 443, "https");
//    System.out.println(targetHost);
//
//    // ���������
//    HttpHost proxy = new HttpHost(HsbcSmsUtilConfiguration.getPROXY_ADDRESS(), HsbcSmsUtilConfiguration.getPROXY_PORT());
//    RequestConfig config = RequestConfig.custom().setProxy(proxy).build();


}
