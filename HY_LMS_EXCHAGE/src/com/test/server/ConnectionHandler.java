/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test
 * 文   件  名:ConnectionHandler.java
 * 创 建日期:2018年10月16日-上午10:28:02
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * 类名称:ConnectionHandler
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 上午10:28:02
 * 修改备注:
 * @version 1.0.0
 */
public class ConnectionHandler extends IoHandlerAdapter{
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        System.out.println("session created with IP: " + clientIp);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        System.out.println("session opened with IP: " + clientIp);
//        SessionManager.getManager().add(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("session closed ");
        SessionManager.getManager().remove(session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        System.out.println("server message received with message: "+"["+new LocalDateTime()+"]" + message.toString());
        //判断是否是客户端发起的心跳包，是则返回应答心跳包
//    	心跳包由应用系统发送，发送内容为字符串“heartBeatRequest”，消息服务中心收到该心跳包后，会立即响应，内容为字符串“heartBeatResponse”。
//    	heartBeatRequest 
        if("heartBeatRequest".equals(message)){//error 心跳包，应该由服务心跳filter发出，而非用此方法
        	session.write("heartBeatResponse");
        	System.out.println("服务端收到心跳包:["+message+"]，返回应答包[heartBeatResponse]");
        }else{
        	SessionManager.getManager().add(session);
        	System.out.println("服务存入会话["+session+"]");
        }
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        System.out.println("session in idle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        System.out.println("exception");
        session.closeOnFlush();
        SessionManager.getManager().remove(session);
    }
}


