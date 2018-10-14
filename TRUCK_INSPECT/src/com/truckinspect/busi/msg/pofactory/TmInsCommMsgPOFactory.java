/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.msg.pofactory
 * ��   ��  ��:TmInsCommMsgPOFactory.java
 * �� ������:2018��9��29��-����6:05:49
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.msg.pofactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:TmInsCommMsgPOFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��9��29�� ����6:05:49
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TmInsCommMsgPOFactory extends POFactory {
	
	private static Logger log=Logger.getLogger(TmInsCommMsgPOFactory.class);
	
	public static PageQuery queryCommMsgList(Connection conn,String msgLevel,String msgContent,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.*,IF(PUBLISH_TAG='1',B.MEMBER_NAME,'') AS PUB_NAME, "
				+ " IF(PUBLISH_TAG='1',DATE_FORMAT(A.PUBLISH_TIME,'%Y-%m-%d %H:%i:%S'),'') AS PUB_TIME_STR  "
				+ " FROM TM_INS_COMM_MSG A "
				+ " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.PUBLISH_MEM_ID=B.ID "
				+ " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(msgLevel))sql+=" AND A.COM_MSG_LEVEL LIKE '%"+msgLevel+"%' ";
		if(StringUtils.isNotBlank(msgContent))sql+=" AND A.COMM_MSG_CONTENT LIKE '%"+msgContent+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
		sql+=" ORDER BY A.CREATE_TIME DESC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
	
	public static DynaBean queryCommMsgDetail(Connection conn,int msgId){
		
		String sql=" SELECT A.ID ,"
				 + " A.COMM_MSG_TITLE,A.COMM_MSG_SUB_TITLE,A.COMM_MSG_CONTENT, A.COM_MSG_LEVEL,"
				 + " A.PUBLISH_TAG,"
				 + " IF(A.PUBLISH_TAG='1',B.MEMBER_NAME,'') AS PUB_MEM_NAME,"
				 + " IF(A.PUBLISH_TAG='1',DATE_FORMAT(A.PUBLISH_TIME,'%Y-%m-%d %H:%i:%S'),'') AS PUB_TIME_STR,"
				 + " C.MEMBER_NAME AS CREATE_MEM_NAME,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR "
				 + " FROM TM_INS_COMM_MSG A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.PUBLISH_MEM_ID=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON B.STATUS='1' AND A.CREATE_BY=C.ID "
				 + " WHERE A.STATUS='1' "
				 + " AND A.ID= "+msgId;
		
		
		log.debug("sql:="+sql);
		try {
			return DBConUtil.getSingleResult(conn, sql, "COMM_MSG_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ҳ��ѯ�ѷ�������
	 * ��   ��  ��:queryMainPageCommMsgList
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@return
	 * ��   ��  ֵ:List<DynaBean>
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryMainPageCommMsgList(Connection conn,int count){
		
		String sql=" SELECT A.*,IF(PUBLISH_TAG='1',B.MEMBER_NAME,'') AS PUB_NAME, "
				+ " IF(PUBLISH_TAG='1',DATE_FORMAT(A.PUBLISH_TIME,'%Y-%m-%d %H:%i:%S'),'') AS PUB_TIME_STR  "
				+ " FROM TM_INS_COMM_MSG A "
				+ " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.PUBLISH_MEM_ID=B.ID "
				+ " WHERE 1=1 AND A.STATUS='1' AND A.FREEZE_TAG ='0'  AND A.PUBLISH_TAG='1' ";
		sql+=" ORDER BY IFNULL(A.PUBLISH_TIME,A.CREATE_TIME ) DESC "
			+" LIMIT  "+count;
		log.debug("sql:="+sql);
		
		try {
			return DBConUtil.getResult(conn, sql, "COMM_MSG_LIST");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
