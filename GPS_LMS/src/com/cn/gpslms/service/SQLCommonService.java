/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:SQLCommonService.java
 * 创 建日期:2016年8月28日-下午12:24:07
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
 * 类名称:SQLCommonService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月28日 下午12:24:07
 * 修改备注:
 * @version 1.0.0
 */
public class SQLCommonService implements Service {
	private static Logger log=Logger.getLogger(SQLCommonService.class);
	/**
	 * 创建一个新的实例 SQLCommonService.
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
		System.out.println("开始初始化 SQLCommonService 配置");
		log.info("开始初始化SQLCommonService 配置");
	    Properties prop = new Properties();   
//	    InputStream in = Object.class.getResourceAsStream("/memcache.properties");   
	    InputStream in = SQLCommonService.class.getClassLoader().getResourceAsStream("/SQLCommon.properties");  
	    try {
			prop.load(in);
//			System.out.println("--"+prop.getProperty("SrcDataPOFatory_querySrcData_SQL"));
			in.close();
			sqlMap=new HashMap<String, String>((Map)prop);
			System.out.println("初始化SQLCommonService配置成功。");
			log.info("初始化SQLCommonService配置成功。");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("初始化SQLCommonService配置失败。");
			log.error("初始化SQLCommonService配置失败。");
		} 
		finally{
			System.out.println("初始化SQLCommonService配置结束。");
			log.info("初始化SQLCommonService配置结束。");
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

	}

}
