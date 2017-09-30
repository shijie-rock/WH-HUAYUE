/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.remoteclient
 * ��   ��  ��:RemoteClientFactory.java
 * �� ������:2015��6��3��-����10:15:42
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.remoteclient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

import com.youbus.framework.comm.security.MsgEncryptService;
import com.youbus.framework.comm.security.MyEncryptServiceImp;
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
		HttpPost post =null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
	    CloseableHttpClient client = null; 
	    CloseableHttpResponse response =null;
		try {
		System.out.println("serverUrl :="+serverUrl);
		MsgEncryptService encrypt=MyEncryptServiceImp.getInstance();
		params.add(new ParamBean("action",remoteAction));
		String encryptReqStr=encrypt.encrypt(parseReqStrFromParamBeans(params));
		System.out.println("encryptReqStr :="+encryptReqStr);
		if(serverUrl.startsWith("https")){
			client=SSLClient.createSSLClientDefault();
		}else{
			client=httpClientBuilder.build();  
		}
		NameValuePair[] values = new NameValuePair[1];
		values[0]=new BasicNameValuePair("req",encryptReqStr);
//		values[1]=new NameValuePair("token",token);
		List<NameValuePair> paramList=Arrays.asList(values);
		post= new HttpPost(serverUrl+"/"+remoteChannel); //��Ҫ�����ݿ��ȡ //TB_MEMBER_APP_MAP
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
            log.debug("************remote response��" + sf);
            System.out.println("response content:" +sf );  
           }
			String decryptStr=encrypt.decrypt(sf.toString());
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

}
