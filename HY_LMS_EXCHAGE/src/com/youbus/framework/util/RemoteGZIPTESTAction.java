/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.agentcenter.remote.action
 * 文   件  名:RemoteCheckAuthSessionAction.java
 * 创 建日期:2015年7月2日-下午4:11:36
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
 * 类名称:RemoteCheckAuthSessionAction
 * 类描述:接收远程的传来的Auth的session，查看对应的本地session的登录状态
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年7月2日 下午4:11:36
 * 修改备注:
 * @version 1.0.0
 */
public class RemoteGZIPTESTAction extends ActionImpl {

	/**
	 * 创建一个新的实例 RemoteCheckAuthSessionAction.
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

	// 解压缩
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
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
		return out.toString("UTF-8");
//		return out.toString();
	}
}
