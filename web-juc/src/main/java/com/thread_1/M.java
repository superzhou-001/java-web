package com.thread_1;

public class M {
    @Override
    protected void finalize() throws Throwable {
        /*
        * 监控GC什么时候回收
        * */
        System.out.println("finalize");
    }
}
