package com.infoservice.taglib.form;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FormTag extends BodyTagSupport {
	private String name;
	private String target;
	private String file;
	private ArrayList buttonList = new ArrayList();
	private ArrayList jsFunctionList = new ArrayList();

	public int doStartTag() throws JspException {
		String head = "<form method=\"post\" name = \"" + name + "\"";
		if (target != null) {
			head = head + " target=\"" + target + "\"";
		}
		if(file!=null&&file.trim().equalsIgnoreCase("true")){
			head = head + " enctype=\"multipart/form-data\" ";
		}
		head = head + ">";

		try {
			pageContext.getOut().write(head);
			pageContext.getOut().write("\n\r");
		} catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}
	public int doEndTag() throws JspException {
		//����Զ���֤����
		println("<SCRIPT LANGUAGE=\"JavaScript\">");
		println("function _"+name+"_autocheck(){");
	
//�����Ҫ���ķ���		

		for(int i = 0;i<jsFunctionList.size();i++){
			String functionName = (String )jsFunctionList.get(i);
			println("	if(!"+functionName.trim()+"()){");
			
//			println("		alert('�������');");
			println("		return false;");
			println("	}");
		}	
//  if(!_codeIsNull()){
//	 return false;
//  }
//  return true;

		
		println("	 return true;");
		println("}");
		println("</SCRIPT>");

		try {
			pageContext.getOut().write("</form>");
			pageContext.getOut().write("\n\r");
		} catch (Exception e) {
		}
		return super.doEndTag();
	}

	private void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}

	}

	public void registerJSFunction(String functionName) {
		jsFunctionList.add(functionName);
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
	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
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
	public void setTarget(String string) {
		target = string;
	}

	/**
	 * @return
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param string
	 */
	public void setFile(String string) {
		file = string;
	}

}
