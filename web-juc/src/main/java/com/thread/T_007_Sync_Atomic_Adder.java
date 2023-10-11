package com.thread;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Sync 加锁
 * AtomicXX 无锁 CAS
 * XXAdder 无锁 CAS 数组分段处理线程 分段锁
 * 数据量大的情况下 耗时 Sync > AtomicXX > XXAdder
 *  xx.join() 理解：当前线程中调用其他线程.join 表示当前线程要等其他线程执行完毕后，当前线程才接着执行。只有线程调用了.join才会影响当前线程，
 *                 线程未调用.join则不会影响当前线程的执行
 * */
public class T_007_Sync_Atomic_Adder {

    static long count1 = 0;
    static AtomicLong count2 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();
    static long start = 0l;
    static long end = 0l;
    Object object = new Object();
    public void countAdd1 (){
        for (int i = 0; i < 100000; i++) {
            synchronized (object) {
                count1 ++;
            }
        }
        /*try {
            Thread.sleep(2000);
        } catch (Exception e){}
        System.out.println("countAdd1+执行完了");*/
    }

    public void countAdd2 ()  {
        for (int i = 0; i < 100000; i++) {
            count2.incrementAndGet();
        }
        /*try {
            Thread.sleep(5000);
        } catch (Exception e){}
        System.out.println("countAdd2+执行完了");*/
    }

    public void countAdd3 (){
        for (int i = 0; i < 100000; i++) {
            count3.increment();
        }
        /*System.out.println("countAdd3+执行完了");*/
    }

    /**
     * 简单测试一下(不太科学 jmh) 注：不同的写法导致不同的执行结果，这种只能做为简单的理解测试
     * */
    public static void main(String[] args) throws Exception {
        T_007_Sync_Atomic_Adder saa = new T_007_Sync_Atomic_Adder();

//        Thread thread1 = new Thread(saa :: countAdd1);
//        Thread thread2 = new Thread(saa :: countAdd2);
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        System.out.println("主线程执行完了");

 /*       ;
        Thread thread3 = new Thread(saa :: countAdd3);
        thread3.start();
        thread2.start();
        thread1.start();
        thread3.join();*/




        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(saa :: countAdd1);
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("Sync="+count1+ "--耗时"+ (end - start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(saa :: countAdd2);
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("Atomic="+count2+ "--耗时"+ (end - start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(saa :: countAdd3);
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();

        }
        end = System.currentTimeMillis();
        System.out.println("Adder="+count3+ "--耗时"+ (end - start));

    }


}
