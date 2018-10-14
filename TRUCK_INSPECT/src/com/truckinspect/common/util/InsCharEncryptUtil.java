/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.util
 * 文   件  名:InsCharEncryptUtil.java
 * 创 建日期:2018年8月21日-下午5:55:40
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * 类名称:InsCharEncryptUtil
 * 类描述:为手机号加密解密
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年8月21日 下午5:55:40
 * 修改备注:
 * @version 1.0.0
 */
public class InsCharEncryptUtil {

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

		String mobile="18221616910";
		String encode=encode(mobile);
		System.out.println(encode);
		System.out.println(decode(encode));
		
	}
	
	static final String MASK_CODE="082120189753124680";//掩码
	
	
	/**
	 * 编码：原文-》密文
	 * 方   法  名:encode
	 * 方法描述:
	 * 参         数:@param input
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
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
	 * 解码:密文-》原文
	 * 方   法  名:decode
	 * 方法描述:
	 * 参         数:@param input
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
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
