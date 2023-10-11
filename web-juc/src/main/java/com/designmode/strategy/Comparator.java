package com.designmode.strategy;

/**
 * 重写比较器
 * */
public interface Comparator<T> {
    int compare(T t1, T t2);
}
