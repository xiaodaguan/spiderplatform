package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.ratelimiter.RedisSimpleRateLimiterImpl;
import cn.guanxiaoda.spider.scheduler.service.CrawlerScheduleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
@RequestMapping("/scheduler")
public class CrawlerScheduleController {

    @Autowired
    CrawlerScheduleService crawlerScheduleService;

    @Autowired
    @Qualifier("schedulerPool")
    ThreadPoolExecutor queue;

    @Autowired
    RedisSimpleRateLimiterImpl rateLimiter;


    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String sayHi(@RequestBody String taskJson) {
        Task task = JSON.parseObject(taskJson, Task.class);
        while (!rateLimiter.acquire(task.getTaskId(), 0.1)) {
        }
        return crawlerScheduleService.submitTask(taskJson);
    }

}
