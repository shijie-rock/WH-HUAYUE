/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.common
 * 文   件  名:HyLmsServerConstant.java
 * 创 建日期:2018年10月16日-下午5:11:22
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 类名称:HyLmsServerConstant
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午5:11:22
 * 修改备注:
 * @version 1.0.0
 */
public class HyLmsClientConstant {
	
	private static Logger logger=Logger.getLogger(HyLmsClientConstant.class);
	public static int CONNECT_TIME_OUT;
	public static int BEAD_BUFFER_SIZE;
	public static int HEART_BEAT_RATE;
	public static int SERVER_PORT;
	public static String HEART_BEAT_SERVER_RESP;
	public static String HEART_BEAT_CLIENT_RESQ;
	public static String SERVER_HOST;
	
	public static String APP_ID="0";//app id
	public static String SECURITY_CODE="1";//安全码
	
	public static boolean NEED_CLOSE=false;//关闭标示位
	
	public static final String MSG_TYPE_REQ="Request";//消息类型 请求消息
	public static final String MSG_TYPE_RSP="Response";//消息类型 响应消息
	
	public static final String MSG_RESULT_SUCCESS="Success";//执行结果，Success：成功
	public static final String MSG_RESULT_ERROR="Error";//执行结果，Error：失败
	
	
	
	public static String TOPIC_SYS_LOGIN; //消息主题：系统登录请求
	public static String TOPIC_SYS_LOGIN_RES;//消息主题：系统登录应答
	
	public static String TOPIC_SYS_LOGOFF;//消息主题：系统登出请求
	public static String TOPIC_SYS_LOGOFF_RES;//消息主题：系统登出应答
	
	public static String TOPIC_BUSI_BILL_QUERY;//消息主题：运单查询
	public static String TOPIC_BUSI_BILL_CREATE;//消息主题：运单创建
	public static String TOPIC_BUSI_BILL_UPDATE;//消息主题：运单更新
	public static String TOPIC_BUSI_BILL_CANCEL;//消息主题：运单取消
	
	public static String TOPIC_BUSI_TASK_CREATE;//消息主题：运力创建
	
	public static Map<String,String> RESPONSE_PARSER_MAP=new Hashtable<String,String>();//key 主题，value parser class name
	
	//
	public static boolean IS_SYS_LOGIN_IN=false;//是否已登录
	public static String API_SERVER_URL;//api url
	
	static{
		//初始化 response 及对应的 处理类
		RESPONSE_PARSER_MAP.put("lms.message.framework.login.response", "com.test2.response.parser.LoginParser");
		RESPONSE_PARSER_MAP.put("lms.message.framework.logoff.response", "com.test2.response.parser.LogoffParser");
		RESPONSE_PARSER_MAP.put("lms.message.framework.error.response", "com.test2.response.parser.ErrorParser");
		RESPONSE_PARSER_MAP.put("lms.message.trucking.sales.transportbill.transportconsignmentbill.create", "com.test2.response.parser.BillCreateParser");
		RESPONSE_PARSER_MAP.put("lms.message.trucking.sales.transportbill.transportconsignmentbill.update", "com.test2.response.parser.BillUpdateParser");
		RESPONSE_PARSER_MAP.put("lms.message.trucking.sales.transportbill.transportconsignmentbill.cancel", "com.test2.response.parser.BillCancelParser");
		//TODO
		//RESPONSE_PARSER_MAP.put("lms.trucking.sales.transportbill.transporttaskbill.create", "com.test2.response.parser.BillCancelParser");
		
	}
	
	public static void init(){
		
        InputStream in = HyLmsClientConstant.class.getResourceAsStream("/ConnectionMina.properties");
        try {
        	Properties properties = new Properties();
            properties.load(in);
            //连接超时：秒
            CONNECT_TIME_OUT = Integer.valueOf(properties.getProperty("CONNECT_TIME_OUT"));
            //消息buffer大小
            BEAD_BUFFER_SIZE = Integer.valueOf(properties.getProperty("BEAD_BUFFER_SIZE"));
            //心跳频率：秒（X秒发一次）
            HEART_BEAT_RATE = Integer.valueOf(properties.getProperty("HEART_BEAT_RATE"));
            //心跳服务端应答报文
            HEART_BEAT_SERVER_RESP = properties.getProperty("HEART_BEAT_SERVER_RESP");
            //心跳客户端发起请求报文
            HEART_BEAT_CLIENT_RESQ = properties.getProperty("HEART_BEAT_CLIENT_RESQ");
            //服务端发起服务端口
            SERVER_PORT = Integer.valueOf(properties.getProperty("SERVER_PORT"));
            //服务端地址
            SERVER_HOST = properties.getProperty("SERVER_HOST");
            //app id
            APP_ID = properties.getProperty("APP_ID");
            //安全码
            SECURITY_CODE = properties.getProperty("SECURITY_CODE");
            //topic 
            TOPIC_SYS_LOGIN = properties.getProperty("TOPIC_SYS_LOGIN");
            TOPIC_SYS_LOGIN_RES = properties.getProperty("TOPIC_SYS_LOGIN_RES");
            
            TOPIC_SYS_LOGOFF = properties.getProperty("TOPIC_SYS_LOGOFF");
            TOPIC_SYS_LOGOFF_RES = properties.getProperty("TOPIC_SYS_LOGOFF_RES");
            
            TOPIC_BUSI_BILL_QUERY = properties.getProperty("TOPIC_BUSI_BILL_QUERY");
            TOPIC_BUSI_BILL_CREATE = properties.getProperty("TOPIC_BUSI_BILL_CREATE");
            TOPIC_BUSI_BILL_UPDATE = properties.getProperty("TOPIC_BUSI_BILL_UPDATE");
            TOPIC_BUSI_BILL_CANCEL = properties.getProperty("TOPIC_BUSI_BILL_CANCEL");
            
            TOPIC_BUSI_TASK_CREATE = properties.getProperty("TOPIC_BUSI_TASK_CREATE");
            API_SERVER_URL = properties.getProperty("API_SERVER_URL");
            
            String cfgString="CONNECT_TIME_OUT["+CONNECT_TIME_OUT+"]\r\n";
            cfgString+="BEAD_BUFFER_SIZE["+BEAD_BUFFER_SIZE+"]\r\n";
            cfgString+="HEART_BEAT_RATE["+HEART_BEAT_RATE+"]\r\n";
            cfgString+="HEART_BEAT_SERVER_RESP["+HEART_BEAT_SERVER_RESP+"]\r\n";
            cfgString+="HEART_BEAT_CLIENT_RESQ["+HEART_BEAT_CLIENT_RESQ+"]\r\n";
            cfgString+="SERVER_HOST["+SERVER_HOST+"]\r\n";
            cfgString+="SERVER_PORT["+SERVER_PORT+"]\r\n";
            
            System.out.println("client connection init finish.");
            System.out.println(cfgString);
            logger.debug("client connection init finish.");
            logger.debug(cfgString);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
		
	}
}
