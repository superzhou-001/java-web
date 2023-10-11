//package com.thread;
//
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//
///**
// *  1.9以后才有
// *  VarHandle 一个引用 具体实现是c和c++
// *  特点：1、普通属性也能成为原子操作 2、比反射快 因为是直接操作的二进制码
// *
// * */
//public class T_MS004_HelloVarHandle {
//
//    Integer x = 8;
//
//    private static VarHandle varHandle;
//
//    static {
//        try {
//            // MethodHandles.lookup() 固定写法
//            varHandle = MethodHandles.lookup().findVarHandle(T_MS004_HelloVarHandle.class, "x", Integer.class);
//        } catch (NoSuchFieldException e) {
//            e.getStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        // 作用  可以通过varHandle 对象直接操作T_MS004_HelloVarHandle 中的x的值
//        T_MS004_HelloVarHandle t = new T_MS004_HelloVarHandle();
//        System.out.println(varHandle.get(t));
//
//        varHandle.set(t, 9);
//        System.out.println(t.x);
//
//        varHandle.compareAndSet(t, 9, 10); //同过cas操作将9变更为10 原子性
//        System.out.println(t.x);
//
//        varHandle.getAndAdd(t, 10); // getAndAdd 也是原子性的
//        System.out.println(t.x);
//
//    }
//
//}
