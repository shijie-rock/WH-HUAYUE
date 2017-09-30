/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:InitConnectParamService.java
 * �� ������:2016��8��30��-����11:33:04
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

/**��ʼ��lmsͨѶ����
 * ������:InitConnectParamService
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��8��30�� ����11:33:05
 * �޸ı�ע:
 * @version 1.0.0
 */
public class InitConnectParamService implements Service {
	private static Logger log=Logger.getLogger(InitConnectParamService.class);
	/**
	 * ����һ���µ�ʵ�� InitConnectParamService.
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
		System.out.println("��ʼ��ʼ�� InitConnectParamService ����");
		log.info("��ʼ��ʼ��InitConnectParamService ����");
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
			

			System.out.println("��ʼ��InitConnectParamService���óɹ���");
			log.info("��ʼ��InitConnectParamService���óɹ���");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ʼ��InitConnectParamService����ʧ�ܡ�");
			log.error("��ʼ��InitConnectParamService����ʧ�ܡ�");
		} 
		finally{
			System.out.println("��ʼ��InitConnectParamService���ý�����");
			log.info("��ʼ��InitConnectParamService���ý�����");
		}
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
