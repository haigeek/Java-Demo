package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-25 22:48
 */
public class HelloThread extends Thread {
    @Override
    public void run() {
        //每个线程都有一个id和name
        System.out.println("thread name："+Thread.currentThread().getName());
        System.out.println("thread id："+Thread.currentThread().getId());
        //线程的优先级
        System.out.println("thread Priority："+Thread.currentThread().getPriority());
        /*
         * 线程的状态
         * NEW：没有被调用的状态
         * RUNNABLE：调用start后线程在执行run方法且没有阻塞
         * BLOCKED：被阻塞
         * WAITING：被阻塞
         * TIMED_WAITING：被阻塞
         * TIMEWAITED：线程运行结束状态
         */
        System.out.println("thread state："+Thread.currentThread().getState());
        //主进程的辅助进程，主要负责线程执行之后的垃圾回收
        System.out.println("thread state："+Thread.currentThread().isDaemon());
        System.out.println("hello");
    }
}
