/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.sys.pofactory
 * ��   ��  ��:TbOptLogsPOFactory.java
 * �� ������:2017��12��7��-����11:44:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.sys.pofactory;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;

/**
 * ������:TbOptLogsPOFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��7�� ����11:44:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TbOptLogsPOFactory extends POFactory {
	
	private static Logger log=Logger.getLogger(TbOptLogsPOFactory.class);
	
	public static PageQuery querySysOptLogsList(Connection conn,String optName,String optContent,String beginTime,String endTime,int customPageSize){
		
		String sql=" SELECT A.ID,IF(A.OPT_NAME IS NULL OR A.OPT_NAME='' ,'--',A.OPT_NAME ) AS OPT_NAME ,"
				 + " A.OPT_ACTION_CODE, A.OPT_ACTION_DESC, "
				 + " IFNULL(A.OPT_CONTENT,'--') AS OPT_CONTENT "
				 + " ,A.ID AS LOGS_ID ,A.OPT_MEMBER_ID, "
				 + " DATE_FORMAT( A.OPT_TIME,'%Y-%m-%d %H:%i:%S') AS OPT_TIME "
				 + " FROM TB_OPT_LOGS A WHERE 1=1 AND STATUS='1' ";
		if(StringUtils.isNotBlank(optName))sql+=" AND OPT_NAME LIKE '%"+optName+"%' ";
		if(StringUtils.isNotBlank(optContent))sql+=" AND OPT_CONTENT LIKE '%"+optContent+"%' ";
		
		if(StringUtils.isNotBlank(beginTime))sql+=" AND OPT_TIME>=  DATE_FORMAT('"+beginTime+"','%Y-%m-%d %H:%i:%S') ";
		if(StringUtils.isNotBlank(endTime))sql+=" AND OPT_TIME<=  DATE_FORMAT('"+endTime+"','%Y-%m-%d %H:%i:%S') ";
		sql+=" ORDER BY ID DESC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
}
