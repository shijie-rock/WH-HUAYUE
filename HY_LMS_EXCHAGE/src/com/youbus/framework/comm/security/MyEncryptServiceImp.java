/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.security
 * ��   ��  ��:MyEncryptServiceImp.java
 * �� ������:2015��6��3��-����10:46:49
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.security;

import java.util.Map;
import java.util.Random;

import sun.misc.BASE64Decoder;

import com.infoservice.framework.exceptions.FrameException;

/**
 * ������:MyEncryptServiceImp
 * ������:base64
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��3�� ����10:46:49
 * �޸ı�ע:
 * @version 1.0.0
 */
public class MyEncryptServiceImp extends MsgEncryptService {

	/**
	 * ����һ���µ�ʵ�� MyEncryptServiceImp.
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
//		ϵͳ������ֵ CommonCanstant...XXX =XXX;
	}
	
	private MyEncryptServiceImp() {
		// TODO Auto-generated constructor stub
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
 * �����
 * ��   ��  ��:getRandomString
 * ��������:
 * ��         ��:@param length
 * ��         ��:@return
 * ��   ��  ֵ:String
 * ��   ��  ��:rock
 * @exception
 * @since  1.0.0
 */
	private  String getRandomString(int length) { // length��ʾ�����ַ����ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	// �� s ���� BASE64 ����
	private  String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// �� BASE64 ������ַ��� s ���н���
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
