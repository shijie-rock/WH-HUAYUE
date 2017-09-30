package com.cn.gpslms.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;





//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import com.cn.gpslms.common.CommonConstant;

/**
 * 向远程服务器发请求 类名称:RemoteClientFactory 类描述: 创建人:rock 修改人:rock 修改时间:2016年8月30日
 * 上午10:15:42 修改备注: change 2.0.0 change to HttpClient4.3@9-23 by Rock
 * 
 * @version 1.0.0
 */
public class RemoteLMSNewClient {
	private static Logger log=Logger.getLogger(RemoteLMSNewClient.class);
	/**
	 * 创建一个新的实例 RemoteClientFactory.
	 *
	 */
	public RemoteLMSNewClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 方   法  名:sendWSDL
	 * 方法描述:
	 * 参         数:@param serverUrl
	 * 参         数:@param userName
	 * 参         数:@param password
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @throws MalformedURLException 
	 * @exception
	 * @since  1.0.0
	 */
	public static String sendWSDL(String serverUrl, String userName,
			String password) throws MalformedURLException {
		URL url=new URL(serverUrl);
		HttpGet get = null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()),new UsernamePasswordCredentials(userName,password));
//		credsProvider
//		.setCredentials(new AuthScope("aexdev.whchem.com", 8091),
//				new UsernamePasswordCredentials("user_lms_external",
//						"ABCD1234"));
		// CloseableHttpClient client =
		// HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			System.out.println("serverUrl :=" + serverUrl);
			if (serverUrl.startsWith("https")) {
				client = SSLClient.createSSLClientDefault();
			} else {
				client = httpClientBuilder.build();
			}
			get = new HttpGet(serverUrl); // 需要从配置获取
																										// //TB_MEMBER_APP_MAP
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
			get.setConfig(requestConfig);
			get.addHeader("Connection", "close");
			// get.addRequestHeader("connection","keep-alive");//test
			String charSet = "utf-8";

			// get.addHeader("Content-Type","application/x-www-form-urlencoded");
			// //提报中文
			response = client.execute(get, context);
			String sf = "";
			HttpEntity respEntity = response.getEntity();
			if (respEntity != null) {
				System.out.println("contentEncoding:"
						+ respEntity.getContentEncoding());

				sf = EntityUtils.toString(respEntity, Consts.UTF_8);
				System.out.println("response content:" + sf);
			}
			String decryptStr = (sf.toString());
			System.out.println("response decrypt:=" + decryptStr);
			System.out.println("response return status:="
					+ response.getStatusLine().getStatusCode());
			// if()
			return decryptStr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (get != null)
				get.releaseConnection();
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}

	
	public static String sendAlarmData(String alarmXML) throws MalformedURLException {
		String serverUrl=CommonConstant.WBS_SERVICE_IF11_URL;
		String userName=CommonConstant.WBS_USER_NAME;
		String password=CommonConstant.WBS_PASSWORD;
		URL url=new URL(serverUrl);
		HttpPost post = null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()),new UsernamePasswordCredentials(userName,password));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			System.out.println("serverUrl :=" + serverUrl);
			log.debug(" serverUrl:="+serverUrl);
			log.debug(" alarmXML:="+alarmXML);
			if (serverUrl.startsWith("https")) {
				client = SSLClient.createSSLClientDefault();
			} else {
				client = httpClientBuilder.build();
			}
			post = new HttpPost(serverUrl); // 需要从配置获取
			
//			StringEntity entity = new StringEntity(new String(alarmXML.getBytes(),"UTF-8"));//错误
			StringEntity entity = new StringEntity(alarmXML,Consts.UTF_8);
			entity.setContentEncoding("UTF-8");//设置编码格式
			entity.setContentType("text/xml");
			entity.setChunked(true);  
			
			// Set XML entity
			post.setEntity(entity);
			// Set content type of request header
			post.addHeader("Content-Type", "text/xml;charset=UTF-8");
			//
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(20000).setConnectTimeout(20000).build();// 设置请求和传输超时时间
			post.setConfig(requestConfig);
			post.setHeader("Connection", "close");
			post.setHeader("User-Agent", "Mozilla/4.0(compatible;MSIE5.5;Windows NT; DigExt)");
			post.setHeader("Content-Type", "text/xml;charset=UTF-8");
			
			
			// get.addRequestHeader("connection","keep-alive");//test
			// //提报中文
			
//			context.setRequestConfig(config);
			response = client.execute(post, context);
			String sf = "";
			HttpEntity respEntity = response.getEntity();
			if (respEntity != null) {
				System.out.println("contentEncoding:"+ respEntity.getContentEncoding());
				sf = EntityUtils.toString(respEntity, Consts.UTF_8);
				System.out.println("response content:" + sf);
				log.debug(" RECEIVE RESPONSE:="+sf);
			}
			String decryptStr = (sf.toString());
			System.out.println("response decrypt:=" + decryptStr);
			System.out.println("response return status:="+ response.getStatusLine().getStatusCode());
			// if()
			return decryptStr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (post != null)
				post.releaseConnection();
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	/**
	 * 获取发送参数
	 * 方   法  名:getCFGData
	 * 方法描述:
	 * 参         数:@return
	 * 参         数:@throws MalformedURLException
	 * 返   回  值:Double
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static Double getCFGData() throws MalformedURLException {
		String serverUrl=CommonConstant.WBS_SERVICE_IF12_URL;
		String userName=CommonConstant.WBS_USER_NAME;
		String password=CommonConstant.WBS_PASSWORD;
		URL url=new URL(serverUrl);
		HttpPost post = null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()),new UsernamePasswordCredentials(userName,password));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			System.out.println("serverUrl :=" + serverUrl);
			log.debug(" serverUrl:="+serverUrl);
			if (serverUrl.startsWith("https")) {
				client = SSLClient.createSSLClientDefault();
			} else {
				client = httpClientBuilder.build();
			}
			post = new HttpPost(serverUrl); // 需要从配置获取
			
			StringEntity entity = new StringEntity(getXml());
			  // Set XML entity
			post.setEntity(entity);
			  // Set content type of request header
			post.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			
//			StringEntity entity = new StringEntity(new String(alarmXML.getBytes(),"UTF-8"));//错误
//			StringEntity entity = new StringEntity(alarmXML,Consts.UTF_8);
//			entity.setContentEncoding("UTF-8");//设置编码格式
//			entity.setContentType("text/xml");
//			entity.setChunked(true);  
			
			// Set XML entity
//			post.setEntity(entity);
			// Set content type of request header
			post.addHeader("Content-Type", "text/xml;charset=UTF-8");
			//
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(20000).setConnectTimeout(20000).build();// 设置请求和传输超时时间
			post.setConfig(requestConfig);
			post.setHeader("Connection", "close");
			post.setHeader("User-Agent", "Mozilla/4.0(compatible;MSIE5.5;Windows NT; DigExt)");
			post.setHeader("Content-Type", "text/xml;charset=UTF-8");
			
			
			// get.addRequestHeader("connection","keep-alive");//test
			// //提报中文
			
//			context.setRequestConfig(config);
			response = client.execute(post, context);
			String sf = "";
			HttpEntity respEntity = response.getEntity();
			if (respEntity != null) {
				System.out.println("contentEncoding:"+ respEntity.getContentEncoding());
				sf = EntityUtils.toString(respEntity, Consts.UTF_8);
				System.out.println("response content:" + sf);
				log.debug(" RECEIVE RESPONSE:="+sf);
			}
			String decryptStr = (sf.toString());
			System.out.println("response decrypt:=" + decryptStr);
			System.out.println("response return status:="+ response.getStatusLine().getStatusCode());
			// if()
			String resString= decryptStr;
			return transeResponseSoapXml(resString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (post != null)
				post.releaseConnection();
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
//		return null;
	}
	
	/**
	 * 方 法 名:main 方法描述: 参 数:@param args 返 回 值:void 创 建 人:rock
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(getCFGData());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static String getXml(){
		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://bokesoft.com/dee/service/\"> "
				+ "<soapenv:Header/> "
				+ "   <soapenv:Body> "
				+ "      <ser:L3_LMS_IF12_WS_01/> "
				+ "  </soapenv:Body> "
				+ "</soapenv:Envelope>";
		return xml;
	}
	private static Double transeResponseSoapXml(String responseXml){
		Double value=null;
		try {
			Document doc = DocumentHelper.parseText(responseXml);
			MyVisitor myValue=new MyVisitor();
			doc.accept(myValue);
			System.out.println(myValue.getValue());
			value=myValue.getValue();
			log.debug("获取参数参数value：="+value);
//			return 1d;//test
			return value;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return value;
		}
		
}
class MyVisitor extends VisitorSupport{  
    
    private double value;  
      
    @Override  
    public void visit(Element node) {  
        if("value".equals(node.getName())){  
        	value= Float.valueOf(node.getStringValue()).doubleValue();
        }  
    }  
    @Override  
    public void visit(Attribute node) {  
//        if("id".equals(node.getName())){  
//            value.setId(Integer.parseInt(node.getValue()));  
//        }  
    }  
      
    public double getValue() {  
        return value;  
    } 
}
