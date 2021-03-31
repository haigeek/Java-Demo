package com.haigeek.abstractdemo;

/**
 * @author zhaohj
 * @date 2019/1/18 上午9:58
 * cat属于动物 但是也有自己的属性
 */
public class Cat extends Animal{


    @Override
    public void sleep() {
        System.out.println("cat need sleep");

    }
    @Override
    public void eat(){
        super.eat();
    }

    //猫咪喜欢玩，或许不是所有动物都喜欢玩耍
    public void play(){
        System.out.println("cat also like play");
    }

}
