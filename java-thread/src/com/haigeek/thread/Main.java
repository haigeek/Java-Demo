package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-25 22:49
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new HelloThread();

        /*
        调用thread方法，run方法执行，start表示启动线程，使之成为一条单独的执行流
        一条执行run方法，一条继续执行main方法
        thread name：Thread-0
        hello
         */
        thread.start();
        //当前线程睡眠指定的时间
        Thread.sleep(100);
        //使主线程等待该线程执行完毕再结束
        thread.join();
        /*
        run方法是main方法的一个普通方法
        thread name：main
        hello
         */
        thread.run();

        //仅仅实现Runnable是不够的 要启动线程，还需要一个创建一个Thread对象
        Thread helloThread=new Thread(new HelloRunnable());
        helloThread.start();
    }
}
