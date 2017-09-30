package com.youbus.framework.comm.channel;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infoservice.framework.ChannelListener;
import com.infoservice.framework.Framework;

@SuppressWarnings("serial")
public class HttpExcelChannelServlet extends HttpServlet implements ChannelListener {

	private String defEncoding = "ISO8859-1";
	private String chnId = null;
	  
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String tmp = config.getInitParameter("DefaultEncoding");
		
	    if ((tmp != null) && (tmp.trim().length() > 0)) {
	      
	    	this.defEncoding = tmp.trim();
	    }
	    
	    System.out.println("Set default request encoding : " + this.defEncoding);

	    tmp = config.getInitParameter("ChannelId");
	    if ((tmp == null) || (tmp.trim().length() == 0))
	      
	    	System.err.println("Need read ChannelId parameter!");
	    
	    else {
	      
	    	this.chnId = tmp.trim();
	    }
	    
	    System.out.println("Set Channel ID : " + this.chnId);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doGet(request, response);
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		setEncoding(request);
		
		System.out.println("request1:"+request.getParameter("action"));
		System.out.println("request2:"+request.getAttribute("action"));
		
		Framework.getChannelRepository().getChannel(this.chnId).process(request, response);
	}

	private void setEncoding(HttpServletRequest request) {
	    try {
	      request.setCharacterEncoding(this.defEncoding);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}
