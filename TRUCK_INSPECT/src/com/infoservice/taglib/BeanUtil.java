
package com.infoservice.taglib;

import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;

import com.infoservice.po.DynaBean;


public class BeanUtil {
	
	public final static Object getFiled(Object obj ,String fieldName) throws JspException{

		if (fieldName == null || fieldName.trim().length() == 0) {
			throw new JspException("Field 不存在");
		}
		
		//add by kevin 
		if ( obj instanceof DynaBean ){
			if ( ((DynaBean)obj).containsKey(fieldName.toUpperCase())){
				return ((DynaBean)obj).get(fieldName.toUpperCase());
			}else{
				throw new JspException("Field [" + fieldName + "]不存在");
			}			
		}
		
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
		try {
			Method field = obj.getClass().getMethod(methodName, null);
			Object value = field.invoke(obj, null);
			return value;
		} catch (Exception e) {
			throw new JspException("Field [" + fieldName + "]不存在");
		}

	}
}
