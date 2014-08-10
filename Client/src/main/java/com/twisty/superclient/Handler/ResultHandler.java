package com.twisty.superclient.Handler;

import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by twisty on 14-8-10.
 */
public class ResultHandler implements IoHandler {
    private CommonLog log = LogFactory.createLog();
    @Override
    public void sessionCreated(IoSession session) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.i("MSG:>>"+message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }
}