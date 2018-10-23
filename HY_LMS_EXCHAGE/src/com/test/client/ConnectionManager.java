package com.test.client;


//import com.mina.config.ConnectionConfig;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.test.server.ConnectionConfig;

public class ConnectionManager {
    public static final String OBJECT_FILTER = "objectFilter";
    public static final String HEART_BEAT = "heartbeat";
    public static final String LOGGER = "logger";
    public static final String ACTION = "xproject.longconnection.mina.message";
    public static final String KEY = "mina";
    public static final String HEARTBEAT = "heartBeatRequest";

    private static class HeartbeatFactory implements KeepAliveMessageFactory {
//        private final WeakReference<Context> weakReference;
//
//        HeartbeatFactory(Context context) {
//            this.weakReference = new WeakReference<>(context);
//        }

        @Override
        public boolean isRequest(IoSession ioSession, Object o) {
            return true;
        }

        @Override
        public boolean isResponse(IoSession ioSession, Object o) {
        	//�ж��Ƿ��Ƿ�������Ӧ��Ӧ����������
//        	��������Ӧ��ϵͳ���ͣ���������Ϊ�ַ�����heartBeatRequest ������Ϣ���������յ����������󣬻�������Ӧ������Ϊ�ַ�����heartBeatResponse ����
//        	heartBeatResponse 
        	if(o!=null&&"heartBeatResponse".equals(o.toString())){
        		System.out.println("IS heartBeatResponse");
        		return true;
        	}else{
        		return false;
        	}
        }

        @Override
        public Object getRequest(IoSession ioSession) {
            return HEARTBEAT;
        }
        
        @Override
        public Object getResponse(IoSession ioSession, Object message) {
            System.out.println("�ͻ����յ������Ӧ��������: " + message);
//            Intent intent = new Intent(ACTION);
//            intent.putExtra(KEY, (Serializable) message);
//            LocalBroadcastManager.getInstance(weakReference.get()).sendBroadcast(intent);
            //�жϽ��յı���������Ӧ���ģ�����ҵ����
            //1:��¼��־
            //2:�����ҵ���ģ�������߼�����
            return message;
        }
    }
    
    private class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{
    	/* (non-Javadoc)
    	 * @see org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#keepAliveRequestTimedOut(org.apache.mina.filter.keepalive.KeepAliveFilter, org.apache.mina.core.session.IoSession)
    	 */
    	@Override
    	public void keepAliveRequestTimedOut(KeepAliveFilter arg0, IoSession session)
    			throws Exception {
    		// TODO Auto-generated method stub
    		System.err.println("������ʱ�������δ��Ӧ");
    	}
    }

    private NioSocketConnector connector;
    private IoSession ioSession;
    private ConnectionConfig connectionConfig;

    public ConnectionManager(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
        init();
    }

    public IoSession getIoSession() {
        return ioSession;
    }

    private void init() {
        connector = new NioSocketConnector();
//      connector.setHandler(new IoHandlerAdapter());
//      connector.setHandler(new ClientMessageHandler());
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
        chain.addLast(LOGGER, new LoggingFilter());
        chain.addLast(OBJECT_FILTER, filter);
//      KeepAliveFilter heartBeat = new KeepAliveFilter(new HeartbeatFactory((Context) connectionConfig.getContext()),
        KeepAliveFilter heartBeat = new KeepAliveFilter(new HeartbeatFactory(),IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
        /** �Ƿ�ط� */
        heartBeat.setForwardEvent(true);
     // ���������������ʱ�޷�������µĴ�����ƣ�Ĭ��Ϊ�ر�����,�ڴ˴�����Ϊ�����־����
//      heartBeat.setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler.LOG);
        heartBeat.setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandlerImpl());
        /** ����Ƶ�ʣ���λӦ������ */
        heartBeat.setRequestInterval(connectionConfig.getTimeInterval()); 
        /** ��������������� �ȴ�������ʱʱ�䡣 ������ʱ��������KeepAliveRequestTimeoutHandler.CLOSE */
        heartBeat.setRequestTimeout(15);//  ��λӦ������
        connector.getSessionConfig().setKeepAlive(true);
        chain.addLast(HEART_BEAT, heartBeat);
        connector.setHandler(new ClientMessageHandler());
        
    }

    public boolean connect() {
        try {
            ConnectFuture connectFuture = connector.connect(new InetSocketAddress(connectionConfig.getIp(), connectionConfig.getPort()));
            //�ȴ����Ӵ������
            connectFuture.awaitUninterruptibly();
            ioSession = connectFuture.getSession();
            
            if (connectFuture != null) {
                IoSession ioSession = connectFuture.getSession();
                ioSession.write("hello world from java client with connect ");
                
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                    	ioSession.write("hello world from java client with beatHeart");
//                    }
//                },new Date(System.currentTimeMillis()), 2000);
                for(int i=0;i<100;i++){
                	Thread.sleep(3000);
//                	ioSession.write("hello world from java client with ["+i+"] ");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return ioSession != null;
    }

    public void disConnect() {
        if (ioSession != null) {
            ioSession.closeNow();
            ioSession.getCloseFuture().setClosed();
            ioSession.getCloseFuture().awaitUninterruptibly();
        }
        connector.dispose();
    }
    
    public static void main(String[] args) {
        ConnectionConfig connectionConfig = new ConnectionConfig.Builder(9023).setIp("127.0.0.1").setTimeInterval(20).build();
        ConnectionManager connectionManager = new ConnectionManager(connectionConfig);
        connectionManager.connect();
	}
}
