package com.designmode.singleton;
/**
 * 枚举单例 --可以防止返序列化
 * 原因 没有构造方法
 * */
public enum Single04 {
    INSTANCE;
    public void m() {}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Single04.INSTANCE.hashCode())).start();
        }
    }
}
