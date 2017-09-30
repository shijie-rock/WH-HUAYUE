/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:SQLCommonService.java
 * �� ������:2016��8��28��-����12:24:07
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**
 * ������:SQLCommonService
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��8��28�� ����12:24:07
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SQLCommonService implements Service {
	private static Logger log=Logger.getLogger(SQLCommonService.class);
	/**
	 * ����һ���µ�ʵ�� SQLCommonService.
	 *
	 */
	public SQLCommonService() {
		// TODO Auto-generated constructor stub
	}
	
	private static Map<String, String> sqlMap=new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		//init 
		System.out.println("��ʼ��ʼ�� SQLCommonService ����");
		log.info("��ʼ��ʼ��SQLCommonService ����");
	    Properties prop = new Properties();   
//	    InputStream in = Object.class.getResourceAsStream("/memcache.properties");   
	    InputStream in = SQLCommonService.class.getClassLoader().getResourceAsStream("/SQLCommon.properties");  
	    try {
			prop.load(in);
//			System.out.println("--"+prop.getProperty("SrcDataPOFatory_querySrcData_SQL"));
			in.close();
			sqlMap=new HashMap<String, String>((Map)prop);
			System.out.println("��ʼ��SQLCommonService���óɹ���");
			log.info("��ʼ��SQLCommonService���óɹ���");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ʼ��SQLCommonService����ʧ�ܡ�");
			log.error("��ʼ��SQLCommonService����ʧ�ܡ�");
		} 
		finally{
			System.out.println("��ʼ��SQLCommonService���ý�����");
			log.info("��ʼ��SQLCommonService���ý�����");
		}
		
	}
	
	private static String getSqlFormat(String key){
		String sql=sqlMap.get(key);
		log.debug(" key :="+key+" : SQL FORMATE :="+sql);
		System.out.println(" key :="+key+" : SQL FORMATE :="+sql);
		return sql;
	}
	
	public static String getSql(String key,Object[] values){
		String sqlFormat=getSqlFormat(key);
		String result=null;
		if(sqlFormat!=null)result=MessageFormat.format(sqlFormat, values);
		log.debug(" key :="+key+" : SQL :="+result);
		System.out.println(" key :="+key+" : SQL :="+result);
		return result;
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

}
