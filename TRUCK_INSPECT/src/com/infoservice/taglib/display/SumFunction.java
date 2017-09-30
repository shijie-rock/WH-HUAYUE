package com.infoservice.taglib.display;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import com.infoservice.po.DynaBean;

public class SumFunction extends Function {

	private int count = 0;

	private double resultValue = 0.0;

	private String field;

	private String format;
	public SumFunction(String name, String field,String format) {
		setName(name);
		this.field = field;
		this.format = format;
	}



	public void execute(Object data) {
		try {
			Object value=null;
			if ( data instanceof DynaBean ){
				value = ((DynaBean)data).get(field);
			}else{
				String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
				Method field = data.getClass().getMethod(methodName, null);
				value= field.invoke(data, null);
			}			
			
			double tem = 0.0;
			if (value == null) {
				
//				return null;
			} else {
				tem = Double.parseDouble(value.toString());
//				return value.toString();
			}
			resultValue = resultValue+tem;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getResultValue() {
		Double d = new Double(resultValue);
		if ( format!=null && format.length()>0 ){
			return MessageFormat.format(format, new Object[]{d});
		}else{
			return d.toString();
		}
	}



	/* £¨·Ç Javadoc£©
	 * @see com.infoservice.common.web.jsp.taglib.display.Function#reInit()
	 */
	public void reInit() {
		resultValue = 0;
		
	}
	
	public static void main(String str[]){
		Double d = new Double(50.2);
		if(d.longValue()==d.floatValue()){
			System.out.println("aaaaa"+d.longValue());
		}
		else{
			System.out.println("bbb"+d.floatValue());
		}

	}

}
