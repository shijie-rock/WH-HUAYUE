package com.youbus.framework.comm;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.youbuspay.common.YBPayCommonConstant;
import com.youbus.youbuspay.util.PayPropertiesUtil;

/**
 * ֧��������ʼ��Service
 * @author ace.Huang
 *
 */
public class YBPayCommonContantService implements Service {
	
	private static Logger log = LogManager.getLogger(YBPayCommonContantService.class);
	
	/**
	 * ����һ���µ�ʵ�� YBPayCommonContantService
	 */
	public YBPayCommonContantService() {
	}

	@Override
	public void destroyService() throws FrameException {

	}

	@Override
	public String getName() {
		return null;
	}

	/**
	 * ʵ�г�ʼ������
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(Map arg0) throws FrameException {
		
		try {
			log.info("***************************��ʼ��֧���ó������ÿ�ʼ***************************");
			
			// ΢��֧���̻���ͨ�� ΢�Ż��ṩappid��appsecret���̻���partner
			YBPayCommonConstant.APPID = PayPropertiesUtil.getValueByKey("APPID");
			YBPayCommonConstant.APPSECRET = PayPropertiesUtil.getValueByKey("APPSECRET");
			YBPayCommonConstant.PARTNER = PayPropertiesUtil.getValueByKey("PARTNER");
			
			// �̻���̨���õ�һ��32λ��key,΢���̻�ƽ̨-�˻�����-��ȫ����-api��ȫ
			YBPayCommonConstant.PARTNERKEY = PayPropertiesUtil.getValueByKey("PARTNERKEY");
			// �ͻ���Ӧ��������Key
			YBPayCommonConstant.APP_KEY = PayPropertiesUtil.getValueByKey("APP_KEY");
			
			// ֧������URL
			YBPayCommonConstant.PAY_CENTER_URL = PayPropertiesUtil.getValueByKey("PAY_CENTER_URL");
			// ��ȡOpenId��Servlet
			YBPayCommonConstant.GET_OPENID_SERVLET = PayPropertiesUtil.getValueByKey("GET_OPENID_SERVLET");
			// ����֧��Servlet
			YBPayCommonConstant.APPLY_PAY_SERVLET = PayPropertiesUtil.getValueByKey("APPLY_PAY_SERVLET");
			// ΢��֧���ɹ���֪ͨ��ַ ����Ҫ��80�˿ڲ��ҵ�ַ���ܴ�����
			YBPayCommonConstant.NOTIFYURL = PayPropertiesUtil.getValueByKey("NOTIFYURL");
			// ֧�����ض�֧��ҳ��
			YBPayCommonConstant.PAY_JSP = PayPropertiesUtil.getValueByKey("PAY_JSP");
			// ��ȡOpenId����������Jsp
			YBPayCommonConstant.OPENID_FILTER_JSP = PayPropertiesUtil.getValueByKey("OPENID_FILTER_JSP").split(",");
			
			// Ӧ�ÿͻ�����֤���ͽ����
			YBPayCommonConstant.SUCCESS_FLG = PayPropertiesUtil.getValueByKey("SUCCESS_FLG");
			YBPayCommonConstant.FAIL_FLG = PayPropertiesUtil.getValueByKey("FAIL_FLG");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("***************************��ʼ��֧���ó�������ʧ��***************************");
		} finally {
			log.info("***************************��ʼ��֧���ó������ý���***************************");
		}

	}

}
