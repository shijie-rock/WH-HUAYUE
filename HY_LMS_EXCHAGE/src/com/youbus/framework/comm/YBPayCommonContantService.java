package com.youbus.framework.comm;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.youbuspay.common.YBPayCommonConstant;
import com.youbus.youbuspay.util.PayPropertiesUtil;

/**
 * 支付常量初始化Service
 * @author ace.Huang
 *
 */
public class YBPayCommonContantService implements Service {
	
	private static Logger log = LogManager.getLogger(YBPayCommonContantService.class);
	
	/**
	 * 创建一个新的实例 YBPayCommonContantService
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
	 * 实行初始化方法
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(Map arg0) throws FrameException {
		
		try {
			log.info("***************************初始化支付用常量配置开始***************************");
			
			// 微信支付商户开通后 微信会提供appid和appsecret和商户号partner
			YBPayCommonConstant.APPID = PayPropertiesUtil.getValueByKey("APPID");
			YBPayCommonConstant.APPSECRET = PayPropertiesUtil.getValueByKey("APPSECRET");
			YBPayCommonConstant.PARTNER = PayPropertiesUtil.getValueByKey("PARTNER");
			
			// 商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
			YBPayCommonConstant.PARTNERKEY = PayPropertiesUtil.getValueByKey("PARTNERKEY");
			// 客户端应用请求用Key
			YBPayCommonConstant.APP_KEY = PayPropertiesUtil.getValueByKey("APP_KEY");
			
			// 支付网关URL
			YBPayCommonConstant.PAY_CENTER_URL = PayPropertiesUtil.getValueByKey("PAY_CENTER_URL");
			// 获取OpenId用Servlet
			YBPayCommonConstant.GET_OPENID_SERVLET = PayPropertiesUtil.getValueByKey("GET_OPENID_SERVLET");
			// 申请支付Servlet
			YBPayCommonConstant.APPLY_PAY_SERVLET = PayPropertiesUtil.getValueByKey("APPLY_PAY_SERVLET");
			// 微信支付成功后通知地址 必须要求80端口并且地址不能带参数
			YBPayCommonConstant.NOTIFYURL = PayPropertiesUtil.getValueByKey("NOTIFYURL");
			// 支付网关端支付页面
			YBPayCommonConstant.PAY_JSP = PayPropertiesUtil.getValueByKey("PAY_JSP");
			// 获取OpenId过滤器过滤Jsp
			YBPayCommonConstant.OPENID_FILTER_JSP = PayPropertiesUtil.getValueByKey("OPENID_FILTER_JSP").split(",");
			
			// 应用客户端验证发送结果用
			YBPayCommonConstant.SUCCESS_FLG = PayPropertiesUtil.getValueByKey("SUCCESS_FLG");
			YBPayCommonConstant.FAIL_FLG = PayPropertiesUtil.getValueByKey("FAIL_FLG");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("***************************初始化支付用常量配置失败***************************");
		} finally {
			log.info("***************************初始化支付用常量配置结束***************************");
		}

	}

}
