package com.demo;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  面试题： 写一个固定容量同步容器。拥有put和get方法，以及getCount方法
 *  能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 *  解：ReentrantLock 本质 不同的等待队列
 *  Condition .wait() 等待
 *            .signalAll()
 * */
public class T02_MyContainer {
    public final LinkedList<String> list = new LinkedList<>(); // 容器
    public final int MAX = 20; // 容器最大值
    public int count = 0;

    /*private Lock lock = new ReentrantLock();
    private Condition p = lock.newCondition();
    private Condition c = lock.newCondition();*/


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }



}
