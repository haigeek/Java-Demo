package com.haigeek.thread;

/**
 * @author zhaohj
 * @date 2019-06-26 23:38
 */
public class StaticCounter {
    /**
     * 对静态方法保护的是当前类，即StaticCounter.class
     */
    private static int count;
    public static synchronized void incr(){
        count++;
    }
    public static synchronized int getCount(){
        return count;
    }
}
