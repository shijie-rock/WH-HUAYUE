/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.pofactory
 * ��   ��  ��:TmInsTruckInfoPOFactory.java
 * �� ������:2017��12��12��-����10:35:51
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.pofactory;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;

/**
 * ������:TmInsTruckInfoPOFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��12��12�� ����10:35:51
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TmInsTruckInfoPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TmInsTruckInfoPOFactory.class);
	
	public static PageQuery queryInsTruckList(Connection conn,String truckType,String truckStatus,String truckLincese,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.*,A.ID AS TRUCK_ID,"
				 + " DATE_FORMAT(A.MAKE_DATE,'%Y-%m-%d') AS TRUCK_MAKE_DATE ,"
				 + " DATE_FORMAT(A.LICENSE_DATE,'%Y-%m-%d') AS TRUCK_LICENSE_DATE ,"
				 + " DATE_FORMAT(A.CONFIRM_BEGIN_DATE,'%Y-%m-%d') AS TRUCK_CONFIRM_BEGIN_DATE ,"
				 + " DATE_FORMAT(A.CONFIRM_EXP_DATE,'%Y-%m-%d') AS TRUCK_CONFIRM_EXP_DATE ,"
				 + " COUNT(B.ID) AS IMG_COUNT "
				 + " FROM TM_INS_TRUCK_INFO A "
				 + " LEFT JOIN TT_INS_TRUCK_IMG B "
				 + " ON A.ID=B.TRUCK_ID AND B.STATUS='1' AND B.FREEZE_TAG='0' "
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(truckType))sql+=" AND A.TRUCK_TYPE = '"+truckType+"' ";
		if(StringUtils.isNotBlank(truckStatus))sql+=" AND A.TRUCK_STATUS ='"+truckStatus+"' ";
		if(StringUtils.isNotBlank(truckLincese))sql+=" AND A.TRUCK_LICENSE LIKE '%"+truckLincese+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
		sql+=" GROUP BY A.ID  ";
		sql+=" ORDER BY A.ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}

}
