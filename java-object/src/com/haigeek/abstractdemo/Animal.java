package com.haigeek.abstractdemo;

/**
 * @author zhaohj
 * @date 2019/1/18 上午9:56
 * 抽象类是对属性的抽象，例如动物都需要eat和sleep
 */
public abstract class Animal {

    /**
     * sleep的抽象方法
     */

    public abstract void sleep();

    /**
     * 抽象类可以实现方法
     */
    public void eat(){
        System.out.println("animals need eat");
    }

}
