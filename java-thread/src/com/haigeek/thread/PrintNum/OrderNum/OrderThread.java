package com.haigeek.thread.PrintNum.OrderNum;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-04 10:37
 * @desc
 */
public class OrderThread extends Thread{

    private LockObject lockObject;

    private int printNum=0;

    public OrderThread(LockObject lockObject,int printNum){
        this.lockObject=lockObject;
        this.printNum = printNum;
    }

    @Override
    public void run(){
        synchronized (lockObject){
            while (lockObject.orderNum<= LockObject.maxValue){
                if (lockObject.orderNum==printNum){
                    System.out.println(printNum);
                    lockObject.orderNum++;
                    if (printNum==10){
                        System.out.println("线程打印完毕");
                    }
                    //打印完毕后，唤醒所有的线程【必须唤醒】
                    lockObject.notifyAll();
                }else {
                    try {
                        //不是该线程打印的数字，需要继续等待
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
