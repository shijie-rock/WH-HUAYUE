package com.infoservice.framework.services;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.DAOException;
import com.infoservice.framework.exceptions.ConfigException;


/**
 * @author Kevin Sun
 *
 */
public class DBService implements  Service {
	public  static String NonContainedTxnManager="NoContainedTxnManager";
	private static Logger logger = LogManager.getLogger(DBService.class);
	private static DBService insObj = new DBService();
	private BeanFactory springBeanFactory =null;
	private String conf = null;
	private String defConf = "/DataAccessContext.xml";
	private String defTxn = null;
	private String defDb = null;
	
	public static DBService getInstance(){
		return insObj;
	}
	
	private DBService() {
	}
	
	public String getName(){
		return "DBService";
	}
	
	public void destroyService(){
	}

	public void initialize(Map params) throws ConfigException {
		conf = (String)params.get("BeanFactoryConf");
		defTxn = (String)params.get("DefaultTransaction");
		defDb = (String)params.get("DefaultDataSource");
		
		if ( params.get("NoContainedTxnManager")!=null && 
				params.get("NoContainedTxnManager").toString().length()>0 )
		NonContainedTxnManager = (String)params.get("NoContainedTxnManager");
		
		logger.debug("BeanFactoryConf="+(conf==null?defConf:conf)+" ;DefaultTransactio="+defTxn+" ;DefaultDataSource="+defDb+" ;NonContainedTxnManager="+NonContainedTxnManager);
		if (defTxn==null || defDb==null ){
			throw new ConfigException("frm.msg.ConfigException");
		}
		
		if ( conf!=null ){
			Resource resource = new FileSystemResource(FrameworkConstant.APP_CONTEXT_PATH+File.separator+conf);
			springBeanFactory = new XmlBeanFactory(resource);
		}else{
			Resource resource = new ClassPathResource(defConf);
			springBeanFactory = new XmlBeanFactory(resource);
		}
	}

	public Object getTransaction() {
		return getTransaction(null);
	}
	
	public Object getTransaction(String txnName) {
		return getTransaction(txnName,-1);
	}
	
	public Object getTransaction(String txnName ,int timeOut ){
		PlatformTransactionManager txnManager = 
			(PlatformTransactionManager)springBeanFactory.getBean(txnName==null?defTxn:txnName);
		DefaultTransactionDefinition def =null;
		if ( timeOut > 0 ){
			def = new DefaultTransactionDefinition();
			def.setTimeout(timeOut);
		}		
		Object obj= txnManager.getTransaction(def==null?null:def);
		logger.debug(" Begin txn : "+obj.toString());
		return obj;
	}
	
	public void closeTransaction(Object txn , boolean isCommit) {
		closeTransaction(null,txn,isCommit);
	}
	
	//关闭事务
	public void closeTransaction(String txnName ,Object txn , boolean isCommit) { 
		PlatformTransactionManager txnManager = 
			(PlatformTransactionManager)springBeanFactory.getBean(txnName==null?defTxn:txnName);
		TransactionStatus status = (TransactionStatus)txn;
		if ( status.isCompleted() ) return ;
		
		//if ( isCommit && !status.isRollbackOnly()){
		if ( isCommit ){
			logger.debug(" Commit txn : "+txn.toString());
			txnManager.commit(status);
		}else{
			logger.debug(" Rollback txn : "+txn.toString());
			txnManager.rollback(status);
		}
	}

	public Connection getConnection() {
		return getConnection(null);
	}

	//从spring中获得绑定在当前线程上的数据库连接
	public Connection getConnection(String dsName) {
		try{
			DataSource ds = (DataSource)springBeanFactory.getBean(dsName==null?defDb:dsName);
			Connection conn=DataSourceUtils.getConnection(ds);
			return conn;
		}catch(Exception e){
			throw new DAOException("frm.msg.DAOException.GetConnErr",e);
		}
	}
	
	//关闭数据库连接
	public void closeConnection(Connection conn) {
		try{
			if ( conn!=null && !conn.isClosed() )
				conn.close();
		}catch(Exception e){
			throw new DAOException("frm.msg.DAOException.CloseConnErr",e);
		}
	}
	
//	add by zhaoli ,获得数据源
	public DataSource getDataSource(String dsName) {
		try{
			DataSource ds = (DataSource)springBeanFactory.getBean(dsName==null?defDb:dsName);
			return ds;
		}catch(Exception e){
			throw new DAOException("frm.msg.DAOException.GetDsErr",e);
		}
	}
}
