/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      BaseLabelTag.java
 * @Create on:      2006-3-27   
 * @Author :        Administrator
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			weimei				ChangeReasons
 *
 */
package com.infoservice.taglib.display;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BaseColumnTag extends BodyTagSupport {
	
	protected String field;
	protected String nullvalue;

	
	/* (non-Javadoc)
		 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
		 */
		public int doEndTag() throws JspException {
			
			return super.doEndTag();
		}
		
		
	
	protected void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}
	}
	
	/**
	 * @param string
	 */
	public void setField(String string) {
		field = string;
	}

	/**
	 * @return
	 */
	public String getNullvalue() {
		return nullvalue;
	}

	/**
	 * @param string
	 */
	public void setNullvalue(String string) {
		nullvalue = string;
	}

}