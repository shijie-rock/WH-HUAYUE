/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.test
 * 文   件  名:AESEncryptTest.java
 * 创 建日期:2017年8月9日-下午4:35:55
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.binary.Base64;

//import java.util.Base64;
//import java.util.Base64.Decoder;
//import java.util.Base64.Encoder;

//import org.apache.commons.codec.Encoder;
//import org.apache.commons.codec.binary.Base64;

//import org.apache.commons.codec.Encoder;
//import org.apache.commons.codec.binary.Base64;

/**不兼容ios ，废弃
 * 类名称:AESEncryptTest
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月9日 下午4:35:55
 * 修改备注:
 * @version 1.0.0
 */
public class AESEncryptTest {

	//加密算是是AES
    private static final String ALGORITHM = "AES";
    //转换格式
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    
    //利用16个字节128位的key给src加密，不兼容ios
    public static byte[] encrypt(byte[] src,byte[]key){
        try {
            //加密
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key,ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//            cipher.init(Cipher.ENCRYPT_MODE, keySpec,new SecureRandom());
            byte[] enMsgBytes = cipher.doFinal(src);    
            return enMsgBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //利用16个字节128位的key给src解密  不兼容ios
    public static byte[] decrypt(byte[] encryptBytes,byte[]key){
        try {
            //解密
            Cipher deCipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key,ALGORITHM);
            deCipher.init(Cipher.DECRYPT_MODE, keySpec);
//            deCipher.init(Cipher.DECRYPT_MODE, keySpec,new SecureRandom());
            byte[] deMsgBytes = deCipher.doFinal(encryptBytes);
            return deMsgBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String key = "1234567887654321";
		String msg = "hello world. 你好，DES";
		

		
	    System.out.println("加密前："+msg);
	    byte[] encryptBytes;
		try {
			System.out.println(encryptAES(msg, key));
			
			encryptBytes = encrypt(msg.getBytes("UTF-8"),key.getBytes());

//	    byte[] contents = baos.toByteArray();  
		StringBuffer intStrSbf=new StringBuffer("");
	    byte[] encryptBytes2=new byte[encryptBytes.length];
	    for (int i = 0; i < encryptBytes.length; i++) {  
	        System.out.println("contents:"+(encryptBytes[i]&0xff));  
	        encryptBytes2[i]=(byte) (encryptBytes[i]&0xff);
	        intStrSbf.append( (encryptBytes[i]&0xff)).append(",");
	    }  
	    
	    System.out.println("intStrSbf="+intStrSbf);
	    
	    System.out.println("加密后："+new String(encryptBytes2));
//	    String base64Enc=new String(new Base64().encode(encryptBytes2));
	    String base64Enc=new String(new Base64().encode(encryptBytes));
	    System.out.println("加密后base64："+base64Enc);
	    
	    //base64->byte[]->decrypt
	    byte[] deMsgBytes = decrypt(new Base64().decode(base64Enc.getBytes("UTF-8")),key.getBytes());
	    
//	    byte[] deMsgBytes = decrypt(encryptBytes,key.getBytes());
	    System.out.println("解密后："+new String(deMsgBytes,"UTF-8"));
	    
	    String base64Enc1=new String(new Base64().encode("ABC123ABC123ABC123ABC123ABC123ABC12312312312312".getBytes()));
	    System.out.println("解密后："+base64Enc1);
	    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}
		
	}
	
	/**
	 * 兼容ios
	 * 方   法  名:encryptAES
	 * 方法描述:
	 * 参         数:@param content
	 * 参         数:@param key
	 * 参         数:@return
	 * 参         数:@throws InvalidKeyException
	 * 参         数:@throws NoSuchAlgorithmException
	 * 参         数:@throws NoSuchPaddingException
	 * 参         数:@throws UnsupportedEncodingException
	 * 参         数:@throws InvalidAlgorithmParameterException
	 * 参         数:@throws IllegalBlockSizeException
	 * 参         数:@throws BadPaddingException
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String encryptAES(String content, String key) 
            throws InvalidKeyException, NoSuchAlgorithmException, 
            NoSuchPaddingException, UnsupportedEncodingException, 
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

    byte[] byteContent = content.getBytes("UTF-8");
    
   String IV_STRING = "16-Bytes--String";

    // 注意，为了能与 iOS 统一
    // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
    byte[] enCodeFormat = key.getBytes();
    SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

    byte[] initParam = IV_STRING.getBytes();
    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

    // 指定加密的算法、工作模式和填充方式
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

    byte[] encryptedBytes = cipher.doFinal(byteContent);

    // 同样对加密后数据进行 base64 编码
//    Encoder encoder = Base64.getEncoder();
//    return encoder.encodeToString(encryptedBytes);
    return new String(new Base64().encode(encryptedBytes));
}


}
