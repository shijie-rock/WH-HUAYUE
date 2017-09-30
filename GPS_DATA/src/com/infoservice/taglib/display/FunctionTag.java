package com.infoservice.taglib.display;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;

public abstract class FunctionTag extends BodyTagSupport {
	private String name;
	private Function function;

	public abstract Function createFunction();

	public Object getCurrentObject() {
		try {
			GroupTag tag = (GroupTag) findAncestorWithClass(this, Class.forName(TagUtil.GROUP_TAG_CLASS));
			
			return tag.getCurrentObject();
		} catch (Exception cnfe) {
//			throw new JspException("在OptionsTag外层没有嵌套SelectTag!");
		}
		return null;
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
	public void setName(String string) {
		name = string;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			GroupTag tag = (GroupTag) findAncestorWithClass(this, Class.forName(TagUtil.GROUP_TAG_CLASS));
			if (tag.canOutGroup()) {
				pageContext.getOut().print(function.getResultValue());
//将Function初始化。		
				function.reInit();				
			}
		} catch (Exception cnfe) {
			throw new JspException("在OptionsTag外层没有嵌套SelectTag!");
		}
		return super.doEndTag();
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			GroupTag tag = (GroupTag) findAncestorWithClass(this, Class.forName(TagUtil.GROUP_TAG_CLASS));
			Object obj = tag.getFunction(getName());

			if (obj == null) {
				Function fun = createFunction();
				tag.registerFunction(fun);
				function = fun;
			} else {
				function = (Function) obj;
			}
			//对Function进行统计
			//取出当前的对象
			Object currentObj = getCurrentObject();
			function.execute(currentObj);
		} catch (ClassNotFoundException cnfe) {
			throw new JspException("在OptionsTag外层没有嵌套SelectTag!");
		}
		return super.doStartTag();
	}

}
