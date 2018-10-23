/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test
 * ��   ��  ��:Server.java
 * �� ������:2018��10��14��-����11:51:43
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.joda.time.LocalDateTime;

/**
 * ������:Server
 * ������:error���ͻ����������������յ��˷���˷������������ݡ�error
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��14�� ����11:51:43
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ConnectionServer {

	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
//    private static class ConnectionHandler extends IoHandlerAdapter {
//
//        @Override
//        public void sessionCreated(IoSession session) throws Exception {
//            super.sessionCreated(session);
//            InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
//            String clientIp = remoteAddress.getAddress().getHostAddress();
//            System.out.println("session created with IP: " + clientIp);
//        }
//
//        @Override
//        public void sessionOpened(IoSession session) throws Exception {
//            super.sessionOpened(session);
//            InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
//            String clientIp = remoteAddress.getAddress().getHostAddress();
//            System.out.println("session opened with IP: " + clientIp);
//            SessionManager.getManager().add(session);
//        }
//
//        @Override
//        public void sessionClosed(IoSession session) throws Exception {
//            super.sessionClosed(session);
//            System.out.println("session closed ");
//            SessionManager.getManager().remove(session);
//        }
//
//        @Override
//        public void messageReceived(IoSession session, Object message) {
//            System.out.println("message received with message: " + message.toString());
//            session.write(new Date());
//        }
//
//        @Override
//        public void sessionIdle(IoSession session, IdleStatus status) {
//            System.out.println("session in idle");
//        }
//
//        @Override
//        public void exceptionCaught(IoSession session, Throwable cause) {
//            System.out.println("exception");
//            session.closeOnFlush();
//            SessionManager.getManager().remove(session);
//        }
//    }

    private ConnectionConfig connectionConfig;
    private IoAcceptor ioAcceptor;

    public void sendMessage() {
        Timer timer = new Timer();
        final Random random = new Random();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Weather weather = new Weather();
                weather.humidity = random.nextInt(100);
                weather.temperature = random.nextInt(50);
                weather.wind = random.nextInt(20);
                SessionManager.getManager().update(weather);
                System.out.println("server send weather msg"+"["+new LocalDateTime()+"]");
            }
        },new Date(System.currentTimeMillis()), 2000);
    }

    public ConnectionServer(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    private void bind() {
        if (connectionConfig == null) return;

        ioAcceptor = new NioSocketAcceptor();
        ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
        ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        ioAcceptor.setHandler(new ConnectionHandler());
        ioAcceptor.getSessionConfig().setReadBufferSize(connectionConfig.getBufferSize());
        ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, connectionConfig.getIdleTime());
        try {
            ioAcceptor.bind(new InetSocketAddress(connectionConfig.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage();
    }

    public void unBind() {
        if (ioAcceptor == null) return;

        ioAcceptor.dispose();
        SessionManager.getManager().removeAll();
    }

    public static void main(String[] param) {
        ConnectionConfig config = new ConnectionConfig.Builder(9023).setBufferSize(2048).setIdleTime(60).build();
        ConnectionServer server = new ConnectionServer(config);
        server.bind();
    }
}
