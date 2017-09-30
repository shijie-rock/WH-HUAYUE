/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      SystemStatusFieldTag.java
 * @Create on:      2006-7-4   
 * @Author :        HuangBo
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			Editor				ChangeReasons
 * 2006-7-4    HuangBo              Create
 */
package com.infoservice.taglib.display;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.com.goldtech.util.web.SystemStatusLabelService;
import com.infoservice.framework.Framework;
import com.infoservice.framework.util.JspHelper;
import com.infoservice.taglib.BeanUtil;

/**
 * @author HuangBo
 * @version 1.1
 * @Description :
 *
 */
public class SystemStatusFieldTag extends BodyTagSupport {
	private String field;
	private String dataSource;
	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		JspHelper jspHelper = new JspHelper((HttpServletRequest) pageContext.getRequest());
		String dataSource = getDataSource();
		Object obj = jspHelper.getValue(dataSource);
		if (obj == null) {

		} else {
			if (field == null) {
				print(obj.toString());
			} else {
				try {
					Object value = BeanUtil.getFiled(obj, field);
					if (value != null) {
						SystemStatusLabelService service = (SystemStatusLabelService) Framework.getServiceRepository().getService("CommonCodeService");
		
						String codeValue = service.getCodeValueByCode(value.toString());
						if(codeValue!=null){
							print(codeValue);
						}
					}
				} catch (Exception e) {
					throw new JspException("读取出现错误", e);
				}
			}

		}

		return super.doEndTag();
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		return super.doStartTag();
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
	public String getField() {
		return field;
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
	public void setField(String string) {
		field = string;
	}
	private void print(String str) {
		try {
			pageContext.getOut().print(str);
		} catch (Exception e) {

		}

	}

}
