/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.xssfilter
 * ��   ��  ��:TruckXssAndSqlFilter.java
 * �� ������:2017��12��12��-����1:00:13
 * Copyright @ 2017-YouBus.com.cn
 */
package com.youbus.framework.comm.xssfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.framework.Framework;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.conf.ActionInfo;
import com.youbus.framework.comm.AppLog;
import com.youbus.framework.comm.YBCommonContant;

/**
 * ������:TruckXssAndSqlFilter
 * ������:��ֹxss��sql������
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��12�� ����1:00:13
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TruckXssAndSqlFilter implements Filter {
	private static Logger log=Logger.getLogger(TruckXssAndSqlFilter.class);
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	public FilterConfig config;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		TruckXssAndSqlReqWrapper xssRequest = new TruckXssAndSqlReqWrapper((HttpServletRequest) request);  
        chain.doFilter(xssRequest, response); 
        
        //20180821b���������־���Ա����
        boolean isRecordActionOptLog="true".equals(config.getInitParameter("action_opt_log"));
		String actionId=(String) (request.getAttribute(FrameworkConstant.ACTION_ID)==null?request.getParameter(FrameworkConstant.ACTION_ID):request.getAttribute(FrameworkConstant.ACTION_ID));
		System.out.println("actionId :="+actionId);
        if(isRecordActionOptLog&&StringUtils.isNotBlank(actionId)){
         Logger log=AppLog.getInstance().getActionOptLog();
         HttpSession session = ((HttpServletRequest)request).getSession();
         String userName = (String) session.getAttribute(YBCommonContant.SESSION_USER_NAME); //TM_SYS_MEMBER
         String userId = (String) session.getAttribute(YBCommonContant.SESSION_USER_ID); //TM_SYS_MEMBER
         
         String actionDesc="";
     	 String clientIp = request.getRemoteAddr();//��÷��𷽵�IP //
         String host= ((HttpServletRequest)request).getHeader("Host")==null?((HttpServletRequest)request).getHeader("host"):((HttpServletRequest)request).getHeader("Host");
			ActionInfo actionInfo= Framework.getActionRepository().getActionDef(actionId);
				if(actionInfo!=null){
					actionDesc=actionInfo.getDecription();
				}
         log.debug("�û�["+userId+"]["+userName+"]["+clientIp+"]ִ��Action["+actionId+"]["+actionDesc+"]");
        }
        //������־end
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		  config = filterConfig;//��ʼ������
	}

}
