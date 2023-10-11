package com.designmode.strategy;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        Cat[] cat = {new Cat(4,4), new Cat(3,3), new Cat(2, 2)};
        Sorter<Cat> sorter = new Sorter();
        sorter.sort(cat, new CatWidthComparator());
        System.out.println(Arrays.toString(cat));
    }
}
