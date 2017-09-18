package com.fndsoft.service;

import redis.clients.jedis.Jedis;

/**
 * Created by feibabm on 2017/9/13 0013.
 */
public interface RedisService {

    Jedis getResource();

    void returnResource(Jedis jedis);

    void set(String key, String value);

    String get(String key);

    long incr(String key);

    long decr(String key);
}
