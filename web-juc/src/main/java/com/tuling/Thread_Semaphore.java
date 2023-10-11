package com.tuling;

import java.util.concurrent.Semaphore;

/**
 * 信号量--- 作用：限流
 *
 * */
public class Thread_Semaphore {



    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire();
        semaphore.release();

    }
}
