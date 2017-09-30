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
 * 2006-4-28            HuangBo             修改程序结构
 *
 */
package com.infoservice.taglib.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.infoservice.taglib.ValueUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class SelectTag extends BaseInputTag {

	private String onChange;
	private String selectValue ;
	private String enabled;
	public int doStartTag() throws JspException {
		try {
			setDataSourceName();
			StringBuffer sb = new StringBuffer();
			sb.append("<SELECT ");
			addInput(sb);
			if(onChange!=null){
				sb.append("onChange='"+onChange+";'");
			}
			//add by ansen
			if (enabled!=null)
			{
				if (enabled.equals("0"))
				{
					sb.append(" disabled");
				}
			}
			sb.append(">");
			println(sb.toString());
			if (getValue() != null) {
				selectValue = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException {
		println("</SELECT >");
		return super.doEndTag();
	}
	
	public boolean isSelect(String value){
		if(value!=null&&selectValue!=null&&value.equals(selectValue)){
			return true;
		}
		return false;
	
	}



	/**
	 * @param string
	 */
	public void setOnChange(String string) {
		onChange = string;
	}


	/**
	 * @return
	 */
	public String getOnChange() {
		return onChange;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
