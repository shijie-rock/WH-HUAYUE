/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test.client
 * ��   ��  ��:ClientMessageHandler.java
 * �� ������:2018��10��16��-����11:20:08
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.joda.time.LocalDateTime;

import com.test.server.ConnectionConfig;
import com.test.server.SessionManager;

/**
 * ������:ClientMessageHandler
 * ������:�ͻ��˽��յ��������Ϣ֮����д���
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��16�� ����11:20:08
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ClientMessageHandler extends IoHandlerAdapter {
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
        SessionManager.getManager().add(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("session closed ");
        SessionManager.getManager().remove(session);     
      
        	int i=0;
            while(true) { //ͨ���ر�����
                try {
                    Thread.sleep(3000);
                    // �������첽���� ���Ӻ���������
                    ConnectionConfig connectionConfig = new ConnectionConfig.Builder(19999).setIp("127.0.0.1").setTimeInterval(20).build();
                    ConnectionManager connectionManager = new ConnectionManager(connectionConfig);
                    boolean isConnected=connectionManager.connect();
                    if(isConnected) {
                        break;
                    }else{
                        System.err.println("��������ӳ�ʱ����������");
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                	
                }
            }
           
//       new HyLmsClient().connect();
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        System.out.println("client message received with message: "+"["+new LocalDateTime()+"]" + message.toString());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        System.out.println("session in idle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        System.out.println("exception");
        session.closeOnFlush();
//        SessionManager.getManager().remove(session);
    }
}
