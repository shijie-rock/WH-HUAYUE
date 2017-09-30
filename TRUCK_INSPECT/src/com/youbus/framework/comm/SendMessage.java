package com.youbus.framework.comm;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import net.sf.ehcache.Cache;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.youbus.framework.util.DBConUtil;
/**
 * 发送短信验证码
 * @copyright gold-tech
 * @author emmy
 * @date  2015年1月8日 下午3:26:09
 */
public class SendMessage {
	private static Logger looger=Logger.getLogger(SendMessage.class);
	public static final String SUCCESS="2";
	public static final String REGISTER="注册验证码：{0}（30分钟内输入有效），请不要把验证码泄露给任何其他人";//注册模板
	public static final String SAFE_CHANGE="更换手机验证码：{0}（30分钟内输入有效），请不要把验证码泄露给任何其他人";//安全中心手机更换模板
	public static final String SAFE_BIND="绑定手机验证码：{0}（30分钟内输入有效），请不要把验证码泄露给任何其他人";//安全中心手机绑定模板
	public static final String PASSWORD="忘记密码验证码：{0}（30分钟内输入有效），请不要把验证码泄露给任何其他人";//忘记密码模板
	public static final String PASS_CHANGE="修改登录密码验证码：{0}（30分钟内输入有效），请不要把验证码泄露给任何其他人";//忘记密码模板
	public static final String COMMON="您的验证码是：{0}（30分钟内输入有效）。请不要把验证码泄露给其他人。";//公共模板
	public static void main(String[] args) {
		//sendMsg("sss", "");
	}
	public static MessageResultPO send(String content,String toPhone){
		MessageResultPO result=new MessageResultPO();
		try {
			Properties prop=new Properties();
			prop.load(SendMessage.class.getResourceAsStream("/Msg.properties"));
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(prop.getProperty("message.url"));
			client.getParams().setContentCharset("UTF-8");
			method.setRequestHeader("ContentType",
					"application/x-www-form-urlencoded;charset=UTF-8");
			//String content = new String("您的验证码是："+toCode+"。请不要把验证码泄露给其他人。");
			NameValuePair[] data = {// 提交短信
					new NameValuePair("account", prop.getProperty("message.account")),
					new NameValuePair("password", prop.getProperty("message.password")), // 密码可以使用明文密码或使用32位MD5加密
					// new NameValuePair("password",
					// util.StringUtil.MD5Encode("密码")),
					new NameValuePair("mobile", toPhone),
					new NameValuePair("content", content), };

			method.setRequestBody(data);
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			// System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			result.setCode(root.elementText("code"));
			result.setMsg(root.elementText("msg"));
			result.setSmsId( root.elementText("smsid"));
			/*
			if (code == "2") {
				System.out.println("短信提交成功");
			}*/

		} catch (Exception e) {
			looger.error(e.getMessage(),e);
		} 
		return result;
	}
	/**
	 * 使用公共模板发送短信
	 * @param toCode
	 * @param toPhone
	 * @return MessageResultPO
	 *//*
	public static MessageResultPO sendMsg(String toCode, String toPhone) {
		String content = new String("您的验证码是："+toCode+"。请不要把验证码泄露给其他人。");
		return send(content,toPhone);
	}*/
	/**
	 * 使用模板类型发送短信
	 * @param templeType 模板类型
	 * @param toCode
	 * @param toPhone
	 * @return MessageResultPO
	 */
	public static MessageResultPO sendMsg(String templeType,String toCode, String toPhone) {
		String content = MessageFormat.format(templeType,toCode);
		return send(content,toPhone);
	}
	
	/**
	 * 检验 手机校验码是否正确
	 * 方   法  名:checkCode
	 * 方法描述:
	 * 参         数:@param checkType
	 * 参         数:@param mobileCode
	 * 参         数:@param code
	 * 参         数:@return
	 * 返   回  值:AgentMobileCheckResultBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkCode(String checkType,String mobileCode,String code){
		if(!DBConUtil.stringNotNULL(code))return new AgentMobileCheckResultBean(0,"未输入验证码");//未输入验证码
		Cache codeCache=TruckInsNativeCacheService.getCache("AGENT_MOBILE_CHECK_CODE_CACHE");
		String key=checkType+mobileCode;
		if(!codeCache.isKeyInCache(key))return new AgentMobileCheckResultBean(2,"未发送验证码");//未发送验证码
		net.sf.ehcache.Element e=codeCache.get(key);
		AgentMobileCheckCodeBean bean=(AgentMobileCheckCodeBean) e.getValue();
		Date now=new Date(System.currentTimeMillis());
		if(now.getTime()>bean.getExpireTime().getTime()) return new AgentMobileCheckResultBean(3,"验证码已过期失效");
		if(!code.equals(bean.getCheckCode()))return new AgentMobileCheckResultBean(4,"验证码输入错误");//。
	//	if(!"0".equals(bean.getCheckStatus()))return new AgentMobileCheckResultBean(5,"验证码已经被使用，失效");//取消仅能使用一次
		
		//有效
		bean.setCheckStatus("1");
		bean.setCheckTime(now);
		codeCache.put(new net.sf.ehcache.Element(key, bean));
		
		return new AgentMobileCheckResultBean(1,"验证成功");
	}
	
	/**
	 * 验证 绑定手机时，输入验证码是否正确
	 * 方   法  名:checkBindCode
	 * 方法描述:
	 * 参         数:@param mobileCode
	 * 参         数:@param code
	 * 参         数:@return
	 * 返   回  值:AgentMobileCheckResultBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkBindCode(String mobileCode,String code){
		return checkCode(YBCommonContant.MOBILE_CHECK_CODE_BIND, mobileCode, code);
	}
	
	/**
	 * 验证 修改密码时，输入验证码是否正确
	 * 方   法  名:checkModiyCode
	 * 方法描述:
	 * 参         数:@param mobileCode
	 * 参         数:@param code
	 * 参         数:@return
	 * 返   回  值:AgentMobileCheckResultBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkModiyCode(String mobileCode,String code){
		return checkCode(YBCommonContant.MOBILE_CHECK_CODE_MODIFY, mobileCode, code);
	}
}
