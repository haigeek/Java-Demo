package com.haigeek.utils.fastjson;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

import java.io.Serializable;

/**
 * @author zhaohj@dist.com.cn
 * @date 2020-08-20 12:07
 * @desc
 */
public class TestObject implements Serializable {

    public static SerializeFilter[] serializeFilters = new SerializeFilter[]{
            new PropertyFilter() {
                public boolean apply(Object o, String s, Object o1) {
                    return false;
                }
            },
           new AfterFilter() {
               @Override
               public void writeAfter(Object o) {
                writeKeyValue(TestObject.class.getName(),o.toString());
               }
           }
    };
    private final String id;
    private final String email;

    public TestObject(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}