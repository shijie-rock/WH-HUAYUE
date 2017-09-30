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

import java.util.Enumeration;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * @Description :用于codeList支持的实现
 *
 */
public class PrePageTag extends BodyTagSupport {
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			Enumeration parameterNames = pageContext.getRequest().getParameterNames();
			while(parameterNames.hasMoreElements()) {
				String parameterName = (String)parameterNames.nextElement();
				String request[] = pageContext.getRequest().getParameterValues(parameterName);
				

				if (request != null) {
					for (int i = 0; i < request.length; i++) {
						if (request[i] != null) {
							StringBuffer hidden = new StringBuffer();
					
							hidden.append("<input type='hidden' name='");
							hidden.append(parameterName);
							hidden.append("' value='");
							hidden.append(request[i]);
							hidden.append("' >");
					
							System.out.println(hidden);
							pageContext.getOut().println(hidden.toString());
						}
					}
				}
			}

		} catch (Exception e) {
			throw new JspException(e);

		}
		return super.doEndTag();
	}


}
