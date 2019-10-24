package com.haigeek.java8;

import com.haigeek.java8.entity.User;

import java.util.*;

/**
 * @author zhaohj
 * @date 2019/5/14 下午1:33
 */
public class lambda {
    public static void sort(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //使用工具方法进行排序
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        names.stream().forEach(System.out::println);
        //使用lambda表达式
        Collections.sort(names,(String a,String b)->{
            return b.compareTo(a);
        });
        //更优雅的写法,编译器可以自动识别参数的类型
        Collections.sort(names,(a,b)->b.compareTo(a));
        names.stream().forEach(System.out::println);
    }

    //方法和构造函数引用
    public static void method(){

    }
    public static void main(String[] args){
        
        sort();

    }
}
