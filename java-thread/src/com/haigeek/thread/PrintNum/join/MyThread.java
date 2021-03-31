package com.haigeek.thread.PrintNum.join;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-04 10:09
 * @desc
 */
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<=20;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}
