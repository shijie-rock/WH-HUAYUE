/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.common
 * 文   件  名:HyLmsSignUtil.java
 * 创 建日期:2018年10月17日-上午11:25:34
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import com.youbus.framework.comm.AppLog;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * 类名称:HyLmsSignUtil
 * 类描述:签名工具类
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月17日 上午11:25:34
 * 修改备注:
 * @version 1.0.0
 */
public class HyLmsSignUtil {
	private static Logger log = LogManager.getLogger(HyLmsSignUtil.class);
	private static Logger appLog=AppLog.getInstance().getDELog();
	/**
	 * 
	 * 方   法  名:MD5Encode
	 * 方法描述:MD5 加密
	 * 参         数:@param sourceString
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 转16进制
	 * 方   法  名:byte2hexString
	 * 方法描述:
	 * 参         数:@param bytes
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	
    private static final char[] hexDigit = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
   
    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    
	
//	md5（密钥+参数按字母升序排列+密钥）后的大写字母
	/**
	 * 获取签名
	 * 方   法  名:getSign
	 * 方法描述:
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getSign(List<ParamBean> paramList){
		if(paramList==null||paramList.size()<1)return null;
		Map<String,String> paramMap = new TreeMap<String,String>();
		for(ParamBean pBean:paramList){
			if(!"sign".equals(pBean.getParamName())){
				paramMap.put(pBean.getParamName(), pBean.getParamValue());
			}
			//sign 不参与签名计算
		}
		Set keySet=paramMap.keySet();
		Iterator<String> iter=keySet.iterator();
		StringBuffer sbf=new StringBuffer();
		sbf.append(HyLmsClientConstant.SECURITY_CODE);
		while(iter.hasNext()){
			String key=iter.next();
			String value=paramMap.get(key);
			value=(value==null?"":value);
			//在mina报文 content字段，api报表data字段，因为需要传json内容，json字符串，
//			为了保证服务端能够解析content 和data，所以，发送方，将这两个字段的json字符串，加了转移符。
//			如："data":"{\"no\":\"SA18102600036\"}"，在客户端签名时，需要把 这个字符串中的转移符去掉，
//			服务端在验签时，是不加 斜杠的
			if(("data".equals(key)||"conent".equals(key))&&StringUtils.isNotBlank(value)){
				value=value.replaceAll("\\\\", "");//去掉转移符
			}
			sbf.append(key+"="+value);
//			sbf.append(key+value);
		}
		sbf.append(HyLmsClientConstant.SECURITY_CODE);
		log.debug("src sign :="+sbf.toString());
		String paramMD5=DigestUtils.md5Hex(sbf.toString());//apache md5 及16进制
		return paramMD5.toUpperCase();
	}

	
	/**
	 * 将对象转换为list<param>，主要用于对象转为Param集合
	 * 方   法  名:getListBeanParam
	 * 方法描述:
	 * 参         数:@param bean
	 * 参         数:@return
	 * 参         数:@throws IllegalAccessException
	 * 参         数:@throws IllegalArgumentException
	 * 参         数:@throws InvocationTargetException
	 * 返   回  值:List<ParamBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<ParamBean> getListBeanParam(Serializable bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class userCla = (Class) bean.getClass();
		List<ParamBean> result=new ArrayList<ParamBean>();
		Field[] fs = userCla.getDeclaredFields();
	       for(int i = 0 ; i < fs.length; i++){
	    	   Field f = fs[i];
	           f.setAccessible(true); //设置些属性是可以访问
//	           Object val = f.get(bean);//得到此属性的值   
//	           System.out.println("name:"+f.getName()+"\t value = "+val);
	           String name=f.getName();
	           String type=f.getType().toString();//数据类型
	           if (type.endsWith("String")) {
	        	   Method m=getGetMethod(userCla, name);
	        	   if(m!=null){
		        	   Object value=m.invoke(bean, new Object[0]);   
		        	   System.out.println("对象["+userCla+"]字段["+name+"],值["+value+"]");
		        	   ParamBean param=new ParamBean(name,(String)value);
		        	   result.add(param);
	        	   }
	           }
	       }
		return result;
	}
	
	/**
	 * 获取消息对象的签名
	 * 方   法  名:getSignFromMsgBean
	 * 方法描述:
	 * 参         数:@param bean
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getSignFromMsgBean(Serializable bean){
		try {
			return getSign(getListBeanParam(bean));
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将api请求转化为json 字符串，主要用于请求报文转化
	 * 方   法  名:getApiRequestJson
	 * 方法描述:
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getApiRequestJson(List<ParamBean> paramList){
		if(paramList!=null&&paramList.size()>0){
			StringBuffer sbf=new StringBuffer("{");
			for(int i=0;i<paramList.size();i++){
				ParamBean paramBean=paramList.get(i);
				String value=paramBean.getParamValue()==null?"":paramBean.getParamValue();
				sbf.append("\""+paramBean.getParamName()+"\""+":"+"\""+value+"\"");
				if(i<paramList.size()-1){
					sbf.append(",");
				}
			}
			sbf.append("}");
//			sbf.append("\r\n");//粘包分离
//			粘包 已由mina处理			
//			connector.getFilterChain().addLast("codec1",
//			new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),MSG_SPLIT,MSG_SPLIT)));//utf-8
			
			System.out.println("bean to json :="+sbf.toString());
			log.debug("bean to json :="+sbf.toString());;
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 将对象转化为json 字符串，主要用于请求报文转化
	 * 方   法  名:getRequestBeanJson
	 * 方法描述:
	 * 参         数:@param bean
	 * 参         数:@return
	 * 参         数:@throws IllegalAccessException
	 * 参         数:@throws IllegalArgumentException
	 * 参         数:@throws InvocationTargetException
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getRequestBeanJson(Serializable bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<ParamBean> paramList=getListBeanParam(bean);
		String requestJson=getApiRequestJson(paramList);
		appLog.debug("requestJson:="+requestJson);
		return requestJson;
	}
	
	
	/**
	 * 通过Bean属性，获取get方法
	 * 方   法  名:getGetMethod
	 * 方法描述:
	 * 参         数:@param objectClass
	 * 参         数:@param fieldName
	 * 参         数:@return
	 * 返   回  值:Method
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static Method getGetMethod(Class objectClass, String fieldName) {       
	    StringBuffer sb = new StringBuffer();       
	    sb.append("get");       
	    sb.append(fieldName.substring(0, 1).toUpperCase());       
	    sb.append(fieldName.substring(1));       
	    try {       
	        return objectClass.getMethod(sb.toString());       
	    } catch (Exception e) {    
	    	e.printStackTrace();
	    }       
	    return null;       
	  
	} 
	
	public static String getNewMsgId(){
		String id= UUID.randomUUID().toString();
		System.out.println("new id:="+id);
		return id;
	}
	
	/**
	 * 将api 请求参数中的data参数表，转为json
	 * 方   法  名:getApiRequestContentJson
	 * 方法描述:
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getApiRequestContentJson(List<ParamBean> paramList){
		if(paramList!=null&&paramList.size()>0){
			StringBuffer sbf=new StringBuffer("{");
			for(int i=0;i<paramList.size();i++){
				ParamBean paramBean=paramList.get(i);
				String value=paramBean.getParamValue()==null?"":paramBean.getParamValue();
				sbf.append("\""+paramBean.getParamName()+"\""+":"+"\""+value+"\"");
				if(i<paramList.size()-1){
					sbf.append(",");
				}
			}
			sbf.append("}");
//			sbf.append("\r\n");//粘包分离
			System.out.println("bean to json :="+sbf.toString());
			log.debug("bean to json :="+sbf.toString());;
			return sbf.toString();
		}
		return null;
	}
	/**
	 * 返回api请求内容json字符串，带转移符
	 * 方   法  名:getApiRequestContentJson2
	 * 方法描述:
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getApiRequestContentJson2(List<ParamBean> paramList){
		if(paramList!=null&&paramList.size()>0){
			StringBuffer sbf=new StringBuffer("{");
			for(int i=0;i<paramList.size();i++){
				ParamBean paramBean=paramList.get(i);
				String value=paramBean.getParamValue()==null?"":paramBean.getParamValue();
				//签名时，有问题，会把斜杠也加入签名。在转签名的过程中，要把转移符替换掉
				sbf.append("\\\""+paramBean.getParamName()+"\\\""+":"+"\\\""+value+"\\\"");
				if(i<paramList.size()-1){
					sbf.append(",");
				}
			}
			sbf.append("}");
//			sbf.append("\r\n");//粘包分离
			System.out.println("bean to json :="+sbf.toString());
			log.debug("bean to json :="+sbf.toString());;
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		getNewMsgId();
		String str="\"no\":\"SA18102600036\"";
		
		System.out.println(str.replaceAll("\\\\", ""));
		
	}
	
}
