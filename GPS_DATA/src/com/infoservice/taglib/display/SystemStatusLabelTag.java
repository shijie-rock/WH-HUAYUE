/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      SystemStatusLabelTag.java
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

import cn.com.goldtech.util.web.SystemStatusLabelService;
import com.infoservice.framework.Framework;

public class SystemStatusLabelTag extends BaseLabelTag {
	
	private String statusId;
	
	public int doEndTag() throws JspException {
		
		// remove by kevin
//		HttpSession session = pageContext.getSession();
//
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
//		
//	    pageLanguage = langService.getLanguageMap(pageLanguage);

	    SystemStatusLabelService service = (SystemStatusLabelService) Framework.getServiceRepository().getService("CommonCodeService");
		
	    String codeValue = service.getCodeValueByCode(statusId);
 	
		try {
			if (codeValue==null||codeValue.length()==0) {
				println("statusId : <font color=red>" + statusId + "</font>" + " undefined");
			} else {
				println(codeValue);
			}
			
		} catch (Exception e) {
		
		}

		return super.doEndTag();
	}
	
	public void setStatusId(String stsId) {
		
		statusId = stsId;	
	}
}