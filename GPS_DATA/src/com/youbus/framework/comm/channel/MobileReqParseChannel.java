/**
 * 项目名称:YOUBUS_GPS
 * 包         名:com.youbus.framework.comm.channel
 * 文   件  名:MobileReqParseChannel.java
 * 创 建日期:2015年8月4日-上午10:59:30
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.channel;

import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.de2.vo.VO;
import com.infoservice.framework.Channel;
import com.infoservice.framework.Framework;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.channels.json.driver.JSONArray;
import com.infoservice.framework.channels.json.driver.JSONObject;
import com.infoservice.framework.conf.ActionInfo;
import com.infoservice.framework.conf.ChannelInfo;
import com.infoservice.framework.conf.DataElementInfo;
import com.infoservice.framework.conf.PresentationContent;
import com.infoservice.framework.datacontext.DataContext;
import com.infoservice.framework.datacontext.DataElementType;
import com.infoservice.framework.datacontext.HttpRequestDataContext;
import com.infoservice.framework.datacontext.HttpSessionDataContext;
import com.infoservice.framework.exceptions.ActionNotFoundException;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.MessageService;
import com.infoservice.po.DataBean;
import com.infoservice.po.DynaBean;
import com.youbus.framework.comm.security.MsgEncryptService;
import com.youbus.framework.comm.security.MyEncryptServiceImp;

/**
 * 类名称:MobileReqParseChannel
 * 类描述:处理移动端应答返回数据
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年8月4日 上午10:59:30
 * 修改备注:
 * @version 1.0.0
 */
public class MobileReqParseChannel extends Channel {

	/**
	 * 创建一个新的实例 MobileReqParseChannel.
	 *
	 */
	public MobileReqParseChannel() {
		// TODO Auto-generated constructor stub
	}

	private MsgEncryptService encrypt;
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Channel#initialize(com.infoservice.framework.conf.ChannelInfo)
	 */
	@Override
	public void initialize(ChannelInfo channelDef) {
		// TODO Auto-generated method stub
		super.initialize(channelDef);
		encrypt=MyEncryptServiceImp.getInstance();
	}
	
	private static Logger logger =LogManager.getLogger(RemoteParserChannel.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected DataContext buildActionDataContext(ServletRequest request,ServletResponse response) throws FrameException {
			
		
		
		String actionId =(String)request.getAttribute(FrameworkConstant.ACTION_ID);
		if ( actionId ==null ){
			actionId = request.getParameter(FrameworkConstant.ACTION_ID);
		}
		
//		System.out.println(request.getParameter("aaa"));
/*		System.out.println(request.getAttributeNames().nextElement());
		System.out.println((request).getParameter("req"));*/
		
		
		if (null == actionId) {
			logger.warn("NOT found action variable!");
			throw new ActionNotFoundException(actionId);
		}

		ActionInfo actionDef = Framework.getActionRepository().getActionDef(actionId);
		if (null == actionDef) {
			logger.warn("NOT found action define in action depository!");
			throw new ActionNotFoundException(actionId);
		}

		HttpRequestDataContext hrdc = new HttpRequestDataContext(actionDef.getActionContextDef(),
				(HttpServletRequest) request);
		return hrdc;
	}

	protected DataContext buildSessionDataContext(ServletRequest request,ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		return new HttpSessionDataContext(channelDef.getSessionContextDef(), httpRequest.getSession());
	}


	protected void handleException(ServletRequest request,ServletResponse response, String actionId, Throwable exception,
			PresentationContent presentationContent) throws FrameException {
		OutputStreamWriter osw =null;
		try {			
			//检查客户端是否接受压缩流
			String ae = ((HttpServletRequest)request).getHeader("Accept-Encoding");
			response.setContentType("application/json");
//			response.setContentType("text/plain");
			(response).setCharacterEncoding("UTF-8");
			if ( ae!=null && ae.indexOf("gzip")>=0 ){
				((HttpServletResponse)response).addHeader("Content-Encoding", "gzip");
				java.util.zip.GZIPOutputStream zipOut = new java.util.zip.GZIPOutputStream(response.getOutputStream());
				osw = new OutputStreamWriter(zipOut, "UTF-8");
			}else{
				osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			}
//			osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			
			JSONObject jobj = new JSONObject();
			jobj.put("SUCCESS", 0);
			jobj.put("MSGCODE", (String)request.getAttribute(FrameworkConstant.ERROR_CODE));
			jobj.put("MSG", (String)request.getAttribute(FrameworkConstant.ERROR_MESSAGE));
			
			System.out.println("jobj.toString:="+jobj.toString());
//			jobj.write(osw);
			osw.write((jobj.toString()));
			osw.flush();
		} catch (Exception e) {
			logger.error("Channel HandleException Error!", e);
		}finally{
			if ( osw!=null ){
				try{
					osw.close();
				}catch(Exception e){
					logger.error("Close Channel error", e);
				}
			}
		}
	}

	protected void presentResult(ServletRequest request,ServletResponse response, String actionId, int forwardCode,
			PresentationContent presentationContent) throws FrameException {
		
		OutputStreamWriter osw = null;
		try {
			DataContext dc = (DataContext) request.getAttribute(FrameworkConstant.DMS_CONTEXT);
			DataElementInfo[] elements = dc.keys();

			//检查客户端是否接受压缩流
			String ae = ((HttpServletRequest)request).getHeader("Accept-Encoding");
			response.setContentType("application/json");
//			response.setContentType("text/plain");
			(response).setCharacterEncoding("UTF-8");
			((ServletResponse) response).setCharacterEncoding("UTF-8");
			if ( ae!=null && ae.indexOf("gzip")>=0 ){
				((HttpServletResponse)response).addHeader("Content-Encoding", "gzip");
				java.util.zip.GZIPOutputStream zipOut = new java.util.zip.GZIPOutputStream(response.getOutputStream());
				osw = new OutputStreamWriter(zipOut, "UTF-8"); 
//			     Content-Encoding:gzip
//			     eyJNU0ciOiLOtNaqtO3O8yIsIlNVQ0NFU1MiOjAsIk1TR0NPREUiOiJFQy1GUk0tMTAwMCJ9 
//				 应答的压缩流格式
			}else{
				osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			}
//			osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			
			JSONObject jobj = new JSONObject();
			String errorCode = (String) request.getAttribute(FrameworkConstant.ERROR_CODE);
			if (errorCode != null && errorCode.trim().length() > 0) {  //执行Action错误				
				jobj.put("SUCCESS", 0);
				jobj.put("MSGCODE", (String)request.getAttribute(FrameworkConstant.ERROR_CODE));
				jobj.put("MSG", (String)request.getAttribute(FrameworkConstant.ERROR_MESSAGE));
			} else { //成功返回
				jobj.put("SUCCESS", 1);

				for (int i = 0; i < elements.length; i++) {
					if (!elements[i].isOutput())
						continue;
					writeVar(jobj,elements[i],dc.getValue(elements[i].getName(),elements[i].getType()));
				}
			}	
			
			System.out.println("jobj.toString:="+jobj.toString());
//			jobj.write(osw,"");
//			osw.write(encrypt.encrypt(jobj.toString()));
			osw.write(jobj.toString());
			osw.flush();
		} catch (Exception err) {
			err.printStackTrace();
			throw new FrameException("EC_FRM-1101", MessageService.getInstance()
					.getMessage("EC_FRM-1101", actionId), err);
		} finally {
			try {
				if (osw != null)
					osw.close();
			} catch (Exception e) {
				logger.error("Close Channel error", e);
			}
		}
	}
	

//=============================================================================	
	private static void writeVar(Object jobj,DataElementInfo dataDef,Object val) throws Exception{
		if ( val==null ) return ;
		if ( val instanceof Object[] && ((Object[])val).length==0 ) return ;
		
		if ( DataElementType.TYPE_STRING== dataDef.getType() ){
			((JSONObject)jobj).put(dataDef.getName(), val);
		}else if(DataElementType.TYPE_INTEGER==dataDef.getType() ){
			((JSONObject)jobj).put(dataDef.getName(), val);
		}else if(DataElementType.TYPE_LONG==dataDef.getType()){
			((JSONObject)jobj).put(dataDef.getName(), val);
		}else if(DataElementType.TYPE_FLOAT==dataDef.getType()){
			((JSONObject)jobj).put(dataDef.getName(), val);
		}else if(DataElementType.TYPE_DOUBLE==dataDef.getType()){
			((JSONObject)jobj).put(dataDef.getName(), val);
		}else if(DataElementType.TYPE_OBJECT==dataDef.getType()){
			JSONObject jino = new JSONObject();
			if ( val instanceof DynaBean ){
				writeDynaBean(jino,JSONObject.prefix,(DynaBean)val);
			}else if( val instanceof VO ){
				writeVO(jino,JSONObject.prefix,(VO)val);
			}else if( val instanceof DataBean ){
				writeDataBean(jino,JSONObject.prefix,(DataBean)val);
			}else{
				throw new Exception("not supported object type:"+val.getClass());
			}
			((JSONObject)jobj).put(dataDef.getName(), jino);
		}else if(DataElementType.TYPE_ARRAY==dataDef.getType()){
			JSONArray jarray = new JSONArray();
			Object[] objs = (Object[])val;
			for( Object obj : objs ){
				JSONObject jino = new JSONObject();
				if ( obj instanceof DynaBean ){
					writeDynaBean(jino,JSONObject.prefix,(DynaBean)obj);
				}else if( obj instanceof VO ){
					writeVO(jino,JSONObject.prefix,(VO)obj);
				}else if( obj instanceof DataBean ){
					writeDataBean(jino,JSONObject.prefix,(DataBean)obj);
				}else{
					throw new Exception("not supported object type:"+obj.getClass());
				}
				jarray.put(jino);
			}
			((JSONObject)jobj).put(dataDef.getName(), jarray);
		}
	}
	private static void writeDynaBean(Object jobj,String prefix,DynaBean bean) throws Exception{
		if ( bean==null ) return ;
		Iterator ite = bean.getNames();
		String key = null;
		Object val = null;
		while( ite.hasNext() ){
			key = (String)ite.next();
			val = bean.get(key);
			if ( val ==null ) continue;
	
			if ( val instanceof String ){
				((JSONObject)jobj).put(key, val);
			}else if( val instanceof Integer){
				((JSONObject)jobj).put(key, val);
			}else if( val instanceof Long){
				((JSONObject)jobj).put(key, val);
			}else if( val instanceof Float){
				((JSONObject)jobj).put(key, val);
			}else if( val instanceof Double){
				((JSONObject)jobj).put(key, val);
			}else if( val instanceof Date){
				((JSONObject)jobj).put(key, val==null?null:sdf.format(val));
			}else{
				throw new Exception("not supported type : "+val.getClass().getName());
			}
		}
	}
	private static void writeVO(Object jobj,String prefix, VO vo) throws Exception{
		if ( vo==null ) return ;
		Method[] methods=vo.getClass().getMethods();
		Class retType=null;
		Object val = null;
		Object[] params = new Object[0];
		String name = null;
		for (Method m : methods){
			if ( m.getName().startsWith("set") 
					|| m.getName().equals("getClass")
					|| !m.getName().startsWith("get")) continue;
			
			name = m.getName().substring(3);
			retType = m.getReturnType();
			val = m.invoke(vo, params);
			
			if ( retType.equals(Integer.class) || retType.equals(Integer.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Long.class) || retType.equals(Long.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Float.class) || retType.equals(Float.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Double.class) || retType.equals(Double.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(String.class)){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Date.class) ){
				((JSONObject)jobj).put(name, val==null?null:sdf.format((Date)val));
			}else if( retType.equals(LinkedList.class) ){
				if ( val!=null && ((LinkedList)val).size()>0 ){
					JSONArray jarray =new JSONArray();
					for ( Object obj : (LinkedList)val ){
						JSONObject jino = new JSONObject();
						writeVO(jino,prefix+JSONObject.prefix,(VO)obj);
						jarray.put(jino);
					}
					((JSONObject)jobj).put(name, jarray);
				}				
			}else{
				throw new Exception("not support! Name="+name+" retType="+retType+" val="+val);
			}
		}
	}
	private static void writeDataBean(Object jobj,String prefix,DataBean bean) throws Exception{
		if ( bean==null ) return ;
		Method[] methods=bean.getClass().getMethods();
		Class retType=null;
		Object val = null;
		Object[] params = new Object[0];
		String name = null;
		for (Method m : methods){
			if ( m.getName().startsWith("set") 
					|| m.getName().equals("getClass")
					|| !m.getName().startsWith("get")) continue;
			
			name = m.getName().substring(3);
			retType = m.getReturnType();
			val = m.invoke(bean, params );
			
			if ( retType.equals(Integer.class) || retType.equals(Integer.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Long.class) || retType.equals(Long.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Float.class) || retType.equals(Float.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Double.class) || retType.equals(Double.TYPE) ){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(String.class)){
				((JSONObject)jobj).put(name, val);
			}else if( retType.equals(Date.class) ){
				((JSONObject)jobj).put(name, val==null?null:sdf.format((Date)val));
			}else{
				throw new Exception("not support! Name="+name+" retType="+retType+" val="+val);
			}
		}
	}	/**
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

	}

}
