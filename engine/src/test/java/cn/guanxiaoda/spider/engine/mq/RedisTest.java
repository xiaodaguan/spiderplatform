package cn.guanxiaoda.spider.engine.mq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

/**
 * Created by guanxiaoda on 2018/1/12.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> listRedisTemplate;

    @Test
    public void stringTemplateTest() {
        stringRedisTemplate.opsForValue().set("test", "gxd");
        String res = stringRedisTemplate.opsForValue().get("test");
        System.out.println(res);
        Assert.assertEquals("gxd", res);
    }


    @Test
    public void listTemplatePushTest() {
        IntStream.range(0, 10).forEach(i -> listRedisTemplate.opsForList().leftPush("mq_test", "msg" + i));
    }

    @Test
    public void listPopTest() {
        IntStream.range(0, 10).forEach(i-> System.out.println(listRedisTemplate.opsForList().rightPop("mq_test")));
    }
}
