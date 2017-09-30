/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      SystemStatusColumnTag.java
 * @Create on:      2006-3-31   
 * @Author :        weimei
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			weimei				Create
 *
 */
package com.infoservice.taglib.display;

import javax.servlet.jsp.JspException;

import cn.com.goldtech.util.web.SystemStatusLabelService;
import com.infoservice.framework.Framework;
import com.infoservice.taglib.TagUtil;

public class SystemStatusColumnTag extends BaseColumnTag {
	
	private Object value;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {
			TableTag tag = (TableTag) findAncestorWithClass(this, Class.forName(TagUtil.TABLE_TAG_CLASS));
			value = tag.findFieldValueByName(field).toString();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		if(value==null){
			return super.doEndTag();
		}

		SystemStatusLabelService service = (SystemStatusLabelService) Framework.getServiceRepository().getService("CommonCodeService");
		
		if(service==null){
			throw new JspException("Service Is Null!");
		}
		
		String codeValue = service.getCodeValueByCode(value.toString());
		if(codeValue!=null){
			println(codeValue);
		}
		
		return super.doEndTag();
	}
}