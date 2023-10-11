package com.thread_2;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentHashMap 插的时候要检查东西很多（各种cas判断），则效率相对来说慢
 * ConcurrentHashMap 解决读取效率 高并发使用
 *
 * 为什么没有ConcurrentTreeMap 因为cas加树非常麻烦，则没有，
 * 解决: 使用跳表结构--- ConcurrentSkipListMap 拿出关键元素做层数，从顶层开始查
 *
 * // map 实现方式 hashMap(hash表) treeMap(红黑数 做了排序及平衡操作)
 *      数组 + 链表 + 红黑树
 * */
public class T_R04_ConcurrentHashMap extends BaseCount {
    public static ConcurrentHashMap<UUID, UUID> m = new ConcurrentHashMap<>(); // 通过hash表实现 无排序
    // public static ConcurrentSkipListMap<UUID, UUID> m1 = new ConcurrentSkipListMap<>(); // 实现 跳表结构（有序）
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
        // 每个线程启动
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待每个线程完成
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
