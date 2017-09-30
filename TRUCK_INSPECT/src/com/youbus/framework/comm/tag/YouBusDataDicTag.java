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
 * 类名称:YouBusDataDicTag
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月10日 下午12:22:06
 * 修改备注:
 * @version 1.0.0
 * 输出select标签
    ... ...
    
    </select>
    <yb:dataDic dataDicType="TRIP_TYPE,ASSGIN_TYPE"/>
 */
public class YouBusDataDicTag extends SimpleTagSupport {

	/**
	 * 创建一个新的实例 YouBusDataDicTag.
	 *
	 */
	private String dataDicType; /**ASSIGN_STATUS,BUS_GRADE 多个类别，","分割 **/
	/**
	 * @param selectId the selectId to set
	 */


	public YouBusDataDicTag() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dataDicType the dataDicType to set
	 */
	public void setDataDicType(String dataDicType) {
		this.dataDicType = dataDicType;
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
		
	}
	
	/**
	 * 根据dataSource 获取对应字典集合
	 */
	public void doTag() throws JspException, IOException{
		JspWriter out=getJspContext().getOut();
//		Cache cityCache=YoubusNativeCacheService.CITY_CACHE;
		if(dataDicType==null||dataDicType.trim().length()<1) return;
		Cache cache=TruckInsNativeCacheService.DATA_DIC_CACHE;
		System.out.println(cache);

		String[] typeCodeArray=dataDicType.split(",");
		Element e=null;
		StringBuffer outPutStr=new StringBuffer("");
		for(String typeCode:typeCodeArray){
			String subType=null;
			if(typeCode.split("\\.").length>=2){//含有SUB_TYPE_CODE
				subType=typeCode.split("\\.")[1];
				typeCode=typeCode.split("\\.")[0];
			}
			
			e=cache.get(typeCode);
			if(e==null)continue;

			
			for(TsDataDicPO ddPO:(List<TsDataDicPO>)e.getValue()){
				if(DBConUtil.stringNotNULL(subType)&&!subType.equals(ddPO.getSubTypeCode()))continue;
				outPutStr.append(ddPO.getCode()+":"+ddPO.getCodeDesc()+",");
			}
		}
		String outStr=null;
		if(outPutStr!=null&&outPutStr.length()>1)outStr=outPutStr.substring(0,outPutStr.length()-1);
		System.out.println(outStr);
		out.print(outStr);
		
	}
}
