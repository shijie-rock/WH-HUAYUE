package com.youbus.framework.util;

import java.math.BigDecimal;

/**
 * ��ѧ��������
 * 
 * @author Bill Tang
 *
 */
public class MathUtil {

	public static int compare(BigDecimal val1, BigDecimal val2) {  
//	    if (val1.compareTo(val2) < 0) {  
//	        result = "�ڶ�λ����";  
//	    }  
//	    if (val1.compareTo(val2) == 0) {  
//	        result = "��λ��һ����";  
//	    }  
//	    if (val1.compareTo(val2) > 0) {  
//	        result = "��һλ����";  
//	    }  
		
	    int result = val1.compareTo(val2);  
	    
	    return result;  
	}  
}
