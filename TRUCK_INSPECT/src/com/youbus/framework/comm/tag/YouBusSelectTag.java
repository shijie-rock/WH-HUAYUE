/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.tag
 * 文   件  名:YouBusSelectTag.java
 * 创 建日期:2015年4月5日-下午12:22:06
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
 * 类名称:YouBusSelectTag
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月5日 下午12:22:06
 * 修改备注:
 * @version 1.0.0
 * 输出select标签
 * <select id="1111" name="1111" class="selectclass" datasource="数据字典的type或者ehcache的name:如DATA_DIC">
	<option class="optionclass" value ="">----</option>
    <option class="optionclass" value ="key">desc</option>
    ... ...
    
    </select>
    <yb:select dataSource="DATA_DIC.TRIP_TYPE"/>
 */
public class YouBusSelectTag extends SimpleTagSupport {

	/**
	 * 创建一个新的实例 YouBusSelectTag.
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
		String typeCode="CITY_CACHE.aq1".split("\\.")[1];
		System.out.println(typeCode);
	}
	
	/**
	 * 根据dataSource 获取对应字典
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
		
		if(dataSource.split("\\.").length>=3){//含有SUB_TYPE_CODE
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
