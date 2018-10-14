/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:YouBusCacheTag.java
 * �� ������:2015��4��29��-����4:12:23
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.ehcache.Cache;

import com.youbus.framework.comm.YoubusNativeCacheService;

/**
 * ������:YouBusCacheTag
 * ������:���ݻ������ּ�key��������Ӧ�Ļ��漰key��Ӧ��ֵ������ǰ̨��ʾ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��29�� ����4:12:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YouBusCacheTag extends SimpleTagSupport {

	/**
	 * ����һ���µ�ʵ�� YouBusCacheTag.
	 *
	 */
	public YouBusCacheTag() {
		// TODO Auto-generated constructor stub
	}
	
	private String CName; //��Ӧ��������
	private String KName; //��Ӧkey
	

	/**
	 * cName
	 *
	 * @return  the cName
	 * @since   1.0.0
	 */
	
	public String getCName() {
		return CName;
	}


	/**
	 * @param cName the cName to set
	 */
	public void setCName(String cName) {
		CName = cName;
	}


	/**
	 * kName
	 *
	 * @return  the kName
	 * @since   1.0.0
	 */
	
	public String getKName() {
		return KName;
	}


	/**
	 * @param kName the kName to set
	 */
	public void setKName(String kName) {
		KName = kName;
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void doTag() throws IOException{
		JspWriter out=getJspContext().getOut();
		HttpSession session = ((PageContext)this.getJspContext()).getSession();
		HttpServletRequest request = (HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		
		Cache targetCache=YoubusNativeCacheService.getCache(CName);
		String str=targetCache!=null?targetCache.get(KName).getValue().toString():null;
		out.print(str);
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
