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

import org.apache.log4j.Logger;

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

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		TruckXssAndSqlReqWrapper xssRequest = new TruckXssAndSqlReqWrapper((HttpServletRequest) request);  
        chain.doFilter(xssRequest, response); 

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
