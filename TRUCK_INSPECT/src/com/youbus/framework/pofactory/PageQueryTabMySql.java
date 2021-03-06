package com.youbus.framework.pofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.services.MessageService;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactoryUtil;
import com.infoservice.po.PageQuery;

public class PageQueryTabMySql extends com.infoservice.po.PageQuery {
	public static final int PAGE_SIZE = 10;
	private Logger logger = LogManager.getLogger(PageQuery.class);
	private Connection conn = null;
	private String sql;
	private List params;
	private int pageSize;
	private int totalRecords = -1;
	
	public PageQueryTabMySql(Connection conn, String sql, List params, int pageSize) {
		super(conn, sql, params, pageSize);
		this.conn = conn;
	    this.sql = sql;
	    this.params = (params == null ? new ArrayList() : params);
	    this.pageSize = pageSize;
	}

	public List getResult(String dynaBeanName,int startPage) throws Exception {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	      StringBuffer sbuf = new StringBuffer();
	      if (startPage > 0)
	        sbuf.append(MessageService.getInstance().getMessage("frm.sql.pagequery.query", new Object[] { this.sql}));
	      else {
	        sbuf.append(this.sql);
	      }

	      ps = this.conn.prepareStatement(sbuf.toString());
	      LinkedList tmp = new LinkedList();
	      tmp.addAll(this.params);
	      if (startPage > 0) {
	        tmp.addLast(new Integer((startPage - 1) * this.pageSize));
	        tmp.addLast(new Integer(this.pageSize));
	      }

	      POFactoryUtil.setParamsToPreparedStatment(ps, tmp);

	      this.logger.debug(sbuf.toString() + "  " + tmp.toString());

	      rs = ps.executeQuery(); 

	      int colCount = rs.getMetaData().getColumnCount();
	      List<Map> allRowsList = new ArrayList<Map>();
	      while(rs.next()){
	    	  Map rowMap = new HashMap();
		      for(int i=1; i<=colCount; i++){
		    	  String colName = rs.getMetaData().getColumnName(i) ; 
		    	  int colType = rs.getMetaData().getColumnType(i);
		    	  Object colVal = null;
		    	  
		    	switch (rs.getMetaData().getColumnType(i)) {
                	case Types.DATE:
                		colVal =(null==rs.getDate(i))?"":DateFormatUtils.format(rs.getDate(i), "yyyy-MM-dd HH:mm:ss");
                		break;
                	case Types.DOUBLE:
                		colVal = rs.getDouble(i);
                		break;
                	case Types.NUMERIC:
                    	colVal = rs.getDouble(i);
                    	break;
                	case Types.DECIMAL:
                		colVal = rs.getDouble(i);
                        break;
                	case Types.INTEGER:
                    	colVal = rs.getInt(i);
                    	break;
                	default:
                		colVal = rs.getString(i);
                		break;
                }
		    	
		    	rowMap.put(colName,colVal);
		    	  
		      }
		      
		      allRowsList.add(rowMap);
	      }
	      
	      return allRowsList;
	      
	    } catch (Exception e) {
	      this.logger.debug("PageQuery execute query error!", e);
	      throw e;
	    } finally {
	      if (rs != null) rs.close();
	      if (ps != null) ps.close(); 
	    }
	    
	  }


	//�ܼ�¼��
	public int getTotalPage() throws Exception {
	    if (this.totalRecords < 0) {
	      getTotalRecords();
	    }
	    
	    return this.totalRecords / this.pageSize + (this.totalRecords % this.pageSize > 0 ? 1 : 0);
	  }

	  public int getTotalRecords() throws Exception {
	    if (this.totalRecords >= 0) 
	    	return this.totalRecords;

	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	      StringBuffer sbuf = new StringBuffer();
	      sbuf.append(MessageService.getInstance().getMessage("frm.sql.pagequery.count", new Object[] { this.sql }));
//	      sbuf.append(MessageService.getInstance().getMessage("frm.sql.pagequery.count", new Object[] { this.sql.toUpperCase() }));//not need to upper

	      ps = this.conn.prepareStatement(sbuf.toString());
	      POFactoryUtil.setParamsToPreparedStatment(ps, this.params);

	      this.logger.debug(sbuf.toString() + "  " + this.params.toString());

	      rs = ps.executeQuery();
	      rs.next();
	      this.totalRecords = rs.getInt(1);
	      int i = this.totalRecords;
	      return i;
	    } catch (Exception e) {
	      this.logger.error("PageQuery execute error ", e);
	      throw e;
	    } finally {
	      if (rs != null) rs.close();
	      if (ps != null) ps.close(); 
	    }
	    //throw localObject;
	  }
	  
		public List getResultToDynaBeanList(String dynaBeanName,int startPage) throws Exception {
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      StringBuffer sbuf = new StringBuffer();
		      if (startPage > 0)
		        sbuf.append(MessageService.getInstance().getMessage("frm.sql.pagequery.query", new Object[] { this.sql}));
		      else {
		        sbuf.append(this.sql);
		      }

		      ps = this.conn.prepareStatement(sbuf.toString());
		      LinkedList tmp = new LinkedList();
		      tmp.addAll(this.params);
		      if (startPage > 0) {
		        tmp.addLast(new Integer((startPage - 1) * this.pageSize));
		        tmp.addLast(new Integer(this.pageSize));
		      }

		      POFactoryUtil.setParamsToPreparedStatment(ps, tmp);

		      this.logger.debug(sbuf.toString() + "  " + tmp.toString());

		      rs = ps.executeQuery(); 

		      int colCount = rs.getMetaData().getColumnCount();
		      List<DynaBean> allRowsList = new ArrayList<DynaBean>();
		      while(rs.next()){
		    	  DynaBean bean = new DynaBean(dynaBeanName);
			      for(int i=1; i<=colCount; i++){
			    	  String colName = rs.getMetaData().getColumnName(i) ; 
			    	  int colType = rs.getMetaData().getColumnType(i);
			    	  Object colVal = null;
			    	  
			    	switch (rs.getMetaData().getColumnType(i)) {
	                	case Types.DATE:
	                		colVal =(null==rs.getDate(i))?"":DateFormatUtils.format(rs.getDate(i), "yyyy-MM-dd HH:mm:ss");
	                		break;
	                	case Types.DOUBLE:
	                		colVal = rs.getDouble(i);
	                		break;
	                	case Types.NUMERIC:
	                    	colVal = rs.getDouble(i);
	                    	break;
	                	case Types.DECIMAL:
	                		colVal = rs.getDouble(i);
	                        break;
	                	case Types.INTEGER:
	                    	colVal = rs.getInt(i);
	                    	break;
	                	default:
	                		colVal = rs.getString(i);
	                		break;
	                }
			    	
			    	bean.add(colName,colVal);
			    	  
			      }
			      
			      allRowsList.add(bean);
		      }
		      
		      return allRowsList;
		      
		    } catch (Exception e) {
		      this.logger.debug("PageQuery execute query error!", e);
		      throw e;
		    } finally {
		      if (rs != null) rs.close();
		      if (ps != null) ps.close(); 
		    }
		    
		  }

}
