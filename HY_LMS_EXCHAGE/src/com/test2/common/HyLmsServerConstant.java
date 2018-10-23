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
public class HyLmsServerConstant {
	
	private static Logger logger=Logger.getLogger(HyLmsServerConstant.class);
	public static int CONNECT_TIME_OUT;
	public static int BEAD_BUFFER_SIZE;
	public static int HEART_BEAT_RATE;
	public static int SERVER_PORT;
	public static String HEART_BEAT_SERVER_RESP;
	public static String HEART_BEAT_CLIENT_RESQ;
	
	public static void init(){
		
        InputStream in = HyLmsServerConstant.class.getResourceAsStream("/ConnectionMina.properties");
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
            
            String cfgString="CONNECT_TIME_OUT["+CONNECT_TIME_OUT+"]\r\n";
            cfgString+="BEAD_BUFFER_SIZE["+BEAD_BUFFER_SIZE+"]\r\n";
            cfgString+="HEART_BEAT_RATE["+HEART_BEAT_RATE+"]\r\n";
            cfgString+="HEART_BEAT_SERVER_RESP["+HEART_BEAT_SERVER_RESP+"]\r\n";
            cfgString+="HEART_BEAT_CLIENT_RESQ["+HEART_BEAT_CLIENT_RESQ+"]\r\n";
            cfgString+="SERVER_PORT["+SERVER_PORT+"]\r\n";
            
            System.out.println("server connection init finish.");
            System.out.println(cfgString);
            logger.debug("server connection init finish.");
            logger.debug(cfgString);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
		
	}
}
