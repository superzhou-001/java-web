package com.thread_1;

import java.io.IOException;

/**
 * 强引用
 * */
public class T_M01_NormalReference {

    public void m() {
        M m = new M();
    }

    public static void main(String[] args) throws IOException {
        T_M01_NormalReference a = new T_M01_NormalReference();
        M m = new M();
        m = null; // 如果不把m 设置为null 则GC不会回收
        //a.m(); // 方法执行完 强引用断掉 gc可以直接回收
        System.gc(); // 输出了finalize 说明m被回收了

        System.in.read(); // 阻塞当前线程
    }
}
