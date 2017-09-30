/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:InitConnectParamService.java
 * 创 建日期:2016年8月30日-下午11:33:04
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.common.CommonConstant;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**初始化lms通讯参数
 * 类名称:InitConnectParamService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月30日 下午11:33:05
 * 修改备注:
 * @version 1.0.0
 */
public class InitConnectParamService implements Service {
	private static Logger log=Logger.getLogger(InitConnectParamService.class);
	/**
	 * 创建一个新的实例 InitConnectParamService.
	 *
	 */
	public InitConnectParamService() {
		// TODO Auto-generated constructor stub
	}

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
		System.out.println("开始初始化 InitConnectParamService 配置");
		log.info("开始初始化InitConnectParamService 配置");
	    Properties prop = new Properties();   
	    InputStream in = SQLCommonService.class.getClassLoader().getResourceAsStream("/LMSConnectParam.properties");  
	    try {
			prop.load(in);
			String WBS_USER_NAME=prop.getProperty("WBS_USER_NAME");
			String WBS_PASSWORD=prop.getProperty("WBS_PASSWORD");
			if(StringUtils.isNotBlank(WBS_USER_NAME))CommonConstant.WBS_USER_NAME=WBS_USER_NAME;
			if(StringUtils.isNotBlank(WBS_PASSWORD))CommonConstant.WBS_PASSWORD=WBS_PASSWORD;
			
			String WBS_SERVICE_IF11_URL=prop.getProperty("WBS_SERVICE_IF11_URL");
			String WBS_SERVICE_IF12_URL=prop.getProperty("WBS_SERVICE_IF12_URL");
			if(StringUtils.isNotBlank(WBS_SERVICE_IF11_URL))CommonConstant.WBS_SERVICE_IF11_URL=WBS_SERVICE_IF11_URL;
			if(StringUtils.isNotBlank(WBS_SERVICE_IF12_URL))CommonConstant.WBS_SERVICE_IF12_URL=WBS_SERVICE_IF12_URL;
			
			String WBS_SOAP_XML_START=prop.getProperty("WBS_SOAP_XML_START");
			String WBS_SOAP_XML_END=prop.getProperty("WBS_SOAP_XML_END");
			if(StringUtils.isNotBlank(WBS_SOAP_XML_START))CommonConstant.WBS_SOAP_XML_START=WBS_SOAP_XML_START;
			if(StringUtils.isNotBlank(WBS_SOAP_XML_END))CommonConstant.WBS_SOAP_XML_END=WBS_SOAP_XML_END;
			
			String WBS_SOAP_HEAD_XML=prop.getProperty("WBS_SOAP_HEAD_XML");
			String WBS_SOAP_BODY_START=prop.getProperty("WBS_SOAP_BODY_START");
			String WBS_SOAP_BODY_END=prop.getProperty("WBS_SOAP_BODY_END");
			if(StringUtils.isNotBlank(WBS_SOAP_HEAD_XML))CommonConstant.WBS_SOAP_HEAD_XML=WBS_SOAP_HEAD_XML;
			if(StringUtils.isNotBlank(WBS_SOAP_BODY_START))CommonConstant.WBS_SOAP_BODY_START=WBS_SOAP_BODY_START;
			if(StringUtils.isNotBlank(WBS_SOAP_BODY_END))CommonConstant.WBS_SOAP_BODY_END=WBS_SOAP_BODY_END;
			

			System.out.println("初始化InitConnectParamService配置成功。");
			log.info("初始化InitConnectParamService配置成功。");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("初始化InitConnectParamService配置失败。");
			log.error("初始化InitConnectParamService配置失败。");
		} 
		finally{
			System.out.println("初始化InitConnectParamService配置结束。");
			log.info("初始化InitConnectParamService配置结束。");
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
