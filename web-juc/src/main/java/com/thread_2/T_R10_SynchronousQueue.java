package com.thread_2;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0 不可以往里装
 * 必须有人在前面等待
 * 对比 ExChange 两个线程等待同步
 *     SynchronousQueue 只需要一个线程等待
 * */
public class T_R10_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Object> queue = new SynchronousQueue<Object>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (Exception e) {

            }
        }).start();
        queue.put("aa");
        System.out.println(queue.size());
    }

}
