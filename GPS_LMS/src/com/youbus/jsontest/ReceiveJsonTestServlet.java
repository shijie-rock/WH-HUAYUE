/**
 * 项目名称:YOUBUS_GPS
 * 包         名:com.youbus.framework.comm
 * 文   件  名:ReceiveJsonTestServlet.java
 * 创 建日期:2015年8月5日-下午2:10:58
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.jsontest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;

/**
 * 类名称:ReceiveJsonTestServlet
 * 类描述:接收json报文
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年8月5日 下午2:10:58
 * 修改备注: http://192.168.0.109:8088/JSON_TEST/jsontest
 * @version 1.0.0
 */
public class ReceiveJsonTestServlet extends HttpServlet {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	private String defEncoding = "UTF-8";
	private static Logger log = LogManager.getLogger(ReceiveJsonTestServlet.class);
	private static final long serialVersionUID = -9146152634739934076L;
	/**
	 * 创建一个新的实例 ReceiveJsonTestServlet.
	 *
	 */
	public ReceiveJsonTestServlet() {
		// TODO Auto-generated constructor stub
	}
//	private Connection con;
	public void init(ServletConfig config) throws ServletException {
		
	}
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
        response.setContentType("text/html;charset=UTF-8");  
        String acceptjson = "";  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));  
            StringBuffer sb = new StringBuffer("");  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                sb.append(temp);  
            }  
            br.close();  
            acceptjson = sb.toString();  
            System.out.println(acceptjson);
            if (acceptjson!=""&&acceptjson.length()>5) { 
        		JSONObject jo = JSONObject.fromObject(acceptjson.substring(5));  
                JSONArray infArray = jo.getJSONArray("data");  
                JSONObject infObject=null;
            	Connection con=DBService.getInstance().getConnection();
            	con.setAutoCommit(false);
            	if(infArray!=null)
            		for(int i=0;i<infArray.size();i++){
            			infObject = JSONObject.fromObject(infArray.get(i));  	
            			if(infObject==null)continue;
            			insert(con, infObject);
            		}
            	if(con!=null){
            		con.commit();
            		con.close();
            	}
            }  
            response.getWriter().write("success");  
        } catch (Exception e) {  
            e.printStackTrace();  
            try {
				response.getWriter().write("error");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        }  
	}
	private void setEncoding(HttpServletRequest request) {		
		try{			
			request.setCharacterEncoding(this.defEncoding);
		}catch(Exception e){
			log.error("set Encoding error!",e);
		}		
	}
	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json=readjson();
		JSONObject jo = JSONObject.fromObject(json.substring(5));  
        JSONArray infArray = jo.getJSONArray("data");  
        JSONObject infObject = JSONObject.fromObject(infArray.get(0));  
//        System.out.println(infObject.get("time"));  
//        System.out.println(infObject.get("rssi"));  
//        System.out.println(infObject.get("mac"));  
//        System.out.println(infObject.get("id"));  
	}
	
	private static void insert(Connection conn,JSONObject infObject ){
		String time=(String) infObject.get("time");
		String rssi=(String) infObject.get("rssi");
		String mac=(String) infObject.get("mac");
		String wiffId=(String) infObject.get("id");
		TmJsonTestPO jtPO=new TmJsonTestPO();
		if(time!=null)jtPO.setTime(time);
		if(rssi!=null)jtPO.setRssi(rssi);
		if(mac!=null)jtPO.setMac(mac);
		if(wiffId!=null)jtPO.setWiffId(wiffId);
		jtPO.setId(POFactory.getIntPriKey(conn, jtPO));
		jtPO.setCreateBy(0);
		jtPO.setCreateTime(new Date(System.currentTimeMillis()));
		POFactory.insert(conn, jtPO);
		
	}
	
	private static String readjson(){
		File f=new File("F:/jsontest.txt");
		try {
			FileInputStream fis=new FileInputStream(f);
			BufferedReader br=new BufferedReader(new FileReader(f));
			StringBuffer sb=new StringBuffer();
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			System.out.println(sb.toString());
			
			return sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
