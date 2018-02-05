package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.ctx.Selector;
import cn.guanxiaoda.spider.scheduler.ratelimiter.RedisSimpleRateLimiterImpl;
import cn.guanxiaoda.spider.scheduler.seed.ITaskGenerator;
import cn.guanxiaoda.spider.scheduler.service.CrawlerEngineClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    ThreadPoolExecutor schedulerPool;

    @Autowired
    RedisSimpleRateLimiterImpl rateLimiter;

    @Value("${rate.common}")
    double qps = 0.1;

    @RequestMapping(value = "/receive")
    public void receiveTaskJson(@RequestParam String taskJson) {
        Task task = JSON.parseObject(taskJson, Task.class);
        while (!rateLimiter.acquire(task.getSite() + Const.Seps.COLON + task.getSource(), qps)) {
        }
        log.info("scheduler receive task: {}", taskJson);
        schedulerPool.submit(() -> push(task));
    }

    private void push(@RequestParam Task task) {
        log.info("{} submit task, taskId = {}, qps = {}", this.getClass().getSimpleName(), task.getTaskId(), qps);
        crawlerEngineClient.submitTask(JSON.toJSONString(task));
    }

    @RequestMapping(value = "/schedule")
    public void scheduleTasks(@RequestParam int site, @RequestParam int source, @RequestParam int entity, @RequestParam int type) {
        ITaskGenerator generator = Selector.selectTaskGenerator(site, source, entity, type);
        generator.getTaskListFromDB(site, source, entity, type).parallelStream().forEach(task -> {
            while (!rateLimiter.acquire(task.getSite() + Const.Seps.COLON + task.getSource(), qps)) {
            }
            log.info("{} schedule task, taskId = {}", this.getClass().getSimpleName(), task.getTaskId());
            schedulerPool.submit(() -> push(task));
        });
    }

}
