package com.thread;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交替打印A1B2C3D4
 *
 * wait、notify、notifyAll是Object对象的属性，并不属于线程。我们先解释这三个的一个很重要的概念
 *
 * wait：使持有该对象的线程把该对象的控制权交出去，然后处于等待状态（这句话很重要，也就是说当调用wait的时候会释放锁并处于等待的状态）
 *
 * notify：通知某个正在等待这个对象的控制权的线程可以继续运行（这个就是获取锁，使自己的程序开始执行，最后通过notify同样去释放锁，并唤醒正在等待的线程）
 *
 * notifyAll:会通知所有等待这个对象控制权的线程继续运行(和上面一样，只不过是唤醒所有等待的线程继续执行)
 *
 * 这个就好了，从上面的解释我们可以看出通过wait和notify可以做线程之间的通信，当A线程处理完毕通知B线程执行，B线程执行完毕以后A线程可以继续执行。
 * */

public class T_MS003 {

    Lock lock = new ReentrantLock();
    Condition c = lock.newCondition();
    Condition d = lock.newCondition();

    public void soutC() {
        try {
            lock.lock();
            String b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int j = 0; j < b.length(); j++) {
                System.out.print(b.charAt(j));
                d.signalAll();
                c.await();
            }
            d.signalAll(); // 运行完后 使线程结束
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void soutI() {
        try {
            lock.lock();
            for (int i = 0; i < 26; i++) {
                System.out.print(i+1);
                c.signalAll();
                d.await();
            }
            c.signalAll(); // 运行完后 使线程结束
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public synchronized void printCharsByWaitNotify(){
        try{
            String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                this.notify();//通知唤醒线程，但还没释放锁
                this.wait();//当前线程进入等待队列 让出锁(释放锁)
            }
            this.notify();//唤醒线程 使线程结束
        }catch (Exception e){e.printStackTrace();}
    }
    public synchronized void printNumsByWaitNotify(){
        try{
            for (int i = 0; i < 26; i++) {
                System.out.print(i+1);
                this.notify();//通知唤醒线程，但还没释放锁
                this.wait();//释放锁
            }
            this.notify();//唤醒线程 使线程结束
        }catch (Exception e){e.printStackTrace();}
    }





    public static void main(String[] args) {
        // 警告： 写多线程千万别通过sleep 来达到效果
        /*T_MS003 tMs003 = new T_MS003();
        new Thread(() -> tMs003.soutC() ).start();
        new Thread(() -> tMs003.soutI() ).start();*/


        T_MS003 solution = new T_MS003();
        Thread t1 = new Thread(solution::printCharsByWaitNotify);
        Thread t2 = new Thread(solution::printNumsByWaitNotify);
        t1.start();
        t2.start();


        // 容器实现 --- 和 ExChange 差不多
        /*LinkedTransferQueue<Object> q = new LinkedTransferQueue<Object>();
        new Thread(() -> {
            String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < str.length(); i++) {
                try {
                    System.out.print(q.take()); // 取数据
                    q.transfer(str.charAt(i)); // 加入后等待 消费者消息后再次加入
                } catch (Exception e){}
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    q.transfer(i+1); // 加入后等待 消费者消息后再次加入
                    System.out.print(q.take()); // 取数据
                } catch (Exception e){}
            }
        }).start();*/
    }


}
