package com.zhou.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class GuavaList {

    /**
     * guava中的Join and
     * @return
     */
    private String listJoin(){

        List<String> names = Lists.newArrayList("zhou","shi","xian");
        String userName = Joiner.on(",").join(names);
        return userName;
    }
}
