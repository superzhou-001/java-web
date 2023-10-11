package com.thread_3;

import org.omg.CORBA.TIMEOUT;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 定时任务线程池
 * */
public class T_011_SchedulePool {

    public static void main(String[] args) {
        /*
         * 周期性任务
         * scheduleAtFixedRate 和 scheduleWithFixedDelay 循环延迟线程池
         *  区别： scheduleAtFixedRate 不会根据 线程执行内容 而延迟指定开始循环时间
         *  scheduleWithFixedDelay 会根据 线程执行内容 而延迟指定开始循环时间
         * */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        /*
        * 实例： 在分布式锁中--redis 给锁续命，锁的时间
        * */
        service.scheduleWithFixedDelay(() -> {

        },1000, 2000, TimeUnit.MICROSECONDS);

        service.scheduleAtFixedRate(() -> {

        }, 1000, 2000, TimeUnit.MICROSECONDS);


        /*
        * Timer 和 ScheduledExecutorService 区别
        * Timer 中 线程内容执行时出现异常， 则怎个线程将会挂掉 后面的Timer 则不会执行
        * ScheduledExecutorService 反之则不会, 当某个延迟循环任务异常后 不影响其他任务执行
        * 但是 异常任务得捕获
        * */
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000, 2000);


    }
}
