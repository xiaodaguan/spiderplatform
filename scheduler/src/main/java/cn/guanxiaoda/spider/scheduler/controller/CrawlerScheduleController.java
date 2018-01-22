package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.ratelimiter.RedisSimpleRateLimiterImpl;
import cn.guanxiaoda.spider.scheduler.service.CrawlerScheduleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties
public class CrawlerScheduleController {

    @Autowired
    CrawlerScheduleService crawlerScheduleService;

    @Autowired
    @Qualifier("schedulerPool")
    ThreadPoolExecutor queue;

    @Autowired
    RedisSimpleRateLimiterImpl rateLimiter;


    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String receiveTaskJson(@RequestBody String taskJson) {
        Task task = JSON.parseObject(taskJson, Task.class);
        // todo qps config
        while (!rateLimiter.acquire(task.getSite() + Const.Seps.COLON + task.getSource(), 0.1)) {
        }
        return crawlerScheduleService.submitTask(taskJson);
    }

}
