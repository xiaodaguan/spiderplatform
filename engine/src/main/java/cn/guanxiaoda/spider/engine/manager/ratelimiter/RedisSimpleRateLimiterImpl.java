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
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    @Override
    public boolean acquire(String limitKey, double qps) {
//        if (qps < (1 / Const.Numbers.MINITE)) {
//            throw new RuntimeException("unsupport qps range:" + qps);
//        } else {
//            if (qps < 1) {
//                return acquire(limitKey, (int) (qps * 60), TimeUnit.MINUTES, 61);
//            } else {
//                return acquire(limitKey, (int) qps, TimeUnit.SECONDS, 2);
//            }
//        }
        String key = String.format("%s:%s", redisKey, limitKey);
        Long interval = new Double(1.0 / qps * 1000).longValue();
        Long expire = interval * 2;
        Long current = System.currentTimeMillis();


        return longRedisTemplate.execute(redisScript, Collections.singletonList(key), new String[]{String.valueOf(interval), String.valueOf(expire), String.valueOf(current)}).equals(1L);
    }

    /**
     * @param limitKey
     * @param limit
     * @param timeUnit
     * @param expire   unit: seconds
     * @return
     */
    private boolean acquire(String limitKey, int limit, TimeUnit timeUnit, int expire) {
        String key = null;

        if (timeUnit == TimeUnit.SECONDS) {
            key = String.format("%s:%s:%s", redisKey, limitKey, LocalDateTime.now().format(dtf));
        } else if (timeUnit == TimeUnit.MINUTES) {
            key = String.format("%s:%s:%s", redisKey, limitKey, LocalDateTime.now().format(dtf1));
        }
        // todo 从服务器获取时钟
        // 1秒1个key
        List<String> keys = Collections.singletonList(key);
        String[] args = new String[]{String.valueOf(limit), String.valueOf(expire)};
        return longRedisTemplate.execute(redisScript, keys, args).equals(1L);
    }

}
