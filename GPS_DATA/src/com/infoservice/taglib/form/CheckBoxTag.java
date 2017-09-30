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

import javax.servlet.jsp.JspException;


/**
 * @Description :用于codeList支持的实现
 *
 */
public class CheckBoxTag extends BaseInputTag {

	private String disabled;
	private String checked;
	private String onchange;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			setDataSourceName();
			StringBuffer checkBox = new StringBuffer();
			checkBox.append("<input type='checkBox' ");
			addInput(checkBox);
			if (onchange != null) {
				checkBox.append("onchange='");
				checkBox.append(onchange.trim());
				checkBox.append("' ");			
			}
			if (disabled != null && (disabled.trim().equalsIgnoreCase("true") || disabled.trim().equalsIgnoreCase("false"))) {
				checkBox.append("disabled='");
				checkBox.append(disabled.trim());
				checkBox.append("' ");			
			}
			int check = 0;
			if (getRewriteable() != null && getRewriteable().trim().equalsIgnoreCase("true")) {
				String request = pageContext.getRequest().getParameter(getName());
				if (request != null && request.trim().length() > 0) {
					if (getValue().trim().equals(request)) {
						checkBox.append(" checked ");
						check = 1;			
					}
				}
			}
			if (check == 0) {
				if (checked != null && (checked.trim().equalsIgnoreCase("true") || checked.trim().equalsIgnoreCase("false"))) {
					checkBox.append("checked='");
					checkBox.append(checked.trim());
					checkBox.append("' ");			
				}	
			}
			checkBox.append("value='");
			checkBox.append(getValue());
			checkBox.append("' ");
			checkBox.append(">");
			
			println(checkBox.toString());

		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	/**
	 * @param string
	 */
	public void setDisabled(String string) {
		disabled = string;
	}

	/**
	 * @param string
	 */
	public void setChecked(String string) {
		checked = string;
	}

	/**
	 * @param string
	 */
	public void setOnchange(String string) {
		onchange = string;
	}

}
