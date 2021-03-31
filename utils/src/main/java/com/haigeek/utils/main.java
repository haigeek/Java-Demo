package com.haigeek.utils;

import com.alibaba.fastjson.JSON;
import com.haigeek.utils.fastjson.TestObject;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-20 12:12
 * @desc
 */
public class main {
    public static void main(String[] args) {
        TestObject testObject = new TestObject("1", "1@qq.com");
        //序列化
        System.out.println(JSON.toJSONString(testObject, TestObject.serializeFilters));
    }
}
