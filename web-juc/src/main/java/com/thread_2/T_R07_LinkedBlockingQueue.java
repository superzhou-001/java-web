package com.thread_2;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Blocking 阻塞
 * 链表 最大 integer.MAX_VALUE
 * LinkedBlockingQueue 阻塞队列 底层实现 LockSupport.park
 * 在ConcurrentQueue 基础上增加了 put take 方法
 * 其中 put() 添 take() 取 真正实现了堵塞
 *
 * */
public class T_R07_LinkedBlockingQueue {
    static BlockingQueue<String> q = new LinkedBlockingDeque<String>();
    public static void main(String[] args) {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                    q.put("a"+i); // 添加满了之后 会等待
                } catch (Exception e){}
            }
        },"pp").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"  take-" + q.take());
                    } catch (Exception e) {}
                }
            }, "p"+i).start();
        }
    }
}
