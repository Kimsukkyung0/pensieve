package com.example.pensieve.common.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.data.redis.core.StringRedisTemplate;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class RedisService {
    private final StringRedisTemplate strRedisTemplate;

    // key를 통해 value 리턴
    public String getData(String key) {
        return strRedisTemplate.opsForValue().get(key);
    }

    public void setData(String key, String value) { strRedisTemplate.opsForValue().set(key, value);
    }

    // 유효 시간 동안(key, value)저장
    public void setDataExpire(String key, String value, long duration) {
        strRedisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
    }

    public void deleteData(String key) {
        strRedisTemplate.delete(key);
    }

    //////////////////////////////////////메일///////////////////////////////////////

    public void setMailVerificationData(String key, String authCode, Duration duration) {
        strRedisTemplate.opsForValue().set(key, authCode, duration);
    }

    public boolean checkExistValue(String redisAuthCode){
        log.info("redisAuthCode {}",redisAuthCode);
        boolean yn = strRedisTemplate.hasKey(redisAuthCode);
        log.info("존재하는가 ? ",yn);
        return yn;

    }

}

