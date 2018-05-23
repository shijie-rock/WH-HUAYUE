/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.pofactory
 * 文   件  名:TmInsCheckObjClassSupperPOFactory.java
 * 创 建日期:2018年1月31日-上午11:18:23
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.pofactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:TmInsCheckObjClassPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年1月31日 上午11:18:23
 * 修改备注:
 * @version 1.0.0
 */
public class TmInsCheckObjClassPOFactory extends POFactory {

	private static Logger log=Logger.getLogger(TmInsCheckObjClassPOFactory.class);
	
	private static Map<String,String> CHECK_OBJ_CLASS_MAP=new Hashtable<String,String>();
	
	/**
	 * 查询一级分类列表
	 * 方   法  名:queryInsCheckObjectClassSupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param objCode
	 * 参         数:@param objName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckObjectClassSupList(Connection conn,String objCode,String objName,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS OBJ_SUP_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUPPER A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID"
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(objCode))sql+=" AND A.OBJ_CLASS_CODE LIKE '%"+objCode+"%' ";
		if(StringUtils.isNotBlank(objName))sql+=" AND A.OBJ_CLASS_NAME LIKE '%"+objName+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	/**
	 * 查询二级分类列表
	 * 方   法  名:queryInsCheckObjectClassSupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param objCode
	 * 参         数:@param objName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception TM_INS_CHECK_OBJ_CLASS_MIDDLE
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckObjectClassMidList(Connection conn,String objCode,String objName,String supCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS OBJ_MID_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR,"
				 + " S.OBJ_CLASS_NAME AS SUP_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_MIDDLE A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUPPER S ON S.STATUS='1' AND A.OBJ_CLASS_F_CODE=S.OBJ_CLASS_CODE "
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(objCode))sql+=" AND A.OBJ_CLASS_CODE LIKE '%"+objCode+"%' ";
		if(StringUtils.isNotBlank(objName))sql+=" AND A.OBJ_CLASS_NAME LIKE '%"+objName+"%' ";
		if(StringUtils.isNotBlank(supCode))sql+=" AND A.OBJ_CLASS_F_CODE LIKE '%"+supCode+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	/**
	 * 查询三级分类列表
	 * 方   法  名:queryInsCheckObjectClassSupList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param objCode
	 * 参         数:@param objName
	 * 参         数:@param includeStop
	 * 参         数:@param customPageSize
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception TM_INS_CHECK_OBJ_CLASS_SUB
	 * @since  1.0.0
	 */
	public static PageQuery queryInsCheckObjectClassSubList(Connection conn,String objCode,String objName,String midCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT A.* ,A.ID AS OBJ_SUB_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR, "
				 + " M.OBJ_CLASS_NAME AS MID_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUB A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE M ON M.STATUS='1' AND A.OBJ_CLASS_F_CODE=M.OBJ_CLASS_CODE  "
				 + " WHERE 1=1 AND A.STATUS='1' ";
		if(StringUtils.isNotBlank(objCode))sql+=" AND A.OBJ_CLASS_CODE LIKE '%"+objCode+"%' ";
		if(StringUtils.isNotBlank(objName))sql+=" AND A.OBJ_CLASS_NAME LIKE '%"+objName+"%' ";
		if(StringUtils.isNotBlank(midCode))sql+=" AND A.OBJ_CLASS_F_CODE LIKE '%"+midCode+"%' ";
		if(!includeStop)sql+=" AND A.FREEZE_TAG ='0' ";
//		sql+=" ORDER BY A.ID ASC ";
		sql+=" ORDER BY A.SORT ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
	/**
	 * 获取检查项目树状结构
	 * 方   法  名:queryObjectTreeDynaBean
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param includeStop
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryObjectTreeDynaBean(Connection conn,boolean includeStop){
		
		String subA="";
		String subB="";
		String subC="";
		String subD="";
		
		if(!includeStop){
			subA+=" AND A.FREEZE_TAG ='0' ";
			subB+=" AND B.FREEZE_TAG ='0' ";
			subC+=" AND C.FREEZE_TAG ='0' ";
			subD+=" AND D.FREEZE_TAG ='0' ";
		}
		
		String sql=" SELECT "
				  +" A.OBJ_CLASS_CODE AS OBJ_SUP_CODE,A.OBJ_CLASS_NAME AS OBJ_SUP_NAME,A.ID AS OBJ_SUP_ID, "
				  +" B.OBJ_CLASS_CODE AS OBJ_MID_CODE,B.OBJ_CLASS_NAME AS OBJ_MID_NAME,B.ID AS OBJ_MID_ID, "
				  +" C.OBJ_CLASS_CODE AS OBJ_SUB_CODE,C.OBJ_CLASS_NAME AS OBJ_SUB_NAME,C.ID AS OBJ_SUB_ID ,"
				  +" D.CHECK_OBJ_CODE,D.CHECK_OBJ_NAME "
				  +" FROM  "
				  +" TM_INS_CHECK_OBJ_CLASS_SUPPER A "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE B ON A.OBJ_CLASS_CODE=B.OBJ_CLASS_F_CODE AND B.`STATUS`='1'  "+subB
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB C ON B.OBJ_CLASS_CODE=C.OBJ_CLASS_F_CODE AND C.`STATUS`='1'  "+subC
				  +" LEFT JOIN TM_INS_CHECK_OBJ_ITEM D ON C.OBJ_CLASS_CODE=D.OBJ_CLASS_CODE_SUB AND D.`STATUS`='1'  "+subD
				  +" WHERE A.`STATUS`='1'  "+subA
				  +" ORDER BY "
				  +" A.SORT,A.OBJ_CLASS_CODE,"
				  +" B.SORT,B.OBJ_CLASS_CODE,"
				  +" C.SORT,C.OBJ_CLASS_CODE,"
				  +" D.SORT,D.CHECK_OBJ_CODE ASC ";
		try {
			return DBConUtil.getResult(conn, sql, "OBJECT_TREE_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String queryObjectTreeHtml(Connection conn,boolean includeStop){
		
		String subA="";
		String subB="";
		String subC="";
		String subD="";
		
		if(!includeStop){
			subA+=" AND A.FREEZE_TAG ='0' ";
			subB+=" AND B.FREEZE_TAG ='0' ";
			subC+=" AND C.FREEZE_TAG ='0' ";
			subD+=" AND D.FREEZE_TAG ='0' ";
		}
		
		String sql=" SELECT "
				  +" A.OBJ_CLASS_CODE AS OBJ_SUP_CODE,A.OBJ_CLASS_NAME AS OBJ_SUP_NAME,A.ID AS OBJ_SUP_ID, "
				  +" B.OBJ_CLASS_CODE AS OBJ_MID_CODE,B.OBJ_CLASS_NAME AS OBJ_MID_NAME,B.ID AS OBJ_MID_ID "
				  +" FROM  "
				  +" TM_INS_CHECK_OBJ_CLASS_SUPPER A "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE B ON A.OBJ_CLASS_CODE=B.OBJ_CLASS_F_CODE AND B.`STATUS`='1'  "+subB
				  +" WHERE A.`STATUS`='1'  "+subA
				  +" ORDER BY "
				  +" A.SORT,A.OBJ_CLASS_CODE,"
				  +" B.SORT,B.OBJ_CLASS_CODE ASC ";
		
		String sql1=" SELECT "
				  +" B.OBJ_CLASS_CODE AS OBJ_MID_CODE,B.OBJ_CLASS_NAME AS OBJ_MID_NAME,B.ID AS OBJ_MID_ID, "
				  +" C.OBJ_CLASS_CODE AS OBJ_SUB_CODE,C.OBJ_CLASS_NAME AS OBJ_SUB_NAME,C.ID AS OBJ_SUB_ID "
				  +" FROM  "
				  +" TM_INS_CHECK_OBJ_CLASS_MIDDLE B "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB C ON B.OBJ_CLASS_CODE=C.OBJ_CLASS_F_CODE AND C.`STATUS`='1'  "+subC
				  +" WHERE B.`STATUS`='1'  "+subB
				  +" ORDER BY "
				  +" B.SORT,B.OBJ_CLASS_CODE,"
				  +" C.SORT,C.OBJ_CLASS_CODE"
				  +" ASC ";
		
		String sql2=" SELECT "
				  +" C.OBJ_CLASS_CODE AS OBJ_SUB_CODE,C.OBJ_CLASS_NAME AS OBJ_SUB_NAME,C.ID AS OBJ_SUB_ID ,"
				  +" D.CHECK_OBJ_CODE,D.CHECK_OBJ_NAME ,D.ID AS CHECK_OBJ_ID "
				  +" FROM  "
				  +" TM_INS_CHECK_OBJ_CLASS_SUB C "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_ITEM D ON C.OBJ_CLASS_CODE=D.OBJ_CLASS_CODE_SUB AND D.`STATUS`='1'  "+subD
				  +" WHERE C.`STATUS`='1'  "+subC
				  +" ORDER BY "
				  +" C.SORT,C.OBJ_CLASS_CODE,"
				  +" D.SORT,D.CHECK_OBJ_CODE ASC ";
		
		try {
			Map<String,DynaBean> supBeanMap=new LinkedHashMap<String,DynaBean>();
			Map<String,List<DynaBean>> supMidMap=new LinkedHashMap<String,List<DynaBean>>();
			
			List<DynaBean> sup2MidList=DBConUtil.getResult(conn, sql, "OBJECT_SUP_MID_BEAN");
			if(sup2MidList!=null){
				for(DynaBean bean:sup2MidList){
					String supCode=bean.getString("OBJ_SUP_CODE");
					String supName=bean.getString("OBJ_SUP_NAME");
					Integer supId=bean.getInt("OBJ_SUP_ID");
					
					String midCode=bean.getString("OBJ_MID_CODE");
					String midName=bean.getString("OBJ_MID_NAME");
					Integer midId=bean.getInt("OBJ_MID_ID");
					
					DynaBean supDynaBean=new DynaBean("SUP");
					supDynaBean.add("OBJ_SUP_CODE", supCode);
					supDynaBean.add("OBJ_SUP_NAME", supName);
					supDynaBean.add("OBJ_SUP_ID", supId);
					
					DynaBean midDynaBean=new DynaBean("MID");
					midDynaBean.add("OBJ_MID_CODE", midCode);
					midDynaBean.add("OBJ_MID_NAME", midName);
					midDynaBean.add("OBJ_MID_ID", midId);
					
					supBeanMap.put(supCode, supDynaBean);
					
					if(supMidMap.containsKey(supCode)){
						List<DynaBean> midList=supMidMap.get(supCode);
						midList.add(midDynaBean);
						supMidMap.put(supCode, midList);
					}else{
						List<DynaBean> midList=new ArrayList<DynaBean>(); 
						midList.add(midDynaBean);
						supMidMap.put(supCode, midList);
					}
				}
			}
			Map<String,List<DynaBean>> midSubMap=new LinkedHashMap<String,List<DynaBean>>();
			List<DynaBean> mid2SubList=DBConUtil.getResult(conn, sql1, "OBJECT_MID_SUB_BEAN");	
			if(mid2SubList!=null){
				for(DynaBean bean:mid2SubList){
					String subCode=bean.getString("OBJ_SUB_CODE");
					String subName=bean.getString("OBJ_SUB_NAME");
					Integer subId=bean.getInt("OBJ_SUB_ID");
					
					String midCode=bean.getString("OBJ_MID_CODE");
//					String midName=bean.getString("OBJ_MID_NAME");
//					Long midId=bean.getLong("OBJ_MID_ID");

					DynaBean subDynaBean=new DynaBean("SUB");
					subDynaBean.add("OBJ_SUB_CODE", subCode);
					subDynaBean.add("OBJ_SUB_NAME", subName);
					subDynaBean.add("OBJ_SUB_ID", subId);
					
					if(midSubMap.containsKey(midCode)){
						List<DynaBean> subList=midSubMap.get(midCode);
						subList.add(subDynaBean);
						midSubMap.put(midCode, subList);
					}else{
						List<DynaBean> subList=new ArrayList<DynaBean>(); 
						subList.add(subDynaBean);
						midSubMap.put(midCode, subList);
					}
				}
			}
			Map<String,List<DynaBean>> subItemMap=new LinkedHashMap<String,List<DynaBean>>();

			List<DynaBean> sub2ItemList=DBConUtil.getResult(conn, sql2, "OBJECT_SUB_ITEM_BEAN");	
			if(sub2ItemList!=null){
				for(DynaBean bean:sub2ItemList){
					String subCode=bean.getString("OBJ_SUB_CODE");
					String subName=bean.getString("OBJ_SUB_NAME");
					Integer subId=bean.getInt("OBJ_SUB_ID");
					
					String itemCode=bean.getString("CHECK_OBJ_CODE");
					String itemName=bean.getString("CHECK_OBJ_NAME");
					Integer itemId=bean.getInt("CHECK_OBJ_ID");
					
					DynaBean itemDynaBean=new DynaBean("ITEM");
					itemDynaBean.add("CHECK_OBJ_CODE", itemCode);
					itemDynaBean.add("CHECK_OBJ_NAME", itemName);
					itemDynaBean.add("CHECK_OBJ_ID", itemId);
					System.out.println("subCode:="+subCode);
					
					if(subItemMap.containsKey(subCode)){
						List<DynaBean> itemList=subItemMap.get(subCode);
						System.out.println("itemList:="+itemList);
						itemList.add(itemDynaBean);
						subItemMap.put(subCode, itemList);
					}else{
						List<DynaBean> itemList=new ArrayList<DynaBean>(); 
						itemList.add(itemDynaBean);
						subItemMap.put(subCode, itemList);
					}
				}
			}

			
			StringBuffer sbf=new StringBuffer();
			//pack tree html
			if(supMidMap!=null){
				Set supCodeSet=supMidMap.keySet();
				Iterator<String> supCodeIter=supCodeSet.iterator();
				while(supCodeIter.hasNext()){
					String supCode=supCodeIter.next();
					String supName="";
					Integer supId=null;
					if(supBeanMap!=null&&supBeanMap.containsKey(supCode)){
						supName=supBeanMap.get(supCode).getString("OBJ_SUP_NAME");
						supId=supBeanMap.get(supCode).getInt("OBJ_SUP_ID");
					}
//					sbf.append("<li><span id='"+supCode+"'  ><i class='glyphicon glyphicon-remove-circle-sign'></i>[1]"+supName+"</span> <a href='#'>增加节点/删除节点</a>");
//					sbf.append("<li><span id='"+supCode+"' title='"+supName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[1]"+supName+"</span> <a href='#'>增加节点/删除节点</a>");
//					sbf.append("<li><span id='"+supCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+supName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[1]"+supName+"</span> <a href='#'>增加节点/删除节点</a>");
					sbf.append("<li><span id='"+supCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+supName+"' data-tree-path-name='"+supName+"' data-obj-class-code='"+supCode+"' data-obj-class-level-code='1' >"
							+ "<span class='label label-primary tree_level'>1</span><i class='icon-minus'></i>"+supName+"</span>"
							+ "<a href='javascript:volid(0);' onclick=\"queryObjSub('1','"+supCode+"','"+supCode+"','一')\" class='btn btn-default tree_a_see'  data-toggle='tooltip' data-placement='top' title='查看节点'><i class='glyphicon glyphicon-info-sign'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"editObjSub('1','"+supCode+"','"+supCode+"','一')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='编辑节点'><i class='glyphicon glyphicon-edit'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"addObjSub('1','"+supCode+"','"+supCode+"','一')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='增加节点'><i class='glyphicon glyphicon-plus-sign'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"optConfirm('stop','1','"+supCode+"','"+supCode+"','一')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='停用节点'><i class='glyphicon glyphicon-ban-circle'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"optConfirm('delete','1','"+supCode+"','"+supCode+"','一')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='删除节点'><i class='glyphicon glyphicon-remove-circle'></i></a>");
					List<DynaBean> midList=supMidMap.get(supCode);
					boolean hasMid=false;
					if(midList!=null){
						hasMid=true;
						sbf.append("<ul>");
						for(DynaBean midBean:midList){
							String midCode=midBean.getString("OBJ_MID_CODE");
							if(StringUtils.isBlank(midCode))continue;
							String midName=midBean.getString("OBJ_MID_NAME");
							Integer midId=midBean.getInt("OBJ_MID_ID");
//							sbf.append("<li><span id='"+supCode+"|"+midCode+"'  ><i class='glyphicon glyphicon-remove-circle-sign'></i>[2]"+midName+"</span> <a href='#'>增加节点/删除节点</a>");
//							sbf.append("<li><span id='"+supCode+"|"+midCode+"'  title='"+midName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[2]"+midName+"</span> <a href='#'>增加节点/删除节点</a>");
//							sbf.append("<li><span id='"+supCode+"|"+midCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+midName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[2]"+midName+"</span> <a href='#'>增加节点/删除节点</a>");
							sbf.append("<li><span id='"+supCode+"|"+midCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+midName+"' data-tree-path-name='"+supName+">"+midName+"' data-obj-class-code='"+midCode+"' data-obj-class-level-code='2' >"
									+ "<span class='label label-info tree_level'>2</span><i class='icon-minus'></i>"+midName+"</span> "
//									+ "<a href='javascript:volid(0);' style='margin-left:10px;' onclick=\"addObjSub('2','"+supCode+"','"+midCode+"','二')\" class='btn btn-Info '>增加节点/删除节点</a>");
							+ "<a href='javascript:volid(0);' onclick=\"queryObjSub('2','"+supCode+"','"+midCode+"','二')\" class='btn btn-default tree_a_see'  data-toggle='tooltip' data-placement='top' title='查看节点'><i class='glyphicon glyphicon-info-sign'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"editObjSub('2','"+supCode+"','"+midCode+"','二')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='编辑节点'><i class='glyphicon glyphicon-edit'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"addObjSub('2','"+supCode+"','"+midCode+"','二')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='增加节点'><i class='glyphicon glyphicon-plus-sign'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"optConfirm('stop','2','"+supCode+"','"+midCode+"','二')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='停用节点'><i class='glyphicon glyphicon-ban-circle'></i></a>"
							+ "<a href='javascript:volid(0);' onclick=\"optConfirm('delete','2','"+supCode+"','"+midCode+"','二')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='删除节点'><i class='glyphicon glyphicon-remove-circle'></i></a>");
							List<DynaBean> subList=midSubMap.get(midCode);
							boolean hasSub=false;
							if(subList!=null){
								hasSub=true;
								sbf.append("<ul>");
								for(DynaBean subBean:subList){
									String subCode=subBean.getString("OBJ_SUB_CODE");
									if(StringUtils.isBlank(subCode))continue;
									String subName=subBean.getString("OBJ_SUB_NAME");
									Integer subId=subBean.getInt("OBJ_SUB_ID");
//									sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[3]"+subName+"</span> <a href='#'>增加节点/删除节点</a>");
//									sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"' title='"+subName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[3]"+subName+"</span> <a href='#'>增加节点/删除节点</a>");
//									sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+subName+"' ><i class='glyphicon glyphicon-remove-circle-sign'></i>[3]"+subName+"</span> <a href='#'>增加节点/删除节点</a>");
									sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+subName+"' data-tree-path-name='"+supName+">"+midName+">"+subName+"'   data-obj-class-code='"+subCode+"' data-obj-class-level-code='3' >"
											+ "<span class='label label-success tree_level'>3</span><i class='icon-minus'></i>"+subName+"</span> "
//											+ "<a href='javascript:volid(0);' style='margin-left:10px;' onclick=\"addObjSub('3','"+midCode+"','"+subCode+"','三')\" class='btn btn-Info '>增加节点/删除节点</a>");
									+ "<a href='javascript:volid(0);' onclick=\"queryObjSub('3','"+midCode+"','"+subCode+"','三')\" class='btn btn-default tree_a_see'  data-toggle='tooltip' data-placement='top' title='查看节点'><i class='glyphicon glyphicon-info-sign'></i></a>"
									+ "<a href='javascript:volid(0);' onclick=\"editObjSub('3','"+midCode+"','"+subCode+"','三')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='编辑节点'><i class='glyphicon glyphicon-edit'></i></a>"
									+ "<a href='javascript:volid(0);' onclick=\"addObjSub('3','"+midCode+"','"+subCode+"','三')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='增加节点'><i class='glyphicon glyphicon-plus-sign'></i></a>"
									+ "<a href='javascript:volid(0);' onclick=\"optConfirm('stop','3','"+midCode+"','"+subCode+"','三')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='停用节点'><i class='glyphicon glyphicon-ban-circle'></i></a>"
									+ "<a href='javascript:volid(0);' onclick=\"optConfirm('delete','3','"+midCode+"','"+subCode+"','三')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='删除节点'><i class='glyphicon glyphicon-remove-circle'></i></a>");
									//NOT DISPLAY ITEM BEGIN 20180318
									/*
									List<DynaBean> itemList=subItemMap.get(subCode);
									boolean hasItem=false;
										if(itemList!=null){
											hasItem=true;
											sbf.append("<ul>");
											for(DynaBean itemBean:itemList){
												String itemCode=itemBean.getString("CHECK_OBJ_CODE");
												if(StringUtils.isBlank(itemCode))continue;
												String itemName=itemBean.getString("CHECK_OBJ_NAME");
												Integer itemId=itemBean.getInt("CHECK_OBJ_ID");
//												sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"|"+itemCode+"' ><i class='glyphicon glyphicon-remove-circle-leaf'></i>[4]"+itemName+"</span> <a href='#'>增加节点/删除节点</a></li>");
//												sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"|"+itemCode+"'  title='"+itemName+"' ><i class='glyphicon glyphicon-remove-circle-leaf'></i>[4]"+itemName+"</span> <a href='#'>增加节点/删除节点</a></li>");
//												sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"|"+itemCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+itemName+"' ><i class='glyphicon glyphicon-remove-circle-leaf'></i>[4]"+itemName+"</span> <a href='#'>增加节点/删除节点</a></li>");
												sbf.append("<li><span id='"+supCode+"|"+midCode+"|"+subCode+"|"+itemCode+"' class='data-detail' data-toggle='tooltip' data-placement='top' title='"+itemName+"' >"
														+ "<span class='label label-success tree_level'>4</span><i class='icon-minus'></i>"+itemName+"</span> "
//														+ "<a href='javascript:volid(0);' style='margin-left:10px;' onclick=\"addObjSub('4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-Info '>增加节点/删除节点</a></li>");
												+ "<a href='javascript:volid(0);' onclick=\"queryObjSub('4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-default tree_a_see'  data-toggle='tooltip' data-placement='top' title='查看节点'><i class='glyphicon glyphicon-info-sign'></i></a>"
												+ "<a href='javascript:volid(0);' onclick=\"editObjSub('4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='编辑节点'><i class='glyphicon glyphicon-edit'></i></a>"
												+ "<a href='javascript:volid(0);' onclick=\"addObjSub('4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='增加节点'><i class='glyphicon glyphicon-plus-sign'></i></a>"
												+ "<a href='javascript:volid(0);' onclick=\"optConfirm('stop','4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='停用节点'><i class='glyphicon glyphicon-ban-circle'></i></a>"
												+ "<a href='javascript:volid(0);' onclick=\"optConfirm('delete','4','"+subCode+"','"+itemCode+"','四')\" class='btn btn-default tree_a'  data-toggle='tooltip' data-placement='top' title='删除节点'><i class='glyphicon glyphicon-remove-circle'></i></a>");

											}
											sbf.append("</ul>");
									}*/
									//NOT DISPLAY ITEM END
								}
								sbf.append("</ul>");
							}
						}
						sbf.append("</ul>");
					}
					sbf.append("</li>");
				}
			}
			log.debug("tree:"+sbf.toString());
			return sbf.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 查询检查对象小类对应的，检查项目二类，及与二类同级的公共检查项目三级类
	 * 方   法  名:querySubObjByMidCodeAndSupCode
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param supCode
	 * 参         数:@param midCode
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> querySubObjByMidCodeAndSupCode(Connection conn,String entSubCode,String supCode,String midCode){
		
		String sql=" SELECT A.MID_CODE,A.MID_NAME,A.MID_SORT,A.SUB_CODE,A.SUB_NAME,A.SUB_SORT,M.OBJ_CLASS_SUB_CODE  "
				  +" FROM( "
				  +" SELECT  "
				  +" M.OBJ_CLASS_CODE AS MID_CODE,M.OBJ_CLASS_NAME AS MID_NAME,M.SORT AS MID_SORT, "
				  +" S.OBJ_CLASS_CODE AS SUB_CODE,S.OBJ_CLASS_NAME AS SUB_NAME,S.SORT AS SUB_SORT "
				  +" FROM TM_INS_CHECK_OBJ_CLASS_MIDDLE M,TM_INS_CHECK_OBJ_CLASS_SUB S "
				  +" WHERE M.`STATUS`='1' AND S.`STATUS`='1' "
				  +" AND M.CHECK_ENT_MID_CODE='"+midCode+"' "
				  +" AND M.OBJ_CLASS_CODE=S.OBJ_CLASS_F_CODE "
				  +" UNION  "
				  +" SELECT "
				  +" M.OBJ_CLASS_CODE AS MID_CODE,M.OBJ_CLASS_NAME AS MID_NAME,M.SORT AS MID_SORT, "
				  +" S.OBJ_CLASS_CODE AS SUB_CODE,S.OBJ_CLASS_NAME AS SUB_NAME,S.SORT AS SUB_SORT "
				  +" FROM TM_INS_CHECK_OBJ_CLASS_MIDDLE M,TM_INS_CHECK_OBJ_CLASS_SUB S "
				  +" WHERE M.`STATUS`='1' AND S.`STATUS`='1' "
				  +" AND M.OBJ_CLASS_F_CODE='"+supCode+"' "
				  +" AND M.OBJ_CLASS_CODE=S.OBJ_CLASS_F_CODE "
				  +" AND (M.CHECK_ENT_MID_CODE IS NULL OR M.CHECK_ENT_MID_CODE='') "
				  +" )A "
				  + " LEFT JOIN TT_INS_CHECK_ENTITY_OBJ_SUB_MAP M ON M.CHECK_ENT_SUB_CODE='"+entSubCode+"' AND M.FREEZE_TAG='0' AND A.SUB_CODE=M.OBJ_CLASS_SUB_CODE "
				  +" ORDER BY  A.MID_SORT ASC,A.MID_CODE ASC,A.SUB_SORT ASC,A.SUB_CODE ASC ";
		try {
			return DBConUtil.getResult(conn, sql, "SUB_OBJ_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过检查对象大类查询，查询检查所对应项目小类
	 * 方   法  名:queryTruckObjSupMidSubDataBySupCode
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryTruckObjSupMidSubDataBySupCode(Connection conn,String supEntCode){
		
		String sql=" SELECT "
				  +" A.OBJ_CLASS_CODE AS SUP_CODE,A.OBJ_CLASS_NAME AS SUP_NAME, "
				  +" B.OBJ_CLASS_CODE AS MID_CODE,B.OBJ_CLASS_NAME AS MID_NAME, "
				  +" C.OBJ_CLASS_CODE AS SUB_CODE,C.OBJ_CLASS_NAME AS SUB_NAME, "
				  +" B.SORT,C.SORT,A.CHECK_ENT_SUP_CODE "
				  +" FROM TM_INS_CHECK_OBJ_CLASS_SUPPER A,TM_INS_CHECK_OBJ_CLASS_MIDDLE B,TM_INS_CHECK_OBJ_CLASS_SUB C "
				  +" WHERE  "
				  +" A.`STATUS`='1' AND A.FREEZE_TAG='0' "
				  +" AND B.`STATUS`='1' AND B.FREEZE_TAG='0' "
				  +" AND C.`STATUS`='1' AND C.FREEZE_TAG='0' "
				  +" AND A.OBJ_CLASS_CODE=B.OBJ_CLASS_F_CODE "
				  +" AND B.OBJ_CLASS_CODE=C.OBJ_CLASS_F_CODE "
				  +" AND A.CHECK_ENT_SUP_CODE='"+supEntCode+"' "
				  +" ORDER BY B.SORT,B.OBJ_CLASS_CODE,C.SORT,C.OBJ_CLASS_CODE ASC ";
		try {
			return DBConUtil.getResult(conn, sql, "SUP_MID_SUB_OBJ_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 根据层级及代码，查询检查项目类明细 
	 * 方   法  名:queryCheckObjDetailByLevel
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param levelCode
	 * 参         数:@param nowCode
	 * 参         数:@param upCode
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean queryCheckObjDetailByLevel(Connection conn,String levelCode,String nowCode,String upCode){
		
		String tableName=" TM_INS_CHECK_OBJ_CLASS_SUPPER ";
		String fCodeSql="";
		String upName="";
		if("2".equals(levelCode)){
			tableName=" TM_INS_CHECK_OBJ_CLASS_MIDDLE ";
			fCodeSql=" AND OBJ_CLASS_F_CODE ='"+upCode+"'";
			//query sup
			TmInsCheckObjClassSupperPO supPOCon=new TmInsCheckObjClassSupperPO();
			supPOCon.setStatus("1");
			supPOCon.setObjClassCode(upCode);
			supPOCon=POFactory.getByPriKey(conn, supPOCon);
			if(supPOCon!=null){
				upName=supPOCon.getObjClassName();
			}
		}else if("3".equals(levelCode)){
			tableName=" TM_INS_CHECK_OBJ_CLASS_SUB ";
			fCodeSql=" AND OBJ_CLASS_F_CODE ='"+upCode+"'";
			//query mid
			TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
			midPOCon.setStatus("1");
			midPOCon.setObjClassCode(upCode);
			midPOCon=POFactory.getByPriKey(conn, midPOCon);
			if(midPOCon!=null){
				upName=midPOCon.getObjClassName();
			}
			
		}else{ //1
			tableName=" TM_INS_CHECK_OBJ_CLASS_SUPPER ";
			fCodeSql="";
			upName="";
		}
		
		String sql=" SELECT A.OBJ_CLASS_CODE,A.OBJ_CLASS_NAME,A.OBJ_CLASS_F_CODE,A.OBJ_CLASS_DESC,A.SORT,A.OBJ_CLASS_LEVEL ,"
				 + " A.ID AS OBJ_ID ,B.MEMBER_NAME AS CREATE_NAME, C.MEMBER_NAME AS UPDATE_NAME ,"
				 + " DATE_FORMAT(A.CREATE_TIME,'%Y-%m-%d %H:%i:%S') AS CREATE_TIME_STR, "
				 + " DATE_FORMAT(A.UPDATE_TIME,'%Y-%m-%d %H:%i:%S') AS UPDATE_TIME_STR,'"+upName+"' AS OBJ_CLASS_F_NAME "
				 + " FROM "+tableName+" A "
				 + " LEFT JOIN TM_SYS_MEMBER B ON B.STATUS='1' AND A.CREATE_BY=B.ID "
				 + " LEFT JOIN TM_SYS_MEMBER C ON C.STATUS='1' AND A.UPDATE_BY=C.ID"
				 + " WHERE 1=1 AND A.STATUS='1'  AND A.FREEZE_TAG ='0' "
				 + " AND A.OBJ_CLASS_CODE='"+nowCode+"' "
				 + fCodeSql;
		
		try {
			return DBConUtil.getSingleResult(conn, sql, "OBJECT_DETAIL_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询某个检查子类对应的所有检查项目集合,排序顺序：子类或者其上级代码 正序，项目sort正序，
	 * 结果集形式：
	 * upCode，itemA
	 * upCode，itemB
	 * upCode1，itemC
	 * 用于组织：待处理
	 * Map<upCode,List<itemA,itemB>>
	 * Map<upCode1,List<itemC>>
	 * 方   法  名:queryOneSubClassNodeCheckObjList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param objClassSubCode
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryOneSubClassNodeCheckObjList(Connection conn,String objClassSupCode,String objClassMidCode,String objClassSubCode){
		
		String sql=" SELECT DISTINCT A.ID,A.OBJ_CLASS_CODE_SUP,A.OBJ_CLASS_CODE_MID,A.OBJ_CLASS_CODE_SUB,A.OBJ_CLASS_TYPE_CODE,"
				 + " A.CHECK_OBJ_CODE,A.CHECK_OBJ_NAME,A.CHECK_OBJ_DESC,A.SORT,"
				 + " IFNULL(A.CHECK_OBJ_F_CODE,'"+objClassSubCode+"') AS F_CODE,"
				 + " IFNULL(B.CHECK_OBJ_NAME, IFNULL(A.CHECK_OBJ_F_CODE,'"+objClassSubCode+"')) AS F_NAME "
				 + " FROM TM_INS_CHECK_OBJ_ITEM A "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_ITEM B ON B.STATUS='1' AND B.FREEZE_TAG ='0' AND A.CHECK_OBJ_F_CODE=B.CHECK_OBJ_CODE "
				 + " WHERE A.STATUS='1' AND A.FREEZE_TAG ='0' "
				 + " AND A.NODE_PATH LIKE '"+objClassSupCode+"$$"+objClassMidCode+"$$"+objClassSubCode+"%'"
//				 + " ORDER BY B.SORT ASC, A.SORT ASC,A.CHECK_OBJ_CODE ASC ";
				 + " ORDER BY B.SORT ASC, IFNULL(A.CHECK_OBJ_F_CODE,'"+objClassSubCode+"') ASC, A.SORT ASC ";
		try {
			List<DynaBean> list=DBConUtil.getResult(conn, sql, "OBJ_ITEM_BEAN");
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	/**
	 * 根据检查对象类别查询检查项目list（为树形结构做准备）
	 * 方   法  名:queryOneSubClassNodeCheckObjListByEntType
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param entType
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryOneSubClassNodeCheckObjListByEntType(Connection conn,String entType){
		String subSql="";
		if(StringUtils.isNotBlank(entType)){
			subSql=" AND A.OBJ_CLASS_CODE_SUP IN (SELECT A.OBJ_CLASS_CODE FROM TM_INS_CHECK_OBJ_CLASS_SUPPER A ,TM_INS_CHECK_ENTITY_SUPPER  B "
					+ " WHERE A.STATUS='1' AND A.FREEZE_TAG='0' "
					+ " AND B.STATUS='1' AND B.FREEZE_TAG='0' "
					+ " AND A.CHECK_ENT_SUP_CODE=B.CHECK_ENT_CODE "
					+ " AND B.CHECK_ENT_TYPE_CODE='"+entType+"') ";
		}		
		String sql=" SELECT DISTINCT A.ID,A.OBJ_CLASS_CODE_SUP,A.OBJ_CLASS_CODE_MID,A.OBJ_CLASS_CODE_SUB,A.OBJ_CLASS_TYPE_CODE,"
				+ " A.CHECK_OBJ_CODE AS CODE,A.CHECK_OBJ_NAME AS NAME ,A.CHECK_OBJ_DESC,A.SORT,"
				+ " IFNULL(A.CHECK_OBJ_F_CODE,A.OBJ_CLASS_CODE_SUB) AS F_CODE,"
				+ " IFNULL(B.CHECK_OBJ_NAME,C.OBJ_CLASS_NAME) AS F_NAME "
				+ " FROM TM_INS_CHECK_OBJ_ITEM A "
				+ " LEFT JOIN TM_INS_CHECK_OBJ_ITEM B ON B.STATUS='1' AND B.FREEZE_TAG ='0' AND A.CHECK_OBJ_F_CODE=B.CHECK_OBJ_CODE "
				+ " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB C ON C.STATUS='1' AND C.FREEZE_TAG ='0' AND A.OBJ_CLASS_CODE_SUB=C.OBJ_CLASS_CODE "
				+ " WHERE A.STATUS='1' AND A.FREEZE_TAG ='0' "+subSql
//				+ " AND A.NODE_PATH LIKE '"+objClassSupCode+"$$"+objClassMidCode+"$$"+objClassSubCode+"%'"
//				+ " ORDER BY B.SORT ASC, A.SORT ASC,A.CHECK_OBJ_CODE ASC ";
				+ " ORDER BY B.SORT ASC, IFNULL(A.CHECK_OBJ_F_CODE,A.OBJ_CLASS_CODE_SUB) ASC, A.SORT ASC ";
		try {
			List<DynaBean> list=DBConUtil.getResult(conn, sql, "OBJ_ITEM_BEAN");
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	/**
	 * 处理 queryOneSubClassNodeCheckObjListByEntType 返回的list，包装成 packSupMidSubMap 相同的结构
	 * 方   法  名:packSupMidSubMap2
	 * 方法描述:
	 * 参         数:@param list
	 * 参         数:@return
	 * 返   回  值:Map<String,List<DynaBean>>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static Map<String,List<DynaBean>> packSupMidSubMap2(List<DynaBean> inputList){
		if(inputList==null)return null;
		Map<String,List<DynaBean>> resultMap=new LinkedHashMap<String, List<DynaBean>>();
		for(DynaBean dyBean:inputList){
			String upCode=dyBean.getString("F_CODE");//上级代码
			String upName=dyBean.getString("F_NAME");//上级名称
			String key=upCode+"$$"+upName;
			
			if(resultMap.containsKey(key)){
				List<DynaBean> list=resultMap.get(key);
				list.add(dyBean);
				resultMap.put(key, list);
			}else{
				List<DynaBean> list=new ArrayList<DynaBean>();
				list.add(dyBean);
				resultMap.put(key, list);
			}
		}
		
		Set keySet=resultMap.keySet();
		Iterator<String> iter=keySet.iterator();
		while(iter.hasNext()){
			String key=(String) iter.next();
			List<DynaBean> newList=resultMap.get(key);
			System.out.println("key:="+key);
			for(DynaBean bean:newList){
				System.out.print("bean= ["+bean.getString("CODE")+":"+bean.getString("NAME")+"]");
			}
			System.out.println("end");
		}
		return resultMap;
		
	}
	
	/**
	 * 将 queryOneSubClassNodeCheckObjList 方法返回的list，包装成map
	 * Map<upCode$$upName,List<itemA,itemB>>
	 * Map<upCode1$$upName1,List<itemC>>
	 * 方   法  名:getSameUpCodeItemListMap
	 * 方法描述:
	 * 参         数:@param inputList
	 * 参         数:@return
	 * 返   回  值:Map<String,List<DynaBean>>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static Map<String,List<DynaBean>> getSameUpCodeItemListMap(List<DynaBean> inputList){
		
		if(inputList==null)return null;
		Map<String,List<DynaBean>> resultMap=new Hashtable<String, List<DynaBean>>();
		for(DynaBean dyBean:inputList){
			String upCode=dyBean.getString("F_CODE");//上级代码
			String upName=dyBean.getString("F_NAME");//上级名称
			String key=upCode+"$$"+upName;
			
			if(resultMap.containsKey(key)){
				List<DynaBean> list=resultMap.get(key);
				list.add(dyBean);
				resultMap.put(key, list);
			}else{
				List<DynaBean> list=new ArrayList<DynaBean>();
				list.add(dyBean);
				resultMap.put(key, list);
			}
		}
		
		Set keySet=resultMap.keySet();
		Iterator<String> iter=keySet.iterator();
		while(iter.hasNext()){
			String key=(String) iter.next();
			List<DynaBean> newList=resultMap.get(key);
			System.out.println("key:="+key);
			for(DynaBean bean:newList){
				System.out.print("bean= ["+bean.getString("CHECK_OBJ_CODE")+":"+bean.getString("CHECK_OBJ_NAME")+"]");
			}
			System.out.println("end");
		}
		return resultMap;
	}
	
	
	
	
	/**
	 * 将queryOneSubClassNodeCheckObjList方法生成的map，
	 * 包装成 ul，li 的html内容(一个subObjCode对应一组li)
	 * 方   法  名:getSubObjClassNodeHtml
	 * 方法描述:
	 * 参         数:@param map
	 * 参         数:@param key
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getSubObjClassNodeHtml(Map<String,List<DynaBean>> map,String key){
		StringBuffer sbf=new StringBuffer("");
		sbf.append("<li>");
		sbf.append("<span>");
		sbf.append(key);
		sbf.append("</span>----");
		sbf.append("<a>"+key+"链接</a>");
		if(map.containsKey(key)){
			sbf.append("<ul>");
			List<DynaBean> itemDyList=map.get(key);
			for(DynaBean itemDyBean:itemDyList){
				String subKey=itemDyBean.getString("CHECK_OBJ_CODE");
				System.out.println("sub key:="+itemDyBean.getString("CHECK_OBJ_CODE"));
				System.out.println("sub name:="+itemDyBean.getString("CHECK_OBJ_NAME"));
				sbf.append(getSubObjClassNodeHtml(map, subKey));
			}
			sbf.append("</ul>");
			map.remove(key);//性能优化
		}
		sbf.append("</li>");
		return sbf.toString();
	}
	

	
	/**
	 * TEST BEGIN
	 */
	
	/**
	 * TM_INS_CHECK_OBJ_ITEM
	 * 0：所有级别项目：OBJ_CLASS_CODE_SUP，OBJ_CLASS_CODE_MID，OBJ_CLASS_CODE_SUB，OBJ_CLASS_TYPE_CODE，CHECK_OBJ_LEVEL，NODE_PATH 都有值，
	 * 1:一级项目：CHECK_OBJ_LEVEL=1;CHECK_OBJ_F_CODE=null;NODE_PATH=SUP_CODE$$MID_CODE$$SUB_CODE;
	 * 2：二级及以后级项目：CHECK_OBJ_LEVEL=2.3.4..;CHECK_OBJ_F_CODE=上级CHECK_OBJ_CODE;NODE_PATH=SUP_CODE$$MID_CODE$$SUB_CODE$$CHECK_OBJ_CODE$$CHECK_OBJ_CODE...;
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	
	public static void main(String args[]){
		Map<String,String> map=new Hashtable<String,String>();
		map.put("A", "B1$$B2$$B3");//CHECK SUB OBJ CLASS CODE
		map.put("B1", "C1$$C2");
		map.put("B2", "C3");
		map.put("C1", "D1");
		long before=System.currentTimeMillis();
		System.out.println(getNodeHtml(map, "A"));
		System.out.println(System.currentTimeMillis()-before);
		
		String str="CHE_010801$$车轴普通";
		System.out.println("1:="+str.split("[$][$]")[0]);
		System.out.println("2:="+str.split("[$][$]")[1]);
		
	}
	
	private static String getNodeHtml(Map<String,String> map,String key){
		StringBuffer sbf=new StringBuffer("");
		sbf.append("<li>");
		sbf.append("<span>");
		sbf.append(key);
		sbf.append("</span>----");
		sbf.append("<a>"+key+"链接</a>");
		if(map.containsKey(key)){
			sbf.append("<ul>");
			String value=map.get(key);
			String[] subKeyArray=value.split("[$][$]");
			for(String subKey:subKeyArray){
				System.out.println("sub key:="+subKey);
				sbf.append(getNodeHtml(map, subKey));
			}
			sbf.append("</ul>");
			map.remove(key);//性能优化
		}
		sbf.append("</li>");
		return sbf.toString();
	}
	/**
	 * TEST END
	 */
	
	/**
	 * 根据检查项目小类，查询中类，及大类的名称及代码
	 * 方   法  名:querySubMidSupObjClassBySubCode
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param subCode
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean querySubMidSupObjClassBySubCode(Connection conn,String subCode){
		
		String sql=" SELECT SUB.OBJ_CLASS_CODE AS SUB_CODE,SUB.OBJ_CLASS_NAME AS SUB_NAME,SUB.OBJ_CLASS_DESC AS SUB_DESC,SUB.SORT AS SUB_SORT ,"
				 + " MID.OBJ_CLASS_CODE AS MID_CODE,MID.OBJ_CLASS_NAME AS MID_NAME,MID.OBJ_CLASS_DESC AS MID_DESC,MID.SORT AS MID_SORT ,"
				 + " SUP.OBJ_CLASS_CODE AS SUP_CODE,SUP.OBJ_CLASS_NAME AS SUP_NAME,SUP.OBJ_CLASS_DESC AS SUP_DESC,SUP.SORT AS SUP_SORT "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUB SUB "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE MID"
				 + " ON SUB.OBJ_CLASS_F_CODE=MID.OBJ_CLASS_CODE "
				 + " AND MID.STATUS='1' AND MID.FREEZE_TAG='0' "
				 + " LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUPPER SUP "
				 + " ON MID.OBJ_CLASS_F_CODE=SUP.OBJ_CLASS_CODE "
				 + " AND SUP.STATUS='1' AND SUP.FREEZE_TAG='0' "
				 + " WHERE SUB.FREEZE_TAG='0' "
				 + " AND SUB.STATUS='1' "
				 + " AND SUB.OBJ_CLASS_CODE= '"+subCode+"' ";
		try {
			return DBConUtil.getSingleResult(conn, sql, "SUB_MID_SUP_OBJ_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据代码及名称查询项目大类，为前台提供联想输入
	 * 方   法  名:querySupClassBySupCodeAndSupName
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param supCode
	 * 参         数:@param supName
	 * 参         数:@param rows
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> querySupClassBySupCodeAndSupName(Connection conn,String supCode,String supName,int rows){
		
		String sql=" SELECT DISTINCT ID AS SUP_ID,OBJ_CLASS_CODE AS SUP_CODE,OBJ_CLASS_NAME AS SUP_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUPPER "
				 + " WHERE STATUS='1'"
				 + " AND FREEZE_TAG='0' "
				 + "";
		if(StringUtils.isNotBlank(supCode)){
			sql+=" AND OBJ_CLASS_CODE LIKE '%"+supCode+"%'";
		}
		if(StringUtils.isNotBlank(supName)){
			sql+=" AND OBJ_CLASS_NAME LIKE '%"+supName+"%'";
		}
			sql+=(" ORDER BY SORT  LIMIT 0, "+rows);
		try {
			return DBConUtil.getResult(conn, sql, "SUP_CLASS_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据代码及名称查询项目大类，中类，为前台提供联想输入
	 * 方   法  名:querySupMidClassByMidCodeAndMidName
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param midCode
	 * 参         数:@param midName
	 * 参         数:@param rows
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> querySupMidClassByMidCodeAndMidName(Connection conn,String midCode,String midName,String supCode,String supName,int rows){
		
		String sql=" SELECT DISTINCT SUP.ID AS SUP_ID,SUP.OBJ_CLASS_CODE AS SUP_CODE,SUP.OBJ_CLASS_NAME AS SUP_NAME ,"
				 + " MID.ID AS MID_ID,MID.OBJ_CLASS_CODE AS MID_CODE,MID.OBJ_CLASS_NAME AS MID_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUPPER SUP,TM_INS_CHECK_OBJ_CLASS_MIDDLE MID "
				 + " WHERE SUP.STATUS='1' AND SUP.FREEZE_TAG='0' AND  MID.STATUS='1' AND MID.FREEZE_TAG='0' "
				 + " AND MID.OBJ_CLASS_F_CODE=SUP.OBJ_CLASS_CODE "
				 + "";
		if(StringUtils.isNotBlank(midCode)){
			sql+=" AND MID.OBJ_CLASS_CODE LIKE '%"+midCode+"%'";
		}
		if(StringUtils.isNotBlank(midName)){
			sql+=" AND MID.OBJ_CLASS_NAME LIKE '%"+midName+"%'";
		}
		if(StringUtils.isNotBlank(supCode)){
			sql+=" AND SUP.OBJ_CLASS_CODE LIKE '%"+supCode+"%'";
		}
		if(StringUtils.isNotBlank(supName)){
			sql+=" AND SUP.OBJ_CLASS_NAME LIKE '%"+supName+"%'";
		}
			sql+=("  ORDER BY MID.SORT  LIMIT 0, "+rows);
		try {
			return DBConUtil.getResult(conn, sql, "MID_CLASS_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据代码及名称查询项目大类，中类，小类，为前台提供联想输入
	 * 方   法  名:querySupMidSubClassBySubCodeAndSubName
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param subCode
	 * 参         数:@param subName
	 * 参         数:@param rows
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> querySupMidSubClassBySubCodeAndSubName(Connection conn,String subCode,String subName,String midCode,String midName,String supCode,String supName,int rows){
		
		String sql=" SELECT DISTINCT SUP.ID AS SUP_ID,SUP.OBJ_CLASS_CODE AS SUP_CODE,SUP.OBJ_CLASS_NAME AS SUP_NAME ,"
				+ " MID.ID AS MID_ID,MID.OBJ_CLASS_CODE AS MID_CODE,MID.OBJ_CLASS_NAME AS MID_NAME,"
				+ " SUB.ID AS SUB_ID,SUB.OBJ_CLASS_CODE AS SUB_CODE,SUB.OBJ_CLASS_NAME AS SUB_NAME "
				+ " FROM TM_INS_CHECK_OBJ_CLASS_SUPPER SUP,TM_INS_CHECK_OBJ_CLASS_MIDDLE MID,TM_INS_CHECK_OBJ_CLASS_SUB SUB "
				+ " WHERE "
				+ " SUP.STATUS='1' AND SUP.FREEZE_TAG='0' "
				+ " AND MID.STATUS='1' AND MID.FREEZE_TAG='0' "
				+ " AND SUB.STATUS='1' AND SUB.FREEZE_TAG='0' "
				+ " AND MID.OBJ_CLASS_F_CODE=SUP.OBJ_CLASS_CODE "
				+ " AND SUB.OBJ_CLASS_F_CODE=MID.OBJ_CLASS_CODE "
				+ "";
		if(StringUtils.isNotBlank(subCode)){
			sql+=" AND SUB.OBJ_CLASS_CODE LIKE '%"+subCode+"%'";
		}
		if(StringUtils.isNotBlank(subName)){
			sql+=" AND SUB.OBJ_CLASS_NAME LIKE '%"+subName+"%'";
		}
		if(StringUtils.isNotBlank(midCode)){
			sql+=" AND MID.OBJ_CLASS_CODE LIKE '%"+midCode+"%'";
		}
		if(StringUtils.isNotBlank(midName)){
			sql+=" AND MID.OBJ_CLASS_NAME LIKE '%"+midName+"%'";
		}
		if(StringUtils.isNotBlank(supCode)){
			sql+=" AND SUP.OBJ_CLASS_CODE LIKE '%"+supCode+"%'";
		}
		if(StringUtils.isNotBlank(supName)){
			sql+=" AND SUP.OBJ_CLASS_NAME LIKE '%"+supName+"%'";
		}
		sql+=(" ORDER BY SUB.SORT  LIMIT 0, "+rows);
		try {
			return DBConUtil.getResult(conn, sql, "SUB_CLASS_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据检查对象类型，查询其对应的检查项目类：大，中，小类，用于组织 二级结构hash map，并最终生成
	 * 检查单界面的检查项目树状图所需数据
	 * 20180408 增加检查项目的树状list
	 * 方   法  名:querySupMidSubClassListByEntType
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param checkEntTypeCode
	 * 参         数:@return
	 * 返   回  值:List<DynaBean(sup,mid,sub)>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static List<DynaBean> querySupMidSubClassListByEntType(Connection conn,String checkEntTypeCode){
		String subSql="";
		if(StringUtils.isNotBlank(checkEntTypeCode)){
			subSql=" AND SUP.CHECK_ENT_SUP_CODE IN (SELECT CHECK_ENT_CODE FROM TM_INS_CHECK_ENTITY_SUPPER WHERE STATUS='1' AND FREEZE_TAG='0' AND CHECK_ENT_TYPE_CODE='"+checkEntTypeCode+"') ";
		}
		String sql=" SELECT DISTINCT SUP.OBJ_CLASS_CODE AS SUP_CODE,SUP.OBJ_CLASS_NAME AS SUP_NAME,SUP.SORT AS SUP_SORT, "
				  +" MID.OBJ_CLASS_CODE AS MID_CODE,MID.OBJ_CLASS_NAME AS MID_NAME,MID.SORT AS MID_SORT, "
				  +" SUB.OBJ_CLASS_CODE AS SUB_CODE,SUB.OBJ_CLASS_NAME AS SUB_NAME,SUB.SORT AS SUB_SORT "
				  +" FROM TM_INS_CHECK_OBJ_CLASS_SUPPER SUP "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_MIDDLE MID ON SUP.OBJ_CLASS_CODE=MID.OBJ_CLASS_F_CODE AND MID.FREEZE_TAG='0' AND MID.`STATUS`='1' "
				  +" LEFT JOIN TM_INS_CHECK_OBJ_CLASS_SUB SUB ON  MID.OBJ_CLASS_CODE=SUB.OBJ_CLASS_F_CODE AND SUB.FREEZE_TAG='0' AND SUB.`STATUS`='1' "
				  +" WHERE SUP.FREEZE_TAG='0' AND SUP.`STATUS`='1'  "+subSql
				  +" ORDER BY SUP.SORT,SUP.OBJ_CLASS_CODE,MID.SORT,MID.OBJ_CLASS_CODE,SUB.SORT,SUB.OBJ_CLASS_CODE ASC  ";
		log.debug("SQL:="+sql);
		try {
			return DBConUtil.getResult(conn, sql, "SUP_MID_SUB_LIST");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(" QUEREY ERROR "+e);
		}
		return null;
	}
	
	/**
	 * 将querySupMidSubClassListByEntType 生成的list ，包装成
	 * hashtable <upcode$$upName,list<dynaBean>>
	 * 确保排序，需要LinkedHashMap
	 * 方   法  名:packSupMidSubMap
	 * 方法描述:
	 * 参         数:@param list
	 * 参         数:@return
	 * 返   回  值:Map
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static Map<String,List<DynaBean>> packSupMidSubMap(List<DynaBean> list){
		Map<String,List<DynaBean>> supMidMap=new LinkedHashMap<String,List<DynaBean>>();//supCode$$supName:List<midDynaBean>
		Map<String,List<DynaBean>> midSubMap=new LinkedHashMap<String,List<DynaBean>>();//midCode$$midName:List<subDynaBean>
		supMidMap.put("ROOT", new ArrayList<DynaBean>());
		if(list!=null){
			for(DynaBean bean:list){
				String supCode=bean.getString("SUP_CODE");
				String supName=bean.getString("SUP_NAME");
				
				String midCode=bean.getString("MID_CODE");
				String midName=bean.getString("MID_NAME");
				
				String subCode=bean.getString("SUB_CODE");
				String subName=bean.getString("SUB_NAME");
				
				String supKey=supCode+"$$"+supName;
				String midKey=midCode+"$$"+midName;
				
				DynaBean supValue=new DynaBean("SUP_BEAN");//sup,mid,sub code不能相同
				supValue.add("CODE", supCode);
				supValue.add("NAME", supName);
				
				DynaBean midValue=new DynaBean("MID_BEAN");
				midValue.add("CODE", midCode);
				midValue.add("NAME", midName);
				
				DynaBean subValue=new DynaBean("SUB_BEAN");
				subValue.add("CODE", subCode);
				subValue.add("NAME", subName);
				//
				
				//package root sup
				if(supMidMap.containsKey("ROOT")&&supMidMap.get("ROOT").size()>0){
					List<DynaBean> supValueList=supMidMap.get("ROOT");
					DynaBean lastSupValueBean=supValueList.get(supValueList.size()-1);
					if(!lastSupValueBean.getString("CODE").equals(supValue.getString("CODE"))){
//					if(!lastSupValueBean.equals(supValue)){
						supValueList.add(supValue);
						supMidMap.put("ROOT", supValueList);
					}
				}else{
					List<DynaBean> supValueList=new ArrayList<DynaBean>();
					supValueList.add(supValue);
					supMidMap.put("ROOT", supValueList);
				}
				//package sup mid
				//null 处理
				if(midValue.get("CODE")!=null){
					if(supMidMap.containsKey(supKey)){
						List<DynaBean> midValueList=supMidMap.get(supKey);
						DynaBean lastMidValueBean=midValueList.get(midValueList.size()-1);
						if(!lastMidValueBean.getString("CODE").equals(midValue.getString("CODE"))){
	//					if(!lastMidValueBean.equals(midValue)){
	//					if(lastMidValueBean.getString("MID_CODE").equals(midCode)){
							midValueList.add(midValue);
							supMidMap.put(supKey, midValueList);
						}
					}else{
						List<DynaBean> midValueList=new ArrayList<DynaBean>();
						midValueList.add(midValue);
						supMidMap.put(supKey, midValueList);
					}
				}
				//package mid sub
				if(subValue.getString("CODE")!=null){
					if(midSubMap.containsKey(midKey)){
						List<DynaBean> subValueList=midSubMap.get(midKey);
						DynaBean lastSubValueBean=subValueList.get(subValueList.size()-1);
						if(!lastSubValueBean.getString("CODE").equals(subValue.getString("CODE"))){
	//					if(!lastSubValueBean.equals(subValue)){
	//					if(lastMidValueBean.getString("MID_CODE").equals(midCode)){
							subValueList.add(subValue);
							midSubMap.put(midKey, subValueList);
						}
					}else{
						List<DynaBean> subValueList=new ArrayList<DynaBean>();
						subValueList.add(subValue);
						midSubMap.put(midKey, subValueList);
					}
				}
			}
		}
		
		supMidMap.putAll(midSubMap);
		Set keySet=supMidMap.keySet();
		Iterator<String> iter=keySet.iterator();
		while(iter.hasNext()){
			String key=(String) iter.next();
			List<DynaBean> newList=supMidMap.get(key);
			System.out.println("key:="+key);
			for(DynaBean bean:newList){
				System.out.print("bean= ["+bean.getString("CODE")+":"+bean.getString("NAME")+"]");
			}
			System.out.println("end");
		}
		return supMidMap;
	}
	/**
	 * for bootstrap tree view
	 * 方   法  名:getOnceSupMidSubTreeViewString
	 * 方法描述:
	 * 参         数:@param key
	 * 参         数:@param dataMap
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	/*
	private static String getOnceSupMidSubTreeViewString(String key, Map<String,List<DynaBean>> dataMap){
		 if(dataMap!=null){
			 StringBuffer sbf=new StringBuffer("");
//			 StringBuffer sbf=new StringBuffer("[{");
//			 Set<String> keySet=dataMap.keySet();
//			 Iterator<String> keyIter=keySet.iterator();
//			 while(keyIter.hasNext()){
//				 String key=keyIter.next();
				 String classCode=key.split("[$][$]")[0];
				 String className=key.split("[$][$]")[1];
				 System.out.println(" getOnceSupMidSubTreeViewString key:="+key);
				 System.out.println(" getOnceSupMidSubTreeViewString classCode:="+classCode);
				 System.out.println(" getOnceSupMidSubTreeViewString className:="+className);
				 
				 List<DynaBean> value=dataMap.get(key);//
				 
				 sbf.append("text: '"+className+"',");
				 sbf.append("href: '#"+classCode+"',");
				 sbf.append("tags: ['"+(value!=null?value.size():0)+"']");
				 if(value!=null){
					 DynaBean temp=value.get(0);
					 if(temp!=null&&(temp.getString("F_CODE")==null)){//改为只针对1，2级
//					 if(temp!=null&&(temp.getString("F_CODE")==null||temp.getString("OBJ_CLASS_CODE_SUB").equals(classCode))){
						 sbf.append(",backColor:'hsla(0, 6%, 19%, 0.16)' ");//样式处理，非检查项目，背景色为
//						 sbf.append(",backColor:'#56b0ca' ");//样式处理，非检查项目，背景色为
//						 sbf.append(",backColor:'#17a2b8' ");//样式处理，非检查项目，背景色为
						 sbf.append(",color:'#FFFFFF' ");//样式处理，非检查项目，背景色为
					 }
					 sbf.append(",nodes:");
					 sbf.append("[");
					 for(DynaBean valueBean:value){
						 String valueCode=valueBean.getString("CODE");
						 String valueName=valueBean.getString("NAME");
						 String valueKey=valueCode+"$$"+valueName;
						 System.out.println("valueKey:="+valueKey);
						 sbf.append("{");
						 sbf.append(getOnceSupMidSubTreeViewString(valueKey,dataMap));
//						 System.out.println("valueKey:="+valueKey);
//						 sbf.append("text: '"+valueName+"',");
//						 sbf.append("href: '#"+valueCode+"',");
//						 sbf.append("tags: ['"+(dataMap!=null&&dataMap.get(valueKey)!=null?dataMap.get(valueKey).size():0)+"'],");
//						 if(dataMap!=null&&dataMap.get(valueKey)!=null){
//							 sbf.append("nodes:"+getOnceSupMidSubTreeViewString(valueKey,dataMap));
//						 }
						 sbf.append("},");
					 }
					 sbf.append("]");
				 }	
				 dataMap.remove(key);
//			 }
//			 sbf.append("}],");
			 return sbf.toString();
		 }
		return null;
	}
	
	*/
	
	/**
	 * for gijgo.min ,20180427
	 * 方   法  名:getOnceSupMidSubTreeViewString
	 * 方法描述:
	 * 参         数:@param key
	 * 参         数:@param dataMap
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static String getOnceSupMidSubTreeViewString(String key, Map<String,List<DynaBean>> dataMap){
		 if(dataMap!=null){
			 StringBuffer sbf=new StringBuffer("");
//			 StringBuffer sbf=new StringBuffer("[{");
//			 Set<String> keySet=dataMap.keySet();
//			 Iterator<String> keyIter=keySet.iterator();
//			 while(keyIter.hasNext()){
//				 String key=keyIter.next();
				 String classCode=key.split("[$][$]")[0];
				 String className=key.split("[$][$]")[1];
				 System.out.println(" getOnceSupMidSubTreeViewString key:="+key);
				 System.out.println(" getOnceSupMidSubTreeViewString classCode:="+classCode);
				 System.out.println(" getOnceSupMidSubTreeViewString className:="+className);
				 
				 List<DynaBean> value=dataMap.get(key);//
				 
//				 sbf.append("id: "+System.currentTimeMillis()+",");
				 sbf.append("text: '"+className+"',");
				 sbf.append("pk: '"+key+"',");//设置为唯一标示 即 对应id
				 sbf.append("population: "+System.currentTimeMillis()+",");
				 sbf.append("flagUrl: '#"+classCode+"',");
				 sbf.append("checked: "+false+",");
				 sbf.append("hasChildren: "+false+",");
				 sbf.append("tags: ['"+(value!=null?value.size():0)+"'],");
				 sbf.append("ORDER_ITEM_ID: -1,");//CHECK ORDER ITEM ID
				 sbf.append("IS_ITEM_TAG:"+!CHECK_OBJ_CLASS_MAP.containsKey(key));//true:是检查项目，false：不是检查项目（是检查项目类别）
				 if(value!=null){
					 DynaBean temp=value.get(0);
					 if(temp!=null&&(temp.getString("F_CODE")==null)){//改为只针对1，2级
//					 if(temp!=null&&(temp.getString("F_CODE")==null||temp.getString("OBJ_CLASS_CODE_SUB").equals(classCode))){
//						 sbf.append(",backColor:'hsla(0, 6%, 19%, 0.16)' ");//样式处理，非检查项目，背景色为
//						 sbf.append(",backColor:'#56b0ca' ");//样式处理，非检查项目，背景色为
//						 sbf.append(",backColor:'#17a2b8' ");//样式处理，非检查项目，背景色为
//						 sbf.append(",color:'#FFFFFF' ");//样式处理，非检查项目，背景色为
					 }
					 sbf.append(",children:");
					 sbf.append("[");
					 for(DynaBean valueBean:value){
						 String valueCode=valueBean.getString("CODE");
						 String valueName=valueBean.getString("NAME");
						 String valueKey=valueCode+"$$"+valueName;
						 System.out.println("valueKey:="+valueKey);
						 sbf.append("{");
						 sbf.append(getOnceSupMidSubTreeViewString(valueKey,dataMap));
//						 System.out.println("valueKey:="+valueKey);
//						 sbf.append("text: '"+valueName+"',");
//						 sbf.append("href: '#"+valueCode+"',");
//						 sbf.append("tags: ['"+(dataMap!=null&&dataMap.get(valueKey)!=null?dataMap.get(valueKey).size():0)+"'],");
//						 if(dataMap!=null&&dataMap.get(valueKey)!=null){
//							 sbf.append("nodes:"+getOnceSupMidSubTreeViewString(valueKey,dataMap));
//						 }
						 sbf.append("},");
					 }
					 sbf.append("]");
				 }	
				 dataMap.remove(key);
//			 }
//			 sbf.append("}],");
			 return sbf.toString();
		 }
		return null;
	}
	
	/**
	 * 根据检查对象类型，生成tree view 需要的树状字符串格式
	 * 方   法  名:getSupMidSubTreeViewStringByEntCheckType
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param checkEntTypeCode
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getSupMidSubTreeViewStringByEntCheckType(Connection conn,String checkEntTypeCode){

		 Map<String,List<DynaBean>> dataMap=packSupMidSubMap(querySupMidSubClassListByEntType(conn, checkEntTypeCode));
		 Map<String,List<DynaBean>> itemMap=packSupMidSubMap2(queryOneSubClassNodeCheckObjListByEntType(conn, checkEntTypeCode));
		 if(dataMap!=null){
			 dataMap.putAll(itemMap);
			 initCheckObjClassMap(conn);
		 }
		 
		 if(dataMap!=null&&dataMap.get("ROOT")!=null){
			List<DynaBean> supList=dataMap.get("ROOT");
			StringBuffer sbf=new StringBuffer("[");
			for(DynaBean supBean:supList){
				sbf.append("{");
				String supKey=supBean.getString("CODE")+"$$"+supBean.getString("NAME");
				String result= getOnceSupMidSubTreeViewString(supKey,dataMap);	
				System.out.println(result); 
				sbf.append(result);
				sbf.append("},");
			}
			sbf.append("]");
			System.out.println(sbf.toString()); 
			return sbf.toString();

		 }
		 return null;
	}
	

	/**
	 * 将TM_INS_CHECK_OBJ_CLASS_SUPPER
		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TM_INS_CHECK_OBJ_CLASS_SUB
		code 及 name 放入CHECK_OBJ_CLASS_MAP中
	 * 方   法  名:initCheckObjClassMap
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static void initCheckObjClassMap(Connection conn){

		String sql=" SELECT OBJ_CLASS_CODE,OBJ_CLASS_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUPPER WHERE STATUS='1' "
				 + " UNION ALL "
				 + " SELECT OBJ_CLASS_CODE,OBJ_CLASS_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_MIDDLE WHERE STATUS='1' "
				 + " UNION ALL "
				 + " SELECT OBJ_CLASS_CODE,OBJ_CLASS_NAME "
				 + " FROM TM_INS_CHECK_OBJ_CLASS_SUB WHERE STATUS='1' ";
		try {
			List<DynaBean> list=DBConUtil.getResult(conn, sql, "CHECK_OBJ_CLASS");
			if(list!=null){
				for(DynaBean bean:list){
					String keyValue=bean.getString("OBJ_CLASS_CODE")+"$$"+bean.getString("OBJ_CLASS_NAME");
					CHECK_OBJ_CLASS_MAP.put(keyValue, keyValue);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
