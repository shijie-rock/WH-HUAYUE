/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.remoteclient
 * ��   ��  ��:SSLClient.java
 * �� ������:2015��9��23��-����10:27:40
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.httpclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * ������:SSLClient
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��9��23�� ����10:27:40
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SSLClient{

	/**
	 * ����һ���µ�ʵ�� SSLClient.
	 *
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// ��������
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
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

}
