package com.haigeek;

import com.haigeek.abstractdemo.Cat;
import com.haigeek.datademo.StringDemo;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Cat cat = new Cat();
        cat.eat();

        System.out.println("String demo result");
        StringDemo stringDemo=new StringDemo();
        stringDemo.test1();
        stringDemo.test2();


    }


}
