package com.tuling;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;



/**
 *  if (!tryAcquire(arg) &&
 *     acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 *     selfInterrupt();
 *  tryAcquire(arg) --- 尝试获取锁 （ 锁竞争逻辑 false未抢到锁）
 *
 *  // CLH 队列
 *  addWaiter(Node.EXCLUSIVE) --- 线程入队， Node节点， Node对Thread引用
 *     Node属性：共享， 独占  由SHARED、EXCLUSIVE 来控制共享 还是 独占（EXCLUSIVE）
 *     创建节点 Node = prev, next, waiteStatus, thread 重要属性
 *     waiteStatus 节点的生命状态： 信号量
 *         SIGNAL = -1 //可被唤醒
 *         CANCELLED = 1 //代表出现异常，中断引起的，需要废弃结束
 *         CONDITION = -2 //条件等待
 *         PROPAGATE = -3 //传播
 *         0 - 初始状态Init状态
 *
 * */

public class Thread_01_ReentrantLock {

    private int count = 0;
    private static int total = 0;
    private static Object object = new Object();
    private static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException,Exception{

        Thread_01_ReentrantLock cc = new Thread_01_ReentrantLock();

        // atomicxx 原子性操作
        AtomicInteger total1 = new AtomicInteger();

        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    synchronized (object) {
//                        total ++;
//                    }
                    LockSupport.park();

                    lock.lock();
                    //
                    // lock.lockInterruptibly();
                    total ++;
                    cc.count ++;
                    total1.incrementAndGet();  // 自加
                    lock.unlock();
                }
            }).start();
        }
        Thread.sleep(1000);
        latch.countDown();
        Thread.sleep(1000);
        System.out.println(total + "---" + total1 + "---" + cc.count);

/*        Thread_01_ReentrantLock cc = new Thread_01_ReentrantLock();

        B b = cc.new B();
        b.aaa();
        C c = cc.new C();
        c.aaa();*/
/*        try {
            for (int i = 0; i < 1; i++) {
                System.out.println("000000000");
                if (i == 5) {
                    throw new Exception();
                }
                System.out.println("22222222");
            }
        } catch (Exception e) {
            System.out.println("33333");
        } finally {
            System.out.println("4444");
        }*/




    }

    public abstract class aa {
        public void aaa() {
            System.out.println("----aa");
        }
    }

    public class B extends aa {
        public void aaa() {
            System.out.println("aaaccc");
        }
    }

    public class C extends aa {
        public void aaa() {
            System.out.println("cccbbb");
        }
    }
}
