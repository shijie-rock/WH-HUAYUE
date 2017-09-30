/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:CharacterEncodingFilter.java
 * �� ������:2015��4��15��-����11:17:17
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.jsontest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ������:CharacterEncodingFilter
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��15�� ����11:17:17
 * �޸ı�ע:
 * @version 1.0.0
 */
public class CharacterEncodingFilter implements Filter {

	/**
	 * ����һ���µ�ʵ�� CharacterEncodingFilter.
	 *
	 */
	public CharacterEncodingFilter() {
		// TODO Auto-generated constructor stub
	}
	private String encoding = null; 
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
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.encoding = filterConfig.getInitParameter("encoding");
	}

}
