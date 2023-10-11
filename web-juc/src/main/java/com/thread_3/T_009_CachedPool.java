package com.thread_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 无核心线程数
 * */
public class T_009_CachedPool {

    public static void main(String[] args) {
       ExecutorService service =  Executors.newCachedThreadPool();
    }
}
