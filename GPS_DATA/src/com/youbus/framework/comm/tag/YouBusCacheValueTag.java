package com.youbus.framework.comm.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.youbus.framework.comm.YoubusNativeCacheService;

/**
 * ���ݻ������ּ�key��������Ӧ�Ļ��漰key��Ӧ��ֵ������������ǩ���ʹ��
 * 
 * @author Bill Tang
 *
 */
public class YouBusCacheValueTag extends SimpleTagSupport {

	private String name;//��Ӧ��������(���ڶ���󻺴�)
	private String key;//��Ӧkey(ͬ��һ�����µĲ�ͬkeyֵ����)
	private String map;//����ǰ̨ҳ��Ļ����б�
	
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
