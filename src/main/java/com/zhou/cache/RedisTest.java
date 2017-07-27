package com.zhou.cache;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/7/27.
 */
public class RedisTest {

    @Test
    public void getValue(){

        String s = RedisUtil.getValue("zhou","group");
        System.out.println(s);
    }
}
