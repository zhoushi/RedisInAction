package com.zhou.guava;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class JavaException {

    //java8的异常处理机制 ， 在流中进行往外抛出
    public static void main(String[] args){
        List<String> list = Lists.newArrayList("zhou","shi","xian");
        List<String> lists = Lists.newArrayList();

        list.stream().forEach(s -> {
            s = StringUtils.upTo(s);
            lists.add(s);
        });

//        list.stream().forEach(s -> {
//            s.toUpperCase();
//        });

        System.out.println(lists.toString());
    }
}
