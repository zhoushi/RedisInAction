package com.zhou.cache;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;


/**
 * Created by zhou on 2017/7/9.
 */

public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    //redis连接池
    private static final Map<String,JedisPool> jedisPool = Maps.newIdentityHashMap();

    //
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(200);
        String host = "127.0.0.1";
        String password = "";
        JedisPool pool = new JedisPool(config,host,6379,3000,password);
        jedisPool.put("group",pool);
    }

    //get Value String
    public static String getValue(String key,String group){
        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = jedisPool.get(group);
            jedis = pool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("错误");
        }
        return jedis.get(key);
    }

    public static void setString(String key,String value){

    }
}
