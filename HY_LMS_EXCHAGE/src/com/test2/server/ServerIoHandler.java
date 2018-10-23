/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.server
 * 文   件  名:ServerIoHandler.java
 * 创 建日期:2018年10月16日-下午6:11:31
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.test.server.SessionManager;

/**
 * 类名称:ServerIoHandler 类描述: 创建人:rock 修改人:rock 修改时间:2018年10月16日 下午6:11:31 修改备注:
 * 
 * @version 1.0.0
 */
public class ServerIoHandler extends IoHandlerAdapter {
	private static Logger logger = Logger.getLogger(ServerIoHandler.class);
	private int count = 0;
	private static int i = 0;
	ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public void sessionOpened(final IoSession session) throws Exception {
	count++;
	logger.info("第 " + count + " 个 client 登陆！address： : " + session.getRemoteAddress());
	
	session.write("count:="+count);
	/*
	executor.submit(new Callable<String>() {
	public String loop() {
				try {
					while (true) {
						session.write(i);
						logger.info(i + "");
						i++;
						Thread.sleep(10000);
					}
				} catch (Exception e) {

				} finally {

				}
				return null;
			}

	@Override
	public String call() throws Exception {
		return loop();
			}
		});
	*/
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {

	}

	@Override
	public void messageReceived(IoSession session, Object message)throws Exception {
		System.out.println("server messageReceived:="+message);
	}
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        System.out.println("session created with IP: " + clientIp);
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
