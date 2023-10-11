package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch --- 线程同步工具
 *
 * 倒数的门栓
 * */
public class T_009_CountDownLatch {
    public static void myCountDownLatch () {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int num = 0;
                for (int j = 0; j < 1000; j++) {
                    num ++;
                }
                System.out.println(Thread.currentThread().getName()+"---"+num);
                latch.countDown(); // 线程结束 减1， 减到0的时候 门栓打开
            }, "t"+i);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try{
            latch.await(); // 门栓栓住 阻塞
        } catch (Exception e){}
        /*for (int i = 0; i < 100 ; i++) {
            try {
                threads[i].join();
            } catch (Exception e){}
        }*/

        System.out.println("end latch");
    }

    public static void main(String[] args) {
        myCountDownLatch();
    }
}
