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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.infoservice.taglib.ValueUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class OptionTag extends BaseOptionTag {
	
	private String text;
	private String value;
//	private String selected;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try{
			
			if (value!= null) {
				String valueStr = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getValue());
				String textStr = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getText());
				if(valueStr!=null){
					outOption(valueStr,textStr);
				}
			}
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}


	/**
	 * @return
	 */
//	public String getSelected() {
//		return selected;
//	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param string
	 */
//	public void setSelected(String string) {
//		selected = string;
//	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

}
