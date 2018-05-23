/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.pofactory
 * 文   件  名:TmInsCheckObjItemPOFactory.java
 * 创 建日期:2018年3月8日-下午2:41:53
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.pofactory;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:TmInsCheckObjItemPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月8日 下午2:41:53
 * 修改备注:
 * @version 1.0.0
 */
public class TmInsCheckObjItemPOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TmInsCheckObjItemPOFactory.class);
	
	/**
	 * 查询检查项目列表
	 * 方   法  名:queryInsCheckItemList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param supCode
	 * 参         数:@param supName
	 * 参         数:@param midCode
	 * 参         数:@param midName
	 * 参         数:@param subCode
	 * 参         数:@param subName
	 * 参         数:@param itemCode
	 * 参         数:@param itemName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckItemList(Connection conn,String supCode,String supName,String midCode,String midName,String subCode,String subName,
			String itemCode,String itemName,String itemFName,String itemFCode ,boolean includeStop,int customPageSize){
		String sql=" SELECT ITEM.ID,ITEM.CHECK_OBJ_CODE,ITEM.FREEZE_TAG ,"
				 + " ITEM.OBJ_CLASS_CODE_SUP AS SUP_CODE,"
				 + " ITEM.OBJ_CLASS_CODE_MID AS MID_CODE,"
				 + " ITEM.OBJ_CLASS_CODE_SUB AS SUB_CODE,"
				 + " ITEM.OBJ_CLASS_TYPE_CODE,"
				 + " SUB.OBJ_CLASS_NAME AS SUB_NAME,"
				 + " MID.OBJ_CLASS_NAME AS MID_NAME,"
				 + " SUP.OBJ_CLASS_NAME AS SUP_NAME,"
				 + " ITEM.CHECK_OBJ_NAME,ITEM.CHECK_OBJ_DESC,"
				 + " ITEM.COMLIANCE_DESC,ITEM.OBJ_CHECK_WAY,ITEM.OBJ_CHECK_FREQUENCY,ITEM.OBJ_CHECK_PHOTO,"
				 + " ITEM.OBJ_CHECK_BEF,ITEM.OBJ_CHECK_AFT ,ITEM.SORT,"
				 + " DATE_FORMAT(ITEM.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(ITEM.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR, "
				 + " C.MEMBER_NAME AS CREATE_NAME, U.MEMBER_NAME AS UPDATE_NAME "
				 + " FROM  TM_INS_CHECK_OBJ_ITEM ITEM "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB SUB ON ITEM.OBJ_CLASS_CODE_SUB=SUB.OBJ_CLASS_CODE AND SUB.STATUS='1' "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE MID ON ITEM.OBJ_CLASS_CODE_MID=MID.OBJ_CLASS_CODE AND MID.STATUS='1'"
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUPPER SUP  ON ITEM.OBJ_CLASS_CODE_SUP=SUP.OBJ_CLASS_CODE AND SUP.STATUS='1'"
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND ITEM.CREATE_BY=C.ID "
				 + " LEFT JOIN TM_SYS_MEMBER U ON U.STATUS='1' AND ITEM.UPDATE_BY=U.ID"
				 + " WHERE 1=1 AND ITEM.STATUS='1' ";
		if(StringUtils.isNotBlank(supCode))sql+=" AND ITEM.OBJ_CLASS_CODE_SUP LIKE '%"+supCode+"%' ";
		if(StringUtils.isNotBlank(midCode))sql+=" AND ITEM.OBJ_CLASS_CODE_MID LIKE '%"+midCode+"%' ";
		if(StringUtils.isNotBlank(subCode))sql+=" AND ITEM.OBJ_CLASS_CODE_SUB LIKE '%"+subCode+"%' ";
		
		if(StringUtils.isNotBlank(supName))sql+=" AND SUP.OBJ_CLASS_NAME LIKE '%"+supName+"%' ";
		if(StringUtils.isNotBlank(midName))sql+=" AND MID.OBJ_CLASS_NAME LIKE '%"+midName+"%' ";
		if(StringUtils.isNotBlank(subName))sql+=" AND SUB.OBJ_CLASS_NAME LIKE '%"+subName+"%' ";
		
		if(StringUtils.isNotBlank(itemCode))sql+=" AND ITEM.CHECK_OBJ_CODE LIKE '%"+itemCode+"%' ";
		if(StringUtils.isNotBlank(itemName))sql+=" AND ITEM.CHECK_OBJ_NAME LIKE '%"+itemName+"%' ";
		
		if(StringUtils.isNotBlank(itemFCode))sql+=" AND ITEM.CHECK_OBJ_F_CODE LIKE '%"+itemFCode+"%' ";
		if(StringUtils.isNotBlank(itemFName))sql+=" AND EXISTS  "
				+ " ( SELECT DISTINCT CHECK_OBJ_CODE FROM TM_INS_CHECK_OBJ_ITEM ITEM_F  "
				+ " WHERE ITEM_F.STATUS='1' AND ITEM_F.FREEZE_TAG ='0'"
				+ " AND ITEM_F.CHECK_OBJ_NAME LIKE '%"+itemFName+"%'"
				+ " AND ITEM_F.CHECK_OBJ_CODE=ITEM.CHECK_OBJ_F_CODE ) ";
		
		if(!includeStop)sql+=" AND ITEM.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY "
		   + " SUP.SORT,SUP.OBJ_CLASS_CODE, "
		   + " MID.SORT,MID.OBJ_CLASS_CODE, "
		   + " SUB.SORT,SUB.OBJ_CLASS_CODE, "
		   + " ITEM.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);

		
	}
	
	/**
	 * 查询检查项目明细
	 * 方   法  名:queryObjItemDetail
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param itemId
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean queryObjItemDetail(Connection conn,Integer itemId){
		
		String sql=" SELECT ITEM.*,SUP.OBJ_CLASS_NAME AS SUP_NAME, MID.OBJ_CLASS_NAME AS MID_NAME,SUB.OBJ_CLASS_NAME AS SUB_NAME,"
				 + " DATE_FORMAT(ITEM.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(ITEM.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR, "
				 + " C.MEMBER_NAME AS CREATE_NAME, U.MEMBER_NAME AS UPDATE_NAME "
				 + " "
				 + " FROM  TM_INS_CHECK_OBJ_ITEM ITEM "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUPPER SUP ON SUP.STATUS='1' AND SUP.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_SUP=SUP.OBJ_CLASS_CODE "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE MID ON MID.STATUS='1' AND MID.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_MID=MID.OBJ_CLASS_CODE "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB SUB ON SUB.STATUS='1' AND SUB.FREEZE_TAG='0' AND ITEM.OBJ_CLASS_CODE_SUB=SUB.OBJ_CLASS_CODE "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND ITEM.CREATE_BY=C.ID "
				 + " LEFT JOIN TM_SYS_MEMBER U ON U.STATUS='1' AND ITEM.UPDATE_BY=U.ID"
				 + " WHERE ITEM.STATUS='1' AND ITEM.ID= "+itemId;
		
		log.debug("sql:="+sql);
		try {
			return DBConUtil.getSingleResult(conn, sql, "OBJ_ITEM_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 正在编辑检查单项目时，对于去掉的检查单项目，进行处理
	 * 方   法  名:updateNeedDeleteCheckOrderItem
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param orderNo
	 * 参         数:@param existsIds
	 * 参         数:@param updateOptId
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void updateNeedDeleteCheckOrderItem(Connection conn,String orderNo,String existsIds,Integer updateOptId){
		
		String sql=" UPDATE TM_INS_CHECK_ORDER_ITEM SET "
				 + " STATUS='0',UPDATE_BY= "+updateOptId+",UPDATE_TIME=NOW() "
				 + " WHERE CHECK_ORDER_NO='"+orderNo+"' "
				 + " AND STATUS='1' ";
		if(StringUtils.isNotBlank(existsIds)){
			sql+=" AND ID NOT IN("+existsIds+") ";
		} 
		
		log.debug("UPDATE SQL:="+sql);
		try {
			DBConUtil.update(conn, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			
		}
		
	}
}
