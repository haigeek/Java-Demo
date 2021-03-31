package com.haigeek.thread.PrintNum.OrderNum;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-04 10:46
 * @desc
 */
public class OrderThreadTest {
    public static void main(String [] args){
        LockObject lockObject=new LockObject(0);
        OrderThread[] orderThreads=new OrderThread[10];
        for (int i=0;i<10;i++){
            orderThreads[i]=new OrderThread(lockObject,i);
            orderThreads[i].start();
        }
    }
}
