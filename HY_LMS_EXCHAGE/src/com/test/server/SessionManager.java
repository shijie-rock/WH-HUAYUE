/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test
 * ��   ��  ��:SessionManager.java
 * �� ������:2018��10��16��-����10:29:35
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

/**
 * ������:SessionManager
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��16�� ����10:29:35
 * �޸ı�ע:
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
//            ioSession.write(message+"���������Ҵ�ʦ��");
        }
    }
}
