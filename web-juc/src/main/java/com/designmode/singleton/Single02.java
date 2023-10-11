package com.designmode.singleton;
/**
 * 单例模式 懒汉式
 * double check
 * volatile 防止指令重排序
 * 对象生成三步
 * 1、分配内存空间
 * 2、创建实例对象
 * 3、把这个内存地址赋值给变量的引用
 * 可能因为指定优化的原因 出现先执行1和3，最后才执行2 会导致对象为空
 *
 * 反射方式能暴力破解--解决创建一个无参的构造方法
 * */
public class Single02 {

    private static volatile Single02 instance;

    // 做用使这个类new不出来
    private Single02(){}

    public static Single02 getInstance() {
        if (instance == null) {
            synchronized (Single02.class) {
                if (instance == null) {
                    instance = new Single02();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Single02.getInstance().hashCode());
            }).start();
        }
    }

}
