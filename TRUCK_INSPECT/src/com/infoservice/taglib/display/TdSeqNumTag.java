/**
 * Copyright (c) 2006-2008 JAC Infoservice Corp. 2006-2008,All Rights Reserved.
 * This software is published under the Infoservice DCS Solution Team.
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @File name:  TdSeqNumTag.java
 * @Create on:  2007-1-17
 * @Author   :  ansen
 *
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
package com.infoservice.taglib.display;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;

public class TdSeqNumTag extends BodyTagSupport{
	
	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
	
		try {
			TableTag tag = (TableTag) findAncestorWithClass(this, Class.forName(TagUtil.TABLE_TAG_CLASS));
			Object obj = tag.getSeqNum();
			if(obj!=null){
				pageContext.getOut().print(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return super.doEndTag();
	}

	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

}
