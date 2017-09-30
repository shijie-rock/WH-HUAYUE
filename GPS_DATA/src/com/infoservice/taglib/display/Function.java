
package com.infoservice.taglib.display;

public abstract class Function {
	private String name;
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public abstract void reInit();
	
	public abstract void execute(Object data);
	
	
	public abstract Object getResultValue();
	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}
