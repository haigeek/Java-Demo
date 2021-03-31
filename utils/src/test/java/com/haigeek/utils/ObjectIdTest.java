package com.haigeek.utils;

import org.junit.Test;

/**
 * @author zhaohj
 * @date 2020-06-25 17:46
 */
public class ObjectIdTest {
    @Test
    public void getObjectId(){
        for (int i=0;i<1;i++){
            System.out.println(ObjectId.next());
        }
    }

    @Test
    public void test(){
        int code=-1922433024>>16;
        System.out.println(code);
        int code1=-29334<<16;
        System.out.println(code1);
        int code3=124<<16;
        System.out.println(code3);
        long a=1409584490;
        long code4 =a<<16;
        System.out.println(code4);
    }

}
