/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.tag
 * 文   件  名:YouBusCacheTag.java
 * 创 建日期:2015年4月29日-下午4:12:23
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

import com.youbus.framework.comm.TruckInsNativeCacheService;

/**
 * 类名称:YouBusCacheTag
 * 类描述:根据缓存名字及key，查找响应的缓存及key对应的值，进行前台显示
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月29日 下午4:12:23
 * 修改备注:
 * @version 1.0.0
 */
public class YouBusCacheTag extends SimpleTagSupport {

	/**
	 * 创建一个新的实例 YouBusCacheTag.
	 *
	 */
	public YouBusCacheTag() {
		// TODO Auto-generated constructor stub
	}
	
	private String CName; //对应缓存名字
	private String KName; //对应key
	

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
		
		Cache targetCache=TruckInsNativeCacheService.getCache(CName);
		String str=targetCache!=null?targetCache.get(KName).getValue().toString():null;
		out.print(str);
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
