package com.thread_2;


import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * Vector 中方法实现也是都是加了 Synchronized
 *
 * */
public class T_R05_Vector extends BaseCount{
    public static List<UUID> list = new Vector<>();
    static UUID[] values = new UUID[count];
    static Thread[] threads = new Thread[thread_count];

    static {
        for (int i = 0; i < count; i++) {
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        public MyThread(int start) {
            this.start = start;
        }
        @Override
        public void run () {
            int gap = count/thread_count;
            for (int i = start; i < gap + start ; i++) {
                list.add(values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i*(count/thread_count));
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {}
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
        System.out.println(list.size());

        // -------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
          threads[i] = new Thread(() ->{
                for (int j = 0; j < count; j++) {
                    list.get(10);
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {}
        }
        end = System.currentTimeMillis();
        System.out.println((end - start));
    }


}
