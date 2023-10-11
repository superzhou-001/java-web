package com.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 本质不同的等待队列
 *
 * */
public class T_MS002_WithoutVolatile2<T> {

    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;
    Lock lock = new ReentrantLock();
    // new出了两个队列
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            ++ count;
            consumer.signalAll();
        } catch (Exception e) { }
        finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            count --;
            producer.signalAll();
        } catch (Exception e) { }
        finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T_MS002_WithoutVolatile2<String> ms002 = new T_MS002_WithoutVolatile2<>();
        //消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(ms002.get());
                }
            }).start();
        }


        // 生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    ms002.put(Thread.currentThread().getName()+" "+j);
                }
            }, "p"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e){}


    }
}
