/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      BaseOptionTag.java
 * @Create on:      2006-4-28   
 * @Author :        HuangBo
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			Editor				ChangeReasons
 * 2006-4-28    HuangBo              Create
 */
package com.infoservice.taglib.form;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;

/**
 * @author HuangBo
 * @version 1.1
 * @Description :
 *
 */
public class BaseOptionTag extends BodyTagSupport {

	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	protected void outOption(String value,String text){
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<OPTION VALUE='");
			if(value!=null){
				sb.append(value);
			}
			sb.append("' ");
			SelectTag tag = (SelectTag) findAncestorWithClass(this, Class.forName(TagUtil.SELECT_TAG_CLASS));
			if(tag.isSelect(value)){
				sb.append("SELECTED");
			}
			sb.append(">");
			if(text!=null){
				sb.append(text);
			}
			sb.append("</OPTION>");
			pageContext.getOut().println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	

}
