package com.thread;

/**
 * volatile
 *   保证线程可见性
 *      - MESI
 *      - 缓存一致性
 *   禁止指令重排序（cup级别） --- 添加内存屏障
 *      - DCL（double check lock）单例
 * 每个线程都共享一个内存flag，但是每个线程都会开辟一个空间flag(副本), 则线程之间是不可见的
 *
 * JVM做的
 * new一个对象 分三步 正常顺序 1、2、3
 *    1、 申请一块内存 int a = 0 （赋初始值）
 *    2、 赋值 a = 8 （设置成真正的值）
 *    3、 （对象）站内存 地址指向这块内存
 * 超高并发出现 （京东淘宝秒杀）
 * 问题: 第三步有可能和第二步交换
 *      半初始化
 * */
public class T_005_volatile {

    // 加volatile
    private static volatile T_005_volatile t_005 = null;
    /*
    * 单例模式
    * 双重判断 double check
    * */
    public static T_005_volatile getInstance() {
        if (t_005 == null) {
            synchronized (T_005_volatile.class) {
                if (t_005 == null) {
                    t_005 = new T_005_volatile();
                }
            }
        }
        return t_005;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(T_005_volatile.getInstance().hashCode());
            }).start();
        }
    }

}
