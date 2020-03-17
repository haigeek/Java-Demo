package com.haigeek.datademo;

/**
 * @author zhaohj
 * @date 2020-03-17 10:20 下午
 */
public class StringDemo {
    public void test1(){
        int a;
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
