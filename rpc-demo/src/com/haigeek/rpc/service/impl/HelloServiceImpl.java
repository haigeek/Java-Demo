package com.haigeek.rpc.service.impl;

import com.haigeek.rpc.service.HelloService;

/**
 * @author zhaohj
 * @date 2019/5/13 上午11:21
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        String result = "hello world " + msg;
        System.out.println(result);
        return result;
    }
}
