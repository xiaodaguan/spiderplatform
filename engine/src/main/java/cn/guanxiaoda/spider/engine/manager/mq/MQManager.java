package cn.guanxiaoda.spider.engine.manager.mq;

import cn.guanxiaoda.spider.core.item.Task;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by guanxiaoda on 2018/1/12.
 */
@Service
@ConfigurationProperties
@Slf4j
public class MQManager {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 失败返回null!!
     */
    public Task receiveTask(String topic) {
        String msg = (String) redisTemplate.opsForList().rightPop(topic);
        Task task = null;
        try {
            task = JSON.parseObject(msg, Task.class);
        } catch (Exception e) {
            log.error("receive task failure, msg={}", msg, e);
        }
        return task;
    }

    public boolean submitTask(String topic, Task task) {
        try {
            redisTemplate.opsForList().leftPush(topic, JSON.toJSONString(task));
            return true;
        } catch (Exception e) {
            log.error("submit task failure, task={}", JSON.toJSONString(task));
            return false;
        }
    }
}
