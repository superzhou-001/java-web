package com.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可看T_MS002_WithoutVolatile2
 *
 * ReentrantLock  可重入锁 可替代Synchronized 排它锁（互斥锁） 一旦锁住其他线程不想拿到
 * 区别 Synchronized 自动解锁 锁升级过程
 *    ReentrantLock 需手动加锁 手动解锁 CAS操作
 * ReentrantLock 可进行tryLock() 尝试加锁
 * ReentrantLock 用lockInterruptibly() 锁定后用 interrupt 唤醒
 * ReentrantLock 可以切换锁是否是公平的
 * 公平锁概念： 一堆线程争锁,争不到的进入队列等待。公平锁 新的线程过来后会检测队列里面是否有其他线程有则进队列等待，
 *                                      非公平锁 新线程过来后不检查队列里是否有线程，来了之后直接和队列第一个线程抢
 * */

public class T_008_CAS_ReentrantLock {
    Lock lock = new ReentrantLock();

    public /*synchronized*/ void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                Thread.sleep(1000);
                /*if (i == 3) {
                    m2(); // m1中调用m2 方法属于同一个线程  synchronized 中可以调用 synchronized 方法 （可重入）
                }*/
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    /**
     * 尝试锁 tryLock
     * */
    public /*synchronized*/ void m2() {
        boolean locked = false;
        /*try {
            lock.lock();
            System.out.println("这是m2....");
        } catch (Exception e) {}
        finally {
            lock.unlock();
        }*/
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS); // 5秒内尝试获取锁
            System.out.println("这是m2...."+ locked);
        } catch (Exception e) {}
        finally {
            if(locked) lock.unlock();
        }

    }

    /**
     * 线程打断
     * */
    public void m3() {
        try {
            lock.lock();
            System.out.println("m3...start");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            System.out.println("m3...end");
        } catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

    public void m4() {
        try{
            lock.lockInterruptibly(); // 可以用interrupt()方法唤醒
            System.out.println("m4...start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m4...end");
        } catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

    /*
    * 公平锁测试
    * */
    ReentrantLock reentrantLock = new ReentrantLock(false); // true 设置为公平锁 默认为非公平锁

    public void m5() {
        for (int i = 0; i < 10; i++) {
            try {
                reentrantLock.lock();
                //TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } catch (Exception e){

            } finally {
                reentrantLock.unlock();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        T_008_CAS_ReentrantLock lock = new T_008_CAS_ReentrantLock();
        /*new Thread(lock :: m1).start();
        Thread.sleep(1000);
        new Thread(lock :: m2).start(); // 两个线程有争用，则m2等m1执行完才能得到这一把锁*/

        new Thread(lock :: m5).start();
        new Thread(lock :: m5).start();


    }
}
