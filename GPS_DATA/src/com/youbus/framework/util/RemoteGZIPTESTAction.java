/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.agentcenter.remote.action
 * ��   ��  ��:RemoteCheckAuthSessionAction.java
 * �� ������:2015��7��2��-����4:11:36
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;

/**
 * ������:RemoteCheckAuthSessionAction
 * ������:����Զ�̵Ĵ�����Auth��session���鿴��Ӧ�ı���session�ĵ�¼״̬
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��7��2�� ����4:11:36
 * �޸ı�ע:
 * @version 1.0.0
 */
public class RemoteGZIPTESTAction extends ActionImpl {

	/**
	 * ����һ���µ�ʵ�� RemoteCheckAuthSessionAction.
	 *
	 */
	public RemoteGZIPTESTAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		boolean localLoginStatus=false;
		String req=atx.getStringValue("req");
		System.out.println("req:="+req);
		try {
			System.out.println("req2:="+uncompress(req));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
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

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("ISO-8859-1");
	}

	// ��ѹ��
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		// toString()ʹ��ƽ̨Ĭ�ϱ��룬Ҳ������ʽ��ָ����toString(&quot;GBK&quot;)
		return out.toString("UTF-8");
//		return out.toString();
	}
}
