/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      SystemStatusOptionsTag.java
 * @Create on:      2006-4-27   
 * @Author :        HuangBo
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			Editor				ChangeReasons
 * 2006-4-27    HuangBo              Create
 */
package com.infoservice.taglib.form;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import cn.com.goldtech.util.web.SystemStatusLabelService;
import cn.com.goldtech.util.web.NameValueBean;
import com.infoservice.framework.Framework;

/**
 * @author HuangBo
 * @version 1.1
 * @Description : 用于根据类型输出SystemStatus的Option
 *
 */
public class SystemStatusOptionsTag extends BaseOptionTag {
	
	private String codeTypeId;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		if(codeTypeId==null){
			return EVAL_PAGE;
		}
		SystemStatusLabelService service = (SystemStatusLabelService) Framework.getServiceRepository().getService("CommonCodeService");
		ArrayList al = service.getListByCodeType(codeTypeId);
		if(al==null||al.size()==0){
			return EVAL_PAGE;
		}
		for(int i =0;i<al.size();i++){
			NameValueBean bean =(NameValueBean) al.get(i);
			outOption(bean.getValue().toString(),bean.getName()); 
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
	public String getCodeTypeId() {
		return codeTypeId;
	}

	/**
	 * @param string
	 */
	public void setCodeTypeId(String string) {
		codeTypeId = string;
	}

}
