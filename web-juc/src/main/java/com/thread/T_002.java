package com.thread;

/**
 *  线程中简单的方法
 *  sleep(休息)  yield(谦让一下)  join(让其他线程先执行完毕，执行完后再接着执行自己)
 * */
public class T_002 {

    static void testYield() {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                System.out.println("----a"+ i);
                if (i%10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                System.out.println("----b"+ i);
                if (i%10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    public static void testJoin() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                //System.out.println("------A"+i);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
            System.out.println("t1执行完了");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                //System.out.println("------B"+i);
                if (i == 10) {
                    try {
                        t1.join();
                    } catch (Exception e) {
                    }
                }
            }
            System.out.println("t2执行完了");
        });
        t2.start();
    }


    public static void main(String[] args) {
        //T_002.testYield();
        T_002.testJoin();
        System.out.println("主线程执行完了");
    }

}
