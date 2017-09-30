/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      BaseInputTag.java
 * @Create on:      2006-3-27   
 * @Author :        WangBo
 * 
 * @ChangeList
 * ---------------------------------------------------
 * 2006-3-27			WangBo				Create
 *
 */
package com.infoservice.taglib.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.infoservice.framework.util.JspHelper;
import com.infoservice.taglib.BeanUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class OptionsTag extends BaseOptionTag {

	private String valueField;
	private String textField;
	private String listObject;
	
	
	

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		
		try{
			JspHelper jspHelper = new JspHelper((HttpServletRequest)pageContext.getRequest());
			Object obj = jspHelper.getValue(listObject);
			if(obj==null){
				return super.doEndTag();			
			}
			//by kevin
			if ( obj instanceof Object[] ){
				List al = new ArrayList();
				for ( Object tmp : (Object[])obj ){
					al.add(tmp);
				}
				obj = al;
			}	
			
			if(obj instanceof List){
				List al = (List) obj;
				for(int i=0;i<al.size();i++){
					Object valueObj = al.get(i);
					Object textObj = BeanUtil.getFiled(valueObj,textField);
					Object valueFiedObj = BeanUtil.getFiled(valueObj,valueField);
					String text = null;
					String value = null;
					if(textObj!=null){
						text = textObj.toString();
					}
					if(valueFiedObj!=null){
						value = valueFiedObj.toString();
					}
					outOption(value,text);
				}
			}
			else {
				throw new Exception("listObject ["+listObject+"] Is Not List");
			}

		}
		catch(Exception e){
			throw new JspException(e);
		}
		

		return super.doEndTag();
	}


	/**
	 * @return
	 */
	public String getTextField() {
		return textField;
	}

	/**
	 * @return
	 */
	public String getValueField() {
		return valueField;
	}

	/**
	 * @param string
	 */
	public void setTextField(String string) {
		textField = string;
	}

	/**
	 * @param string
	 */
	public void setValueField(String string) {
		valueField = string;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */

	/**
	 * @return
	 */
	public String getListObject() {
		return listObject;
	}

	/**
	 * @param string
	 */
	public void setListObject(String string) {
		listObject = string;
	}

}
