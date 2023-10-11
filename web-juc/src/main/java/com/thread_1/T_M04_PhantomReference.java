package com.thread_1;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 * 管理堆外内存使用 手动回收堆外内存
 * NIO 里面 DirectByteBuffer 直接内存 是不被JVM管理的 直接被操作系统管理
 *
 * unsafe中 allocateMemory 直接分配内存
 * unsafe中 freeMemory 回收内存
 * */
public class T_M04_PhantomReference {

    private static final List<Object> list = new LinkedList<>();
    private static final ReferenceQueue queue = new ReferenceQueue(); // 虚引用一旦回收 会被加入到该队列中 相当于一个通知

    public static void main(String[] args) {
        // 必须有两个参数
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), queue);
        new Thread(() -> {
            for (;;) {
                list.add(new byte[1024*1024*10]);
                System.out.println(list.get(0));
            }
        }).start();
    }

}
