/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test
 * ��   ��  ��:ConnectionHandler.java
 * �� ������:2018��10��16��-����10:28:02
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
 * ������:ConnectionHandler
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��16�� ����10:28:02
 * �޸ı�ע:
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
        //�ж��Ƿ��ǿͻ��˷���������������򷵻�Ӧ��������
//    	��������Ӧ��ϵͳ���ͣ���������Ϊ�ַ�����heartBeatRequest������Ϣ���������յ����������󣬻�������Ӧ������Ϊ�ַ�����heartBeatResponse����
//    	heartBeatRequest 
        if("heartBeatRequest".equals(message)){//error ��������Ӧ���ɷ�������filter�����������ô˷���
        	session.write("heartBeatResponse");
        	System.out.println("������յ�������:["+message+"]������Ӧ���[heartBeatResponse]");
        }else{
        	SessionManager.getManager().add(session);
        	System.out.println("�������Ự["+session+"]");
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


