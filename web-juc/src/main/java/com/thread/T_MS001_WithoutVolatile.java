package com.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 淘宝面试题
 *    实现一个容器，提供两个方法， add、size
 *    写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 *    当个数到5的时候，线程2给出提示并结束
 * notify 只是唤醒锁 不释放锁
 * */
public class T_MS001_WithoutVolatile {
     // volatile 尽量别修饰引用类型 ---不能解决这个问题
     /*volatile */ List<Integer> list = new ArrayList<>();
     // 使用同步容器 也不行
     //volatile List<Integer> list = Collections.synchronizedList(new LinkedList<>());

    public void add(Integer str) {
        list.add(str);
    }

    public int size() {
        return list.size();
    }

    static Thread t1,t2 = null;



    public static void main(String[] args) {
        T_MS001_WithoutVolatile ms001 = new T_MS001_WithoutVolatile();

        /*t1 = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    //TimeUnit.SECONDS.sleep(1);
                    ms001.add(i);
                    System.out.println(ms001.size());
                    if (ms001.size() == 5) {
                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }
                }catch (Exception e){}
            }
        },"t1");
        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("t2结束，检测到t1加了五个元素了");
            LockSupport.unpark(t1);
        }, "t2");
        t1.start();
        t2.start();*/

        Object lock = new Object();

        t1 = new Thread(() ->{
            System.out.println("t1 开始");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception e){};
                        ms001.add(i);
                        System.out.println("t1 获得了锁--"+i);
                        if (ms001.size() == 5) {
                            System.out.println("t1 唤醒t2继续执行");
                            lock.notify(); // 唤醒t2继续执行,但是t1没有让出锁，所以这时候t2是醒着等待
                            try {
                                System.out.println("t1 等待并让出锁, 等待t2执行完后被唤醒");
                                lock.wait(); // t1等待让出锁,这时候t2由等待状态进入运行状态 等待t2执行完后唤醒
                            } catch (Exception e) {}
                        }
                }
            }
            System.out.println("t1 结束");
        }, "t1");
        // 等待 一种是没有获得锁醒着等待(锁队列) 一种是wait() 睡着等待(等待队列)
        // wait()之后进入到等待队列，notifyAll()后进入锁队列
        t2 = new Thread(() -> {
            System.out.println("t2 开始");
            synchronized (lock) {
                System.out.println("t2 获得了锁");
                if (ms001.size() != 5) {
                    try {
                        System.out.println("t2 等待并让出锁");
                        lock.wait(); // t2等待堵塞 让出锁
                    } catch (Exception e) {}
                }
                System.out.println("size == 5,t2唤醒t1继续执行,t2 结束");
                lock.notify(); // 唤醒t1, t2继续执行完
            }
        }, "t2");
        t2.start();
        t1.start();
    }
}
