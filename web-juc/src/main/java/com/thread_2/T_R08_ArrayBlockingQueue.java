package com.thread_2;

import java.util.concurrent.*;

/**
 * 有界 new ArrayBlockingQueue<>(10); 指定固定长度
 * */
public class T_R08_ArrayBlockingQueue {

    static BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
    public static void main(String[] args) {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                    q.put("a"+i); // 添加满了之后 会等待
                } catch (Exception e){}
            }
        },"pp").start();
/*        try {
            q.put("a1"); // 添加满了之后 会等待
            //q.take();
            //q.add("a1"); // 添加满了会报错
            //q.offer("a1"); //
            //q.offer("a1", 1 , TimeUnit.SECONDS); // 阻塞一秒后尝试往里加
        } catch (Exception e) {}*/


        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                for (;;) {
                    try {
                        //TimeUnit.MILLISECONDS.sleep(2000);
                        System.out.println(Thread.currentThread().getName()+"  take-" + q.take());
                    } catch (Exception e) {}
                }
            }, "p"+i).start();
        }
    }

}
