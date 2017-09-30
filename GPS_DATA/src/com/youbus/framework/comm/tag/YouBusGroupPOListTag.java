/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.tag
 * 文   件  名:YouBusGroupOptionTag.java
 * 创 建日期:2015年6月11日-下午1:52:12
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
 * 类名称:YouBusGroupOptionTag
 * 类描述:从缓存读取当前agent的班组信息
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月11日 下午1:52:12
 * 修改备注:
 * @version 1.0.0
 */
public class YouBusGroupPOListTag extends SimpleTagSupport {

	/**
	 * 创建一个新的实例 YouBusGroupOptionTag.
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
