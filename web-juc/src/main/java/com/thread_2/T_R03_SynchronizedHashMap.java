package com.thread_2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * SynchronizedHashMap 可以将 HashMap 变成线程安全的
 * */
public class T_R03_SynchronizedHashMap extends BaseCount{
    // Collections.synchronizedMap 把非同步的hashMap 变为同步的hashMap
    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<UUID, UUID>());
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    // 预先设置的目的 保证插入的元素是一样的
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count/thread_count;

        public MyThread(int start) {this.start = start;}

        @Override
        public void run() {
            // 表示每个线程插10000条记录 第一个线程插0~10000个， 第二个插10001 ~ 20000个依次类推
            for (int i = start; i < gap + start; i++) {
                m.put(keys[i], values[i]);
            }
        }

    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[thread_count];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i*(count/thread_count));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {}
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
        System.out.println(m.size());

        //---------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            // 100个线程一块去读一百万数据中第10个元素
            threads[i] = new Thread(() ->{
                for (int j = 0; j < count; j++) {
                    m.get(keys[10]);
                }
            });
        }
        for (Thread thread : threads) { thread.start(); }

        for (Thread thread : threads) {
            try { thread.join(); } catch (Exception e) {}
        }
        end = System.currentTimeMillis();
        System.out.println((end - start));
    }

}
