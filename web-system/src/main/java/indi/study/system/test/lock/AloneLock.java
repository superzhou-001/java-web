package indi.study.system.test.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class AloneLock implements Lock {

    AtomicReference<Thread> owner = new AtomicReference<>(); // 存放当前线程
    LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>(); // 存放为等待线程

    @Override
    public void lock() {
        // 未抢到锁的线程进入等待队列并阻塞
        while(!owner.compareAndSet(null, Thread.currentThread())) {
            waiters.add(Thread.currentThread());
            LockSupport.park(); // 当前线程未抢到锁，阻塞当前线程
            waiters.remove(Thread.currentThread()); // 当前线程释放，等待队列删除当前线程
        }
    }

    @Override
    public void unlock() {
        if(owner.compareAndSet(Thread.currentThread(), null)) {
            // 如果是当前线程，则释放等待线程
            for (Object object : waiters.toArray()){
                Thread thread = (Thread) object;
                LockSupport.unpark(thread);
            }
        }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
