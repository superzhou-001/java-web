package com.thread;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  ReadWriteLock
 *  、
 *
 *
 *  读写锁概念
 *    -- 共享锁 读锁 可共享这把锁 速度快
 *    -- 排它锁 写锁 独享这把锁
 *  读如果不加锁 有可能会脏读
 * */
public class T_012_ReadWriteLock {
    static ReentrantLock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    static int value = 0;

    static public void read (Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over");
        } catch (Exception e) {}
        finally {
            lock.unlock();
        }
    }
    static public void write(Lock lock, int v) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = v;
            System.out.println("write over");
        } catch (Exception e) {}
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock1 = lock; // 使用ReentrantLock 互斥锁 会执行20s , 用读锁则很快
        Runnable able1 = () -> read(readLock);
        Runnable able2 = () -> write(writeLock, new Random().nextInt());
        for (int i = 0; i < 18; i++) { new Thread(able1).start(); }
        for (int i = 0; i < 2; i++) { new Thread(able2).start(); }
    }

}
