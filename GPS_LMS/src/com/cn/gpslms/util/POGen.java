package com.cn.gpslms.util;


import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author KevinSun
 *
 */
public class POGen {
	private String confFile ;	
	private static final int TYPE_CLSNAME = 1;
	private static final int TYPE_ATTRNAME = 2;
	
	private String jdbcDriver =null;
	private String jdbcUrl =null;
	private String jdbcUser=null;
	private String jdbcPwd =null;
	
	private String genPath=null;
	private String packName=null;
	
	private LinkedList<String> tabNames = new LinkedList<String>();
	
	private String db2Query = "select * from (select {0}.*,rownumber() over() as RN from {0}) as A1 where A1.RN<2";
	private String oracleQuery ="select * from {0} where rownum<2";
	private String firebird = "SELECT * FROM {0} ROWS 1 TO 2";
	private String mysql = "SELECT * FROM {0}  limit 0,1";
	
	private String comment =  "/*\n"
							+ "* Copyright (c) 2005 Infoservice, Inc. All  Rights Reserved.\n"
							+ "* This software is published under the terms of the Infoservice Software\n"
							+ "* License version 1.0, a copy of which has been included with this\n"
							+ "* distribution in the LICENSE.txt file.\n"
							+ "*\n" 
							+ "* CreateDate : {0,date,yyyy-MM-dd HH:mm:ss}\n"
							+ "* CreateBy   : {1}\n"
							+ "* Comment    : generate by com.infoservice.po.POGen\n"
							+ "*/\n";
	
	//package and import 
	private int impDate = 1;
	private String headTemp = "package {0};\n\n"
							+ "{1,choice,0#|1#import java.util.Date;\n}"
							+ "import com.infoservice.po.DataBean;\n"
							+ "import com.infoservice.po.POFactoryUtil;\n";
	
	private String clsTemp = "@SuppressWarnings(\"serial\")\n"
							+ "public class {0}PO implements DataBean'{'\n";
	
	private String toXml = "\tpublic String toXMLString(){\n"
							+ "\t\treturn POFactoryUtil.beanToXmlString(this);\n"
							+ "\t}\n";

	private String attrDecl = "\tprivate {1} {0};\n";

	//set方法模版
	private String setTemp = "\tpublic void set{0}({1} {2})'{'\n"
							+"\t\tthis.{2}={2};\n"
							+"\t}\n";
	//get方法模版
	private String getTemp = "\tpublic {1} get{0}()'{'\n"
							+"\t\treturn this.{2};\n"
							+"\t}\n";
	
	
	public POGen(String conf){
		this.confFile = conf;
	}
	
	public void readParams(){		
		try{
			System.out.println("读取配置文件: "+this.confFile);
			InputStream is =POGen.class.getResourceAsStream("/"+this.confFile);
			Properties pros = new Properties();
			pros.load(is);
			
			this.jdbcDriver =  pros.getProperty("jdbc.driver");
			this.jdbcUrl = pros.getProperty("jdbc.url");
			this.jdbcUser = pros.getProperty("jdbc.user");
			this.jdbcPwd = pros.getProperty("jdbc.pwd");
			System.out.println("jdbc.driver="+this.jdbcDriver);
			System.out.println("jdbc.usrl="+this.jdbcUrl);
			System.out.println("jdbc.user="+this.jdbcUser);
			System.out.println("jdbc.pwd="+this.jdbcPwd);
			
			this.packName = pros.getProperty("PackageName");
			this.genPath = pros.getProperty("POPath");
			System.out.println("PackageName="+this.packName);
			System.out.println("POPath="+this.genPath);	
			
			Iterator ite =pros.keySet().iterator();
			String key = null;
			while( ite.hasNext() ){
				key = (String)ite.next();
				if ( key.startsWith("table.") ){
					this.tabNames.addLast(pros.getProperty(key));
				}
			}
			System.out.println("Table nums : "+this.tabNames.size());
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private String tabName2PoName(int type, String tname) {
		byte ascii = 'Z'-'z';
		tname = tname.toLowerCase();
		StringBuilder sbd = new StringBuilder();
		for (int i = 0; i < tname.length(); i++) {
			if (tname.charAt(i)=='_' && i+1<tname.length()) {
				i++;
				if (tname.charAt(i) >= 'a' && tname.charAt(i) <= 'z') {
					sbd.append((char)(tname.charAt(i) + ascii));
				} else {
					sbd.append(tname.charAt(i));
				}
			} else {
				sbd.append(tname.charAt(i));
			}
		}
		if ( type == POGen.TYPE_CLSNAME ){
			if ( sbd.charAt(0)>='a' && sbd.charAt(0)<='z' )
				sbd.setCharAt(0,(char)(sbd.charAt(0)+ascii));
		}
		return sbd.toString();
	}
	
	//写文件
	private void writePOFile(String tabName,StringBuilder sbd){
		try{
			String file = this.genPath+'/'+this.packName.replace('.','/')+'/';
			File dir = new File(file);
			if ( !dir.exists() ) dir.mkdirs();
			file += this.tabName2PoName(POGen.TYPE_CLSNAME, tabName)+"PO.java";
			
			System.out.println("Write Class File : "+file);			
			FileWriter fw = new FileWriter(file);
			fw.write(sbd.toString());
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//生成set 和 get 方法
	private void genMethod(StringBuilder sbd,String attrName,Class clsType){
		try{
			String attrName1 = this.tabName2PoName(POGen.TYPE_CLSNAME, attrName);
			String attrName2 = this.tabName2PoName(POGen.TYPE_ATTRNAME, attrName);
			sbd.append(MessageFormat.format(this.setTemp, attrName1,clsType.getSimpleName(),attrName2));
			sbd.append("\n");
			sbd.append(MessageFormat.format(this.getTemp,attrName1,clsType.getSimpleName(),attrName2));
			sbd.append("\n");
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void genPOFile(String tabName,HashMap<String,Class> infos){
		try{
			if ( infos.containsValue(java.util.Date.class) ){
				this.impDate = 1;
			}else{
				this.impDate = 0;
			}
			StringBuilder sbd = new StringBuilder();
			sbd.append(MessageFormat.format(this.comment, new Date(),System.getenv("USERNAME")));
			sbd.append("\n");
			sbd.append(MessageFormat.format(this.headTemp,this.packName,this.impDate));
			sbd.append("\n");
			sbd.append(MessageFormat.format(this.clsTemp,this.tabName2PoName(POGen.TYPE_CLSNAME, tabName)));
			sbd.append("\n");			
			
			Iterator <String>ite=infos.keySet().iterator();
			String key =null;
			
			while(ite.hasNext()){
				key = ite.next();
				sbd.append(MessageFormat.format(this.attrDecl, this.tabName2PoName(POGen.TYPE_ATTRNAME, key),infos.get(key).getSimpleName()));
			}
			sbd.append("\n");
			
			ite = infos.keySet().iterator();
			while ( ite.hasNext() ){
				key = ite.next();
				genMethod(sbd,key,infos.get(key));
			}
			
			sbd.append(this.toXml);
			
			sbd.append("}");

			writePOFile(tabName,sbd);

		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	public void gen() throws Exception{
		Connection conn=null;
		try{
			Class.forName(this.jdbcDriver);
			conn=DriverManager.getConnection(this.jdbcUrl, this.jdbcUser,this.jdbcPwd);
			Statement ste = conn.createStatement();
			String query = null;
			if ( this.jdbcDriver.indexOf("oracle")>=0 ){
				query = this.oracleQuery;
			}else if ( this.jdbcDriver.indexOf("db2")>=0 ){
				query = this.db2Query;
			}else if ( this.jdbcDriver.indexOf("firebird")>=0 ){
				query = this.firebird;
			}else if ( this.jdbcDriver.indexOf("mysql")>=0 ){
				query = this.mysql;
			}else{
				throw new Exception ("not supported db!");
			}
			
			for (String tab : this.tabNames ){
				System.out.println("------Gen tab : "+tab+"------");
				System.out.println(MessageFormat.format(query,tab));
				ResultSet rs = ste.executeQuery(MessageFormat.format(query,tab));
				HashMap<String,Class> infos=POFactoryUtil.getResultSetMetaData(rs);
				
				if ( this.jdbcDriver.indexOf("db2")>=0 ){
					infos.remove("RN");
				}
				
				genPOFile(tab, infos);
				rs.close();
			}
			ste.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}finally{
			if ( conn!=null )
				conn.close();
		}
	}
	

	public static void main(String[] args) throws Exception{
		System.out.println("args:"+args);
		System.out.println("args:"+args.length);
		if ( args.length == 0 ){
			System.out.println("usage: POGen conf1 conf2 ...");
			System.exit(0);
		}
		
		for ( int i=0;i<args.length;i++ ){
			System.out.println("### config file : "+args[i]+" ###");
			POGen gen =new POGen(args[i]);
			gen.readParams();
			gen.gen();
			System.out.println("###########################\n\n");
		}
	}

}
