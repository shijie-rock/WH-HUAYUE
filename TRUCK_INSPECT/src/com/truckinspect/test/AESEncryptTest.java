/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.test
 * ��   ��  ��:AESEncryptTest.java
 * �� ������:2017��8��9��-����4:35:55
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

/**������ios ������
 * ������:AESEncryptTest
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��9�� ����4:35:55
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AESEncryptTest {

	//����������AES
    private static final String ALGORITHM = "AES";
    //ת����ʽ
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    
    //����16���ֽ�128λ��key��src���ܣ�������ios
    public static byte[] encrypt(byte[] src,byte[]key){
        try {
            //����
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

    //����16���ֽ�128λ��key��src����  ������ios
    public static byte[] decrypt(byte[] encryptBytes,byte[]key){
        try {
            //����
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
		String msg = "hello world. ��ã�DES";
		

		
	    System.out.println("����ǰ��"+msg);
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
	    
	    System.out.println("���ܺ�"+new String(encryptBytes2));
//	    String base64Enc=new String(new Base64().encode(encryptBytes2));
	    String base64Enc=new String(new Base64().encode(encryptBytes));
	    System.out.println("���ܺ�base64��"+base64Enc);
	    
	    //base64->byte[]->decrypt
	    byte[] deMsgBytes = decrypt(new Base64().decode(base64Enc.getBytes("UTF-8")),key.getBytes());
	    
//	    byte[] deMsgBytes = decrypt(encryptBytes,key.getBytes());
	    System.out.println("���ܺ�"+new String(deMsgBytes,"UTF-8"));
	    
	    String base64Enc1=new String(new Base64().encode("ABC123ABC123ABC123ABC123ABC123ABC12312312312312".getBytes()));
	    System.out.println("���ܺ�"+base64Enc1);
	    
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
	 * ����ios
	 * ��   ��  ��:encryptAES
	 * ��������:
	 * ��         ��:@param content
	 * ��         ��:@param key
	 * ��         ��:@return
	 * ��         ��:@throws InvalidKeyException
	 * ��         ��:@throws NoSuchAlgorithmException
	 * ��         ��:@throws NoSuchPaddingException
	 * ��         ��:@throws UnsupportedEncodingException
	 * ��         ��:@throws InvalidAlgorithmParameterException
	 * ��         ��:@throws IllegalBlockSizeException
	 * ��         ��:@throws BadPaddingException
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String encryptAES(String content, String key) 
            throws InvalidKeyException, NoSuchAlgorithmException, 
            NoSuchPaddingException, UnsupportedEncodingException, 
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

    byte[] byteContent = content.getBytes("UTF-8");
    
   String IV_STRING = "16-Bytes--String";

    // ע�⣬Ϊ������ iOS ͳһ
    // ����� key ������ʹ�� KeyGenerator��SecureRandom��SecretKey ����
    byte[] enCodeFormat = key.getBytes();
    SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

    byte[] initParam = IV_STRING.getBytes();
    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

    // ָ�����ܵ��㷨������ģʽ����䷽ʽ
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

    byte[] encryptedBytes = cipher.doFinal(byteContent);

    // ͬ���Լ��ܺ����ݽ��� base64 ����
//    Encoder encoder = Base64.getEncoder();
//    return encoder.encodeToString(encryptedBytes);
    return new String(new Base64().encode(encryptedBytes));
}


}
