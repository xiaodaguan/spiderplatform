package cn.guanxiaoda.spider.engine.controller;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.service.CrawlerService;
import cn.guanxiaoda.spider.engine.service.SchedulerClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
@Slf4j
public class CrawlerController {

    @Autowired
    CrawlerService crawlerService;
    @Autowired
    SchedulerClient schedulerClient;

    @RequestMapping(value = "/receive")
    public void receiveTaskMsg(String taskJson) {
        log.info("crawler receive task: {}", taskJson);
        Task task = JSON.parseObject(taskJson, Task.class);
        crawlerService.handleTask(task);
    }

    @RequestMapping(value = "/submit")
    public void submitTaskMsg(@RequestParam String taskJson) {
        schedulerClient.sendTaskMsg(taskJson);
    }

}
