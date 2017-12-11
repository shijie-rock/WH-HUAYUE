/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.pofactory
 * 文   件  名:TmInspactGroupPOFactory.java
 * 创 建日期:2017年8月20日-下午5:09:04
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.pofactory;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;

/**
 * 类名称:TmInspactGroupPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:09:04
 * 修改备注:
 * @version 1.0.0
 */
public class TmInspactGroupPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TmInspactGroupPOFactory.class);
		
	public static PageQuery queryInsGroupList(Connection conn,String groupName,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.*,A.INS_GROUP_DESC AS INS_GROUP_ITEM ,A.ID AS INS_GROUP_ID "
				 + " FROM TM_INSPACT_GROUP A "
				 + " WHERE 1=1 "
				 + " AND STATUS='1' ";
		if(StringUtils.isNotBlank(groupName))sql+=" AND INS_GROUP_NAME LIKE '%"+groupName+"%' ";
		if(!includeStop)sql+=" AND FREEZE_TAG ='0' ";
		sql+=" ORDER BY ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
}
