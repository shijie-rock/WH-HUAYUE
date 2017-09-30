package com.cn.gpslms.util;
/*
 * Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.
 * This software is published under the terms of the Infoservice Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * CreateDate : 2006-2-14
 * CreateBy   : Kevin Sun
 * Comment    :
 */



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.BeanInfo;
import com.infoservice.po.DataBean;
import com.infoservice.po.DynaBean;
import com.infoservice.po.ObjFormater;


/**
 * Factory类的工具方法集
 * @author Kevin Sun
 * @version %I%, %G%
 */
public class POFactoryUtil {
	private static HashMap<Class,BeanInfo> beanInfoMap=new HashMap<Class,BeanInfo>();
	private static Logger logger = LogManager.getLogger(POFactoryUtil.class);
	
	/**
	 * 根据参数bean的类型从缓存中获得bean的反射信息，如果缓存中没有则利用反射机制生成BeanInfo.
	 * @param bean
	 * @return
	 * @author Kevin Sun
	 */
	public static BeanInfo getDataBeanInfo(DataBean bean){
		if ( bean == null ) return null;
		
		if ( !beanInfoMap.containsKey(bean.getClass()))
			beanInfoMap.put(bean.getClass(),new BeanInfo(bean));
			
		return (BeanInfo)beanInfoMap.get(bean.getClass());		
	}
	
	/**
	 * 根据BeanInfo生成insert动作的sql语句
	 * @param info
	 * @return
	 * @author Kevin Sun
	 */
	public static String getInsertSql(BeanInfo info,DataBean bean) throws Exception{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("insert into "+info.getTabName()+"( ");
		
		int size = info.getColSize();
		int colSize = 0;
		for ( int i=0;i<size;i++){
			if ( info.getColVal(bean,i)!=null ){
				sbuf.append(info.getColName(i)+",");
				colSize ++;
			}				
		}
		sbuf.deleteCharAt(sbuf.length()-1);
		
		sbuf.append(") values(");
		
		for ( int i=0;i<colSize;i++){
			sbuf.append("?,");
		}
		sbuf.deleteCharAt(sbuf.length()-1);
		
		sbuf.append(")");
		
		return sbuf.toString();
	}
	
	/**
	 * 根据传入的bean，查询条件（condition）,更新字段（value)生成更新sql语句。
	 * @param info
	 * @param condition
	 * @param value
	 * @return
	 * @throws Exception
	 * @author Kevin Sun
	 */
	public static String getUpdateSql(BeanInfo info,DataBean condition,DataBean value) throws Exception{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("update "+info.getTabName()+" set ");
		
		int size = info.getColSize();
		int tmp =0;
		for ( int i=0;i<size;i++ ){
			if ( info.getColVal(value,i)!=null ){
				sbuf.append(info.getColName(i)+"=?,");
				tmp++;
			}
		}
		if ( tmp>0 ) sbuf.deleteCharAt(sbuf.length()-1);

		
		sbuf.append(" where 1=1 ");
		tmp = 0;
		for ( int i=0;i<size;i++ ){
			if ( info.getColVal(condition,i)!=null ){
				sbuf.append(" and "+info.getColName(i)+"=? ");
				tmp++;
			}
		}
		
		return sbuf.toString();
	}
	
	/**
	 * 根据传入的bean信息和删除条件生成删除操作的sql语句。
	 * @param info
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Kevin Sun
	 */
	public static String getDeleteSql(BeanInfo info ,DataBean condition) throws Exception{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("delete from "+info.getTabName()+" where 1=1 ");
		
		int size = info.getColSize();
		for ( int i=0;i<size;i++ ){
			if ( info.getColVal(condition,i)!=null )	
				sbuf.append(" and "+info.getColName(i)+"=? ");
		}
		
		return sbuf.toString();
	}
	
	/**
	 * 根据bean和查询信息生成查询语句的sql语句
	 * @param info
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Kevin Sun
	 */
	public static String getSelectSql(BeanInfo info,DataBean condition) throws Exception{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("select ");
		
		int size = info.getColSize();
		for ( int i=0;i<size;i++){
			sbuf.append(info.getColName(i)+",");
		}
		sbuf.deleteCharAt(sbuf.length()-1);
		
		sbuf.append(" from "+info.getTabName()+" where 1=1 ");		
		
		for ( int i=0;i<size;i++ ){
			if ( info.getColVal(condition,i)!=null )
				sbuf.append(" and "+info.getColName(i)+"=? ");
		}
		
		return sbuf.toString();
	}
	
	/**
	 * 把java.util.Date转换成java.sql.Timestamp
	 * @param dt
	 * @return
	 * @author Kevin Sun
	 */
	public static java.sql.Timestamp toSqlDate(Date dt){
		if ( dt ==null )
			return null;
		else
			return new java.sql.Timestamp(dt.getTime());
	}
	
	/**
	 * 把java.sql.Timestamp转换成java.util.Date
	 * @param dt
	 * @return
	 * @author Kevin Sun
	 */
	public static Date toJavaDate(java.sql.Timestamp dt){
		if (dt==null)
			return null;
		else
			return new Date(dt.getTime());
	}
	
	/**
	 * 把实现了DataBean接口的类转换成XML格式的字符串
	 * @param bean
	 * @return
	 * @author Kevin Sun
	 */
	public static String beanToXmlString(DataBean bean) {
//		StringBuffer sbuf = new StringBuffer();
//		BeanInfo info = getDataBeanInfo(bean);
//		sbuf.append("<BEAN");
//		
//		int size = info.getColSize();
//		Object val =null;
//		for ( int i=0;i<size;i++ ) {
//			try{
//				val = info.getColVal(bean,i);
//				sbuf.append(" "+info.getColName(i).toUpperCase()+"=\"");
//				
//				if ( val == null ){
//					sbuf.append("\"");					
//				}else if( info.getColType(i).equals(String.class) )
//					sbuf.append(info.getColVal(bean,i)+"\"");
//				else if (info.getColType(i).equals(java.util.Date.class))
//					sbuf.append(ObjFormater.dateFormat((java.util.Date)info.getColVal(bean,i))+"\"");
//				else if (info.getColType(i).equals(Float.class))
//					sbuf.append(ObjFormater.decimalFormat(((Float)info.getColVal(bean,i)).floatValue())+"\"");
//				else if ( info.getColType(i).equals(Double.class))
//					sbuf.append(ObjFormater.decimalFormat(((Double)info.getColVal(bean,i)).doubleValue())+"\"");
//				else
//					sbuf.append(info.getColVal(bean,i)+"\"");
//					
//			}catch(Exception e){
//				e.printStackTrace();
//				return null;
//			}
//		}
//		
//		sbuf.append("/>");
//		return sbuf.toString();
		
		StringBuilder sbuf = new StringBuilder();
		BeanInfo info = getDataBeanInfo(bean);
		sbuf.append("<"+info.getTabName().toUpperCase()+">\n");
		
		int size = info.getColSize();
		Object val =null;
		for ( int i=0;i<size;i++ ) {
			try{
				val = info.getColVal(bean,i);
				sbuf.append("\t<"+info.getColName(i).toUpperCase()+">");
				
				if ( val == null ){
					sbuf.append("<![CDATA[]]>");					
				}else if( info.getColType(i).equals(String.class) )
					sbuf.append("<![CDATA["+info.getColVal(bean,i)+"]]>");
				else if (info.getColType(i).equals(Date.class))
					sbuf.append("<![CDATA["+ObjFormater.dateFormat((Date)info.getColVal(bean,i))+"]]>");
				else if (info.getColType(i).equals(Float.class))
					sbuf.append("<![CDATA["+ObjFormater.decimalFormat(((Float)info.getColVal(bean,i)).floatValue())+"]]>");
				else if ( info.getColType(i).equals(Double.class))
					sbuf.append("<![CDATA["+ObjFormater.decimalFormat(((Double)info.getColVal(bean,i)).doubleValue())+"]]>");
				else
					sbuf.append("<![CDATA["+info.getColVal(bean,i)+"]]>");
					
				sbuf.append("</"+info.getColName(i).toUpperCase()+">\n");
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		sbuf.append("</"+info.getTabName().toUpperCase()+">\n");
		return sbuf.toString();
	}
	
	/**
	 * 根据使用者传入的结果集和dyncBean的名称，把结果集中的数据构造成动态bean并返回动态bean的list集合。
	 * @param beanName
	 * @param rs
	 * @return
	 * @throws Exception
	 * @author Kevin Sun
	 */
	public static List<DynaBean> getDynaBeansFromResultSet(String beanName,ResultSet rs) throws Exception{
		HashMap<String,Class> lhm = getResultSetMetaData(rs);
		LinkedList<DynaBean> result = new LinkedList<DynaBean>();
		Iterator ite = null;
		String key ;
		Class val ;
		while ( rs.next() ){
			DynaBean bean = new DynaBean(beanName);
			ite = lhm.keySet().iterator();
			while ( ite.hasNext()){
				key = (String)ite.next();
				val = lhm.get(key);
				
				if ( String.class.equals(val) )
					bean.add(key,rs.getString(key));
				else if (Integer.class.equals(val))
					bean.add(key,rs.getInt(key));
				else if (Long.class.equals(val))
					bean.add(key,rs.getLong(key));
				else if (Float.class.equals(val))
					bean.add(key,rs.getFloat(key));
				else if (Double.class.equals(val))
					bean.add(key,rs.getDouble(key));
				else if (Date.class.equals(val))
					bean.add(key,rs.getTimestamp(key)==null?null:new Date(rs.getTimestamp(key).getTime()));
				else{
					bean.add(key,rs.getObject(key));
					//TODO : jdk
//					logger.info("============>"+key+" val="+rs.getObject(key)+"  valType="+val);
				}
			}
			result.addLast(bean);
		}
		return result;
	}
	
	/**
	 * 根据用户传入的dtabean类型从ResultSet中读取数据，返回List结果集。
	 * @param bean
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static<T extends DataBean> List<T> getDataBeansFromResultSet(T bean ,ResultSet rs) throws Exception{
		BeanInfo binfo = getDataBeanInfo(bean);
		LinkedList<T> result = new LinkedList<T>();
		
		String colName =null;
		Class colType =null;
		Object val = null;
		while( rs.next() ){
			T tmp =(T)(binfo.getCls().newInstance());
			for ( int i=0;i<binfo.getColSize();i++ ){
				colName = binfo.getColName(i);
				colType = binfo.getColType(i);
				val = rs.getObject(colName);
				if ( val==null ) continue;
				
				if ( colType.equals(Date.class))
					binfo.setColVal(tmp,i,rs.getTimestamp(colName)==null?null:new Date(rs.getTimestamp(colName).getTime()));
				else if ( colType.equals(Integer.class))
					binfo.setColVal(tmp,i,new Integer(rs.getInt(colName)));
				else if ( colType.equals(Long.class))
					binfo.setColVal(tmp,i,new Long(rs.getLong(colName)));
				else if ( colType.equals(Float.class))
					binfo.setColVal(tmp,i,new Float(rs.getFloat(colName)));
				else if ( colType.equals(Double.class))
					binfo.setColVal(tmp,i,new Double(rs.getDouble(colName)));
				else if ( colType.equals(String.class))
					binfo.setColVal(tmp,i,rs.getString(colName));
				else
					binfo.setColVal(tmp,i,val);					
			}
			result.addLast(tmp);
		}
		return result;
	}
	
	public static HashMap<String,Class> getResultSetMetaData(ResultSet rs) throws Exception{
		HashMap<String,Class> lhs = new HashMap<String,Class>(5);
		ResultSetMetaData rsmd=rs.getMetaData();
		int colSize = rsmd.getColumnCount();

		for ( int i=1;i<colSize+1;i++){	
			lhs.put(rsmd.getColumnName(i).toUpperCase(),getJavaType(rsmd.getColumnName(i),rsmd.getColumnType(i),rsmd.getPrecision(i),rsmd.getScale(i)));
			//TODO : jdk
//			if ( lhs.get(rsmd.getColumnName(i)).equals(java.math.BigDecimal.class) ){
//				logger.info("=====>Name:"+rsmd.getColumnName(i)+" Coltype:"+rsmd.getColumnType(i)+" preci:"+rsmd.getPrecision(i)+" Scale:"+rsmd.getScale(i));
//			}
		}

		return lhs;
	}
	
	private static Class getJavaType(String colName,int colType, int colPrecision, int colScale) throws Exception {
		Class cls = null;
		String jType =null;
		
		switch (colType) {
			case Types.ARRAY:
				jType = "ARRAY";
				break;
			case Types.BIGINT:
				jType = "BIGINT";
				cls = Long.class;
				break;
			case Types.BINARY:
				jType = "BINARY";
				break;
			case Types.BIT:
				jType = "BIT";
				// update by liuyin 2010-7-26
				cls = Integer.class;
				break;
			case Types.BLOB:
				jType = "BLOB";
				break;
			case Types.BOOLEAN:
				jType = "BOOLEAN";
				break;
			case Types.CHAR:
				jType = "CHAR";
				cls = String.class;
				break;
			case Types.CLOB:
				jType = "CLOB";
				break;
			case Types.DATALINK:
				jType = "DATALINK";
				break;
			case Types.DATE:
				jType = "DATE";
				cls = Date.class;
				break;
			case Types.DECIMAL:
				jType = "NUMERIC";
				// add by liuyin 2010-7-5
				if (colScale == -127) {
					cls = Double.class;
				}
				else if (colPrecision == 0 && colScale <= 0) { // oracle下函数没有精度设置.
					cls = Double.class;
				} else if (colScale == 0) { //number(m);
					if (colPrecision > 9) {
						cls = Long.class;
					} else {
						cls = Integer.class;
					}
				} else if (colScale > 0) { //number(m,n)
					if (colPrecision > 9) {
						cls = Double.class;
					} else {
						cls = Float.class;
					}
				}
				break;
			case Types.DISTINCT:
				jType = "DISTINCT";
				break;
			case Types.DOUBLE:
				jType = "DOUBLE";
				cls = Double.class;
				break;
			case Types.FLOAT:
				jType = "FLOAT";
				cls = Float.class;
				break;
			case Types.INTEGER:
				jType = "INTEGER";
				cls = Integer.class;
				break;
			case Types.JAVA_OBJECT:
				jType = "JAVA_OBJECT";
				break;
			case Types.LONGVARBINARY:
				jType = "LONGVARBINARY";
				break;
			case Types.LONGVARCHAR:
				jType = "LONGVARCHAR";
				cls = String.class;
				break;
			case Types.NULL:
				jType = "NULL";
				break;
			case Types.NUMERIC:
				jType = "NUMERIC";
				// add by liuyin 2010-7-5
				if (colScale == -127) {
					cls = Double.class;
				} 
				else if (colPrecision == 0 && colScale <= 0) { // oracle下函数没有精度设置.
					cls = Double.class;
				} else if (colScale == 0) { //number(m);
					if (colPrecision > 9) {
						cls = Long.class;
					} else {
						cls = Integer.class;
					}
				} else if (colScale > 0) { //number(m,n)
					if (colPrecision > 9) {
						cls = Double.class;
					} else {
						cls = Float.class;
					}
				}
				break;
			case Types.OTHER:
				jType = "OTHER";
				break;
			case Types.REAL:
				jType = "REAL";
				break;
			case Types.REF:
				jType = "REF";
				break;
			case Types.SMALLINT:
				jType = "SMALLINT";
				cls = Integer.class;
				break;
			case Types.STRUCT:
				jType = "STRUCT";
				break;
			case Types.TIME:
				jType = "TIME";
				break;
			case Types.TIMESTAMP:
				jType = "TIMESTAMP";
				cls = Date.class;
				break;
			case Types.TINYINT:
				jType = "TINYINT";
				cls = Integer.class;
				break;
			case Types.VARBINARY:
				jType = "VARBINARY";
				break;
			case Types.VARCHAR:
				jType = "VARCHAR";
				cls = String.class;
				break;
			default :
				jType = "JDBC_NOT_SUPPORT";
		}
		if (cls != null){
			//TODO : jdk
//			if ( cls.equals(java.math.BigDecimal.class) ){
//				logger.info("=====>Name:"+colName+" Coltype:"+colType+" preci:"+colPrecision+" Scale:"+colScale+" jType:"+jType);
//			}
			return cls;
		}else
			throw new Exception("Not supported column type! colName="+colName+" jdbcType="+jType+" type=" + colType
					+ " precision=" + colPrecision + " Scale=" + colScale);
	}
	
	public static void setParamsToPreparedStatment(PreparedStatement ps,List params) throws Exception{
		Object item = null;
		int idx = 1;
		for (int i=0;i<params.size();i++){
			item = params.get(i);
			if ( item instanceof Date)
				ps.setTimestamp(idx++,POFactoryUtil.toSqlDate((Date)item));
			else if ( item instanceof Collection){
				Object[] vals = ((Collection)item).toArray();
				for ( int j=0;j<vals.length;j++ ){
					ps.setObject(idx++,vals[j]);
				}				
			}else{
				ps.setObject(idx++,item);
			}
				
		}
	}
	
	public final static int APPEND_HEAD = 1;
	public final static int APPEND_TAIL = 2;
	/**
	 * 
	 * @param source 源字符串长度
	 * @param length 希望长度
	 * @param appendType 前补足或者后补足
	 * @param padding 用于补足的字符
	 * @return
	 */
	public static String appendLength(String source,int length,int appendType,char padding ){
		if ( source==null ) return null;
		if ( source.length() >= length ) return source;
		
		StringBuilder sbd = new StringBuilder(source);
		if ( appendType==APPEND_HEAD ){
			for ( int i=length-source.length(); i>0 ;i--){
				sbd.insert(0, padding);
			}
		}else{
			for ( int i=source.length();i<length;i++ ){
				sbd.append(padding);
			}
		}
		

		return sbd.toString();
	}
	
	
	/**
	 * 方   法  名:genJsonResultStr
	 * 方法描述:封装action查询结果为json
	 * 参         数:@param atx
	 * 参         数:@param pageList
	 * 参         数:@param iTotalRecords
	 * 返   回  值:void
	 * 创   建  人:suhaitao
	 * @exception
	 * @since  1.0.0
	*/
	public static void genJsonResultStr(ActionContext atx,List<Map> pageList,int iTotalRecords){
		String sEcho =  atx.getStringValue("sEcho");
		Map map = new HashMap();
		map.put("aaData", pageList.toArray());
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords", iTotalRecords);
		map.put("sEcho", sEcho);
		String jsonResult = JSONObject.fromObject(map).toString();
		
		System.out.println(jsonResult);
		
		atx.setStringValue("jsonResult", jsonResult);
	}
	
	
	/**
	 * 方   法  名:getCurrentPageNo
	 * 方法描述:获得当前查询页码
	 * 参         数:@param atx
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:suhaitao
	 * @exception
	 * @since  1.0.0
	*/
	public static int getCurrentPageNo(ActionContext atx){
		int iDisplayStart = atx.getIntValue("iDisplayStart");
		int iDisplayLength = atx.getIntValue("iDisplayLength");  //pageSize
	 	double cPage = Math.ceil(iDisplayStart/iDisplayLength); 
	 	int currPage= cPage==0?  1 : (int)cPage +1;
	 	return currPage;
	}
	
	
	/**
	 * 方   法  名:getPageSize
	 * 方法描述:获得当前每页显示条数
	 * 参         数:@param atx
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:suhaitao
	 * @exception
	 * @since  1.0.0
	*/
	public static int getPageSize(ActionContext atx){
		int iDisplayLength = atx.getIntValue("iDisplayLength");  //pageSize
		return iDisplayLength;
	}
	
	
//	public static void main(String[] arg) throws Exception{
//		String t = "TEST";
//		System.out.println("1: "+appendLength(null,10,APPEND_HEAD,' '));
//		System.out.println("2: "+appendLength(t,4,APPEND_HEAD,'A'));
//		System.out.println("3: "+appendLength(t,10,APPEND_HEAD,' '));
//		System.out.println("4: "+appendLength(t,10,APPEND_TAIL,'A'));
//		System.out.println("5: "+appendLength(t,3,APPEND_HEAD,'A'));
//	}
	
}