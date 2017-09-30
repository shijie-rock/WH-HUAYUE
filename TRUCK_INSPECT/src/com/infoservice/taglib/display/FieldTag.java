package com.infoservice.taglib.display;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.framework.util.JspHelper;
import com.infoservice.taglib.BeanUtil;

public class FieldTag extends BodyTagSupport {
	private String field;
	private String dataSource;
	private String format;
	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		JspHelper jspHelper = new JspHelper((HttpServletRequest) pageContext.getRequest());
		String dataSource = getDataSource();
		Object obj = jspHelper.getValue(dataSource);
		if (obj == null) {

		} else {
			if (field == null) {
				print(obj);
			} else {
				try {
					Object value = BeanUtil.getFiled(obj, field);
					if (value != null) {
						print(value);
					}
				} catch (Exception e) {
					throw new JspException("读取出现错误", e);
				}
			}

		}
		return super.doEndTag();
	}

	/**
	 * @return
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @return
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param string
	 */
	public void setDataSource(String string) {
		dataSource = string;
	}

	/**
	 * @param string
	 */
	public void setField(String string) {
		field = string;
	}

	private void print(Object str) {
		try {
			if ( this.format!=null && this.format.length()>0 ){
				pageContext.getOut().print(MessageFormat.format(format,new Object[]{str}));
			}else{
				pageContext.getOut().print(str);
			}		
		} catch (Exception e) {

		}

	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
