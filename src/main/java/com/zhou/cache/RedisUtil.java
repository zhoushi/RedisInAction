package com.zhou.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by zhou on 2017/7/9.
 */
@Configuration
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getValue(String key){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();

        boolean haskey = redisTemplate.hasKey(key);
        if (haskey){
            return operations.get(key);
        }
        return null;
    }

}
