package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AQS实现原理
 * 自旋 + LockSupport + cas + queue
 *
 * CAS 操作 保证线程安全 无锁 (自旋) 乐观锁
 * -- 乐观锁概念
 * AtomicXX 原子性 都采用的无锁（CAS -- Compare And Set ;; 比较 and 设定）
 * XXAdder (也是cas操作)类似于分段锁概念 ---分段处理线程
 *   cas(V（要改的值 比如：0）,Expected（期望值 0 ）, NewValue（要改的新值 1）)
 *       for(;;) {
 *        if V == E
 *           V = N
 *       }
 *       修改直至成功
 *   cas 是cpu原语支持 （指令级操作） 不可打断
 *       cup不可打断实现 在执行指令上下 加读写屏障
 *
 * 基础类型（int ... ）不会产生ABA问题
 *CAS （对象）会产生ABA问题--- 举例： 一个箱子借给别人用后，还给你的时候 箱子内部使用情况你是不清楚的
 *  解决 加版本号 Atomic内提供的方法  AtomicStampedReference 解决ABA问题（加时间戳）
 *
 * unsafe类 直接操作内存
 * unsafe类 jdk9 后关闭了
* */
public class T_006_CAS {

    static AtomicInteger count = new AtomicInteger();
    /*static Integer count = 0;*/

    public /*synchronized*/ void m () {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet(); // 等于count ++
            /*count ++;*/
        }
    }

    public static void main(String[] args) {
        T_006_CAS cas = new T_006_CAS();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(cas :: m));
        }
        threadList.forEach((list) ->{
            list.start();
        });
        try {
            Thread.sleep(1000);
        } catch (Exception e){}
        System.out.println(count);
    }
}
