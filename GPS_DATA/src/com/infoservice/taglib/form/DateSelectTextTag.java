/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      DateSelectTextTag.java
 * @Create on:      2006-4-29   
 * @Author :        HuangBo
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			Editor				ChangeReasons
 * 2006-4-29    HuangBo              Create
 */
package com.infoservice.taglib.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.infoservice.taglib.ValueUtil;

/**
 * @author HuangBo
 * @version 1.1
 * @Description : 用于输出日期选择框
 *
 */
public class DateSelectTextTag extends BaseInputTag {
	private String nullable;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {
			setDataSourceName();
			StringBuffer text = new StringBuffer();
			text.append("<input type='text' size='10'");
			addInput(text);
			if (getValue() != null) {
				String value = ValueUtil.parseValue((HttpServletRequest) pageContext.getRequest(), getValue());
				if (value != null) {
					text.append(" value='");
					text.append(value);
					text.append("' ");
				}
			}
			text.append(" readonly>");
			text.append("<input type='button' value='...' onclick=\"show_calendar('"+getElementId()+"');\">");
			println(text.toString());
			outJsFunction();			
		} catch (Exception e) {
			throw new JspException(e);
		}

		return super.doEndTag();
	}

	/**
	 * @return
	 */
	public String getNullable() {
		return nullable;
	}

	/**
	 * @param string
	 */
	public void setNullable(String string) {
		nullable = string;
	}
	private void outJsFunction() throws JspException {
		String formName = getFormName();
		String function = "_" + getElementId() + "_check";
		println("<script language=\"JavaScript\" src=\"/"+pageContext.getServletContext().getServletContextName()+"/js/date-picker_ex.js\"></script>");
		println("<SCRIPT LANGUAGE=\"JavaScript\">");
		println("function " + function + "(){");
		println("	var str = document.getElementById('" + getElementId() + "').value;");
		if (nullable != null && nullable.trim().equalsIgnoreCase("false")) {
			println("	if(str == ''){");
			println("		alert('not null!');");
			println("		document.getElementById('" + getElementId() + "').focus();");
			println("		return false;");
			println("	}");
		}
		println("	return true;");
		println("}");
		println("</SCRIPT>");
		registerJSFunction(function);
	}
}
