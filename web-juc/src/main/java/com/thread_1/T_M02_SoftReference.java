package com.thread_1;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用 --- 只有系统堆内存不够了之后才会被回收
 * 作用：做缓存用
 * */
public class T_M02_SoftReference {

    public static void main(String[] args) throws Exception {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]); //10m数组

        System.out.println(m.get());
        System.gc();// 回收

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println(m.get()); // 如果已经被回收 打印null
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get()); // 第三次回收了


    }
}
