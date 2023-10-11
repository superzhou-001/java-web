package com.thread_2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 10个线程一起卖票
 *
 * Queue 和 list的区别
 *
 * 对线程友好的API offer peek poll
 *
 * BlockingQueue 又加了
 * put take 阻塞方法
 *
 * */
public class T_R06_ConcurrentQueue {

    static Queue<Object> queue = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            queue.add(i); //
            //queue.offer(i);// 会给返回值 是否加成功
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                while (true) {
                    Object s = queue.poll(); // 取出一个元素并删除队列里面
                    //Object s1 = queue.peek(); // 取元素 不删除

                    if(s == null) break;
                    System.out.println("售票员卖一张票:"+s);
                }
            }).start();
        }
    }

}
