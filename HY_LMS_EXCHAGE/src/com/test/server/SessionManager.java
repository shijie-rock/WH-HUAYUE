/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test
 * 文   件  名:SessionManager.java
 * 创 建日期:2018年10月16日-上午10:29:35
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

/**
 * 类名称:SessionManager
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月16日 上午10:29:35
 * 修改备注:
 * @version 1.0.0
 */
public class SessionManager {
    private final static Map<Long, IoSession> sessions = new ConcurrentHashMap<Long, IoSession>();
    private final static SessionManager manager = new SessionManager();

    public static SessionManager getManager() {
        return manager;
    }

    private SessionManager() {}

    public void add(IoSession ioSession) {
        if (ioSession == null) return;

        sessions.put(ioSession.getId(), ioSession);
    }

    public void remove(IoSession ioSession) {
        if (ioSession == null) return;

        sessions.remove(ioSession);
    }

    public void removeAll() {
        if (sessions.size() == 0) return;

        sessions.clear();
    }

    public void update(Object message) {
        for (IoSession ioSession: sessions.values()) {
//            ioSession.write(message+"阿萨法萨芬大师傅");
        }
    }
}
