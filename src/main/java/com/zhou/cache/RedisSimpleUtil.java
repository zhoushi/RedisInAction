package com.zhou.cache;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/7/27.
 */
public class RedisSimpleUtil {

    public static final Jedis jedis = new Jedis("127.0.0.1",6379);
    public static String getValue(String key){
        return jedis.get(key);
    }
}
