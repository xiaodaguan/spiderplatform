package cn.guanxiaoda.spider.scheduler.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@FeignClient(value = "crawlerengine")
public interface CrawlerEngineClient {

    @RequestMapping(value = "/receive")
    void submitTask(@RequestParam(value = "taskJson") String taskJson);
}
