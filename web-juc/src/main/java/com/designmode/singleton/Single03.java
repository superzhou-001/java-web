package com.designmode.singleton;

/**
 * 静态内部内方式
 * JVM保证单例
 * */
public class Single03 {

    private Single03() {}

    public static class Single03Holder {
        public static Single03 INSTANCE = new Single03();
    }

    public static Single03 getInstance() {
        return Single03Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Single03.getInstance().hashCode())).start();
        }
    }
}
