package com.infoservice.taglib.form;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HrefLinkTag extends BodyTagSupport {
	private String action ;
	private String target ;
	private String model ;
	private String name;

	public int doStartTag() throws JspException {

		
		return super.doStartTag();
	}


	public int doEndTag() throws JspException {
		println("<A HREF=\""+action+"\">"+name+"</A>");
		
		return super.doEndTag();
	}

	private void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {
			
		}
	}
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setTarget(String string) {
		target = string;
	}


	/**
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
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
