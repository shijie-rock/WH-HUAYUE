package com.infoservice.taglib.display;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.infoservice.taglib.BeanUtil;

public class Table {
	private int index = 0;

	private HashMap groupMap;

	private List valueList;

	private Object currentObject;

	public Table(List value) {
		this.valueList = value;
		groupMap = new HashMap();

	}

	public void registerGroup(Group group) {
		Object obj = groupMap.get(group.getName());
		if (obj == null) {
			groupMap.put(group.getName(), group);
		}
	}
	
	public Group getGroup(String name) {

		return (Group) groupMap.get(name);
	}
	
	
	public boolean isGroupFinash(String name) {

		return getGroup(name).isGroupFinash();
	}
	
	public Object getCurrentObject(){
		if(valueList.size()>0){
			return valueList.get(index);
		}
		return null;
	
	}


	public Object getNextObject(){
		if(index<valueList.size()-1){
			return valueList.get(index+1);
		}
		return null;
	}

	public boolean next() {
		index++;
		
		if (index > valueList.size()-1) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public Object getFieldValue(String fieldName) throws JspException {
		//by kevin
		return BeanUtil.getFiled(valueList.get(index), fieldName);

	}
	
	//by ansen
	public String getTdBgColor()
	{
		String valueColor = "#DEFEDC";
		int i = 1;
		i = index % 2;
		if (i !=0)
		{
			valueColor="#FFFFFF";
		}
		return valueColor;
	}
	
	public int getTdSeqNum()
	{
		return this.index+1;
	}
}
