package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-25 22:59
 */
public class HelloRunnable implements Runnable{
    /**
    java只能单继承，当一个类已经有父类，就不能再继承thread方法，这时可以使用Runnable
     */
    @Override
    public void run() {
        System.out.println("hello");
    }

}
