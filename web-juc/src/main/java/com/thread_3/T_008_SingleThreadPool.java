package com.thread_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 线程池的工具类
 *
 * */
public class T_008_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
    }
}
