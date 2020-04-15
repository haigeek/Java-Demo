package com.haigeek.datademo;

/**
 * @author zhaohj
 * @date 2020-03-17 10:20 下午
 * 对于String的内容比较使用equals，不再验证 主要验证==
 */
public class StringDemo {
    public void test1(){
        String str1="hello";
        String str2="hello";
        System.out.println(str1==str2);// true str1和str2都指向hello在内存的地址
    }
    public void test2(){
        String str1="hello";
        String str2=new String("hello");
        String str3=str2;//引用传递
        System.out.println(str1==str2);//false 使用new之后会在新开辟一个地址进行存储
        System.out.println(str2==str3);//true 3也指向2开辟出的hello
        System.out.println(str1==str3);//false 1和3指向的是不同的地址
    }
    public void test3(){
        String s1 = "Hello";
        String s2 = new String("Hello");
        /*
          这个步骤是检查常量池是否存在字符串"Hello" 加入存在，返回池里的字符串
          不存在话，新建并把"Hello"添加到字符串池中，然后再返回它的引用。
         */
        s2 = s2.intern();
        System.out.println(s1 == s2);       //  true
    }

    public void test4(){
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
