/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.common.util
 * ��   ��  ��:InsCharEncryptUtil.java
 * �� ������:2018��8��21��-����5:55:40
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * ������:InsCharEncryptUtil
 * ������:Ϊ�ֻ��ż��ܽ���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��8��21�� ����5:55:40
 * �޸ı�ע:
 * @version 1.0.0
 */
public class InsCharEncryptUtil {

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

		String mobile="18221616910";
		String encode=encode(mobile);
		System.out.println(encode);
		System.out.println(decode(encode));
		
	}
	
	static final String MASK_CODE="082120189753124680";//����
	
	
	/**
	 * ���룺ԭ��-������
	 * ��   ��  ��:encode
	 * ��������:
	 * ��         ��:@param input
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String encode(String input){
		if(StringUtils.isBlank(input)){
			return null;
		}
		byte[] codeBytes=MASK_CODE.getBytes();
		byte[] mobileBytes=input.getBytes();
		
		StringBuffer newMobile=new StringBuffer("");
		
		for(int i=0;i<mobileBytes.length;i++){
//			System.out.print("org code["+i+"]:="+codeBytes[i]);
//			System.out.println(" org mobile["+i+"]:="+mobileBytes[i]);
			char newMobileCode=(char)(codeBytes[i]+mobileBytes[i]);
			newMobile.append(newMobileCode);
//			System.out.println("new newMobileCode:="+newMobileCode);
		}
//		System.out.println("newMobile:="+newMobile);
		return newMobile.toString();
		
	}
	

	/**
	 * ����:����-��ԭ��
	 * ��   ��  ��:decode
	 * ��������:
	 * ��         ��:@param input
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String decode(String input){
		if(StringUtils.isBlank(input)){
			return null;
		}
		StringBuffer oldMobile=new StringBuffer("");
		byte[] codeBytes=MASK_CODE.getBytes();
		byte[] newMobileBytes=input.toString().getBytes();

		for(int i=0;i<newMobileBytes.length;i++){
//			System.out.print("org code["+i+"]:="+codeBytes[i]);
//			System.out.println(" new mobile["+i+"]:="+newMobileBytes[i]);
			char oldMobileCode=(char)(newMobileBytes[i]-codeBytes[i]);
			oldMobile.append(oldMobileCode);
//			System.out.println("old oldMobile:="+oldMobileCode);
		}
//		System.out.println("oldMobile:="+oldMobile);
		return oldMobile.toString();
	}
}
