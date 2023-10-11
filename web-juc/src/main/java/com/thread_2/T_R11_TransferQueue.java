package com.thread_2;


import java.util.concurrent.LinkedTransferQueue;

/**
 * 多个容量为0的队列
 * */
public class T_R11_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (Exception e) {

            }
        }).start();
        queue.transfer("aa"); // 和 put 比较  put是加满了之后等待，transfer是加了之后进入队列等待消费（多个线程） 两个线程交替打印 T_MS003
        System.out.println(queue.size());
        
    }

}
