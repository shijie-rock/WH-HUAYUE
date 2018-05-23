package com.truckinspect.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 兼容ios --生产(适用于供应商-券)
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
	
	private static final String IV_STRING2="YYYYMMdd00000000";//固定值
	
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
	
	//20171120-key 改为固定值 begin
	//加密
	public static String aesEncryptString2(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] contentBytes = content.getBytes(charset);
		byte[] keyBytes = key.getBytes(charset);
		byte[] encryptedBytes = aesEncryptBytes2(contentBytes, keyBytes);
//		Encoder encoder = Base64.getEncoder();
//	    return encoder.encodeToString(encryptedBytes);
		return new String(new Base64().encode(encryptedBytes));
	}
	
	//解密
	public static String aesDecryptString2(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
//		Decoder decoder = Base64.getDecoder();
//	    byte[] encryptedBytes = decoder.decode(content);
		byte[] encryptedBytes = new Base64().decode(content);
		byte[] keyBytes = key.getBytes(charset);
		byte[] decryptedBytes = aesDecryptBytes2(encryptedBytes, keyBytes);
		return new String(decryptedBytes, charset);		
	}
	
	public static byte[] aesEncryptBytes2(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return cipherOperation2(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] aesDecryptBytes2(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return cipherOperation2(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
	}
	
	private static byte[] cipherOperation2(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
		byte[] initParam = IV_STRING2.getBytes(charset);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(mode, secretKey, ivParameterSpec);
		
		return cipher.doFinal(contentBytes);
	}
	//20171120-key 改为固定值 end
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
	
	
	/**
	 * 加密-兑换券：YBAESCipher(QNO+_+PIN，YYYYMMdd00000000):偏移量，key 为固定值
	 * 方   法  名:aesEncryptString
	 * 方法描述:
	 * 参         数:@param obj
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String aesEncryptCoupons(String couponsNO,String pin){
		String key = "YYYYMMdd00000000";
		String content=couponsNO+"_"+pin;
//		content=stringToAscii(JSONObject.fromObject(obj).toString());//String ->ascii
		String encryptStr;
		try {
			encryptStr = aesEncryptString2(content, key);
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
	 * 解密 兑换券：YBAESCipher(QNO+_+PIN，YYYYMMdd00000000)偏移量，key 为固定值
	 * 方   法  名:aesDecryptString
	 * 方法描述:
	 * 参         数:@param encryptStr
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String aesDecryptCoupons(String encryptStr){
		String key = "YYYYMMdd00000000";
		try {
			String decryptStr=aesDecryptString2(encryptStr, key);
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
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, EncoderException{
		
		System.out.println("1:="+aesEncryptCoupons("12345678", "1234"));
		System.out.println(aesDecryptCoupons("yoSGxGpoOEY7+lpAsCSQjw=="));
//		System.out.println(aesDecryptString("EWuciHwHuNnMtW4rODtaCeSKFytYutUIokMundeimF+TpR93dOmC7LJCwvkZOr8kBkGiOQ7f35ePxlZe3gOQtIPmx259y4LyUMN1n0ATdLQrsKOifcl11W/Rc75kvOZujlNPMJfpqqapIGZGiJoQxVLdnusPanp5MGFY2npUKkQ="));
//		System.out.println(aesDecryptString("EWuciHwHuNnMtW4rODtaCeSKFytYutUIokMundeimF+TpR93dOmC7LJCwvkZOr8kBkGiOQ7f35ePxlZe3gOQtIPmx259y4LyUMN1n0ATdLQrsKOifcl11W/Rc75kvOZujlNPMJfpqqapIGZGiJoQxYVsHNzxhZ37jPPWU2s7mAA="));
//		System.out.println(aesDecryptString("Z0OzRlaRDtXdoT/+yZqakQyKpAYkrM7pvEuu/ZIaFJs9dv+WJm/cC/Gqz9dhKe8iaTZrKZXvJpliloVM9Spypunvf3UXdUmTVALnS7sV615CDwjjPu/0EX+linlOwdmV0dp6vrx6bIBSUrXbGQYLh9QMGbWQvzpSH8u7G91sOXU="));
//		System.out.println(aesDecryptString("PWKkuUXlUf/M6jVXEqq8N0JRZBGSAk49UaEwy0ifNBMNLYB3s8i5wzo5oybeXKAJb0oPKT1f1db2IqfrVucDDQv6xtvq/dghj6RiqtWrpGpX5VFsxsb84RzqfQdkLzvcI5+6La+ZQ9FD+4ZV7n+T4Ly9YP0S8tS//nOMjBbfWCQ="));
//		System.out.println(aesDecryptString("3s91/ipSmjy4hdh3gLCP7J/0jgDR0pfQdn3kggWIPZ2PnAavEcEuI9er/JTpxK0bQha1OO61sDjtr16tN4cGtpWiZoFE/hAgZ4ft9ilI/VfcEIDte0jEi52T34neOW4eRZD4gps71Q+XDac4Y4Wou/54PZCF4hX4VoqfqEM28+lwRCR1VucSh+T9KcCMeWKF"));
//		System.out.println(aesDecryptString("EYykcHh+2dMfK0+bgbqLvMv91/ToPGMBRY+uD0ShQ71qKKLL1RoxoZWZuXnwfH/k9Tt2lgAoh61z1lUq+ogDhqoMRX2zDHez1rOxjv5O1II3Odesl3ep/g93biNvLxJ3EvETNzi5WMy3zjoIDZCAiCrC0IrwWy0Xhdj14rF0wLc="));
		
	CustQrCodeBean bean=new CustQrCodeBean();
	bean.setExt(null);
	bean.setCustId(4764);
	bean.setStatus("1");
	bean.setName("如若不见亦不念");
	bean.setValiTime(String.valueOf(1515654759847l));
	bean.setMobile("182****8906");
	bean.setMoney(1.41d);

	String old="{\"ext\":\"\",\"id\":4764,\"mobile\":\"182****8906\",\"money\":1.41,\"name\":\"如若不见亦不念\",\"status\":\"1\",\"valiTime\":\"1515654759847\"}";
	
	System.out.println(aesEncryptString(old));
//	System.out.println(aesDecryptString(aesEncryptString(old)));
//	System.out.println(aesEncryptString(bean));
	//base->ascii
	//ascii->encrypt
	//encrypt->base64
	String key = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));
	
	for(int j=0;j<2;j++){
		CustQrCodeBean bean1=new CustQrCodeBean();
		bean1.setExt(null);
//		bean1.setExt(String.valueOf(new Random().nextInt(10000)));
		bean1.setCustId(new Random().nextInt(10000));
		bean1.setStatus("1");
//		bean1.setName(new Random().nextInt(10000)+1+j+"");
		bean1.setName(URLEncoder.encode((new Random().nextInt(10110000)+"如若不见亦不念"+"1"),"UTF-8"));
		bean1.setValiTime(String.valueOf(System.currentTimeMillis()+j*60*1000));
		bean1.setMobile("182****8906");
		bean1.setMoney(1.41d+new Random().nextInt(100));
		

//		String old="{\"ext\":\"\",\"id\":4764,\"mobile\":\"182****8906\",\"money\":1.41,\"name\":\"如若不见亦不念\",\"status\":\"1\",\"valiTime\":\"1515654759847\"}";
		
		String src1=new String(JSONObject.fromObject(bean1).toString().getBytes(),"UTF-8");
//		System.out.println(new Base64().encodeToString(src.getBytes("UTF-8")));
		
		
//		String miwen=aesEncryptString(src,key);
//		String newWen=aesDecryptString(miwen,key);
		
//		System.out.println("["+i+"]原文："+src);
//		System.out.println("密文："+miwen);
//		System.out.println("明文："+newWen);
//		System.out.println(JSONObject.fromObject(newWen));
		
//		src=new String("如若不见亦不念$$$182****8906$$$600".getBytes("UTF-8"),"UTF-8");
		
//		src= string2Unicode("如若不见亦不念$$$182****8906$$$600");
		
		String src=src1;
//		String src=URLEncoder.encode(src1, "UTF-8");
		System.out.println("src:="+src);
		
	    char[] c = src.toCharArray();
	    //使用for循环给字符数组加密
	    for(int i=0;i<c.length;i++){
	        c[i] = (char)(c[i]^1);
	    }
	    //输出加密或者解密结果
	    System.out.println("src："+new String(c));
		
	    String x=new Base64().encodeToString(new String(c).getBytes());
	    System.out.println(x);
	    
	    x=new String(Base64.decodeBase64(x));
	    System.out.println(x);
	    
	    char[] xc=x.toCharArray();
		for(int i=0;i<xc.length;i++){
			
			 c[i] = (char)(xc[i]^1);
		}
		String newSrc=new String(new String(c).getBytes("UTF-8"),"UTF-8");
	    	System.out.println("old："+newSrc);
//	    	newSrc=URLDecoder.decode(newSrc,"UTF-8");
	    	System.out.println("old2："+newSrc);
			System.out.println("["+j+"]:="+JSONObject.fromObject(newSrc));
			JSONObject o=JSONObject.fromObject(newSrc);
			String name=(String) o.get("name");
			System.out.println(URLDecoder.decode(name,"UTF-8"));
			if(!JSONObject.fromObject(newSrc).toString().equals(src1)){
				System.out.println("原文 明文 不一致");
				break;
			}
			
		}
//	if(!src.equals(newWen)||!JSONObject.fromObject(newWen).toString().equals(src)){
//		
//		System.out.println("原文 明文 不一致");
//		break;
//	}

	}
	
//	CustQrCodeBean bean1=new CustQrCodeBean();
//	bean1.setExt(null);
////	bean1.setExt(String.valueOf(new Random().nextInt(10000)));
//	bean1.setCustId(new Random().nextInt(10000));
//	bean1.setStatus("1");
//	bean1.setName(new Random().nextInt(10000)+"如若不见亦不念");
//	bean1.setValiTime(String.valueOf(System.currentTimeMillis()));
//	bean1.setMobile("182****8906");
//	bean1.setMoney(1.41d+new Random().nextInt(100));
//	
//	String src=JSONObject.fromObject(bean1).toString();
//	byte[] byteArr=src.getBytes("UTF-8");
//	byte[] byteArr1=new byte[byteArr.length/2];
//	byte[] byteArr2=new byte[byteArr.length-byteArr1.length];
//	for(int j=0;j<=byteArr.length-1;j++){
//		if(j%2==0){
//			byteArr1[j/2+j%2]=byteArr[j];
//		}
//		else{
//			byteArr2[j/2]=byteArr[j];	
//		}
//	}
	
//	byte[] both = (byte[]) ArrayUtils.addAll(byteArr1, byteArr2);
	
	
//	for(int j=byteArr.length-1;j>=0;j--){
//		
//		byteArr1[byteArr.length-1-j]=byteArr[j];
//	}
//	System.out.println(new Base64().encodeToString(both));
	
//    char[] c = src.toCharArray();
//    //使用for循环给字符数组加密
//    for(int i=0;i<c.length;i++){
//        c[i] = (char)(c[i]^100);
//    }
//    //输出加密或者解密结果
//    System.out.println("src："+new String(c));
//	
//    String x=new Base64().encodeToString(new String(c).getBytes());
//    System.out.println(x);
//    
//    x=new String(Base64.decodeBase64(x));
//    System.out.println(x);
//    
//    char[] xc=x.toCharArray();
//	for(int i=0;i<xc.length;i++){
//		
//		 c[i] = (char)(xc[i]^100);
//	}
//    	System.out.println("old："+new String(c));
    
//	}
	
	public static String string2Unicode(String string) {  
		   
	    StringBuffer unicode = new StringBuffer();  
	   
	    for (int i = 0; i < string.length(); i++) {  
	   
	        // 取出每一个字符  
	        char c = string.charAt(i);  
	   
	        // 转换为unicode  
	        unicode.append("\\u" + Integer.toHexString(c));  
	    }  
	   
	    return unicode.toString();  
	} 
	
	public static String unicode2String(String unicode) {  
		   
	    StringBuffer string = new StringBuffer();  
	   
	    String[] hex = unicode.split("\\\\u");  
	   
	    for (int i = 1; i < hex.length; i++) {  
	   
	        // 转换出每一个代码点  
	        int data = Integer.parseInt(hex[i], 16);  
	   
	        // 追加成string  
	        string.append((char) data);  
	    }  
	   
	    return string.toString();  
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


