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
 * ���Ͷ�����֤��
 * @copyright gold-tech
 * @author emmy
 * @date  2015��1��8�� ����3:26:09
 */
public class SendMessage {
	private static Logger looger=Logger.getLogger(SendMessage.class);
	public static final String SUCCESS="2";
	public static final String REGISTER="ע����֤�룺{0}��30������������Ч�����벻Ҫ����֤��й¶���κ�������";//ע��ģ��
	public static final String SAFE_CHANGE="�����ֻ���֤�룺{0}��30������������Ч�����벻Ҫ����֤��й¶���κ�������";//��ȫ�����ֻ�����ģ��
	public static final String SAFE_BIND="���ֻ���֤�룺{0}��30������������Ч�����벻Ҫ����֤��й¶���κ�������";//��ȫ�����ֻ���ģ��
	public static final String PASSWORD="����������֤�룺{0}��30������������Ч�����벻Ҫ����֤��й¶���κ�������";//��������ģ��
	public static final String PASS_CHANGE="�޸ĵ�¼������֤�룺{0}��30������������Ч�����벻Ҫ����֤��й¶���κ�������";//��������ģ��
	public static final String COMMON="������֤���ǣ�{0}��30������������Ч�����벻Ҫ����֤��й¶�������ˡ�";//����ģ��
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
			//String content = new String("������֤���ǣ�"+toCode+"���벻Ҫ����֤��й¶�������ˡ�");
			NameValuePair[] data = {// �ύ����
					new NameValuePair("account", prop.getProperty("message.account")),
					new NameValuePair("password", prop.getProperty("message.password")), // �������ʹ�����������ʹ��32λMD5����
					// new NameValuePair("password",
					// util.StringUtil.MD5Encode("����")),
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
				System.out.println("�����ύ�ɹ�");
			}*/

		} catch (Exception e) {
			looger.error(e.getMessage(),e);
		} 
		return result;
	}
	/**
	 * ʹ�ù���ģ�巢�Ͷ���
	 * @param toCode
	 * @param toPhone
	 * @return MessageResultPO
	 *//*
	public static MessageResultPO sendMsg(String toCode, String toPhone) {
		String content = new String("������֤���ǣ�"+toCode+"���벻Ҫ����֤��й¶�������ˡ�");
		return send(content,toPhone);
	}*/
	/**
	 * ʹ��ģ�����ͷ��Ͷ���
	 * @param templeType ģ������
	 * @param toCode
	 * @param toPhone
	 * @return MessageResultPO
	 */
	public static MessageResultPO sendMsg(String templeType,String toCode, String toPhone) {
		String content = MessageFormat.format(templeType,toCode);
		return send(content,toPhone);
	}
	
	/**
	 * ���� �ֻ�У�����Ƿ���ȷ
	 * ��   ��  ��:checkCode
	 * ��������:
	 * ��         ��:@param checkType
	 * ��         ��:@param mobileCode
	 * ��         ��:@param code
	 * ��         ��:@return
	 * ��   ��  ֵ:AgentMobileCheckResultBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkCode(String checkType,String mobileCode,String code){
		if(!DBConUtil.stringNotNULL(code))return new AgentMobileCheckResultBean(0,"δ������֤��");//δ������֤��
		Cache codeCache=TruckInsNativeCacheService.getCache("AGENT_MOBILE_CHECK_CODE_CACHE");
		String key=checkType+mobileCode;
		if(!codeCache.isKeyInCache(key))return new AgentMobileCheckResultBean(2,"δ������֤��");//δ������֤��
		net.sf.ehcache.Element e=codeCache.get(key);
		AgentMobileCheckCodeBean bean=(AgentMobileCheckCodeBean) e.getValue();
		Date now=new Date(System.currentTimeMillis());
		if(now.getTime()>bean.getExpireTime().getTime()) return new AgentMobileCheckResultBean(3,"��֤���ѹ���ʧЧ");
		if(!code.equals(bean.getCheckCode()))return new AgentMobileCheckResultBean(4,"��֤���������");//��
	//	if(!"0".equals(bean.getCheckStatus()))return new AgentMobileCheckResultBean(5,"��֤���Ѿ���ʹ�ã�ʧЧ");//ȡ������ʹ��һ��
		
		//��Ч
		bean.setCheckStatus("1");
		bean.setCheckTime(now);
		codeCache.put(new net.sf.ehcache.Element(key, bean));
		
		return new AgentMobileCheckResultBean(1,"��֤�ɹ�");
	}
	
	/**
	 * ��֤ ���ֻ�ʱ��������֤���Ƿ���ȷ
	 * ��   ��  ��:checkBindCode
	 * ��������:
	 * ��         ��:@param mobileCode
	 * ��         ��:@param code
	 * ��         ��:@return
	 * ��   ��  ֵ:AgentMobileCheckResultBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkBindCode(String mobileCode,String code){
		return checkCode(YBCommonContant.MOBILE_CHECK_CODE_BIND, mobileCode, code);
	}
	
	/**
	 * ��֤ �޸�����ʱ��������֤���Ƿ���ȷ
	 * ��   ��  ��:checkModiyCode
	 * ��������:
	 * ��         ��:@param mobileCode
	 * ��         ��:@param code
	 * ��         ��:@return
	 * ��   ��  ֵ:AgentMobileCheckResultBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static AgentMobileCheckResultBean checkModiyCode(String mobileCode,String code){
		return checkCode(YBCommonContant.MOBILE_CHECK_CODE_MODIFY, mobileCode, code);
	}
}
