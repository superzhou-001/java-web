package com.thread;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 交换器 只能是两个线程之间
 * 线程之间交换 通信
 * 场景理解： dnf中 好友之间武器交换
 * */
public class T_014_Exchanger {

    // 相当于一个容器
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() ->{
            String s = "t1";
            try {
                s = exchanger.exchange(s); // 交换，这是个阻塞方法
                System.out.println("这是是t1,输出来的是---"+ s);
            } catch (Exception e) {}
        }).start();
        new Thread(() ->{
            String s = "t2";
            try {
                s = exchanger.exchange(s);
                System.out.println("这是是t2,输出来的是---"+ s);
            } catch (Exception e) {}
        }).start();

    }
}
