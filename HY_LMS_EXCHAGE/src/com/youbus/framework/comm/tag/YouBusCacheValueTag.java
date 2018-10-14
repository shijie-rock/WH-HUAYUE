package com.youbus.framework.comm.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.youbus.framework.comm.YoubusNativeCacheService;

/**
 * 根据缓存名字及key，查找响应的缓存及key对应的值，用于其他标签输出使用
 * 
 * @author Bill Tang
 *
 */
public class YouBusCacheValueTag extends SimpleTagSupport {

	private String name;//对应缓存名字(存在多个大缓存)
	private String key;//对应key(同大一缓存下的不同key值缓存)
	private String map;//返回前台页面的缓存列表
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public void doTag() throws JspException, IOException {
		
		if("AGENT_GROUP_LIST_CACHE".equalsIgnoreCase(this.name)) {

			Element e = YoubusNativeCacheService.getGroupListCache(this.name, this.key);
			this.getJspContext().setAttribute(map, e.getValue());
			
		} else {

			Cache targetCache = YoubusNativeCacheService.getCache(this.name);
			this.getJspContext().setAttribute(map, targetCache!=null?targetCache.get(key).getValue():null);
		}
	}
}
