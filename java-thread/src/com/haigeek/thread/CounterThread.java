package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-25 23:37
 * 竞态条件demo
 */
public class CounterThread extends Thread{
//    计数不准确的demo
//    private static int counter=0;
//
//    @Override
//    public void run() {
//        for (int i=0;i<1000;i++){
//            counter++;
//        }
//    }

    Counter counter;
    public CounterThread(Counter counter){
        this.counter=counter;
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            counter.incr();
        }
    }

    public static void main(String [] args) throws InterruptedException{
        int num=1000;
        Counter counter=new Counter();
        Thread[] threads=new Thread[num];
        for (int i=0;i<num;i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }
        for (int i=0;i<num;i++){
            threads[i].join();
        }
        System.out.println(counter.getCount());
    }
}
