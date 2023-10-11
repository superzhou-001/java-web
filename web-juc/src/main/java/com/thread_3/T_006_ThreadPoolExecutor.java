package com.thread_3;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定义7个参数
 * 两个集合 线程集合
 *         任务集合
 * */
public class T_006_ThreadPoolExecutor {

   static public class  Task implements Runnable {

        private int i;
        public Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-Task-" + i);
            try {
                System.in.read(); // 阻塞
            } catch (Exception e) {}
        }
        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    "}";
        }

    }



    public static void main(String[] args) {
        // pool 运行规则 例：下面可执行8个任务 0 1 进入核心线程 2 3 4 5 进入任务队列等待 6 7 进入非核心线程执行
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4), // 根据不同的任务队列定义了不同的线程池
                Executors.defaultThreadFactory(), // 可自定义指定特定的线程  线程工厂  比如说 线程名字
                new ThreadPoolExecutor.DiscardPolicy()); // 拒绝策略 线程池忙 任务队列满
        // 核心线程数, 最大线程数， 归还线程到处理器时间， 归还单位，定义什么类型的线程池， ，拒绝策略:当线程超过最大线程数时怎么处理
        // 处理机制可以自定义： jdk 默认提供四种机制
        // AbortPolicy:抛异常
        // DiscardPolicy:不抛异常
        // DiscardOldestPolicy: 扔掉排队时间最久的
        // CallerRunsPolicy: 调用者处理该任务（注：如果是主线程中把任务扔给pool pool满了之后则该任务由main主线程运行该任务）

        for (int i = 0; i < 8; i++) {
            pool.execute(new Task(i));
        }

        System.out.println(pool.getQueue()); // 获取队列
        pool.execute(new Task(100));
        System.out.println(pool.getQueue()); // 获取队列

        pool.shutdown();

    }
}
