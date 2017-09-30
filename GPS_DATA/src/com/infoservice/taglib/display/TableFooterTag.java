package com.infoservice.taglib.display;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.framework.util.JspHelper;
import com.infoservice.po.PagingObject;

public class TableFooterTag extends BodyTagSupport {

	private String action;

	private String dataSource;

	private PagingObject pageObject;

	//modify by ansen 2007-1-9
	public int doEndTag() throws JspException {
		JspHelper jspHelper = new JspHelper((HttpServletRequest) pageContext.getRequest());
		String contextPath = jspHelper.getContextPath();
//		println("<form name=\"_pageFrom\" method=\"post\" action="+contextPath+"/"+action+">");
//		println("<table width=\"90%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
//		println("<tr>");
//		println("<td height=\"5\" colspan=\"2\"></td>");
//		println("</tr>");
//		println("<tr>");
//		println("<td width=\"39%\" >");
		println("��"+pageObject.getCurrentPage()+"ҳ/");
		println("��"+pageObject.getTotalPage()+"ҳ|");
		println("��"+pageObject.getTotalSize()+"��");
//		println("</td>");
        println("<td width=\"61%\" align=\"right\">");
		if(pageObject.getCurrentPage()>1){
			println("<a href='#' style='cursor:hand'  onClick=\"gotoPage(1);\">�� ҳ</a>");
			//println("<A  style='cursor:hand'  onClick=\"gotoPage(1);\"><u>��ҳ</u></A>");
			println("<a href='#' style='cursor:hand'  onClick=\"gotoPage("+(pageObject.getCurrentPage()-1)+");\">��һҳ</a>");
			//println("<A  style='cursor:hand' onClick=\"gotoPage("+(pageObject.getCurrentPage()-1)+");\"><u>��һҳ</u></A>");
		}
		else{
			//println("<A  ><u>��ҳ</u></A>");
			println("<a href='#' style='cursor:hand' >�� ҳ</a>");
			//println("<A  ><u>��һҳ</u></A>");
			println("<a style='cursor:hand' >��һҳ</a>");
		}
		if(pageObject.getCurrentPage()<pageObject.getTotalPage()){
			//println("<A  style='cursor:hand' onClick=\"gotoPage("+(pageObject.getCurrentPage()+1)+");\"><u>��һҳ</u></A>");
			println("<a href='#' style='cursor:hand'  onClick=\"gotoPage("+(pageObject.getCurrentPage()+1)+");\">��һҳ</a>");
			//println("<A  style='cursor:hand' onClick=\"gotoPage("+pageObject.getTotalPage()+");\"><u>ĩҳ</u></A>");
			println("<a href='#' style='cursor:hand'  onClick=\"gotoPage("+pageObject.getTotalPage()+");\">β ҳ</a>");
		}
		else{
			//println("<A  ><u>��һҳ</u></A>");
			println("<a href='#' style='cursor:hand' >��һҳ</a>");
			//println("<A  ><u>ĩҳ</u></A>");
			println("<a style='cursor:hand' >β ҳ</a>");
		}
		println("<input name='GOTO_NUM' id='GOTO_NUM' style=\"WIDTH:20px\">");
		println("<a href='#' style='cursor:hand'  onClick=\"onClickGoBtn();\">GO</a>");
//		println("</td>");
//		println("</tr>");
//		println("</table>");

		ServletRequest request =  pageContext.getRequest();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()){
			String paramName = (String )enu.nextElement();
			if(!paramName.startsWith(dataSource+".")&& paramName.startsWith("CON"+"_")){
				String[] values = request.getParameterValues(paramName);
				for(int i=0;values!=null&&i<values.length;i++){
					println("<input type='hidden' name='"+paramName+"' value='"+values[i]+"' >");
				}
			}
		}
		
		println("<input type='hidden'  name='"+dataSource+".currentPage'  id='99999999'  value='"+pageObject.getCurrentPage()+"' >");
		println("<input type='hidden'  name='"+dataSource+".pageSize'   value='"+pageObject.getPageSize()+"' >");
		println("<input type='hidden'  name='"+dataSource+".totalPage'    value='"+pageObject.getTotalPage()+"' >");
		println("<input type='hidden'  name='"+dataSource+".totalSize'   value='"+pageObject.getTotalSize()+"' >");
//		println("</form>");
		return super.doEndTag();
	}

	public int doStartTag() throws JspException {
		JspHelper helper = new JspHelper((HttpServletRequest) pageContext.getRequest());
		pageObject = (PagingObject) helper.getValue(dataSource);
		if (pageObject == null)
		{
			pageObject = new PagingObject();
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {

		action = string;
	}

	private void println(String str) {
		try
		{
			pageContext.getOut().println(str);
		}
		catch (Exception e)
		{

		}
	}

	/**
	 * @return
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param string
	 */
	public void setDataSource(String string) {
		dataSource = string;
	}

}