package com.thread_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 非线程安全转为线程安全
 * */
public class T_R05_02_SynchronizedList {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        List<String> strSyn = Collections.synchronizedList(str);
    }

}
