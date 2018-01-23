package cn.guanxiaoda.spider.scheduler;

import cn.guanxiaoda.spider.scheduler.ratelimiter.RedisSimpleRateLimiterImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by guanxiaoda on 2018/1/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RateLimiterTest {


    @Autowired
    RedisSimpleRateLimiterImpl rateLimiter;

    @Test
    public void test() {
        log.info("before");
        IntStream.range(0, 1000).forEach(i -> {
            if (rateLimiter.acquire("test", 2)) {
                log.info("acquire,i={}", i);
            } else {
//                log.info("i={}", i);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("after");
    }
}
