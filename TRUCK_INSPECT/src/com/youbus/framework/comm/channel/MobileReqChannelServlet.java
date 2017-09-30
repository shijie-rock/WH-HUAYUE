/**
 * 项目名称:YOUBUS_GPS
 * 包         名:com.youbus.framework.comm.channel
 * 文   件  名:MobileReqChannelServlet.java
 * 创 建日期:2015年8月4日-上午10:56:37
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.channel;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.ChannelListener;
import com.infoservice.framework.Framework;
import com.youbus.framework.comm.security.MsgEncryptService;
import com.youbus.framework.comm.security.MyEncryptServiceImp;

/**
 * 类名称:MobileReqChannelServlet
 * 类描述:接收移动端请求
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年8月4日 上午10:56:37
 * 修改备注:
 * @version 1.0.0
 */
public class MobileReqChannelServlet extends HttpServlet implements ChannelListener {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 3462144422839890294L;

	/**
	 * 创建一个新的实例 MobileReqChannelServlet.
	 *
	 */
	public MobileReqChannelServlet() {
		// TODO Auto-generated constructor stub
	}
	private static Logger log = LogManager.getLogger(ReceiveRemoteReqChannelServlet.class);
	private String defEncoding = "ISO8859-1";
	private String chnId	= null;
	private MsgEncryptService encrypt=null;
	
	public void init(ServletConfig config) throws ServletException {
		String tmp = config.getInitParameter("DefaultEncoding");
		encrypt=MyEncryptServiceImp.getInstance();
		if ( tmp!=null && tmp.trim().length()>0 ){
			this.defEncoding = tmp.trim();
		}
		log.info("Set default request encoding : "+this.defEncoding);
		
		tmp = config.getInitParameter("ChannelId");
		if ( tmp==null || tmp.trim().length()==0 ){
			System.err.println("Need read ChannelId parameter!");
		}else{
			chnId = tmp.trim();
		}
		log.info("Set Channel ID : "+this.chnId);
	}
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException {
		setEncoding(request);	
//		System.out.println(request.getAttributeNames().nextElement());
//		System.out.println((request).getAttribute("req"));//no value
//		System.out.println((request).getParameter("req"));
//		String encryptReqStr=request.getParameter("req");
//		if(encryptReqStr!=null&&encryptReqStr.trim().length()>0){
//			String reqStr=encrypt.decrypt(encryptReqStr); //TODO 解码
//			List<ParamBean> list=new ArrayList<ParamBean>();
//			if(DBConUtil.stringNotNULL(reqStr)){
//			String[] reqArray=reqStr.split("&");
//			String key=null;
//			String value=null;
//			String[] kvArray;
//			for(String temp:reqArray){
//				kvArray=temp.split("=");
//				if(kvArray!=null){
//					key=kvArray[0];
//					if(kvArray.length>1)value=kvArray[1];
//					else value=null;
//					request.setAttribute(key, (value=="null"||value==""?null:value));
//				}
//				
//			}
//		}
//		}
		//接收jsonList 地址上报
		try {
			String contentEncoding = request.getHeader("Content-Encoding");  
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
        response.setContentType("text/html;charset=UTF-8");  
//        String acceptjson = "";   
//        try {  
//            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));  
//            StringBuffer sb = new StringBuffer("");  
//            String temp;  
//            while ((temp = br.readLine()) != null) {  
//                sb.append(temp);  
//            }  
//            br.close();  
//            acceptjson = sb.toString();  
//            System.out.println(acceptjson);
//            if(acceptjson!=null&&acceptjson.trim().length()>0)
//            request.setAttribute("JSON_STRING",acceptjson);
//           } catch (Exception e) {  
//            e.printStackTrace();  
//            try {
//				response.getWriter().write("error");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}  
//        }
		Framework.getChannelRepository().getChannel(this.chnId).process(request, response);
	}
	
	private void setEncoding(HttpServletRequest request) {		
		try{			
			request.setCharacterEncoding(this.defEncoding);
		}catch(Exception e){
			log.error("set Encoding error!",e);
		}		
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

	}


}
