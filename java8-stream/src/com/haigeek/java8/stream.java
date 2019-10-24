package com.haigeek.java8;

import com.haigeek.java8.entity.Person;
import sun.jvm.hotspot.HelloWorld;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhaohj
 * @date 2019/5/13 下午3:27
 */
public class stream {

    static List<Person> persons =
            Arrays.asList(
                    new Person("Max", 18),
                    new Person("Peter", 23),
                    new Person("Pamela", 23),
                    new Person("David", 12));

    public static void filter(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //使用filter移除空元素,并使用Collectors转换为list
        List<String> filtered=strings.stream().filter(string->!string.isEmpty()).collect(Collectors.toList());
        //使用filter移除空元素,并使用Collectors将字符串合并
        String mergeString=filtered.stream().filter(string->!string.isEmpty()).collect(Collectors.joining());
        filtered.stream().findFirst().ifPresent(System.out::println);
        System.out.println(filtered);
        System.out.println(mergeString);
    }

    public static void find(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered=strings.stream().filter(string->!string.isEmpty()).collect(Collectors.toList());
        filtered.stream().findFirst().ifPresent(System.out::println);
        //可以不创建集合，直接创建数据流
        Stream.of("abc", "", "bc", "efg", "abcd","", "jkl").findFirst().ifPresent(System.out::println);
    }

    //使用stream提供的forEach来迭代流中的每一个数据
    public static void forEach(){
        Random random=new Random();
        //使用limit获取指定数量的流
        random.ints().limit(10).forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings.stream().forEach(System.out::println);
        //使用intStream代替普通的for循环
        IntStream.range(1,4).forEach(System.out::println);
    }

    public static void map(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取每个值对应的平方数
        List<Integer> squaresList=numbers.stream().map(i->i*i).distinct().collect(Collectors.toList());
        squaresList.stream().forEach(System.out::println);
        //转换数据类型
        Stream.of("a1", "a2", "a3")
                .map(s->s.substring(1))
                .mapToInt(Integer::parseInt).max()
                .ifPresent(System.out::println);
    }

    public static void sort(){
        Random random=new Random();
        //排序
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    public static void parellel(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        Long count=strings.parallelStream().filter(string->!string.isEmpty()).count();
        System.out.println(count);
    }

    public static void analysic(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats=numbers.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    public static void group(){

        //根据age进行分组查询
        Map<Integer,List<Person>> persionByAge=persons.stream()
                .collect(Collectors.groupingBy(p->p.age));
        persionByAge.forEach((age,p)->System.out.format("age %s: %s \n",age,p));
    }

    //将流的元素组合为单一结果
    public static void reduce(){
        //计算年龄最大的人
        persons
                .stream()
                .reduce((p1,p2)->p1.age>p2.age?p1:p2)
                .ifPresent(System.out::println);
        Person result=
                persons
                .stream()
                .reduce(new Person("",0),(p1,p2)->{
                    p1.age+=p2.age;
                    p1.name+=p2.name;
                    return p1;
                });
        System.out.format("mame=%s;age=%s",result.name,result.age);
    }


    public static void main(String[] args){
//        filter();
//        forEach();
//        map();
//        sort();
//        parellel();
//        analysic();
//        find();
//        group();
        reduce();
    }



}
