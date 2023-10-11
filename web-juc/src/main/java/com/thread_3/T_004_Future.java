package com.thread_3;

import java.util.concurrent.*;

/**
 * 取结果
 *
 * FutureTask 又是一个 Runnable 又是一个 future
 * */
public class T_004_Future {

    public static void main(String[] args) throws Exception{


        FutureTask<String> task = new FutureTask<String>(() ->{
            Thread.sleep(2000);
            return "1100"; // 必须return
        });

        new Thread(task).start();
        System.out.println(task.get());


        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<?> future = service.submit(() -> {
            return 1;
        });

    }
}
