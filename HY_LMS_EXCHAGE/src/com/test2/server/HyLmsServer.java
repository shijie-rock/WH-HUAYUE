/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.server
 * 文   件  名:HyLmsServer.java
 * 创 建日期:2018年10月16日-下午4:59:21
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.test2.common.HyLmsServerConstant;

/**
 * 类名称:HyLmsServer
 * 类描述:
 * 参考：https://blog.csdn.net/hannuotayouxi/article/details/78685334
 * 加入心跳机制，模拟万华消息服务器server,
 * 不同的是，这是异步心跳，即client 和 server 互发，万华是同步心跳，client发，server直接应答
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 下午4:59:21
 * 修改备注:
 * @version 1.0.0
 */
public class HyLmsServer {

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static Logger logger=Logger.getLogger(HyLmsServer.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HyLmsServerConstant.init();
		
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(1024);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE,HyLmsServerConstant.CONNECT_TIME_OUT);
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec1", new ProtocolCodecFilter(new TextLineCodecFactory()));
		KeepAliveMessageFactory heartBeatFactory = new ServerKeepAliveMessageFactoryImpl();
		//下面注释掉的是自定义Handler方式
		KeepAliveRequestTimeoutHandler heartBeatHandler = new ServerKeepAliveRequestTimeoutHandlerImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
		IdleStatus.WRITER_IDLE, heartBeatHandler);
		//KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,IdleStatus.READER_IDLE);

		//设置是否forward到下一个filter
		heartBeat.setForwardEvent(true);
		//设置心跳频率
		heartBeat.setRequestInterval(HyLmsServerConstant.HEART_BEAT_RATE);
		acceptor.getFilterChain().addLast("heartbeat", heartBeat);
		acceptor.setHandler(new ServerIoHandler());
		try {
			acceptor.bind(new InetSocketAddress(HyLmsServerConstant.SERVER_PORT));
			logger.info("Server started on port： " + HyLmsServerConstant.SERVER_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Server started error ");
		}

	}

}
