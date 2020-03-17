package com.haigeek.interfacedemo;

/**
 * @author zhaohj
 * @date 2020-03-17 10:15 下午
 */
public class BirdImpl implements Bird {
    @Override
    public void fly() {
        System.out.println("fly is extend by interface FlyAnimal");
    }
}
