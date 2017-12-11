/**
 * ��Ŀ����:AUTH_CENTER
 * ��         ��:com.youbus.authcenter.remote.pofactory
 * ��   ��  ��:TmSupCouponsNOPOFactory.java
 * �� ������:2017��11��16��-����7:17:36
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.infoservice.po.POFactory;

/**
 * ������:TmSupCouponsNOPOFactory
 * ������:���ݹ�Ӧ�̱�ţ�����Ψһ�һ�ȯ�ż�PIN��
 * ���̲߳���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��11��16�� ����7:17:36
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TmSupCouponsNOPOFactory extends POFactory {

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
//		String timeStr=String.valueOf(System.currentTimeMillis());
//		System.out.println(timeStr);
//		System.out.println(timeStr.substring(timeStr.length()-4));
		
		for(int i=0;i<1000;i++){
			Test test=new Test();
			Thread t=new Thread(test);
			t.start();

		}
//		for(int i=0;i<1000;i++){
//			Thread t=new Thread(new Test2());
//			t.start();
//		}
		
	}
	
//	private static Object lock=new Object();
	
	//�洢ȯ���Ͷ�Ӧ��������
	private static Map<String,String> COUPONS_TYPE_MAP;
	
	
	static{
		COUPONS_TYPE_MAP=new Hashtable<String, String>();
		COUPONS_TYPE_MAP.put("TG", "01");//�Ź�
		COUPONS_TYPE_MAP.put("MD", "02");//�򵥣���������
	}
	
	public static int base=0;
	
	//���ݹ�Ӧ�̱�ţ��һ�ȯ���ͣ�����Ψһ�һ�ȯ�ż�PIN��
	//�һ�ȯ���ͣ�01��+��Ӧ��Id��12��+YYMMDD+last4 system.current+random��4��������14λ��pin random 4
	public static Map<String,String> createSupCouponsNo(Connection conn,String supCode,String couponsType){
		synchronized (TmSupCouponsNOPOFactory.class) {

		if(StringUtils.isBlank(supCode)){
			logger.error("SUP_CODE IS NULL");
			return null;
		}
		String CTYPE_CODE="00";
		if(COUPONS_TYPE_MAP.containsKey(couponsType)){
			CTYPE_CODE=COUPONS_TYPE_MAP.get(couponsType);
		}
		
//		YBCommonResourceBean bean=YBCommonSourceBeanPOFactory.getResourceBeanByType(conn, null, supCode, MQMsgConstant.MQ_MSG_S_C_TYPE_SUPPLY_CODE);
//		if(bean==null||StringUtils.isBlank(bean.getId())){
//			logger.error("SUP_CODE["+supCode+"] BEAN IS NULL");
//			return null;
//		}
//		String supId=bean.getId();
//		if(supId.length()<2)supId="0"+supId;
//		
//		String SUP_ID_CODE=supId;
		String SUP_ID_CODE="";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String TIME_CODE=sdf.format(new Date(System.currentTimeMillis()));
		String timeStr=String.valueOf(System.currentTimeMillis());
		String TIMELONG_CODE=timeStr.substring(timeStr.length()-4);
		
		String RAND_CODE=getRandomNumString(4);
		String PIN=getRandomNumString(4);
		
		String COUPONS_NO=CTYPE_CODE+SUP_ID_CODE+TIME_CODE+TIMELONG_CODE+RAND_CODE;
		Map<String,String> resultMap=new Hashtable<String, String>();
		resultMap.put("COUPONS_NO", COUPONS_NO);
		resultMap.put("PIN", PIN);
		
		return resultMap;
		}
	}
	
	
	public static String getRandomNumString(int length) { //length��ʾ�����ַ����ĳ���
	    String base = "1234567890";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 } 
//	synchronized	
	public  static int add(){
		synchronized (TmSupCouponsNOPOFactory.class){
		try {
			Thread.sleep(new Random().nextInt(10)*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		synchronized (TmSupCouponsNOPOFactory.class){		
			base++;
			return base;
		}

	}
	public static synchronized int add1(){
		
		try {
			Thread.sleep(new Random().nextInt(10));
//			Thread.sleep(new Random().nextInt(10)*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			base++;
			return base;
	}
	
}

class Test implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int a=TmSupCouponsNOPOFactory.add();
		System.out.println(a);
	}
	
}

class Test2 implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(TmSupCouponsNOPOFactory.getRandomNumString(4));
	}
	
}
