package com.haigeek.thread.PrintNum.OrderNum;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-04 10:30
 * @desc
 */
public class LockObject {
    final static int maxValue=9;
    int orderNum=0;

    public LockObject(int orderNum) {
        this.orderNum = orderNum;
    }
}
