package com.infoservice.taglib.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException; 

import com.infoservice.taglib.ValueUtil;

/**
 * @Description :用于codeList支持的实现
 *
 */
public class TextareaTag extends BaseInputTag {

	private String nullable;
	private String rows;
	private String cols;
	private String readonly;
	private String maxlength;
	private String style;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			setDataSourceName();
			StringBuffer textarea = new StringBuffer();
			textarea.append("<textarea ");
			addInput(textarea);
			try {
				if (rows != null && rows.trim().length() > 0) {
					textarea.append("rows='" + (new Integer(rows.trim())).intValue() + "' ");
				}
			} catch (Exception e) {
				throw new JspException("rows 设置错误!");
			}
			try {
				if (cols != null && cols.trim().length() > 0) {
					textarea.append("cols='" + (new Integer(cols.trim())).intValue() + "' ");
				}
			} catch (Exception e) {
				throw new JspException("cols 设置错误!");
			}
			if (readonly != null && (readonly.trim().equalsIgnoreCase("true") || readonly.trim().equalsIgnoreCase("false"))) {
				textarea.append("readonly='");
				textarea.append(readonly.trim());
				textarea.append("' ");
			}
			if (getRewriteable() != null && getRewriteable().trim().equalsIgnoreCase("true")) {
				String request = pageContext.getRequest().getParameter(getName());
				if (request != null && request.trim().length() > 0) {
					setValue(request);
				}
			}
			//by ansen		
			if (getStyle() != null) {
				String value = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getStyle());
				if(value!=null){
					textarea.append(" class='");
					textarea.append(value);
					textarea.append("' ");
				}
			}
			textarea.append(">");
			String value = ValueUtil.parseValue((HttpServletRequest) pageContext.getRequest(), getValue());

			if (value != null) {
				textarea.append(value);
			}
			textarea.append("</textarea>");

			println(textarea.toString());
			outJsFunction();

		} catch (JspException e) {
			throw e;

		} catch (Exception e) {
			throw new JspException(e);

		}
		return super.doEndTag();
	}
	private void outJsFunction() throws JspException {
		String formName = getFormName();
		String function = "_" + getElementId() + "_check";
		println("<SCRIPT LANGUAGE=\"JavaScript\">");
		println("function " + function + "(){");
		println("	var str = document.getElementById('" + getElementId() + "').value;");
		if (nullable != null && nullable.trim().equalsIgnoreCase("false")) {
			println("	if(str == ''){");
			println("		alert('not null!');");
			println("		document.getElementById('" + getElementId() + "').focus();");
			println("		return false;");
			println("	}");
		}
		try {
			if (maxlength != null && (new Integer(maxlength.trim())).intValue() > 0) {
				println("	if (str.length >" + maxlength + ") {");
				println("		alert('too long!');");
				println("		document.getElementById('" + getElementId() + "').focus();");
				println("		return false;");
				println("	}");
			}
		} catch (Exception e) {
			System.err.println("maxlength input error! maxlength=" + maxlength);
		}

		println("	return true;");
		println("}");
		println("</SCRIPT>");
		registerJSFunction(function);
	}
	/**
	 * @return
	 */
	public String getCols() {
		return cols;
	}

	/**
	 * @return
	 */
	public String getMaxlength() {
		return maxlength;
	}

	/**
	 * @return
	 */
	public String getNullable() {
		return nullable;
	}

	/**
	 * @return
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * @return
	 */
	public String getRows() {
		return rows;
	}

	/**
	 * @param string
	 */
	public void setCols(String string) {
		cols = string;
	}

	/**
	 * @param string
	 */
	public void setMaxlength(String string) {
		maxlength = string;
	}

	/**
	 * @param string
	 */
	public void setNullable(String string) {
		nullable = string;
	}

	/**
	 * @param string
	 */
	public void setReadonly(String string) {
		readonly = string;
	}

	/**
	 * @param string
	 */
	public void setRows(String string) {
		rows = string;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}

}
