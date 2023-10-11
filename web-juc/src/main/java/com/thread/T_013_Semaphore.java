package com.thread;

import java.util.concurrent.Semaphore;

/**
 * semaphore 信号灯 --- 线程同步工具
 *   使用 限流
 * 控制有多少个线程同时执行
 * */
public class T_013_Semaphore {

    public static void main(String[] args) {
        //Semaphore semaphore = new Semaphore(1); //表示允许一个线程使用
        // 默认非公平 设置 true 设置为公平锁
        Semaphore semaphore = new Semaphore(2, true);
        new Thread(() -> {
            try {
                semaphore.acquire(); // 向Semaphore中申请一把锁，申请后 Semaphore数量减一
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (Exception e){}
            finally {
                semaphore.release();
            }
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire(); // 向Semaphore中申请一把锁，申请后 Semaphore数量减一
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (Exception e){}
            finally {
                semaphore.release();
            }
        }).start();

    }
}
