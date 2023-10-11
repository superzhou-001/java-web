package com.thread_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 固定的线程数
 *
 * 并发 任务提交  并行 任务执行
 * */
public class T_010_FixedPool {

    public static void main(String[] args) {
        // 并行处理
        ExecutorService service = Executors.newFixedThreadPool(10);
    }
}
