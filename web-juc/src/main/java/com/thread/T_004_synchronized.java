package com.thread;

/**
 * 锁 ：对一段代码进行加锁，本质是获得锁
 * 锁定：是锁定一个对象
 * 锁实现： mark word 对象头（64位） 拿出两位 不同组合不同类型的锁
 * 锁的力度：synchronized(this) = synchronized 方法
 * synchronized 悲观锁 非公平锁  可重入的 （相同线程过来发现是自己的锁 直接执行内部的方法） --- Sync方法内部可调用Sync方法
 *     如果方法如果出现异常 则锁自动释放
 *
 * *synchronized 的底层实现*
 * JDK1.5之前 直接向OS申请锁 这是重量级的 悲观锁
 * 后来改进
 *   锁升级概念 （hostPot（JVM）实现）
 *   synchronized(Object)
 *   markword 记录这个线程ID （首先是无锁状态）
 *   如果还是一个线程过来 就不用申请锁  (偏向锁)
 *   如果线程争用（非同一线程） 升级为自旋锁 （占cup）
 *   自旋10次以后 升级为重量锁--os （升级为重量级 不占cup）
 *
 * 什么时候用自旋锁？
 *   加锁代码执行时间段，线程数少用自旋 CAS ?
 *   执行时间长，线程数多用系统锁 Sync ?
 * synchronized(Object)
 *  不能使用 String常量 Integer Long 初始的内容相同 都是指定的是一个对象
 *
 * */
public class T_004_synchronized {
    public int count = 0;
    Object obj = new Object();

    // 理解锁
    public void sync() {
        System.out.println(Thread.currentThread().getName()+"走到这了");
        synchronized(obj) {  // 任何线程要执行一下代码 必须拿到obj这个对象的拥有权
            count ++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            System.out.println(Thread.currentThread().getName()+"---"+count);
        }
        // 这是一条线 synchronized 堵塞了这块也不会执行
        System.out.println(Thread.currentThread().getName()+"执行到这里了");
    }

    public synchronized static void myStatic() {  // 这里等同于 synchronized(T_004_synchronized.class)
        // 理解
        // 每一个class文件 load到内存的时候 都会生成一个class内的对象 和对应的class相对应
    }


    public synchronized void methodA() {
        System.out.println("methodA.....");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void methodB() {
        System.out.println(Thread.currentThread().getName()+"来了B");
        /*try{
            Thread.sleep(600);
        } catch (Exception e) {}*/
        synchronized(this) {
            System.out.println(Thread.currentThread().getName()+"--methodB.....");
        }
    }

    public void methodC() {
        System.out.println(Thread.currentThread().getName()+"来了C");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+"--methodC.....");
        }
    }
    public void methodD() {
        System.out.println(Thread.currentThread().getName()+"来了D");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+"--methodD.....");
        }
    }

    /*
    * 实际指向的是一个对象 Double Float 不是
    * */
    /*String*/ /*Integer*/ Long  s1 = 1L;
    /*String*/ /*Integer*/ Long  s2 = 1L;
    public void m1() {
        synchronized(s1) {
            try {
                Thread.sleep(1000);
            } catch (Exception e){}
            System.out.println("这是m1");
        }
    }

    public void m2() {
        synchronized(s2) {
            System.out.println("这是m2");
        }
    }




    public static void main(String[] args) {
        T_004_synchronized sync = new T_004_synchronized();
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                sync.sync();
            }).start();
        }*/
        /*new Thread(() ->{
            sync.methodA();
        }).start();
        new Thread(() ->{
            sync.methodB();
        }).start();
        new Thread(() ->{
            sync.methodC();
        }).start();
        new Thread(() ->{
            sync.methodD();
        }).start();*/
        new Thread(sync :: m1).start();
        new Thread(sync :: m2).start();

    }
}
