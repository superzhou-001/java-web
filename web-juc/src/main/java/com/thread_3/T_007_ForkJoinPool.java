package com.thread_3;


import java.util.concurrent.ForkJoinPool;

/**
 * 分解汇总任务
 * cpu密集型
 *     线程数 = cpu核数+1（现代cpu支持超线程）
 * IO密集型
 *     线程数 = （（线程等待时间 + 线程CPU时间））* CPU数目
 *
 *
 *  ForkJoin
 * 解决 cpu密集型的框架
 * */
public class T_007_ForkJoinPool {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
    }
}
