package com.thread;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段， 按照不同的阶段控制线程
 * 用到 遗传算法
 *
 * 小例子： 结婚现场 分阶段进行
 * Phaser 不仅可以控制栅栏数量，还能控制线程数量 ---也是一种线程同步工具
 * */
public class T_011_Phaser {
    static Random r = new Random(); //生成随机数
    static Wedding wedding = new Wedding();

    public static void mill(long m) {
        try {
            TimeUnit.MILLISECONDS.sleep(m);
        } catch (Exception e){}
    }


    static class Person extends Thread {
        String name;
        public Person(String name){
            this.name = name;
        }

        public void arrive() {
            mill(r.nextInt(1000));
            System.out.println(name+"到达了现场");
            wedding.arriveAndAwaitAdvance(); // 线程到达了之后等待其他线程后再一起进入下一个阶段 --- 到达等待前进
        }
        public void eat() {
            mill(r.nextInt(1000));
            System.out.println(name+"开始吃饭");
            wedding.arriveAndAwaitAdvance();
        }

        public void leave() {
            mill(r.nextInt(1000));
            System.out.println(name+"离开了");
            wedding.arriveAndAwaitAdvance();
        }

        public void hug() {
            mill(r.nextInt(1000));
            if ("新郎".equals(name) || "新娘".equals(name)) {
                System.out.println(name+"到达了洞房");
                wedding.arriveAndAwaitAdvance();
            } else {
                wedding.arriveAndDeregister(); //注销了
                //wedding.register(); // 加入一个线程
            }
        }

        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }



    static class Wedding extends Phaser {
        /**
         * onAdvance 前进
         * 当线程到全 栅栏自动推到，自动调用这个方法
         * 阶段写死的 必须从0开始
         * @param phase 阶段 从0开始
         * @param registeredParties 线程数
         * */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0 :
                    System.out.println("所有人都到齐了---" + registeredParties);
                    return false;
                case 1 :
                    System.out.println("所有人都吃完饭了---" + registeredParties);
                    return false;
                case 2 :
                    System.out.println("所有人都离开了---" + registeredParties);
                    return false;
                case 3 :
                    System.out.println("新郎新娘已经进被窝了---" + registeredParties);
                    return true; // 栅栏组都执行完了
                default:
                    return true;
            }
        }
    }

    public static void main(String[] args) {
        // 控制栅栏中线程的数量
        wedding.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("t"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }
}
