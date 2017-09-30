/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:YouBusGroupOptionTag.java
 * �� ������:2015��6��11��-����1:52:12
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

import com.youbus.framework.comm.YBCommonContant;
import com.youbus.framework.comm.YoubusNativeCacheService;

/**
 * ������:YouBusGroupOptionTag
 * ������:�ӻ����ȡ��ǰagent�İ�����Ϣ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��11�� ����1:52:12
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YouBusGroupPOListTag extends SimpleTagSupport {

	/**
	 * ����һ���µ�ʵ�� YouBusGroupOptionTag.
	 *
	 */
	public YouBusGroupPOListTag() {
		// TODO Auto-generated constructor stub
	}
	
	public void doTag() throws IOException{
		JspWriter out=getJspContext().getOut();
		HttpSession session = ((PageContext)this.getJspContext()).getSession();
		HttpServletRequest request = (HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		
		Object agentId=session.getAttribute(YBCommonContant.SESSION_AGENT_ID);
		if(agentId!=null){
			if(YoubusNativeCacheService.getGroupListCache("AGENT_GROUP_LIST_CACHE", (String)agentId)!=null)
				request.setAttribute("GROUP_PO_LIST", YoubusNativeCacheService.getGroupListCache("AGENT_GROUP_LIST_CACHE",(String)agentId ));
				System.out.println(YoubusNativeCacheService.getGroupListCache("AGENT_GROUP_LIST_CACHE",(String)agentId ));
//				request.setAttribute("GROUP_PO_LIST", YoubusNativeCacheService.getCache("AGENT_GROUP_LIST_CACHE").get((String)agentId).getValue());
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
