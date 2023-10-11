package com.thread_2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  写时复制
 *  Vector 写读都加锁
 *  CopyOnWriteArrayList 写时加锁 读时不加锁
 * */
public class T_R05_01_CopyOnWriteArrayList {

    public static void main(String[] args) {
        List<String> list =
                //new ArrayList<>();
                //new Vector<>();
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add("t"+r.nextInt(10000));
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e){}
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
        System.out.println(list.size());

    }
}

