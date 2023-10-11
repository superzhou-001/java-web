package com.thread;

import java.util.LinkedList;

/**
 *  写一个固定容量的同步容器，拥有put和get方法，以及getCount方法
 *  能够支持两个生成者线程以及10个消费者线程的阻塞调用
 *
 * */
public class T_MS002_WithoutVolatile<T> {

    final private LinkedList<T>  list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (Exception e){}
        }
        list.add(t);
        ++ count;
        this.notifyAll(); // 当生产者生产到最大时会进入等待队列，所以得唤醒生产者
    }
    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (Exception e){}
        }
        t = list.removeFirst();
        count --;
        this.notifyAll(); // 唤醒消费者
        return t;
    }

    public static void main(String[] args) {
        T_MS002_WithoutVolatile<String> ms002 = new T_MS002_WithoutVolatile<>();
        // 生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    ms002.put(Thread.currentThread().getName()+" "+j);
                }
            }, "p"+i).start();
        }
        //消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(ms002.get());
                }
            }).start();
        }

    }

}
