/**
 * 项目名称:AUTH_CENTER
 * 包         名:com.youbus.authcenter.remote.pofactory
 * 文   件  名:TmSupCouponsNOPOFactory.java
 * 创 建日期:2017年11月16日-下午7:17:36
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
 * 类名称:TmSupCouponsNOPOFactory
 * 类描述:根据供应商编号，生成唯一兑换券号及PIN码
 * 多线程测试
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年11月16日 下午7:17:36
 * 修改备注:
 * @version 1.0.0
 */
public class TmSupCouponsNOPOFactory extends POFactory {

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
	
	//存储券类型对应数字类型
	private static Map<String,String> COUPONS_TYPE_MAP;
	
	
	static{
		COUPONS_TYPE_MAP=new Hashtable<String, String>();
		COUPONS_TYPE_MAP.put("TG", "01");//团购
		COUPONS_TYPE_MAP.put("MD", "02");//买单（即买减活动）
	}
	
	public static int base=0;
	
	//根据供应商编号，兑换券类型，生成唯一兑换券号及PIN码
	//兑换券类型（01）+供应商Id（12）+YYMMDD+last4 system.current+random（4）：共计14位；pin random 4
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
	
	
	public static String getRandomNumString(int length) { //length表示生成字符串的长度
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
