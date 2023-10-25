package com.demo;

import java.util.LinkedList;

/**
 *  面试题： 写一个固定容量同步容器。拥有put和get方法，以及getCount方法
 *  能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 *  解： 使用wait和notifyAll来实现
 * */
public class T01_MyContainer<T> {
    private final LinkedList<T>  list = new LinkedList();
    private final int MAX = 10; //设置容器大小 最大10个元素
    private int count = 0;

    // put方法
    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++ count;
        this.notifyAll();
    }

    // get方法
    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        T01_MyContainer<String> myc = new T01_MyContainer<>();
        // 定义生产者
        for (int i = 0; i < 2 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    myc.put(Thread.currentThread().getName() + " " + j);
                }
            }, "P"+i).start();
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 定义消费者
        for (int i = 0; i < 20 ; i++) {
            new Thread(() ->{
                for (int j = 0; j < 10 ; j++) {
                    System.out.println(Thread.currentThread().getName()+" "+myc.get());
                }
            }, "C"+i).start();
        }

    }
}
