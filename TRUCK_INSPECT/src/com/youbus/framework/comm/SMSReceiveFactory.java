/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:SMSReceiveFactory.java
 * 创 建日期:2015年7月22日-上午11:42:11
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
 * 类名称:SMSReceiveFactory
 * 类描述:短信平台接收短信处理
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年7月22日 上午11:42:11
 * 修改备注:
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
	 * 接收短信平台消息
	 * 方   法  名:receive
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
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
		post= new PostMethod(prop.getProperty("message.receiverul")); //需要远程连接
		post.setRequestHeader("Connection", "close");  
		String charSet=post.getRequestCharSet();
		charSet="utf-8";
//		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  //提报中文
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
		SMSReceiveFactory.getInstance().receive();
	}
	

}
