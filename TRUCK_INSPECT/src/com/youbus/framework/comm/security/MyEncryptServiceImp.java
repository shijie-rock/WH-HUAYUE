/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.security
 * 文   件  名:MyEncryptServiceImp.java
 * 创 建日期:2015年6月3日-上午10:46:49
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.security;

import java.util.Map;
import java.util.Random;

import sun.misc.BASE64Decoder;

import com.infoservice.framework.exceptions.FrameException;

/**
 * 类名称:MyEncryptServiceImp
 * 类描述:base64
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月3日 上午10:46:49
 * 修改备注:
 * @version 1.0.0
 */
public class MyEncryptServiceImp extends MsgEncryptService {

	/**
	 * 创建一个新的实例 MyEncryptServiceImp.
	 *
	 */
	private static MyEncryptServiceImp insObj = new MyEncryptServiceImp();
//	private FileServer server = new YoubusFileServerImpl();
	
	public static MyEncryptServiceImp getInstance(){
		return insObj;
	}
	
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName());
//		系统变量赋值 CommonCanstant...XXX =XXX;
	}
	
	private MyEncryptServiceImp() {
		// TODO Auto-generated constructor stub
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

	/* (non-Javadoc)
	 * @see com.youbus.framework.comm.security.MsgEncryptService#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String src) {
		// TODO Auto-generated method stub
		if(src==null||src.trim().length()<1)
		return null;
		return getBASE64(src);
		
	}

	/* (non-Javadoc)
	 * @see com.youbus.framework.comm.security.MsgEncryptService#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String encryptSrc) {
		// TODO Auto-generated method stub
		if(encryptSrc==null||encryptSrc.trim().length()<1)
		return null;
		return getFromBASE64(encryptSrc);
	}
/**
 * 随机数
 * 方   法  名:getRandomString
 * 方法描述:
 * 参         数:@param length
 * 参         数:@return
 * 返   回  值:String
 * 创   建  人:rock
 * @exception
 * @since  1.0.0
 */
	private  String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	// 将 s 进行 BASE64 编码
	private  String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	private  String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
