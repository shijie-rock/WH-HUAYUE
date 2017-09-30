package com.truckinspect.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 兼容ios --生产
 * 类名称:AESCipher
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月23日 下午8:27:09
 * 修改备注:
 * @version 1.0.0
 */
public class YBAESCipher {
	
	private static Logger log=Logger.getLogger(YBAESCipher.class);
	
//	private static final String IV_STRING = "A-16-Byte-String";
//	private static final String IV_STRING = "1234567887654321";//偏移量 16bit must
	private static final String IV_STRING = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));//偏移量 16bit must
	private static final String charset = "UTF-8";
	
	//加密
	public static String aesEncryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] contentBytes = content.getBytes(charset);
		byte[] keyBytes = key.getBytes(charset);
		byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
//		Encoder encoder = Base64.getEncoder();
//	    return encoder.encodeToString(encryptedBytes);
		return new String(new Base64().encode(encryptedBytes));
	}
	
	//解密
	public static String aesDecryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
//		Decoder decoder = Base64.getDecoder();
//	    byte[] encryptedBytes = decoder.decode(content);
	    byte[] encryptedBytes = new Base64().decode(content);
	    byte[] keyBytes = key.getBytes(charset);
		byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
	    return new String(decryptedBytes, charset);		
	}
	
	public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
	}
	
	private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
	    byte[] initParam = IV_STRING.getBytes(charset);
	    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(mode, secretKey, ivParameterSpec);

 	 	return cipher.doFinal(contentBytes);
	}
	
	/**
	 * 加密
	 * 方   法  名:aesEncryptString
	 * 方法描述:
	 * 参         数:@param obj
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String aesEncryptString(Object obj){
		String key = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));
		String content=JSONObject.fromObject(obj).toString();
//		content=stringToAscii(JSONObject.fromObject(obj).toString());//String ->ascii
		String encryptStr;
		try {
			encryptStr = aesEncryptString(content, key);
			log.debug("encryptStr:="+encryptStr);
			return encryptStr;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * 解密
	 * 方   法  名:aesDecryptString
	 * 方法描述:
	 * 参         数:@param encryptStr
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String aesDecryptString(String encryptStr){
		String key = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));
		try {
			String decryptStr=aesDecryptString(encryptStr, key);
			log.debug("decryptStr:="+decryptStr);
			return decryptStr;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
//http://t.youbus.com.cn/qr/1?EWuciHwHuNnMtW4rODtaCeSKFytYutUIokMundeimF+TpR93dOmC7LJCwvkZOr8kBkGiOQ7f35ePxlZe3gOQtIPmx259y4LyUMN1n0ATdLQrsKOifcl11W/Rc75kvOZujlNPMJfpqqapIGZGiJoQxYVsHNzxhZ37jPPWU2s7mAA=&END=1	
	public static void main(String[] args){
//		System.out.println(aesDecryptString("EWuciHwHuNnMtW4rODtaCeSKFytYutUIokMundeimF+TpR93dOmC7LJCwvkZOr8kBkGiOQ7f35ePxlZe3gOQtIPmx259y4LyUMN1n0ATdLQrsKOifcl11W/Rc75kvOZujlNPMJfpqqapIGZGiJoQxVLdnusPanp5MGFY2npUKkQ="));
//		System.out.println(aesDecryptString("EWuciHwHuNnMtW4rODtaCeSKFytYutUIokMundeimF+TpR93dOmC7LJCwvkZOr8kBkGiOQ7f35ePxlZe3gOQtIPmx259y4LyUMN1n0ATdLQrsKOifcl11W/Rc75kvOZujlNPMJfpqqapIGZGiJoQxYVsHNzxhZ37jPPWU2s7mAA="));
//		System.out.println(aesDecryptString("Z0OzRlaRDtXdoT/+yZqakQyKpAYkrM7pvEuu/ZIaFJs9dv+WJm/cC/Gqz9dhKe8iaTZrKZXvJpliloVM9Spypunvf3UXdUmTVALnS7sV615CDwjjPu/0EX+linlOwdmV0dp6vrx6bIBSUrXbGQYLh9QMGbWQvzpSH8u7G91sOXU="));
		System.out.println(aesDecryptString("EYykcHh+2dMfK0+bgbqLvMv91/ToPGMBRY+uD0ShQ71qKKLL1RoxoZWZuXnwfH/k9Tt2lgAoh61z1lUq+ogDhqoMRX2zDHez1rOxjv5O1II3Odesl3ep/g93biNvLxJ3EvETNzi5WMy3zjoIDZCAiCrC0IrwWy0Xhdj14rF0wLc="));
		
	CustQrCodeBean bean=new CustQrCodeBean();
//	bean.setCodeType("T0002");
	bean.setCustBalance(499490d);
	bean.setCustId(1830);
	bean.setCustStatus("1");
//	bean.setCustType("CT_0012");
	bean.setName("周宏翔");
	bean.setValiTime(String.valueOf(1504166181477l));
//	bean.setValiTime(String.valueOf(System.currentTimeMillis()));
	bean.setMobile("173****2599");
	
	System.out.println(aesEncryptString(bean));
	//base->ascii
	//ascii->encrypt
	//encrypt->base64
	
	
		
	}
	
	//test
	public static void main1(String args[]){
		String key = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));
		System.out.println(key);
		String content = "hello world. 你好，DES";
		
//		CustQrCodeBean bean=new CustQrCodeBean();
////		bean.setCodeType("T0002");
//		bean.setCustBalance(2000.11D);
//		bean.setCustId(123);
//		bean.setCustStatus("1");
////		bean.setCustType("CT_0012");
//		bean.setName("张三");
//		bean.setValiTime(String.valueOf(System.currentTimeMillis()));
//		bean.setMobile("13212345678");
//		
//		content=JSONObject.fromObject(bean).toString();

		System.out.println("加密前："+content);

		
		String encryptStr;
		try {
			System.out.println("加密前 base64："+new String(new Base64().encode(content.getBytes(charset))));
			
			encryptStr = aesEncryptString(content, key);
			
			System.out.println("加密后（base64+AES）："+encryptStr);
//			encryptStr=aesDecryptString("1ayP2OhwZqoW6kFzqR9nbz1+1GD5WdBhyKlKFj0+bAI=", key);
			encryptStr=aesDecryptString(encryptStr, key);
			System.out.println("解密后："+encryptStr);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(",");  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return sbu.toString(); 
	}
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split(",");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return sbu.toString();  
	}  
	
}


