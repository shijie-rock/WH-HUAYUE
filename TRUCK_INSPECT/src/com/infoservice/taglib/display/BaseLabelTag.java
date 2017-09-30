/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservie Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * @File name:      BaseLabelTag.java
 * @Create on:      2006-3-27   
 * @Author :        Administrator
 * 
 * @ChangeList
 * ---------------------------------------------------
 * Date			weimei				ChangeReasons
 *
 */
package com.infoservice.taglib.display;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class BaseLabelTag extends BodyTagSupport {
	
	protected void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}
	}
	
}