package com.infoservice.taglib.display;


public class SumFunctionTag extends FunctionTag {
	private String field;
	private String format;


	public Function createFunction() {
		return new SumFunction(getName(), field,format);
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
