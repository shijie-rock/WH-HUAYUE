package com.infoservice.taglib;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.infoservice.framework.util.JspHelper;

public class ValueUtil {

	private static final String BEAN_PRE = "$";
	private static final String INPUT_PRE = "#";
	private static final String CURRENT_PRE = "%";
	//	#��ͷ��ʾ��һ���ύ�Ŀؼ��Ŀؼ�ȡֵ
	//	$��ͷ��ʾ��ActionContextData��ĳһ������ȡֵ
	//	%��ʾ��ʾ��ǰ���õ�ֵ
	public static String parseValue(HttpServletRequest request, String valueStr) throws Exception {
		if (valueStr == null || valueStr.length() < 1) {
			return null;
		}
		//��һ��Ϊ��־λ		
		String str = valueStr.substring(0, 1);
		String valueName = valueStr.substring(1, valueStr.length());
		if (str.equals(BEAN_PRE)) {
			return findBeanValue(request, valueName);
		} else if (str.equals(INPUT_PRE)) {
			return findInputValue(request, valueName);
		} else if (str.equals(CURRENT_PRE)) {
			return valueName;
		} else {
			return valueStr;
		}
	}
	/**
	 * �������ƴ�ActionContextData��ȡ��һ��Bean����Bean��һ��field
	 * @param request
	 * @param valueStr
	 * @return
	 */
	private static String findBeanValue(HttpServletRequest request, String valueStr) throws Exception {
		int index = valueStr.indexOf(".");
		String beanName = null;
		String fieldName = null;
		//�õ�Bean�����ƺ�Field����		
		if (index > 0) {
			beanName = valueStr.substring(0, index);
			if (index < valueStr.length()) {
				fieldName = valueStr.substring(index + 1, valueStr.length());
			}
		} else {
			beanName = valueStr;
		};
		JspHelper jspHelper = new JspHelper(request);
		Object obj = jspHelper.getValue(beanName);
		if (obj == null) {
			return null;
		}
		if (fieldName == null || fieldName.length() == 0) {
			return formatObject(obj,null,null);
		} else {
			Object fieldObj = BeanUtil.getFiled(obj, fieldName);
			if (fieldObj != null) {
				return formatObject(fieldObj,null,null);
			} else {
				return null;
			}
		}
	}

	private static String findInputValue(HttpServletRequest request, String valueStr) {

		return request.getParameter(valueStr);
	}
	public final static void main(String[] str) {

	}
	
	private static String  formatObject(Object obj,String dataType,String dateFormat){
		if(obj==null){
			return null;
		}
		if(obj instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(obj);
		}
		else{
			return obj.toString();
		}
	}
}
