package com.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 *  LockSupport 可使指定线程堵塞  LockSupport.park();
 *              可叫醒指定线程 LockSupport.unpark();
 *  之前 要一个线程停住 需要加锁 后 wait() 再用 notify(唤醒) 如下
 *
 *
 * */
public class T_015_LockSupport {

    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                //synchronized (T_015_LockSupport.class) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(i);
                        if (i == 5) {
                            LockSupport.park(); // 停车 当前线程停车
                            //T_015_LockSupport.class.wait();
                        }
                    } catch (Exception e){}
                //}

            }
        });
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (Exception e){}
        System.out.println("主线程已经休息8秒了");
        LockSupport.unpark(t1); // 解封t1
    }
}
