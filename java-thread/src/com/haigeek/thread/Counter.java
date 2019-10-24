package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-26 22:35
 * 使用synchronized修饰的计数类
 */
public class Counter {
    /*
    多个线程可以同时操作一个synchronized对象的，只要他们访问的对象是不同的即可

    实际上保护的是同一个对象的方法调用，保护的是当前实例对象，即this，this对象有一个锁和一个等待队列
    锁只能被一个对象持有


    synchronized实例方法的大致过程如下
    1、尝试获得锁，如果可以获得锁，继续执行，否则加入等待队列，阻塞并等待唤醒
    2、执行方法体实例
    3、释放锁。如果等待队列上有等待的线程，从中取一个并唤醒，如果有多个，唤醒哪个是随机的，不能保证公平

    当线程不能获得锁的时候，他会加入等待队列等待，线程的状态会变成BLOCKED
     */
    private int count;

    public synchronized void incr(){
        count++;
    }
    public synchronized int getCount(){
        return count;
    }
}
