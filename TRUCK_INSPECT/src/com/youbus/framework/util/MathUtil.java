package com.youbus.framework.util;

import java.math.BigDecimal;

/**
 * 数学处理工具类
 * 
 * @author Bill Tang
 *
 */
public class MathUtil {

	public static int compare(BigDecimal val1, BigDecimal val2) {  
//	    if (val1.compareTo(val2) < 0) {  
//	        result = "第二位数大！";  
//	    }  
//	    if (val1.compareTo(val2) == 0) {  
//	        result = "两位数一样大！";  
//	    }  
//	    if (val1.compareTo(val2) > 0) {  
//	        result = "第一位数大！";  
//	    }  
		
	    int result = val1.compareTo(val2);  
	    
	    return result;  
	}  
}
