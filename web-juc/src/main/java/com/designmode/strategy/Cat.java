package com.designmode.strategy;

public class Cat {
    private int width;
    private int length;

    public Cat(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
