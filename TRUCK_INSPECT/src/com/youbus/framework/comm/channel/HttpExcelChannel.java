package com.youbus.framework.comm.channel;

import java.io.OutputStream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.infoservice.framework.Channel;
import com.infoservice.framework.Framework;
import com.infoservice.framework.conf.ActionInfo;
import com.infoservice.framework.conf.DataElementInfo;
import com.infoservice.framework.conf.PresentationContent;
import com.infoservice.framework.datacontext.DataContext;
import com.infoservice.framework.datacontext.DataElementType;
import com.infoservice.framework.datacontext.HttpRequestDataContext;
import com.infoservice.framework.datacontext.HttpSessionDataContext;
import com.infoservice.framework.datacontext.UnlimitedUpload;
import com.infoservice.framework.exceptions.ActionNotFoundException;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.MessageService;

public class HttpExcelChannel extends Channel {

	private static Logger logger = LogManager.getLogger(HttpExcelChannel.class);
	 
	@Override
	protected DataContext buildSessionDataContext(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    return new HttpSessionDataContext(this.channelDef.getSessionContextDef(), httpRequest.getSession());
	}

	@Override
	protected DataContext buildActionDataContext(ServletRequest request, ServletResponse response) throws FrameException {
		UnlimitedUpload uu = null;
	    String actionId = ((HttpServletRequest)request).getParameter("action");

	    if ((request.getContentType() != null) && (request.getContentType().indexOf("multipart/form-data") >= 0)) {
	      try {
	        uu = new UnlimitedUpload((HttpServletRequest)request);
	        Object[] objs = uu.getParam("action");
	        if (objs.length > 0) actionId = (String)objs[0]; 
	      }
	      catch (Exception e) { throw new FrameException("EC-FRM-1011", MessageService.getInstance().getMessage("EC-FRM-1011"), e); }


	    }

	    if (actionId == null) {
	      throw new ActionNotFoundException(actionId);
	    }

	    ActionInfo actionDef = Framework.getActionRepository().getActionDef(actionId);
	    if (actionDef == null)
	    {
	      throw new ActionNotFoundException(actionId);
	    }

	    HttpRequestDataContext hrdc = new HttpRequestDataContext(actionDef.getActionContextDef(), (HttpServletRequest)request);
	    hrdc.setUnlimitedUpload(uu);
	    return hrdc;
	}

	@Override
	protected void presentResult(ServletRequest request, ServletResponse response, String actionId, int returnCode, PresentationContent presentationContent) throws FrameException {
		
		OutputStream osw = null;
	    try {
	      DataContext dc = (DataContext)request.getAttribute("dms_context");
	      DataElementInfo[] elements = dc.keys();

	      String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";  
          String headStr = "attachment; filename=\"" + fileName + "\"";  

	      ((HttpServletResponse)response).setContentType("application/x-msdownload");
	      ((HttpServletResponse)response).setHeader("Content-Disposition", headStr);

	      osw = response.getOutputStream();

	      for (int i = 0; i < elements.length; i++) {
	          if (elements[i].isOutput() && DataElementType.TYPE_OBJECT == elements[i].getType()) {
	        	  
	        	  Object obj = dc.getValue(elements[i].getName(), elements[i].getType());
	        	  ((HSSFWorkbook) obj).write(osw);  
	          }
	      }
          
          osw.flush();
          
	    } catch (Exception err) {
	    	
	      throw new FrameException("EC_FRM-1101", MessageService.getInstance().getMessage("EC_FRM-1101", new Object[] { actionId }), err);
	      
	    } finally {
	      try {
	        if (osw != null)
	          osw.close();
	      } catch (Exception e) {
	        logger.error("Close Channel error", e);
	      }
	    }
	}

	@Override
	protected void handleException(ServletRequest request, ServletResponse response, String actionId, Throwable exception, PresentationContent presentationContent) throws FrameException {
		
	}

}
