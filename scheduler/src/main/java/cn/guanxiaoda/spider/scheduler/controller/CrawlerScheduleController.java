package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.scheduler.service.CrawlerScheduleService;
import cn.guanxiaoda.spider.scheduler.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
public class CrawlerScheduleController {

    @Autowired
    CrawlerScheduleService crawlerScheduleService;

    @RequestMapping(value = "/crawl", method = RequestMethod.POST)
    public String sayHi(@RequestBody String taskJson){
        return crawlerScheduleService.submitTask(taskJson);
    }

}
