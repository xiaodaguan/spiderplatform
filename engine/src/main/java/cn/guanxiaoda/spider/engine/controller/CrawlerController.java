package cn.guanxiaoda.spider.engine.controller;

import cn.guanxiaoda.spider.engine.service.AbstractCrawlerService;
import cn.guanxiaoda.spider.engine.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
public class CrawlerController {

    @Autowired
    AbstractCrawlerService crawlerService;

    @RequestMapping("/crawl")
    public String crawl(@RequestBody String taskJson){
        return crawlerService.crawl(taskJson);
    }
}
