package com.thread_1;

import java.lang.ref.WeakReference;

/**
 * 弱引用  ---遇见GC就会被回收
 * 作用：如果有一个强引用指向弱引用的时候 强引用消失 弱引用直接被GC回收
 * ThreadLocal 使用
 * */
public class T_M03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> local = new ThreadLocal<>(); // 强引用

        /*
            Thread t = Thread.currentThread();
            ThreadLocalMap map = getMap(t);
            if (map != null) {
                map.set(this, value); // this是 TheadLocal对象
            } else {
                createMap(t, value);
            }

        * */
        local.set(new M()); // key 指向 ThreadLocal   Entry 继承 WeakReference  key 通过弱引用指向 ThreadLocal
        local.remove(); // key 为null后 ThreadLocalMap map一直存在
                        // 只是key为null value还在 依然会内存不被回收导致内存泄漏
                        // 所以TheadLocal使用 手动remove
     }
}
