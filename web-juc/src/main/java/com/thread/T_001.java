package com.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程的几种实现方式
 * 1、Thread 2、Runnable 3、lambda表达式  3、Executors.newCachedThread (线程池)
 * */
public class T_001 {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread");
                try {
                    Thread.sleep(1);
                } catch (Exception e) {

                }
            }
        }
    }

    public static class MyRun implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable");
                try {
                    Thread.sleep(1);
                } catch (Exception e) {

                }
            }
        }
    }

    /*
    * 线程池
    * */
    private static synchronized  void testSyncMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getId() + "SyncMethod:" + i);
        }
    }

    public static void main(String[] args) {
        /*new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() ->{
           for (int i = 0; i < 5; i++) {
               System.out.println("lambda");
           }
        }).start();
        for (int i = 0; i < 6; i++) {
            System.out.println("main");
        }*/

        // 线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new Thread(()-> testSyncMethod()));
        pool.execute(new Thread(()-> testSyncMethod()));
        pool.shutdown();

    }

}
