package com.thread_3;

import java.util.concurrent.*;

/**
 * 类似于 Runnable 不同的是 Callable有返回值
 * 产生结果
 * */
public class T_003_Callable {

    public static void main(String[] args) throws Exception {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "123123";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable); // 异步

        System.out.println("AA");
        System.out.println(future.get()); // future.get() 是阻塞方法
        System.out.println("BB");

        service.shutdown();


    }
}
