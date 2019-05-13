package com.haigeek.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhaohj
 * @date 2019/5/13 上午11:25
 */
public class RPCProxyClient implements InvocationHandler {

    private Object obj;

    public RPCProxyClient(Object obj) {
        this.obj = obj;
    }

    /**
     * 得到被代理的对象
     * @param obj
     * @return
     */
    public static Object getProxy(Object obj){
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new RPCProxyClient(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result=new Object();
        //执行通信等相关逻辑
        return result;
    }
}
