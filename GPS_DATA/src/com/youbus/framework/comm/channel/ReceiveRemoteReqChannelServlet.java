/**
 * ��Ŀ����:AUTH_CENTER
 * ��         ��:com.youbus.authcenter.common.channel
 * ��   ��  ��:ReceiveRemoteReqChannelServlet.java
 * �� ������:2015��6��2��-����12:04:12
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.channel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.ChannelListener;
import com.infoservice.framework.Framework;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.comm.security.MsgEncryptService;
import com.youbus.framework.comm.security.MyEncryptServiceImp;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:ReceiveRemoteReqChannelServlet
 * ������:����Զ�̺�̨�����糵��ƽ̨����Ӫƽ̨����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��2�� ����12:04:12
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ReceiveRemoteReqChannelServlet extends HttpServlet implements ChannelListener {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -8861438788660813576L;

	/**
	 * ����һ���µ�ʵ�� ReceiveRemoteReqChannelServlet.
	 *
	 */
	public ReceiveRemoteReqChannelServlet() {
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
	

	/**
	 * @see javax.servlet.http.HttpServlet#service(HttpServletRequest, HttpServletResponse)
	 */
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException {
		setEncoding(request);	
//		System.out.println(request.getAttributeNames().nextElement());
//		System.out.println((request).getAttribute("req"));//no value
//		System.out.println((request).getParameter("req"));
		String encryptReqStr=request.getParameter("req");
		if(encryptReqStr!=null&&encryptReqStr.trim().length()>0){
			String reqStr=encrypt.decrypt(encryptReqStr); //TODO ����
			List<ParamBean> list=new ArrayList<ParamBean>();
			if(DBConUtil.stringNotNULL(reqStr)){
			String[] reqArray=reqStr.split("&");
			String key=null;
			String value=null;
			String[] kvArray;
			for(String temp:reqArray){
				kvArray=temp.split("=");
				if(kvArray!=null){
					key=kvArray[0];
					if(kvArray.length>1)value=kvArray[1];
					else value=null;
					request.setAttribute(key, (value=="null"||value==""?null:value));
				}
				
			}
		}
		}
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
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
