package com.thread_3;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture 各种任务的管理类
 * 场景：不同的任务去获得结果
 *      然后组织起来显示  allOf
 * 取一个 anyOf
 *
 * */
public class T_005_CompletableFuture {

    public static void main(String[] args) {
        CompletableFuture<Double> futureTm = CompletableFuture.supplyAsync(() ->{
           return 1d;
        });
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() ->{
            return 3d;
        });
        CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(() ->{
            return 4d;
        });
        // 组合
        CompletableFuture.allOf(futureTm, future2, future3).join();

    }
}
