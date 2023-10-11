package com.thread_2;


import java.util.PriorityQueue;

/**
 *  Priority 优先级
 *  底层实现 堆排序的二叉树树
 *
 * */
public class T_R09_01_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("C");
        queue.add("e");
        queue.add("q");
        queue.add("d");
        queue.add("z");
        int length = queue.size();
        for (int i = 0; i < length; i++) {
            System.out.println(queue.poll()); // poll 取并删除
        }
    }

}
