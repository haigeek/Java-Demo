package com.haigeek.rpc;

import com.haigeek.rpc.proxy.RPCProxyClient;
import com.haigeek.rpc.service.HelloService;
import com.haigeek.rpc.service.impl.HelloServiceImpl;

/**
 * @author zhaohj
 * @date 2019/5/13 上午11:22
 */
public class test {

    public static void local(){

        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello("test");
    }

    public static void rpc(){
        HelloService helloService= (HelloService) RPCProxyClient.getProxy(HelloService.class);
        helloService.sayHello("rpc");
    }

    public static void main(String [] args){
        local();
    }}
