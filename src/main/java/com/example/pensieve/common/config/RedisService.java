package com.example.pensieve.common.config;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    // key를 통해 value 리턴
    public String getData(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setData(String key, String value) { stringRedisTemplate.opsForValue().set(key, value); }

    // 유효 시간 동안(key, value)저장
    public void setDataExpire(String key, String value, long duration) {
        stringRedisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
    }

    public void deleteData(String key) {
        stringRedisTemplate.delete(key);
    }
}
