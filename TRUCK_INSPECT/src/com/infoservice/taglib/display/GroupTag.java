package com.infoservice.taglib.display;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.infoservice.taglib.TagUtil;

public class GroupTag extends BodyTagSupport {
	private Group group;
	private String name;
	private String field;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		return super.doEndTag();
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			TableTag tag = (TableTag) findAncestorWithClass(this, Class.forName(TagUtil.TABLE_TAG_CLASS));
			Object obj = tag.getGroup(name);
			if (obj == null) {
				Group grp = new Group(name, field);
				tag.registerGroup(grp);
				group = grp;
			} else {
				group = (Group) obj;
			}
			//判断是是否需要输出一个Group
			group.checkGroupFinash(tag.getCurrentObject(), tag.getNextObject());
			if(group.isGroupFinash()){
				return EVAL_BODY_INCLUDE;
			}
			else{
				return super.doStartTag();
			}
		} catch (ClassNotFoundException cnfe) {
			throw new JspException("在OptionsTag外层没有嵌套SelectTag!");
		}
	}
	
	public Object getCurrentObject(){
		try {
			TableTag tag = (TableTag) findAncestorWithClass(this, Class.forName(TagUtil.TABLE_TAG_CLASS));
			return tag.getCurrentObject();
		} catch (ClassNotFoundException cnfe) {
//			throw new JspException("在OptionsTag外层没有嵌套SelectTag!");
		}
		return null;
	}
	
	public void registerFunction(Function function) {
		group.registerFunction(function);
	}

	public Function getFunction(String name) {
		return group.getFucntion(name);
	}

	public boolean canOutGroup() {
		return group.isGroupFinash();
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

	/**
	 * @return
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param string
	 */
	public void setField(String string) {
		field = string;
	}

}
