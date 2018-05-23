/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.pofactory
 * 文   件  名:TmInsCheckObjClassSupperPOFactory.java
 * 创 建日期:2018年1月31日-上午11:18:23
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.entity.pofactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.base.po.TtInsTruckSubObjMapPO;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:TmInsCheckEntityPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年1月31日 上午11:18:23
 * 修改备注:
 * @version 1.0.0
 */
public class TmInsCheckEntityPOFactory extends POFactory {

	private static Logger log=Logger.getLogger(TmInsCheckEntityPOFactory.class);
	
	/**
	 * 查询对象大类列表
	 * 方   法  名:queryInsCheckEntitySupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param entCode
	 * 参         数:@param entName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckEntitySupList(Connection conn,String entCode,String entName,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS ENT_SUP_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR "
				 + " FROM TM_INS_CHECK_ENTITY_SUPPER A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID"
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(entCode))sql+=" AND A.CHECK_ENT_CODE LIKE '%"+entCode+"%' ";
		if(StringUtils.isNotBlank(entName))sql+=" AND A.CHECK_ENT_NAME LIKE '%"+entName+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	/**
	 * 查询二级分类列表
	 * 方   法  名:queryInsCheckEntitySupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param entCode
	 * 参         数:@param entName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckEntityMidList(Connection conn,String entCode,String entName,String supCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS ENT_MID_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR, "
				 + " S.CHECK_ENT_NAME AS SUP_NAME "
				 + " FROM TM_INS_CHECK_ENTITY_MIDDLE A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID "
				 + " LEFT JOIN TM_INS_CHECK_ENTITY_SUPPER S ON S.STATUS='1' AND A.CHECK_ENT_F_CODE=S.CHECK_ENT_CODE "
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(entCode))sql+=" AND A.CHECK_ENT_CODE LIKE '%"+entCode+"%' ";
		if(StringUtils.isNotBlank(entName))sql+=" AND A.CHECK_ENT_NAME LIKE '%"+entName+"%' ";
		if(StringUtils.isNotBlank(supCode))sql+=" AND A.CHECK_ENT_F_CODE LIKE '%"+supCode+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	/**
	 * 查询三级分类列表
	 * 方   法  名:queryInsCheckEntitySupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param entCode
	 * 参         数:@param entName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckEntitySubList(Connection conn,String entCode,String entName,String midCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS ENT_SUB_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR,"
				 + " M.CHECK_ENT_NAME AS MID_NAME   "
				 + " FROM TM_INS_CHECK_ENTITY_SUB A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID "
				 + " LEFT JOIN TM_INS_CHECK_ENTITY_MIDDLE M ON M.STATUS='1' AND A.CHECK_ENT_F_CODE=M.CHECK_ENT_CODE"
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(entCode))sql+=" AND A.CHECK_ENT_CODE LIKE '%"+entCode+"%' ";
		if(StringUtils.isNotBlank(entName))sql+=" AND A.CHECK_ENT_NAME LIKE '%"+entName+"%' ";
		if(StringUtils.isNotBlank(midCode))sql+=" AND A.CHECK_ENT_F_CODE LIKE '%"+midCode+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	

	/**
	 * 通过检查对象类型，查询其对应的检查对象中类及小类
	 * 方   法  名:queryInsCheckEntitySubListByEntityTypeCode
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param entityType(数据字典CHECK_ENT_CODE：检查对象类型)
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryInsCheckEntitySubListByEntityTypeCode(Connection conn,String checkEntType){
		
		String sql=" SELECT DISTINCT B.CHECK_ENT_CODE AS MID_CODE,B.CHECK_ENT_NAME AS MID_NAME,B.SORT,"
				+ "  C.CHECK_ENT_CODE AS SUB_CODE,C.CHECK_ENT_NAME AS SUB_NAME,C.SORT "
				+ " FROM TM_INS_CHECK_ENTITY_SUPPER A,TM_INS_CHECK_ENTITY_MIDDLE B ,TM_INS_CHECK_ENTITY_SUB C "
				+ " WHERE A.STATUS='1' AND B.STATUS='1' AND C.STATUS='1' "
				+ " AND A.FREEZE_TAG='0' AND B.FREEZE_TAG='0' AND C.FREEZE_TAG='0' "
				+ " AND A.CHECK_ENT_TYPE_CODE='"+checkEntType+"' "
				+ " AND B.CHECK_ENT_F_CODE=A.CHECK_ENT_CODE "
				+ " AND C.CHECK_ENT_F_CODE=B.CHECK_ENT_CODE "
				+ " ORDER BY B.SORT,B.CHECK_ENT_CODE,C.SORT,C.CHECK_ENT_CODE ASC  ";
		
		try {
			return DBConUtil.getResult(conn, sql, "CHECK_ENT_TYPE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<TtInsTruckSubObjMapPO> querySubObjBySubEnt(Connection conn,int truckId){
		
		String sql=" SELECT A.*, "+truckId+" AS TRUCK_ID  "
				 + " FROM TT_INS_CHECK_ENTITY_OBJ_SUB_MAP A,"
				 + " TT_INS_TRUCK_MID_ENT_MAP B "
				 + " WHERE A.STATUS='1' "
				 + " AND B.STATUS='1' "
				 + " AND A.FREEZE_TAG='0' AND B.FREEZE_TAG='0' "
				 + " AND B.TRUCK_ID= "+truckId
				 + " AND B.CHECK_ENT_SUB_CODE=A.CHECK_ENT_SUB_CODE "
				 + " ";
		try {
			return DBConUtil.getPOResult(conn, sql, new TtInsTruckSubObjMapPO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
