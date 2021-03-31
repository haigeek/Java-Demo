package com.haigeek.interfacedemo;

/**
 * @author zhaohj
 * @date 2020-03-17 10:14 下午
 * 假如只是想关注飞翔这个行为的时候，可以只是继承飞翔的接口，并赋予其他行为，
 */
public interface Bird extends FlyAnimal{
    void otherAction();
}
