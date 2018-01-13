package cn.guanxiaoda.spider.engine.controller;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.service.CrawlerService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
public class CrawlerController {

    @Autowired
    @Qualifier("crawlerService")
    CrawlerService crawlerService;

    @RequestMapping("/crawlTask")
    public String crawl(@RequestBody String taskJson) {
        Task task = JSON.parseObject(taskJson, Task.class);
        crawlerService.handleTask(task);
        return null;
    }
}
