/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:YouBusSelectTag.java
 * �� ������:2015��4��5��-����12:22:06
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.info.base.po.TsDataDicPO;
import com.youbus.framework.comm.TruckInsNativeCacheService;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:YouBusSelectTag
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��5�� ����12:22:06
 * �޸ı�ע:
 * @version 1.0.0
 * ���select��ǩ
 * <select id="1111" name="1111" class="selectclass" datasource="�����ֵ��type����ehcache��name:��DATA_DIC">
	<option class="optionclass" value ="">----</option>
    <option class="optionclass" value ="key">desc</option>
    ... ...
    
    </select>
    <yb:select dataSource="DATA_DIC.TRIP_TYPE"/>
 */
public class YouBusSelectTag extends SimpleTagSupport {

	/**
	 * ����һ���µ�ʵ�� YouBusSelectTag.
	 *
	 */
	private String selectId;
	private String selectClass;
	private String dataSource;
	private String optionClass;
	private String selectName;
	private String includeNull;
	/**
	 * @param selectId the selectId to set
	 */
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}
	public void setIncludeNull(String includeNull) {
		this.includeNull = includeNull;
	}

	/**
	 * @param selectClass the selectClass to set
	 */
	public void setSelectClass(String selectClass) {
		this.selectClass = selectClass;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param optionClass the optionClass to set
	 */
	public void setOptionClass(String optionClass) {
		this.optionClass = optionClass;
	}
	
	
	/**
	 * @param selectName the selectName to set
	 */
	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public YouBusSelectTag() {
		// TODO Auto-generated constructor stub
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
		String typeCode="CITY_CACHE.aq1".split("\\.")[1];
		System.out.println(typeCode);
	}
	
	/**
	 * ����dataSource ��ȡ��Ӧ�ֵ�
	 */
	public void doTag() throws JspException, IOException{
		JspWriter out=getJspContext().getOut();
//		Cache cityCache=YoubusNativeCacheService.CITY_CACHE;
		
		Cache cache=getSwitchCache(dataSource);
		System.out.println(dataSource);
		if(dataSource.split("\\.").length<2)return;
		String typeCode=dataSource.split("\\.")[1];
		StringBuffer returnStr=new StringBuffer();
		System.out.println(dataSource);
		returnStr.append("<select id=\""+selectId+"\" name=\""+selectName+"\" class=\""+selectClass+"\" >");

//		out.println("<select id=\""+selectId+"\" name=\""+selectName+"\" class=\""+selectClass+"\" datasource=\""+dataSource+"\">");
//		out.println("<option class=\""+optionClass+"\" value =\"\">----</option>");		
		if("true".equals(includeNull))
			returnStr.append("<option class=\""+optionClass+"\" value =\"\">----</option>");	
		Element e=cache.get(typeCode);
		
		String subType=null;
		
		if(dataSource.split("\\.").length>=3){//����SUB_TYPE_CODE
			subType=dataSource.split("\\.")[2];
		}
		if(e==null){
			out.println(returnStr.toString());
			return;
		}
		List<TsDataDicPO> list=(List<TsDataDicPO>)e.getValue();
		
		for(TsDataDicPO bean:list){
			if(DBConUtil.stringNotNULL(subType))
				if(!subType.equals(bean.getSubTypeCode()))continue;
			returnStr.append("<option class=\""+optionClass+"\" value =\""+bean.getCode()+"\">"+bean.getCodeDesc()+"</option>");
//			out.println("<option class=\""+optionClass+"\" value =\""+bean.getCode()+"\">"+bean.getCodeDesc()+"</option>");		
		}
		returnStr.append("</select>");
//		out.println("</select>");
		out.print(returnStr.toString());
	}
	
	private Cache getSwitchCache(String dataSourceName) {
		if (dataSourceName.startsWith("DATA_DIC"))
			return TruckInsNativeCacheService.DATA_DIC_CACHE;
		return null;
	}
}
