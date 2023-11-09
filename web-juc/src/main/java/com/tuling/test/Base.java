package com.tuling.test;

public class Base {
    private String baseName = "base";

    public Base() {
        callName();
    }

    public void callName() {
        System.out.println(baseName);
    }
    static class Sub extends Base
    {
        private String baseName = "sub";
        public void callName()
        {
            System. out. println ("----"+baseName) ;
        }
    }
    public static void main(String[] args)
    {
        //Base b = new Sub();
        int i=0;
        Integer j = new Integer("0");
        System.out.println(i==j);
        System.out.println(j.equals(i));
    }
}
