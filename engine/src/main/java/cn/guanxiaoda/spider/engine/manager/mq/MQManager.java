package cn.guanxiaoda.spider.engine.manager.mq;

import cn.guanxiaoda.spider.core.item.Task;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/12.
 */
@Service
@ConfigurationProperties
@Slf4j
public class MQManager {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Value("${mq.receive.interval}")
    int receiveInterval = 50;

    public Task receiveTask(String topic) {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = (String) redisTemplate.opsForList().rightPop(topic);
            Task task = null;
            if (StringUtils.isEmpty(msg)) {
                continue;
            }
            try {
                task = JSON.parseObject(msg, Task.class);
                log.info("{} receive task success, taskId={}", this.getClass().getSimpleName(), task.getTaskId());
                return task;
            } catch (Exception e) {
                log.error("receive task failure, msg={}", msg, e);
            }
        }


    }

    public boolean submitTask(String topic, Task task) {
        try {
            redisTemplate.opsForList().leftPush(topic, JSON.toJSONString(task));
            return true;
        } catch (Exception e) {
            log.error("submit task failure, task={}", JSON.toJSONString(task), e);
            return false;
        }
    }
}
