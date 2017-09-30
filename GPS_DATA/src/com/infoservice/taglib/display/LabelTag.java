/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      LabelTag.java
 * @Create on:      2006-3-27   
 * @Author :        Administrator
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			weimei				ChangeReasons
 *
 */
package com.infoservice.taglib.display;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import cn.com.goldtech.util.web.LabelService;
import com.infoservice.framework.Framework;
  
/**
 * @Description :用于进行多语言支持的实现
 *
 */
public class LabelTag extends BaseLabelTag {
	private String model;
	private String name;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		
//		HttpSession session = pageContext.getSession();

//		WebUser user = WebUser.getCurrentUser(session);
//		
//		String pageLanguage ;
//		if(user==null){
//			pageLanguage = pageContext.getRequest().getLocale().getLanguage();
//		}
//		else {
//			pageLanguage = user.getPageLanguage(); 
//		}
//		
//		LanguageService langService = (LanguageService) Framework.getServiceRepository().getService("language");
//		pageLanguage = langService.getLanguageMap(pageLanguage);
		
		String pageLanguage = pageContext.getRequest().getLocale().getLanguage();

		LabelService service = (LabelService) Framework.getServiceRepository().getService("label");
		String mdl = name+"_"+model;
		
		String message = service.getMessage(mdl,pageLanguage);

		try{
	
			if(message==null||message.length()==0){
				println("name : <font color=red>" +name +"</font>,model : <font color=red>"+model+"</font>, language ：<font color=red>"+pageLanguage+"</font>  undefined");
			}
			else{
				println(message);
			}
		}
		catch(Exception e){
	
		}
		return super.doEndTag();
	}

	/**
	 * @param string
	 */
	public void setModel(String string) {
		model = string;
			
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
}
