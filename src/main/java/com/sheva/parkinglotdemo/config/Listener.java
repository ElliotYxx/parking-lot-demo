package com.sheva.parkinglotdemo.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Sheva
 * @Date 2020/12/3
 */
public class Listener implements SessionListener {

    public static final AtomicInteger sessionCount=new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.getAndIncrement();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.getAndDecrement();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.getAndDecrement();
    }
    public AtomicInteger getSessionCount()
    {
        return sessionCount;
    }
}
