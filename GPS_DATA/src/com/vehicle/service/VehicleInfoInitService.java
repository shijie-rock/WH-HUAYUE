/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.service
 * 文   件  名:VehicleInfoInitService.java
 * 创 建日期:2017年4月16日-下午6:18:35
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.vehicle.common.CommonConstant;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:VehicleInfoInitService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月16日 下午6:18:35
 * 修改备注:
 * @version 1.0.0
 */
public class VehicleInfoInitService implements Service {
	private static Logger log=Logger.getLogger(VehicleInfoInitService.class);
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
		return this.getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map paramMap) throws FrameException {
		// TODO Auto-generated method stub
		log.info("开始初始化配置");
	    Properties prop = new Properties();   
	    InputStream in = VehicleInfoInitService.class.getClassLoader().getResourceAsStream("SysCommon.properties");  
	    try {
			prop.load(in);
			String name=prop.getProperty("NAME");
			String pass=prop.getProperty("PASS");
			String key=prop.getProperty("INTERFACE_KEY");
			String sercret=prop.getProperty("INTERFACE_SERCRET");
			
			String reqURL=prop.getProperty("FUNCTION_REQ_SERVER_URL");
			String method=prop.getProperty("FUNCTION_METHOD_NAME");
			if(DBConUtil.stringNotNULL(key)&&DBConUtil.stringNotNULL(sercret)){
				CommonConstant.NAME=name;
				CommonConstant.PASS=pass;
				CommonConstant.KEY=key;
				CommonConstant.SERCRET=sercret;
				
				CommonConstant.REQ_SERVER_URL=reqURL;
				CommonConstant.METHOD_NAME=method;

				System.out.println("初始化配置成功。");
				log.info("初始化配置成功。");
			}else{
				System.out.println("初始化配置-参数不全。");
				log.info("初始化配置-参数不全。");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("初始化配置失败。");
			log.error("初始化配置失败。");
		} 
		finally{
			System.out.println("初始化配置结束。");
			log.info("初始化配置结束。");
		}
		
		
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
