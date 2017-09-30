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
 * ������:YouBusDataDicTag
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��10�� ����12:22:06
 * �޸ı�ע:
 * @version 1.0.0
 * ���select��ǩ
    ... ...
    
    </select>
    <yb:dataDic dataDicType="TRIP_TYPE,ASSGIN_TYPE"/>
 */
public class YouBusDataDicTag extends SimpleTagSupport {

	/**
	 * ����һ���µ�ʵ�� YouBusDataDicTag.
	 *
	 */
	private String dataDicType; /**ASSIGN_STATUS,BUS_GRADE ������","�ָ� **/
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
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		
	}
	
	/**
	 * ����dataSource ��ȡ��Ӧ�ֵ伯��
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
			if(typeCode.split("\\.").length>=2){//����SUB_TYPE_CODE
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
