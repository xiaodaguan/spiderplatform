package cn.guanxiaoda.spider.engine.manager.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/17.
 */
@Slf4j
@Component
@ConfigurationProperties
public class RedisSimpleRateLimiterImpl implements SimpleRateLimiter {

    @Autowired
    RedisTemplate<String, Long> longRedisTemplate;

    @Value("${ratelimiter.key}")
    String redisKey = "redis_rl_key";
    @Autowired
    RedisScript<Long> redisScript;

    @Override
    public boolean acquire(String limitKey, double qps) {
        String key = String.format("%s:%s", redisKey, limitKey);
        Long interval = new Double(1.0 / qps * 1000).longValue();
        Long expire = interval * 2;
        Long current = System.currentTimeMillis();


        return longRedisTemplate.execute(redisScript, Collections.singletonList(key), new String[]{String.valueOf(interval), String.valueOf(expire), String.valueOf(current)}).equals(1L);
    }

}
