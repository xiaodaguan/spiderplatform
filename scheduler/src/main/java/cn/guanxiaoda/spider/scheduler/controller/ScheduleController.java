package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.ratelimiter.RedisSimpleRateLimiterImpl;
import cn.guanxiaoda.spider.scheduler.service.CrawlerEngineClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
@ConfigurationProperties
@Slf4j
public class ScheduleController {

    @Autowired
    CrawlerEngineClient crawlerEngineClient;

    @Autowired
    @Qualifier("schedulerPool")
    ThreadPoolExecutor queue;

    @Autowired
    RedisSimpleRateLimiterImpl rateLimiter;

    @RequestMapping(value = "/receive")
    public void receiveTaskJson(@RequestParam String taskJson) {
        log.info("scheduler receive task: {}", taskJson);
        Task task = JSON.parseObject(taskJson, Task.class);
        queue.submit(() -> queue(taskJson, task));
    }

    private void queue(@RequestParam String taskJson, Task task) {
        // todo qps config
        while (!rateLimiter.acquire(task.getSite() + Const.Seps.COLON + task.getSource(), 0.1)) {
        }
        crawlerEngineClient.submitTask(taskJson);
    }

}
