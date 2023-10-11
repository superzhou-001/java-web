package com.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 同步工具 CyclicBarrier 循环栅栏 --- 线程同步工具
 * 不满等着
 * 使用场景
 *   复杂操作
 *   1. 去数据库取数据
 *   2. 取网络数据
 *   3. 读文件
 *   原本一条线程依次进行熟读有点慢，并发执行获得结果速度会快
 *
 * */
public class T_010_CyclicBarrier {

    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20); //所有程序到达20后 在打开栅栏
        // () -> == new Runnable 相当于开了个异步线程来处理 栅栏中线程满了之后做的处理
        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("满人, 发车");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                try {
                    barrier.await(); // 理解：await()后 线程只是在这等着，数量还是那么多 不会减少，所以下面还是会执行对应的线程数
                                     // 如果执行的线程数不是栅栏的整数倍 则主线程会一直堵塞
                    //System.out.println("满人, 发车 滴滴滴");
                } catch (Exception e){}
            }).start();
        }
    }
}
