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
 * 2006-4-25            HuangBo            增加Value的选项
 * 2007-5-23			WangPeiwen		   nullable提示修改 比如 nullable="配件代码"
 */
package com.infoservice.taglib.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.infoservice.taglib.ValueUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class TextTag extends BaseInputTag {

	private String rewrited;
	private String size;
	private String maxlength;
	private String readonly;
	private String nullable;
	private String dataType;
	private String outlength;//For length checking
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			setDataSourceName();
			StringBuffer text = new StringBuffer();
			text.append("<input type='text' ");
			addInput(text);
			try {
				if(size!=null&&size.trim().length()>0){
					text.append(" size='" + (new Integer(size.trim())).intValue() + "' ");
				}
			} catch (Exception e) {
				throw new JspException("Size 设置错误!");
			}
			try {
				if(maxlength!=null&&maxlength.trim().length()>0){
					text.append(" maxlength='" + (new Integer(maxlength.trim())).intValue() + "' ");
				}
			} catch (Exception e) {
				throw new JspException("maxlength 设置错误!");
			}
			if (readonly != null && (readonly.trim().equalsIgnoreCase("true") || readonly.trim().equalsIgnoreCase("false"))) {
				text.append(" readonly='");
				text.append(readonly.trim());
				text.append("' ");
			}
			if (getRewriteable() != null && getRewriteable().trim().equalsIgnoreCase("true")) {
				String request = pageContext.getRequest().getParameter(getName());
				if (request != null && request.trim().length() > 0) {
					setValue(request);
				}
			}
			if (getValue() != null) {
				String value = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getValue());
				if(value!=null){
					text.append(" value='");
					text.append(value);
					text.append("' ");
				}
			}
			text.append(">");

			println(text.toString());
			outJsFunction();

		} catch (JspException e) {
			throw e;
		}
		catch(Exception e){
			throw new  JspException(e);
		}
		return super.doEndTag();
	}

	private void outJsFunction() throws JspException {
		String formName = getFormName();
		String function = "_" + getElementId() + "_check";
		println("<SCRIPT LANGUAGE=\"JavaScript\">");
		println("function " + function + "(){");
		println("	var str = document.getElementById('" + getElementId() + "').value;");
		//if (nullable != null && nullable.trim().equalsIgnoreCase("false")) {
		if (nullable != null && !nullable.trim().equalsIgnoreCase("true")) {
			String msg = "";
			if(nullable.trim().equalsIgnoreCase("false")){
				msg="";
			}else{
				msg=nullable.trim() + "不能为空";
			}
			println("	if(str == ''){");
			println("		alert('"+msg+"');");
			println("		document.getElementById('" + getElementId() + "').focus();");
			println("		return false;");
			println("	}");
		}
		if (dataType != null) {
			if (dataType.trim().equalsIgnoreCase("int")) {
				println("	if(!iscInteger(str)){");
				println("		alert('必须为数字类型');");
				println("		document.getElementById('" + getElementId() + "').focus();");
				println("		return false;");
				println("	}");
			} else if (dataType.trim().equalsIgnoreCase("double")) {
				println("	if(!isNumber(str)){");
				println("		alert('必须为小数类型!');");
				println("		document.getElementById('" + getElementId() + "').focus();");
				println("		return false;");
				println("	}");
			}
		}
		try {
			if (outlength != null && (new Integer(outlength.trim())).intValue() > 0) {				
				println("	var outlen = 0;");
				println("	for (var i=0;i<str.length;i++){");
				println("		if (str.charCodeAt(i)>255) outlen+=3; else outlen++;");
				println("	}");
				println("	if (outlen >" + outlength + ") {");
				println("		alert('您输入的字符串太长，为'+ outlen +'字节,字节数应该不能超过"+ this.getOutlength() + "!');");
				println("		document.getElementById('" + getElementId() + "').focus();");
				println("		return false;");
				println("	}");
			}
		} catch (Exception e) {
			System.err.println("outlength input error! outlength=" + outlength);
		}		

		println("	return true;");
		println("}");
		println("</SCRIPT>");
		registerJSFunction(function);
	}


	/**
	 * @param string
	 */
	public void setDataType(String string) {
		dataType = string;
	}

	/**
	 * @param string
	 */
	public void setMaxlength(String string) {
		maxlength = string;
	}

	/**
	 * @param string
	 */
	public void setNullable(String string) {
		nullable = string;
	}

	/**
	 * @param string
	 */
	public void setReadonly(String string) {
		readonly = string;
	}

	/**
	 * @param string
	 */
	public void setRewrited(String string) {
		rewrited = string;
	}

	/**
	 * @param string
	 */
	public void setSize(String string) {
		size = string;
	}

	public String getOutlength() {
		return outlength;
	}

	public void setOutlength(String outlength) {
		this.outlength = outlength;
	}

}
