/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.youbus.framework.comm.xssfilter
 * 文   件  名:TruckXssAndSqlFilter.java
 * 创 建日期:2017年12月12日-下午1:00:13
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
 * 类名称:TruckXssAndSqlFilter
 * 类描述:防止xss及sql过滤器
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年12月12日 下午1:00:13
 * 修改备注:
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
        
        //20180821b补充操作日志，以备审计
        boolean isRecordActionOptLog="true".equals(config.getInitParameter("action_opt_log"));
		String actionId=(String) (request.getAttribute(FrameworkConstant.ACTION_ID)==null?request.getParameter(FrameworkConstant.ACTION_ID):request.getAttribute(FrameworkConstant.ACTION_ID));
		System.out.println("actionId :="+actionId);
        if(isRecordActionOptLog&&StringUtils.isNotBlank(actionId)){
         Logger log=AppLog.getInstance().getActionOptLog();
         HttpSession session = ((HttpServletRequest)request).getSession();
         String userName = (String) session.getAttribute(YBCommonContant.SESSION_USER_NAME); //TM_SYS_MEMBER
         String userId = (String) session.getAttribute(YBCommonContant.SESSION_USER_ID); //TM_SYS_MEMBER
         
         String actionDesc="";
     	 String clientIp = request.getRemoteAddr();//获得发起方的IP //
         String host= ((HttpServletRequest)request).getHeader("Host")==null?((HttpServletRequest)request).getHeader("host"):((HttpServletRequest)request).getHeader("Host");
			ActionInfo actionInfo= Framework.getActionRepository().getActionDef(actionId);
				if(actionInfo!=null){
					actionDesc=actionInfo.getDecription();
				}
         log.debug("用户["+userId+"]["+userName+"]["+clientIp+"]执行Action["+actionId+"]["+actionDesc+"]");
        }
        //补充日志end
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		  config = filterConfig;//初始化参数
	}

}
