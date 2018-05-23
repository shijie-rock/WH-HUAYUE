/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.pofactory
 * 文   件  名:TmInsCheckOrderPOFactory.java
 * 创 建日期:2018年3月26日-下午5:48:18
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.pofactory;

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
 * 类名称:TmInsCheckOrderPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月26日 下午5:48:18
 * 修改备注:
 * @version 1.0.0
 */
public class TmInsCheckOrderPOFactory extends POFactory {
	
	private static Logger log=Logger.getLogger(TmInsCheckOrderPOFactory.class);
	
	public static PageQuery queryInsCheckOrderList(Connection conn,String checkOrderNo,String address,String targetName,String pBeginDate,String pEndDate,
			String rBeginDate,String rEndDate,String checkerName,String checkResult,String checkStatus,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,B.MEMBER_NAME AS CHECKER_NAME,C.MEMBER_NAME AS CREATE_NAME,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS ORDER_CREATE_TIME,"
				 + " DATE_FORMAT(A.CHECK_PL_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS P_BEGIN_TIME, "
				 + " DATE_FORMAT(A.CHECK_PL_END_TIME,'%Y-%m-%d %H:%i:%S') AS P_END_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS R_BEGIN_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_END_TIME,'%Y-%m-%d %H:%i:%S') AS R_END_TIME "
				 + " FROM TM_INS_CHECK_MAIN_ORDER A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CHECK_R_MEMBER_ID=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.CREATE_BY=C.ID"
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(checkOrderNo))sql+=" AND A.CHECK_ORDER_NO LIKE '%"+checkOrderNo.trim()+"%'";
		if(StringUtils.isNotBlank(address))sql+=" AND A.POSITION_NAME LIKE '%"+address.trim()+"%'";
		if(StringUtils.isNotBlank(targetName))sql+=" AND (A.CHECK_TARGET_NAME LIKE '%"+targetName.trim()+"%' OR  A.CHECK_TARGET_CODE LIKE '%"+targetName+"%')  ";
		if(StringUtils.isNotBlank(pBeginDate))sql+=" AND DATE(A.CHECK_PL_BEGIN_TIME) <= '"+pBeginDate+"' ";
		if(StringUtils.isNotBlank(pEndDate))sql+=" AND DATE(A.CHECK_PL_END_TIME) >= '"+pEndDate+"' ";
		if(StringUtils.isNotBlank(rBeginDate))sql+=" AND DATE(A.CHECK_R_BEGIN_TIME) <= '"+rBeginDate+"' ";
		if(StringUtils.isNotBlank(rEndDate))sql+=" AND DATE(A.CHECK_R_END_TIME) >= '"+rEndDate+"' ";
		if(StringUtils.isNotBlank(checkerName))sql+=" AND B.MEMBER_NAME LIKE '%"+checkerName.trim()+"%' ";
		if(StringUtils.isNotBlank(checkResult))sql+=" AND CHECK_ORDER_RESULT='"+checkStatus+"' ";
		if(StringUtils.isNotBlank(checkStatus))sql+=" AND CHECK_ORDER_STATUS='"+checkStatus+"' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.ID ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
		
	}
	
	/**
	 * 根据检查单号，查询检查单明细
	 * 方   法  名:queryInsCheckOrderDetail
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param checkOrderNo
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean queryInsCheckOrderDetail(Connection conn,String checkOrderNo){
		
		String sql=" SELECT O.*,"
				 + " B.MEMBER_NAME AS CHECK_PL_MEMBER_NAME,"
				 + " C.MEMBER_NAME AS CHECK_R_MEMBER_NAME ,"
				 + " DATE_FORMAT(O.CHECK_PL_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS P_BEGIN_TIME, "
				 + " DATE_FORMAT(O.CHECK_PL_END_TIME,'%Y-%m-%d %H:%i:%S') AS P_END_TIME ,"
				 + " DATE_FORMAT(O.CHECK_R_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS R_BEGIN_TIME ,"
				 + " DATE_FORMAT(O.CHECK_R_END_TIME,'%Y-%m-%d %H:%i:%S') AS R_END_TIME "				 
				 + " FROM TM_INS_CHECK_MAIN_ORDER O "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND O.CHECK_PL_MEMBER_ID=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND O.CHECK_R_MEMBER_ID=C.ID "
				 + " WHERE O.STATUS='1' "
				 + " AND O.CHECK_ORDER_NO='"+checkOrderNo+"' ";
		
		String countSql=" SELECT SUM(1) AS ITEM_COUNT,"
					  + " SUM(IF(CHECK_ORDER_RESULT='CIR_0010',1,0)) AS PASS_COUNT "
					  + " FROM TM_INS_CHECK_ORDER_ITEM "
					  + " WHERE STATUS='1' AND FREEZE_TAG='0' "
					  + " AND CHECK_ORDER_NO='"+checkOrderNo+"' ";
		try{
			DynaBean mainBean=DBConUtil.getSingleResult(conn, sql, "MAIN_BEAN");
			DynaBean countBean=DBConUtil.getSingleResult(conn, countSql, "COUNT_BEAN");
			if(mainBean!=null){
				if(countBean!=null){
					mainBean.add("ITEM_COUNT", countBean.getLong("ITEM_COUNT"));
					mainBean.add("PASS_COUNT", countBean.getLong("PASS_COUNT"));
				}
				return mainBean;
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("QUERY SQL ERROR ");
			}
		
		return null;
	}
	
	/**
	 * 根据名称模糊查询车辆信息
	 * 方   法  名:queryCheckTargetAutoComplete
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param targetName
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryCheckTargetAutoComplete(Connection conn,String targetName){
		
		String sql=" SELECT ID,TRUCK_LICENSE"
				 + " FROM TM_INS_TRUCK_INFO WHERE STATUS='1' AND FREEZE_TAG='0' ";
		if(StringUtils.isNotBlank(targetName)){
			sql+=" AND TRUCK_LICENSE LIKE '%"+targetName.trim()+"%' ";
		}
		sql+=" ORDER BY TRUCK_LICENSE ASC ";
		sql+=" LIMIT 10 ";
		try {
			return DBConUtil.getResult(conn, sql, "TRUCK_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据名称模糊查询检查地点信息
	 * 方   法  名:queryCheckPositionAutoComplete
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param positionName
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryCheckPositionAutoComplete(Connection conn,String positionName){
		
		String sql=" SELECT ID,POSITION_CODE,POSITION_NAME,POSITION_DESC,POSITION_ADDRESS "
				 + " FROM TM_INS_POSITION_INFO WHERE STATUS='1' AND FREEZE_TAG='0' ";
		if(StringUtils.isNotBlank(positionName)){
			sql+=" AND POSITION_NAME LIKE '%"+positionName.trim()+"%' ";
		}
		sql+=" ORDER BY POSITION_NAME ASC ";
		sql+=" LIMIT 10 ";
		try {
			return DBConUtil.getResult(conn, sql, "POSITION_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据名称模糊查询检查员信息
	 * 方   法  名:queryCheckerInfoAutoComplete
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param chekerName
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryCheckerInfoAutoComplete(Connection conn,String chekerName){
		
		String sql=" SELECT ID,MEMBER_CODE,MEMBER_NAME,MOBILE,MEMBER_REMARK "
				 + " FROM TM_SYS_MEMBER WHERE STATUS='1' AND FREEZE_TAG='0' AND IS_INSPACTOR='1' ";
		if(StringUtils.isNotBlank(chekerName)){
			sql+=" AND MEMBER_NAME LIKE '%"+chekerName.trim()+"%' ";
		}
		sql+=" ORDER BY MEMBER_NAME ASC ";
		sql+=" LIMIT 10 ";
		try {
			return DBConUtil.getResult(conn, sql, "CHEKER_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据车辆id 查询车辆对应的检查项目
	 * 方   法  名:queryTruckCheckItemByTruckId
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param truckId
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryTruckCheckItemByTruckId(Connection conn,Integer truckId){
		
		String sql=" SELECT B.CHECK_OBJ_CODE,B.CHECK_OBJ_NAME,B.CHECK_OBJ_DESC,B.OBJ_CLASS_CODE_SUB "
				 + " FROM TT_INS_TRUCK_SUB_OBJ_MAP A,TM_INS_CHECK_OBJ_ITEM B"
				 + " WHERE A.STATUS='1' AND A.FREEZE_TAG='0' "
				 + " AND B.STATUS='1' AND B.FREEZE_TAG='0' "
				 + " AND A.TRUCK_ID="+truckId+" "
				 + " AND A.OBJ_CLASS_SUB_CODE=B.OBJ_CLASS_CODE_SUB "
				 + " ORDER BY B.OBJ_CLASS_CODE_SUB,B.CHECK_OBJ_CODE ASC ";
		
		try {
			return DBConUtil.getResult(conn, sql, "TRUCK_CHECK_OBJ_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据车辆的检查对象小类，查询检查项目小类，关联检查项目小类对应的检查项目明细
	 * 方   法  名:queryTruckCheckItemTruckEntTypeByTruckId
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param truckId
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryTruckCheckItemTruckEntTypeByTruckId(Connection conn,Integer truckId){
		
		String sql=" SELECT B.CHECK_OBJ_CODE,B.CHECK_OBJ_NAME,B.CHECK_OBJ_DESC,B.OBJ_CLASS_CODE_SUB "
				 + " FROM TT_INS_TRUCK_MID_ENT_MAP A, TT_INS_CHECK_ENTITY_OBJ_SUB_MAP C, TM_INS_CHECK_OBJ_ITEM B "
				 + " WHERE A.STATUS='1' AND A.FREEZE_TAG='0' "
				 + " AND B.STATUS='1' AND B.FREEZE_TAG='0' "
				 + " AND C.STATUS='1' AND C.FREEZE_TAG='0' "
				 + " AND A.TRUCK_ID="+truckId+" "
				 + " AND A.CHECK_ENT_SUB_CODE=C.CHECK_ENT_SUB_CODE "
				 + " AND C.OBJ_CLASS_SUB_CODE=B.OBJ_CLASS_CODE_SUB "
				 + " ORDER BY B.OBJ_CLASS_CODE_SUB,B.CHECK_OBJ_CODE ASC ";
		try {
			return DBConUtil.getResult(conn, sql, "TRUCK_CHECK_OBJ_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static PageQuery queryInsCheckOrderItemList(Connection conn,String checkOrderNo,String itemCode,String targetName,String pBeginDate,String pEndDate,
			String rBeginDate,String rEndDate,String itemName,String checkResult,String checkStatus,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,B.MEMBER_NAME AS R_CHECKER_NAME,C.MEMBER_NAME AS PL_CHECKER_NAME,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS ORDER_CREATE_TIME,"
				 + " DATE_FORMAT(A.CHECK_PL_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS P_BEGIN_TIME, "
				 + " DATE_FORMAT(A.CHECK_PL_END_TIME,'%Y-%m-%d %H:%i:%S') AS P_END_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS R_BEGIN_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_END_TIME,'%Y-%m-%d %H:%i:%S') AS R_END_TIME,"
				 + " ITEM.ID AS ITEM_ID,ITEM.CHECK_OBJ_CODE,ITEM.CHECK_OBJ_NAME,"
				 + " ITEM.CHECK_OBJ_DESC,"
				 + " ITEM.COMLIANCE_DESC,"
				 + " ITEM.TROUBLE_DESC AS ITEM_TROUBLE_DESC,"
				 + " ITEM.TROUBLE_PARSE_STATUS AS ITEM_TROUBLE_PARSE_STATUS, "
				 + " ITEM.PIC_COUNT ,"
				 + " ITEM.FREEZE_TAG AS ITEM_FREEZE_TAG "
				 + " FROM "
				 + " ( SELECT X.*, COUNT(PIC.ID) AS PIC_COUNT "
				 + " FROM TM_INS_CHECK_ORDER_ITEM X "
				 + " LEFT JOIN TT_INS_CHECK_ORDER_ITEM_PIC PIC ON PIC.STATUS='1' "
				 + " AND PIC.FREEZE_TAG='0' AND PIC.CHECK_ORDER_ITEM_ID=X.ID "
				 + " AND PIC.CHECK_ORDER_NO=X.CHECK_ORDER_NO "
				 + " GROUP BY X.ID "
				 + " ) AS ITEM ,"
				 + " TM_INS_CHECK_MAIN_ORDER A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CHECK_R_MEMBER_ID=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.CHECK_PL_MEMBER_ID=C.ID"
				 + " WHERE 1=1 AND A.STATUS='1' AND ITEM.STATUS='1' AND A.CHECK_ORDER_NO=ITEM.CHECK_ORDER_NO ";
		if(StringUtils.isNotBlank(checkOrderNo))sql+=" AND A.CHECK_ORDER_NO LIKE '%"+checkOrderNo.trim()+"%'";
		if(StringUtils.isNotBlank(itemCode))sql+=" AND ITEM.CHECK_OBJ_CODE LIKE '%"+itemCode.trim()+"%'";
		if(StringUtils.isNotBlank(targetName))sql+=" AND (A.CHECK_TARGET_NAME LIKE '%"+targetName.trim()+"%' OR  A.CHECK_TARGET_CODE LIKE '%"+targetName+"%')  ";
		if(StringUtils.isNotBlank(pBeginDate))sql+=" AND DATE(A.CHECK_PL_BEGIN_TIME) <= '"+pBeginDate+"' ";
		if(StringUtils.isNotBlank(pEndDate))sql+=" AND DATE(A.CHECK_PL_END_TIME) >= '"+pEndDate+"' ";
		if(StringUtils.isNotBlank(rBeginDate))sql+=" AND DATE(A.CHECK_R_BEGIN_TIME) <= '"+rBeginDate+"' ";
		if(StringUtils.isNotBlank(rEndDate))sql+=" AND DATE(A.CHECK_R_END_TIME) >= '"+rEndDate+"' ";
		if(StringUtils.isNotBlank(itemName))sql+=" AND ITEM.CHECK_OBJ_NAME LIKE '%"+itemName.trim()+"%' ";
		if(StringUtils.isNotBlank(checkResult))sql+=" AND ITEM.CHECK_ORDER_RESULT='"+checkResult+"' ";
		if(StringUtils.isNotBlank(checkStatus))sql+=" AND ITEM.CHECK_ORDER_STATUS='"+checkStatus+"' ";
		if(!includeStop)sql+=" AND ITEM.FREEZE_TAG ='0'  AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.CHECK_ORDER_NO ASC ,ITEM.CHECK_OBJ_CODE ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
	
	/**
	 * 根据检查单号及检查单项目id，查询检查单项目明细
	 * 方   法  名:queryInsChckOrerItemDetail
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param itemId
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean queryInsChckOrerItemDetail(Connection conn,String checkOrderNo,Integer checkOrderItemId){
		
		String sql=" SELECT ITEM.*,SUP.OBJ_CLASS_NAME AS SUP_NAME, MID.OBJ_CLASS_NAME AS MID_NAME,SUB.OBJ_CLASS_NAME AS SUB_NAME,"
				 + " DATE_FORMAT(A.CHECK_PL_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS PL_TIME_STR, "
				 + " DATE_FORMAT(A.CHECK_R_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS R_TIME_STR, "
				 + " DATE_FORMAT(B.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(B.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR, "
				 + " A.PL_MEMBER_NAME,A.R_MEMBER_NAME, "
				 + " B.TROUBLE_DESC,B.TROUBLE_PARSE_STATUS,B.POSITION_NAME ,"
				 + " B.CHECK_ORDER_NO,B.CHECK_OBJ_NAME AS DETAIL_CHECK_OBJ_NAME,B.CHECK_OBJ_DESC AS DETAIL_CHECK_OBJ_DESC ,"
				 + " B.CREATE_NAME, B.UPDATE_NAME ,B.CHECK_ORDER_STATUS,B.CHECK_ORDER_RESULT ,B.COMLIANCE_DESC AS DETAIL_COMLIANCE_DESC ,"
				 + " B.ID AS CHECK_ORDER_ITEM_ID "
				 + " FROM "
				 + " (SELECT A.*,C.MEMBER_NAME AS PL_MEMBER_NAME, D.MEMBER_NAME AS R_MEMBER_NAME "
				 + " FROM TM_INS_CHECK_MAIN_ORDER A "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.CHECK_PL_MEMBER_ID=C.ID "
				 + " LEFT JOIN TM_SYS_MEMBER D ON D.STATUS='1' AND A.CHECK_R_MEMBER_ID=D.ID "
				 + " WHERE A.STATUS='1' AND A.CHECK_ORDER_NO='"+checkOrderNo+"' "
				 + " ) A, "
				 + " (SELECT B.*, C.MEMBER_NAME AS CREATE_NAME, D.MEMBER_NAME AS UPDATE_NAME "
				 + " FROM TM_INS_CHECK_ORDER_ITEM B "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND B.CREATE_BY=C.ID "
				 + " LEFT JOIN TM_SYS_MEMBER D ON D.STATUS='1' AND B.UPDATE_BY=D.ID "
				 + " WHERE B.STATUS='1' AND B.CHECK_ORDER_NO='"+checkOrderNo+"'  AND B.ID= "+checkOrderItemId 
				 + " ) B, "
				 + " TM_INS_CHECK_OBJ_ITEM ITEM "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUPPER SUP ON SUP.STATUS='1' AND SUP.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_SUP=SUP.OBJ_CLASS_CODE "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE MID ON MID.STATUS='1' AND MID.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_MID=MID.OBJ_CLASS_CODE "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB SUB ON SUB.STATUS='1' AND SUB.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_SUB=SUB.OBJ_CLASS_CODE "
				 + " WHERE "
				 + " ITEM.STATUS='1'  "
				 + " AND B.CHECK_ORDER_NO=A.CHECK_ORDER_NO "
				 + " AND B.CHECK_OBJ_CODE=ITEM.CHECK_OBJ_CODE ";
				 
		
		log.debug("sql:="+sql);
		try {
			return DBConUtil.getSingleResult(conn, sql, "CHECK_ORDER_ITEM_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询检查单检查项目数，以及通过数
	 * 方   法  名:queryCheckOrderItemCountByCheckOrderNo
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param checkOrderNo
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean queryCheckOrderItemCountByCheckOrderNo(Connection conn,String checkOrderNo){
		
		String sql=" SELECT COUNT(1) AS ALL_COUNT,SUM(IF(B.CHECK_ORDER_RESULT='CIR_0010',1,0)) AS PASS_COUNT "
				 + " FROM TM_INS_CHECK_MAIN_ORDER A, TM_INS_CHECK_ORDER_ITEM B "
				 + " WHERE A.STATUS='1' AND B.STATUS='1' "
				 + " AND A.FREEZE_TAG='0' AND B.FREEZE_TAG='0' "
				 + " AND A.CHECK_ORDER_NO=B.CHECK_ORDER_NO "
				 + " AND A.CHECK_ORDER_NO='"+checkOrderNo+"' ";
		try {
			return DBConUtil.getSingleResult(conn, sql, "ITEM_COUNT_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 根据检查员查询检查单信息
	 * 方   法  名:queryCheckOrderMemberTaskByMemberName
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param memberName
	 * 参         数:@param beginDate
	 * 参         数:@param endDate
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryCheckOrderMemberTaskByMemberName(Connection conn,String memberName,String license,String beginDate,String endDate){
		
		String sql=" SELECT A.*,C.MEMBER_NAME AS PL_MEMBER_NAME,D.MEMBER_NAME AS R_MEMBER_NAME ,"
				 + " DATE_FORMAT(A.CHECK_PL_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS P_BEGIN_TIME, "
				 + " DATE_FORMAT(A.CHECK_PL_END_TIME,'%Y-%m-%d %H:%i:%S') AS P_END_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_BEGIN_TIME,'%Y-%m-%d %H:%i:%S') AS R_BEGIN_TIME ,"
				 + " DATE_FORMAT(A.CHECK_R_END_TIME,'%Y-%m-%d %H:%i:%S') AS R_END_TIME "
				 + " FROM TM_INS_CHECK_MAIN_ORDER A "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.CHECK_PL_MEMBER_ID=C.ID "
				 + " LEFT JOIN TM_SYS_MEMBER D ON D.STATUS='1' AND A.CHECK_R_MEMBER_ID=D.ID "
				 + " WHERE A.STATUS='1' AND A.FREEZE_TAG='0' ";
		
		if(StringUtils.isNotBlank(memberName)){
			sql+=" AND ( C.MEMBER_NAME LIKE '%"+memberName.trim()+"%' OR D.MEMBER_NAME  LIKE '%"+memberName.trim()+"%' )";
		}
		if(StringUtils.isNotBlank(beginDate)){
			sql+=" AND DATE(A.CHECK_PL_END_TIME) >='"+beginDate+"' ";
		}
		if(StringUtils.isNotBlank(endDate)){
			sql+=" AND DATE(A.CHECK_PL_BEGIN_TIME)<='"+endDate+"' ";
		}
		
		if(StringUtils.isNotBlank(license)){
			sql+=" AND ( A.CHECK_TARGET_NAME LIKE '%"+license.trim()+"%' OR A.CHECK_TARGET_CODE  LIKE '%"+license.trim()+"%' )";
		}
		
		try {
			return DBConUtil.getResult(conn, sql, "MEMBER_TASK_LIST");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
