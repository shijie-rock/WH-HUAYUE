/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.test
 * ��   ��  ��:YBAESCipher2.java
 * �� ������:2018��1��11��-����4:13:16
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

/**
 * ������:YBAESCipher2
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��1��11�� ����4:13:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YBAESCipher2 {

	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	  /*
	   * ����
	   * 1.������Կ������
	   * 2.����ecnodeRules�����ʼ����Կ������
	   * 3.������Կ
	   * 4.�����ͳ�ʼ��������
	   * 5.���ݼ���
	   * 6.�����ַ���
	   */
	    public static String AESEncode(String encodeRules,String content){
	        try {
	            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.����ecnodeRules�����ʼ����Կ������
	            //����һ��128λ�����Դ,���ݴ�����ֽ�����
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.����ԭʼ�Գ���Կ
	            SecretKey original_key=keygen.generateKey();
	              //4.���ԭʼ�Գ���Կ���ֽ�����
	            byte [] raw=original_key.getEncoded();
	            //5.�����ֽ���������AES��Կ
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.����ָ���㷨AES�Գ�������
	            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS7Padding");
	              //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽��ܽ���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            //8.��ȡ�������ݵ��ֽ�����(����Ҫ����Ϊutf-8)��Ȼ��������������ĺ�Ӣ�Ļ�����ľͻ����Ϊ����
	            byte [] byte_encode=content.getBytes("utf-8");
	            //9.�����������ĳ�ʼ����ʽ--���ܣ������ݼ���
	            byte [] byte_AES=cipher.doFinal(byte_encode);
	          //10.�����ܺ������ת��Ϊ�ַ���
	            //������Base64Encoder�л��Ҳ�����
	            //����취��
	            //����Ŀ��Build path�����Ƴ�JRE System Library������ӿ�JRE System Library�����±�����һ�������ˡ�
	            String AES_encode=new String(new Base64().encode(byte_AES));
	          //11.���ַ�������
	            return AES_encode;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        
	        //����д�ͷ���nulll
	        return null;         
	    }
	    /*
	     * ����
	     * ���ܹ��̣�
	     * 1.ͬ����1-4��
	     * 2.�����ܺ���ַ������ĳ�byte[]����
	     * 3.���������ݽ���
	     */
	    public static String AESDncode(String encodeRules,String content){
	        try {
	            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.����ecnodeRules�����ʼ����Կ������
	            //����һ��128λ�����Դ,���ݴ�����ֽ�����
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.����ԭʼ�Գ���Կ
	            SecretKey original_key=keygen.generateKey();
	              //4.���ԭʼ�Գ���Կ���ֽ�����
	            byte [] raw=original_key.getEncoded();
	            //5.�����ֽ���������AES��Կ
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.����ָ���㷨AES�Գ�������
	            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS7Padding");
	              //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            //8.�����ܲ����������ݽ�����ֽ�����
	            byte [] byte_content= new Base64().decode(content);
	            /*
	             * ����
	             */
	            byte [] byte_decode=cipher.doFinal(byte_content);
	            String AES_decode=new String(byte_decode,"utf-8");
	            return AES_decode;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        
	        //����д�ͷ���nulll
	        return null;         
	    }
	    
	    public static void main(String[] args) {
	    	String IV_STRING = new SimpleDateFormat("YYYYMMdd00000000").format(new Date(System.currentTimeMillis()));//ƫ���� 16bit must
	    	
	    	CustQrCodeBean bean=new CustQrCodeBean();
	    	bean.setExt(null);
	    	bean.setCustId(4764);
	    	bean.setStatus("1");
	    	bean.setName("���������಻��");
	    	bean.setValiTime(String.valueOf(1515654759847l));
	    	bean.setMobile("182****8906");
	    	bean.setMoney(1.41d);
	    	String content=JSONObject.fromObject(bean).toString();
	        /*
	         * ����
	         */
	    	System.out.println("���ģ�"+content);
	    	String aesContent=AESEncode(IV_STRING, content);
	        System.out.println("��������Ĺ���"+1+"���ܺ��������:"+aesContent);
	       
	        /*
	         * ����
	         */
	        System.out.println("��������Ĺ���"+1+"���ܺ��������:"+AESDncode(IV_STRING, aesContent));
	    }


}
