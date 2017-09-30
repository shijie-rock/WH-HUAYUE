package com.infoservice.taglib.display;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.framework.util.JspHelper;

public class TableTag extends BodyTagSupport {
	private String dataSource;
	private Table table;

	public int doStartTag() throws JspException {
		try {
			List valueList = null;
			JspHelper jspHelper = new JspHelper((HttpServletRequest) pageContext.getRequest());
			String dataSource = getDataSource();
			if (dataSource != null) {
				Object obj= jspHelper.getValue(dataSource);				
				//by kevin
				if ( obj instanceof Object[] ){
					valueList = new ArrayList();
					for ( Object tmp : (Object[])obj ){
						valueList.add(tmp);
					}
				}else if (obj instanceof List){
					valueList = (List)obj;
				}
			}
			if (valueList == null) {
				valueList = new ArrayList();
			}
			if(valueList==null||valueList.size()==0){
				
				println("<table class=csstable width=\"90%\" align=\"center\" border=0>");
				println("<TR>");
				println("<TD colspan=4 align=\"center\"><div align=\"center\" class=\"noSearchRecord\">没有找到查询的记录！</div></TD>");
				println("</TR>");
				println("</table>");
				return SKIP_BODY;
			}
			table = new Table(valueList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException {
		if (table.next()) {
			return EVAL_BODY_AGAIN;
		} else {
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspException {

		return super.doEndTag();
	}
	
	private void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}

	}

	public Object findFieldValueByName(String name) throws JspException {

		return table.getFieldValue(name);
	}
    //	by ansen
	public String findCurrentTdBgColorValue() throws JspException
	{
		return table.getTdBgColor();
	}
	
    //	by ansen
	public int getSeqNum() throws JspException
	{
		return table.getTdSeqNum();
	}
	
	public void registerGroup(Group group) {
		table.registerGroup(group);
	}

	public Group getGroup(String name) {

		return table.getGroup(name);
	}

	public boolean isLast() {

		return true;
	}

	public Object getNextObject() {

		return table.getNextObject();
	}
	public Object getCurrentObject() {

		return table.getCurrentObject();
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String string) {
		dataSource = string;
	}
	

}
