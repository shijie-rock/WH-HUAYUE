package com.infoservice.taglib.display;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Group {

	private String name;

	public HashMap functionMap;

	public HashMap dataMap;

	public String groupField;

	private boolean groupFinash;

	public Group(String name, String groupBy) {
		this.name = name;
		groupField = groupBy.trim();
		functionMap = new HashMap();
	}

	public void registerFunction(Function function) {
		functionMap.put(function.getName(),function);
	}	
	public Function getFucntion(String name) {
		return (Function) functionMap.get(name);
	}
	public boolean isGroupFinash() {

		return groupFinash;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void checkGroupFinash(Object currentObj, Object nextObj) {
		//如果下一个对象为null,那么输出
		if (nextObj == null) {
			groupFinash = true;
			return;
		}
		//如果当前对象和下一个对象值不同，那么输出
		if (currentObj == null) {
			groupFinash = false;
			return;
		}
		Object currentValue = getFieldValue(currentObj, groupField);
		Object nextValue = getFieldValue(nextObj, groupField);
		groupFinash = !compleValue(currentValue, nextValue);

	}

	private boolean compleValue(Object obj, Object nextObj) {
		if (obj == null && nextObj == null) {
			return true;
		} else if (obj == null && nextObj != null) {
			return false;
		} else if (obj != null && nextObj == null) {
			return false;
		} else {
			return obj.toString().equals(nextObj.toString());

		}

	}

	private Object getFieldValue(Object obj, String fieldNmae) {
		String methodName = "get" + fieldNmae.substring(0, 1).toUpperCase() + fieldNmae.substring(1, fieldNmae.length());
		try {
			Method field = obj.getClass().getMethod(methodName, null);
			Object value = field.invoke(obj, null);
			return value;
		} catch (Exception e) {

		}
		return null;
	}

}
