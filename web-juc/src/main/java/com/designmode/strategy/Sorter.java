package com.designmode.strategy;

/**
 * 要干什么事 ---比较大小
 * comparator 自己写的比较器
 * */
public class Sorter<T> {

    /**
     * 冒泡排序
     * t1 内容
     * comparator 方式（策略）
     * */                                                                                                                                         
    public void sort(T[] t, Comparator<T> comparator) {
        for (int i = 0; i < t.length-1; i++) {
            int min = i;
            for (int j = i + 1; j < t.length; j++) {
                // 这里具体实现是实现子类的方法
                min = comparator.compare(t[min], t[j]) == -1 ? min : j;
            }
            // 交换
            swap(t, min, i);
        }
    }

    private void swap(T[] t, int min, int i) {
        T temp = t[i];
        t[i] = t[min];
        t[min] = temp;
    }
}
