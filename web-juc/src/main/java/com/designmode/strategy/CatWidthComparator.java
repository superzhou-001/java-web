package com.designmode.strategy;

public class CatWidthComparator implements Comparator<Cat>{
    /**
     * 重写了父类的方法---调用时引用调用自己的 多态
     * */
    @Override
    public int compare(Cat t1, Cat t2) {
        if (t1.getWidth() < t2.getWidth()) return -1;
        else if (t1.getWidth() > t2.getWidth()) return 1;
        else return 0;
    }
}
