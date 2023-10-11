package com.designmode.singleton;

/**
 *  单例模式 --简单写法 (饿汉式)
 *  类加载到内存后，就实力化一个单例，JVM保证线程安全
 *  简单实用 ， 推荐
 *  唯一缺点: 不管用到与否，类装载时就完成实例化
 *  （意思就是，不管你用不用都会加载）
 *  class.forName("")
 *  另外一种 看single02  double check 方式
 *
 * spring中的bean 取就是一个单例
 * */
public class Single01 {
    //final 引用不可更改
    private static final Single01 instance = new Single01();

    private Single01(){}

    public static Single01 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Single01.getInstance().hashCode());
            }).start();
        }
    }
}
