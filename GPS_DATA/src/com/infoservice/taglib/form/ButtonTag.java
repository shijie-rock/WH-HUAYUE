package com.infoservice.taglib.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;
import com.infoservice.taglib.ValueUtil;

public class ButtonTag extends BodyTagSupport {
	private String name;
	private String action;
	private String autoCheck;
	private boolean isAutoCheck =true;
	private String onClick;
	private String model;
	private String value;
	private String cssStyle;

	public int doEndTag() throws JspException {
		String formName = null;
		//输出JS方法	
		try {
			FormTag formTag = (FormTag) findAncestorWithClass(this, Class.forName(TagUtil.FORM_TAG_CLASS));
			formName = formTag.getName();
		} catch (ClassNotFoundException cnfe) {
			throw new JspException("在ButtonTag外层没有嵌套FormTag!");
		}
		println("<SCRIPT LANGUAGE=\"JavaScript\">");
		println("function _" + name + "_onclick(){");
		if(onClick!=null&&onClick.trim().length()>0){
			println("if(!"+onClick+"){");
			println("	return;");
			println("	}");
		}
		if(isAutoCheck){
			println("	if(_"+formName+"_autocheck()){");
			println("		" + formName + ".action =\"" + action + "\";");
			println("		" + formName + ".submit();");
			println("	}");
		}
		println("}");
		println("</SCRIPT>");
		return super.doEndTag();
	}

	//modify by ansen
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type=\"button\" onclick=\"_" + name + "_onclick();\" name=\"" + name + "\" action =\"" + action + "\" value=\"" + value + "\"");
		if (getCssStyle() != null) {
			String value;
			try
			{
				value = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getCssStyle());
				if(value!=null){
					sb.append(" class='");
					sb.append(value);
					sb.append("' ");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		sb.append(">");
		println(sb.toString());
		return super.doStartTag();
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
	public boolean getAutoCheck() {
		return isAutoCheck;
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
	 * @return
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
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
	public void setAutoCheck(String string) {
		this.autoCheck = string;
		if ("TRUE".equalsIgnoreCase(string.trim())) {
			isAutoCheck = true;
		} else {
			isAutoCheck = false;
		}
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

	/**
	 * @param string
	 */
	public void setOnClick(String string) {
		onClick = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

}
