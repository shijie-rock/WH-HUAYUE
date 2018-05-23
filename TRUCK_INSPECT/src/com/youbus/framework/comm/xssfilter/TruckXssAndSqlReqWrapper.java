/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.xssfilter
 * ��   ��  ��:TruckXssAndSqlReqWrapper.java
 * �� ������:2017��12��12��-����1:01:22
 * Copyright @ 2017-YouBus.com.cn
 */
package com.youbus.framework.comm.xssfilter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

/**
 * ������:TruckXssAndSqlReqWrapper
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��12�� ����1:01:22
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TruckXssAndSqlReqWrapper extends HttpServletRequestWrapper {
	private static Logger log=Logger.getLogger(TruckXssAndSqlReqWrapper.class);
    HttpServletRequest orgRequest = null;  
    
    public TruckXssAndSqlReqWrapper(HttpServletRequest request) {  
        super(request);  
        orgRequest = request;  
    }  
  
    /** 
     * ����getParameter���������������Ͳ���ֵ����xss & sql���ˡ�<br/> 
     * �����Ҫ���ԭʼ��ֵ����ͨ��super.getParameterValues(name)����ȡ<br/> 
     * getParameterNames,getParameterValues��getParameterMapҲ������Ҫ���� 
     */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        log.debug("org name["+name+"] value:="+value);
        if (value != null) {  
            value = xssEncode(value);  
        }  
        log.debug("endCode name["+name+"] value:="+value);
        return value;  
    }  
  
    /** 
     * ����getHeader���������������Ͳ���ֵ����xss & sql���ˡ�<br/> 
     * �����Ҫ���ԭʼ��ֵ����ͨ��super.getHeaders(name)����ȡ<br/> 
     * getHeaderNames Ҳ������Ҫ���� 
     */  
    @Override  
    public String getHeader(String name) {  
        String value = super.getHeader(xssEncode(name));  
        log.debug("org head name["+name+"] value:="+value);
        if (value != null) {  
            value = xssEncode(value);  
        }  
        log.debug("endCode head name["+name+"] value:="+value);
        return value;  
    }  
  
    /** 
     * ����������xss & sql©���İ���ַ�ֱ���滻��ȫ���ַ� 
     *  
     * @param s 
     * @return 
     */  
    private static String xssEncode(String s) {  
        if (s == null || s.isEmpty()) {  
            return s;  
        }else{  
            s = stripXSSAndSql(s);  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append("��");// ת����ں�  
                break;  
            case '<':  
                sb.append("��");// ת��С�ں�  
                break;  
            case '\'':  
                sb.append("��");// ת�嵥����  
                break;  
            case '\"':  
                sb.append("��");// ת��˫����  
                break;  
            case '&':  
                sb.append("��");// ת��&  
                break;  
            case '#':  
                sb.append("��");// ת��#  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
  
    /** 
     * ��ȡ��ԭʼ��request 
     *  
     * @return 
     */  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
     * ��ȡ��ԭʼ��request�ľ�̬���� 
     *  
     * @return 
     */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof TruckXssAndSqlReqWrapper) {  
            return ((TruckXssAndSqlReqWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }  
  
    /** 
     *  
     * ��ֹxss��ű��������滻������ʵ����������� 
     */  
  
    public static String stripXSSAndSql(String value) {  
        if (value != null) {  
            // NOTE: It's highly recommended to use the ESAPI library and  
            // uncomment the following line to  
            // avoid encoded attacks.  
            // value = ESAPI.encoder().canonicalize(value);  
            // Avoid null characters  
/**         value = value.replaceAll("", "");***/  
            // Avoid anything between script tags  
            Pattern scriptPattern = Pattern.compile("<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e-xpression  
            scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Remove any lonesome </script> tag  
            scriptPattern = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Remove any lonesome <script ...> tag  
            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid eval(...) expressions  
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid e-xpression(...) expressions  
            scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid javascript:... expressions  
            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid vbscript:... expressions  
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid onload= expressions  
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            //sql
            String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
                    + "(\\b(select|update|union|and|or|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
//            		+ "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
            scriptPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
        }  
        return value;  
    }  
  
}  
