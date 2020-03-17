package com.haigeek.interfacedemo;

import com.haigeek.abstractdemo.Animal;

/**
 * @author zhaohj
 * @date 2020-03-17 10:10 下午
 * 当想描述一个对象 既有动物的基本属性（eat、sleep）又有自己的飞行方式的时候
 * 先继承于抽象类
 * 再实现飞行接口
 */
public class FlyAnimalImpl extends Animal implements FlyAnimal {
    @Override
    public void sleep() {
        System.out.println("FlyAnimal also need sleep");
    }

    @Override
    public void fly() {
        System.out.println("not all animal can fly");
    }
}
