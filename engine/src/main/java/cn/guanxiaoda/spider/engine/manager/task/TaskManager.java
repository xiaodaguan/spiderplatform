package cn.guanxiaoda.spider.engine.manager.task;

import cn.guanxiaoda.spider.core.item.Task;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by guanxiaoda on 2018/1/19.
 */
@Component
@ConfigurationProperties
public class TaskManager {

    @Autowired
    RestTemplate restTemplate;

    @Value("${scheduler.url}")
    String schedulerUrl;

    public void task2Scheduler(Task task) {
        restTemplate.postForEntity(schedulerUrl, JSON.toJSONString(task), String.class);
    }
}
