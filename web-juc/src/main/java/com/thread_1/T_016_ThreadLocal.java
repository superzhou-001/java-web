package com.thread_1;

import java.util.concurrent.TimeUnit;

/**
 *
 * ThreadLocal而是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据
 * ThreadLocal特性
 * ThreadLocal和Synchronized都是为了解决多线程中相同变量的访问冲突问题，不同的点是
 *
 * Synchronized是通过线程等待，牺牲时间来解决访问冲突
 * ThreadLocal是通过每个线程单独一份存储空间，牺牲空间来解决冲突，
 * 并且相比于Synchronized，ThreadLocal具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问到想要的值。
 * 正因为ThreadLocal的线程隔离特性，使他的应用场景相对来说更为特殊一些。
 * 在android中Looper、ActivityThread以及AMS中都用到了ThreadLocal。
 * 当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal。
 *
 * 作者：ingxin
 * 链接：https://www.jianshu.com/p/3c5d7f09dfbd
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * */
public class T_016_ThreadLocal {

    static ThreadLocal<Person> local = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {}
            local.set(new Person()); // set之后 get不到
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {}
            System.out.println(local.get());
        }).start();

    }

    static class Person{
        String name = "张三";
    }

}
