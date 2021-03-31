package com.haigeek.thread.PrintNum.join;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-04 10:12
 * @desc
 */
public class TestThread {
    public static void main(String [] args) throws InterruptedException {
        MyThread myThread=new MyThread("子线程");
        myThread.start();
        //主线程调用了子线程的join方法，会等子线程执行完之后才执行主线程
        myThread.join();
        for(int i=0;i<=20;i++){
            System.out.println("主线程" + ":" + i);
        }
    }
}
