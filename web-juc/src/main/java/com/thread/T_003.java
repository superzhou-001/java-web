package com.thread;

/**
 * 线程状态 new --> Runnable( ready(准备) --- running(运行) ) -->Teminated（结束）
 *                                        ||
 * LockSupport.parkUntil()                ||
 * LockSupport.parkNanos()          TimedWaiting（限时等待）
 *                                        ||
 wait() join() LockSupport.park()   Waiting （等待）
 *                                        ||
 *     等待进入同步代码的锁             Blocked (阻塞)
 *
 *
 * getState 获取线程状态
 *
 * Thread.interrupted() 打断线程 （了解一下就行）
 * */
public class T_003 {

    static class MyThread extends Thread {
        public void run () {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(i);
                    Thread.sleep(100);
                } catch (Exception e){

                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        try {
            t1.join();
        } catch (Exception e) {}
        System.out.println(t1.getState());
    }

}
