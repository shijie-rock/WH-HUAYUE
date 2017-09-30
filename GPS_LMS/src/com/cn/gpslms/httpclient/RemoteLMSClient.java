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
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

/**
 * 向远程服务器发请求 类名称:RemoteClientFactory 类描述: 创建人:rock 修改人:rock 修改时间:2016年8月30日
 * 上午10:15:42 修改备注: change 2.0.0 change to HttpClient4.3@9-23 by Rock
 * 
 * @version 1.0.0
 */
public class RemoteLMSClient {

	/**
	 * 创建一个新的实例 RemoteClientFactory.
	 *
	 */
	public RemoteLMSClient() {
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

	
	public static String sendData(String serverUrl, String userName,String password) throws MalformedURLException {
		URL url=new URL(serverUrl);
		HttpPost post = null;
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
			//param
//			NameValuePair[] values = new NameValuePair[1];
//			values[0]=new BasicNameValuePair("req","<xml>");
//			values[1]=new NameValuePair("token",token);
//			List<NameValuePair> paramList=Arrays.asList(values);
			
			post = new HttpPost(serverUrl); // 需要从配置获取
			
			StringEntity entity = new StringEntity(getXml());
			  // Set XML entity
			post.setEntity(entity);
			  // Set content type of request header
			post.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			// //TB_MEMBER_APP_MAP
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
			post.setConfig(requestConfig);
			post.addHeader("Connection", "close");
			post.addHeader("User-Agent", "Mozilla/4.0(compatible;MSIE5.5;Windows NT; DigExt)");
			post.addHeader("Content-Type", "text/xml;charset=UTF-8");
			// get.addRequestHeader("connection","keep-alive");//test

//		    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
//		    post.setEntity(entity);
			
			// get.addHeader("Content-Type","application/x-www-form-urlencoded");
			// //提报中文
			response = client.execute(post, context);
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
	 * 方 法 名:main 方法描述: 参 数:@param args 返 回 值:void 创 建 人:rock
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//get
//			sendWSDL("https://aexdev.whchem.com:8091/dir/wsdl?p=ic/489fdc3469a535caa0da0e4b2d4e851f","user_lms_external", "ABCD1234");
//			sendWSDL("https://aexdev.whchem.com:8091/XISOAPAdapter/MessageServlet?senderParty=&senderService=BC_GPS&receiverParty=&receiverService=&interface=SI_381_GPS_OnlinetrackFrequency_Out&interfaceNamespace=http%3A%2F%2Fwhchem.com%2FLMS","user_lms_external", "ABCD1234");
			//post
			String resXml=sendData("https://aexdev.whchem.com:8091/XISOAPAdapter/MessageServlet?senderParty=&senderService=BC_GPS&receiverParty=&receiverService=&interface=SI_381_GPS_OnlinetrackFrequency_Out&interfaceNamespace=http%3A%2F%2Fwhchem.com%2FLMS","user_lms_external", "ABCD1234");
			transeResponseSoapXml(resXml);
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
	
//	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://bokesoft.com/dee/service/">
//	   <soapenv:Header/>
//	   <soapenv:Body>
//	      <ser:L3_LMS_IF11_WS_01>
//	         <!--1 or more repetitions:-->
//	         <ser:Online_Track_Info>
//	            <ser:direction>?</ser:direction>
//	            <ser:direction_degrees>?</ser:direction_degrees>
//	            <ser:latitude>?</ser:latitude>
//	            <ser:longitude>?</ser:longitude>
//	            <ser:place_now>?</ser:place_now>
//	            <ser:spare_fields_head_1>?</ser:spare_fields_head_1>
//	            <ser:spare_fields_head_10>?</ser:spare_fields_head_10>
//	            <ser:spare_fields_head_2>?</ser:spare_fields_head_2>
//	            <ser:spare_fields_head_3>?</ser:spare_fields_head_3>
//	            <ser:spare_fields_head_4>?</ser:spare_fields_head_4>
//	            <ser:spare_fields_head_5>?</ser:spare_fields_head_5>
//	            <ser:spare_fields_head_6>?</ser:spare_fields_head_6>
//	            <ser:spare_fields_head_7>?</ser:spare_fields_head_7>
//	            <ser:spare_fields_head_8>?</ser:spare_fields_head_8>
//	            <ser:spare_fields_head_9>?</ser:spare_fields_head_9>
//	            <ser:speed>?</ser:speed>
//	            <ser:tm_online_track_status_id>?</ser:tm_online_track_status_id>
//	            <ser:track_date>?</ser:track_date>
//	            <ser:truck_name_h>?</ser:truck_name_h>
//	         </ser:Online_Track_Info>
//	      </ser:L3_LMS_IF11_WS_01>
//	   </soapenv:Body>
//	</soapenv:Envelope>
	
	private static void transeResponseSoapXml(String responseXml){
		
//	responseXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//				+ " <SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//				+ "   <SOAP:Header/>"
//				+ "   <SOAP:Body xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
//				+ "    <L3_LMS_IF12_WS_01Response xmlns=\"http://bokesoft.com/dee/service/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//				+ "      <L3_LMS_IF12_WS_01Return>"
//				+ "    <spare_fields_head_1>sf1</spare_fields_head_1>"
//				+ "    <spare_fields_head_2>sf2</spare_fields_head_2>"
//				+ "    <value>10.0</value>" 
//				+ "   </L3_LMS_IF12_WS_01Return>"
//				+ "   </L3_LMS_IF12_WS_01Response>" 
//				+ "  </SOAP:Body>"
//				+ " </SOAP:Envelope>";
	
	
	try {
		Document doc = DocumentHelper.parseText(responseXml);
		MyVisitor myValue=new MyVisitor();
		doc.accept(myValue);
		System.out.println(myValue.getValue());
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	
	
		
	}
	
}

class MyVisitor1 extends VisitorSupport{  
    
    private double value;  
      
    @Override  
    public void visit(Element node) {  
        if("value".equals(node.getName())){  
        	value= Float.valueOf(node.getStringValue());
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
