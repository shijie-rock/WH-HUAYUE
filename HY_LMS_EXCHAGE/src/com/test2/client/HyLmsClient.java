/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.client
 * 文   件  名:HyLmsClient.java
 * 创 建日期:2018年10月16日-下午6:24:51
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.stream.StreamWriteFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.test2.common.ClientHelper;
import com.test2.common.HyLmsClientConstant;
import com.test2.common.HyLmsSignUtil;
import com.test2.dto.BaseRequestParamBean;
import com.test2.msgfactory.HyMessageFactory;

/**
 * 类名称:HyLmsClient
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午6:24:51
 * 修改备注:
 * @version 1.0.0
 */
public class HyLmsClient {
	private static Logger logger=Logger.getLogger(HyLmsClient.class);
	 private IoSession session;
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
		new HyLmsClient().connect();
	}
	
	 public void connect() {
//		HyLmsClientConstant.init();
		logger.info("start socketconnect ：" + HyLmsClientConstant.SERVER_HOST
				+ ":" + HyLmsClientConstant.SERVER_PORT);
		NioSocketConnector connector = new NioSocketConnector();
		connector
				.setConnectTimeoutMillis(HyLmsClientConstant.CONNECT_TIME_OUT * 1000);
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec1",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));//utf-8
		KeepAliveMessageFactory heartBeatFactory = new ClientKeepAliveMessageFactoryImpl();
		// 下面注释掉的是自定义Handler方式
		KeepAliveRequestTimeoutHandler heartBeatHandler = new ClientKeepAliveRequestTimeoutHandlerImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
				IdleStatus.READER_IDLE, heartBeatHandler);
		heartBeat.setRequestTimeoutHandler(heartBeatHandler);
		// 设置是否forward到下一个filter
		heartBeat.setForwardEvent(true);
		// 设置心跳频率
//		heartBeat.setRequestInterval(20);
		heartBeat.setRequestInterval(HyLmsClientConstant.HEART_BEAT_RATE);
		connector.getFilterChain().addLast("heartbeat", heartBeat);
		connector.getFilterChain().addLast("codec", new StreamWriteFilter());
		connector.setHandler(new ClientMsgHandler());//
		connector.setDefaultRemoteAddress(new InetSocketAddress(
				HyLmsClientConstant.SERVER_HOST,
				HyLmsClientConstant.SERVER_PORT));
		connector.getSessionConfig().setKeepAlive(true);//
//		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 45);
		/*
		connector.addListener(new IoListener(){
			@Override
			public void sessionDestroyed(IoSession arg0) throws Exception {

				connect(connector);
			}
		});
		*/
		connect(connector);
	}
	 
	 private void connect(NioSocketConnector connector) {
		for (;;) {
			try {
				ConnectFuture future = connector.connect();
				future.awaitUninterruptibly();
				ClientHelper.getInstance().setCONNECTOR(connector);
				session = future.getSession();
				if (session.isConnected()) {
					logger.info("socketconnect["
							+ connector.getDefaultRemoteAddress().getHostName()
							+ ":"
							+ connector.getDefaultRemoteAddress().getPort()
							+ "] millisecond");
					ClientHelper.getInstance().setSESSION_CHANNAL(session);
					
					if(!HyLmsClientConstant.IS_SYS_LOGIN_IN){
						//
						BaseRequestParamBean reqBean=HyMessageFactory.createSysLoginMsg();
						session.write(HyLmsSignUtil.getRequestBeanJson(reqBean));
						TmExMsgPOFactory.insertReq(reqBean);
					}
					
					break;
				}
			} catch (Exception ex) {
				logger.info("socketconnect["
						+ connector.getDefaultRemoteAddress().getHostName()
						+ ":" + connector.getDefaultRemoteAddress().getPort()
						+ "] fail. retry after " + 3000
						+ " millisecond, error:" + ex.getMessage());
			} finally {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
