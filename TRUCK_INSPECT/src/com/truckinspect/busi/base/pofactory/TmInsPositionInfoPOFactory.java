/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.pofactory
 * ��   ��  ��:TmInsPositionInfoPOFactory.java
 * �� ������:2017��12��3��-����10:42:29
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.pofactory;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;

/**
 * ������:TmInsPositionInfoPOFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��3�� ����10:42:29
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TmInsPositionInfoPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TmInsPositionInfoPOFactory.class);
	
	
	public static PageQuery queryInsPositionList(Connection conn,String positionCode,String positionName,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS POSITION_ID "
				 + " FROM TM_INS_POSITION_INFO A WHERE 1=1 AND STATUS='1' ";
		if(StringUtils.isNotBlank(positionCode))sql+=" AND POSITION_CODE LIKE '%"+positionCode+"%' ";
		if(StringUtils.isNotBlank(positionName))sql+=" AND POSITION_NAME LIKE '%"+positionName+"%' ";
		if(!includeStop)sql+=" AND FREEZE_TAG ='0' ";
		sql+=" ORDER BY ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
}
