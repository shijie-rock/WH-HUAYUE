/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.pofactory
 * 文   件  名:TmSysMemberPOFactory.java
 * 创 建日期:2017年9月2日-上午9:45:59
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
 * 类名称:TmSysMemberPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:45:59
 * 修改备注:
 * @version 1.0.0
 */
public class TmSysMemberPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TmSysMemberPOFactory.class);
	
	public static PageQuery queryMemberList(Connection conn,String memberName,String mobile,String memberCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* FROM TM_SYS_MEMBER A WHERE 1=1 ";
		if(StringUtils.isNotBlank(memberName))sql+=" AND MEMBER_NAME LIKE '%"+memberName+"%' ";
		if(StringUtils.isNotBlank(memberCode))sql+=" AND MEMBER_CODE LIKE '%"+memberCode+"%' ";
		if(StringUtils.isNotBlank(mobile))sql+=" AND MOBILE LIKE '%"+mobile+"%' ";
		if(!includeStop)sql+=" AND FREEZE_TAG ='0' ";
		sql+=" ORDER BY ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
	public static PageQuery queryMemberDetailList(Connection conn,String memberName,String mobile,String memberCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.ID ,A.ID AS MEMBER_ID,A.MEMBER_CODE,A.MEMBER_NAME,"
				 + " A.MOBILE,"
				 + " A.EMAIL, "
				 + " IF(A.IS_INSPACTOR='1','检车员','--') AS IS_INSPACTOR,"
				 + " IF(A.CAN_LOGIN_SYS='1','操作员','--') AS CAN_LOGIN_SYS ,"
				 + " A.FREEZE_TAG,"
				 + " A.JOB_TITEL_TYPE,"
				 + " group_concat(DISTINCT R.ROLE_NAME) AS MEMBER_ROLE, "
				 + " group_concat(DISTINCT G.INS_GROUP_NAME) AS MEMBER_INS_GROUP,"
				 + " A.MEMBER_REMARK "
			     + " FROM TM_SYS_MEMBER A "
			     + " LEFT JOIN TT_SYS_MEMBER_ROLE MR "
			     + " ON A.ID=MR.MEMBER_ID AND MR.STATUS='1' "
			     + " LEFT JOIN TB_SYS_ROLE R "
			     + " ON MR.ROLE_CODE=R.ROLE_CODE AND R.STATUS='1' AND R.FREEZE_TAG='0' "
			     + " LEFT JOIN TT_INS_GROUP_MEMBER MG "
			     + " ON MG.MEMBER_ID=A.ID AND MG.STATUS='1' "
			     + " LEFT JOIN TM_INSPACT_GROUP G "
			     + " ON MG.INS_GROUP_ID=G.ID AND G.STATUS='1' AND G.FREEZE_TAG='0' "
				 + " WHERE A.STATUS='1' ";
		
		if(StringUtils.isNotBlank(memberName))sql+=" AND A.MEMBER_NAME LIKE '%"+memberName+"%' ";
		if(StringUtils.isNotBlank(memberCode))sql+=" AND A.MEMBER_CODE LIKE '%"+memberCode+"%' ";
		if(StringUtils.isNotBlank(mobile))sql+=" AND A.MOBILE LIKE '%"+mobile+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
		sql+=" GROUP BY A.ID ";
		sql+=" ORDER BY A.ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
}
