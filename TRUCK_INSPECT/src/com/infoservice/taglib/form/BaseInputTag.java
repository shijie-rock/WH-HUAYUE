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

import java.util.Random;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class BaseInputTag extends BodyTagSupport {

	private String dataSource;
	private String name;
	private String rewriteable;
	private String value;
	private String cssStyle;
	private int inputId = 0;
	private String id;
	
	private static Random rnd=new Random(System.currentTimeMillis());

	/**
	 * 
	 */
	public BaseInputTag() {
		super();
		inputId = rnd.nextInt(1000000)+1000000;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		return super.doEndTag();
	}

	/**
	 * @return
	 */
	protected StringBuffer addInput(StringBuffer input) throws JspException{
		input.append(" name='");
		input.append(name);
		input.append("' ");

		input.append(" id='");
		if(id==null||id.trim().length()<=0){
			input.append(inputId);
		}
		else{
			if(id.trim().length()!=8){
				throw new JspException("手工设置的ID必须是8位!");	
			}
			else{
				input.append(id);
			}
		}
		input.append("' ");

		if (cssStyle != null && cssStyle.trim().length() > 0) {
			input.append(" class='");
			input.append(cssStyle);
			input.append("' ");
		}
		return input;
	}

	protected void registerJSFunction(String functionName) throws JspException {

		try {
			FormTag formTag = (FormTag) findAncestorWithClass(this, Class.forName(TagUtil.FORM_TAG_CLASS));
			formTag.registerJSFunction(functionName);
		} catch (ClassNotFoundException cnfe) {
			throw new JspException("在ButtonTag外层没有嵌套FormTag!");
		}

	}

	protected String getFormName() throws JspException {
		String formName = null;
		try {
			FormTag formTag = (FormTag) findAncestorWithClass(this, Class.forName(TagUtil.FORM_TAG_CLASS));
			formName = formTag.getName();
		} catch (ClassNotFoundException cnfe) {
			throw new JspException("在ButtonTag外层没有嵌套FormTag!");
		}
		return formName;
	}

	protected void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}

	}

	protected void setDataSourceName() {
		if (dataSource != null && dataSource.trim().length() > 0) {
			name = dataSource + "." + name;
		}
		if (value == null) {
			value = "";
		}
	}
	public String getElementId() {
		if(id!=null){
			return id.trim();
		}
		return inputId+"";
	}

	/**
	 * @return
	 */
	public String getCssStyle() {
		return cssStyle;
	}

	/**
	 * @return
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getRewriteable() {
		return rewriteable;
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
	public void setCssStyle(String string) {
		cssStyle = string;
	}

	/**
	 * @param string
	 */
	public void setDataSource(String string) {
		dataSource = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRewriteable(String string) {
		rewriteable = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	/**
	 * @return
	 */


	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

}
