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
public class HiddenTag extends BaseInputTag {
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			setDataSourceName();
			StringBuffer hidden = new StringBuffer();
			hidden.append("<input type='hidden' ");
			addInput(hidden);
			if (getRewriteable() != null && getRewriteable().trim().equalsIgnoreCase("true")) {
				String request = pageContext.getRequest().getParameter(getName());
				if (request != null && request.trim().length() > 0) {
					setValue(request);
				}
			}
			if (getValue() != null) {
				String value = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getValue());
				if(value!=null){
					hidden.append(" value='");
					hidden.append(value);
					hidden.append("' ");
				}
			}
			hidden.append(">");
			
			println(hidden.toString());

		} catch (Exception e) {

				throw new JspException(e);
				

		}
		return super.doEndTag();
	}


}
