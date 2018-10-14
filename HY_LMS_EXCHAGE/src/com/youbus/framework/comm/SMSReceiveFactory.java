/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:SMSReceiveFactory.java
 * �� ������:2015��7��22��-����11:42:11
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.params.CoreConnectionPNames;

import com.youbus.framework.util.DBConUtil;

/**
 * ������:SMSReceiveFactory
 * ������:����ƽ̨���ն��Ŵ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��7��22�� ����11:42:11
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SMSReceiveFactory {
	private Properties prop;
	private static SMSReceiveFactory instance;
	private SMSReceiveFactory() {
		// TODO Auto-generated constructor stub
		prop=new Properties();
		try {
			prop.load(SMSReceiveFactory.class.getResourceAsStream("/Msg.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static synchronized  SMSReceiveFactory getInstance(){
		if(instance==null){
			instance=new SMSReceiveFactory();
		}
		return instance;
	}
	
	/**
	 * ���ն���ƽ̨��Ϣ
	 * ��   ��  ��:receive
	 * ��������:
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public String receive(){
		HttpClient client=null;
		PostMethod post =null;
		try {
		client = new HttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,10000);
		NameValuePair[] values = new NameValuePair[2];
		values[0]=new NameValuePair("account", prop.getProperty("message.account"));
		values[1]=new NameValuePair("password",DBConUtil.MD5Encode(prop.getProperty("message.password")));
		post= new PostMethod(prop.getProperty("message.receiverul")); //��ҪԶ������
		post.setRequestHeader("Connection", "close");  
		String charSet=post.getRequestCharSet();
		charSet="utf-8";
//		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  //�ᱨ����
		post.setRequestBody(values);
		client.executeMethod(post);
		String result = post.getResponseBodyAsString();
		System.out.println("response return status:="+post.getStatusCode());
		System.out.println("response result:="+result);
//			if()
		return result;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{  
			if(post!=null)
			post.releaseConnection(); 
			if(client!=null)
			client.getHttpConnectionManager().closeIdleConnections(0);    
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
		SMSReceiveFactory.getInstance().receive();
	}
	

}
