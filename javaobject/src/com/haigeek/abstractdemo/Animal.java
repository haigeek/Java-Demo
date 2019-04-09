package com.haigeek.abstractdemo;

/**
 * @author zhaohj
 * @date 2019/1/18 上午9:56
 */
public abstract class Animal {

    /**
     * cat的抽象方法,在子类必须实现
     */
    public abstract void cat();

    /**
     * dog的抽象方法
     */

    public abstract void dog();

    /**
     * 抽象类可以实现方法
     */
    public void eat(){
        System.out.println("animals need eat");
    }

}
