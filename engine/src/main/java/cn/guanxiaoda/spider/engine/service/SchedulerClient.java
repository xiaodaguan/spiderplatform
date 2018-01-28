package cn.guanxiaoda.spider.engine.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@FeignClient(value = "scheduler")
public interface SchedulerClient {

    @RequestMapping(value = "/receive")
    void sendTaskMsg(@RequestParam(value = "taskJson") String taskJson);

}

//@FeignClient(value = "crawlerengine")
//public interface CrawlerEngineClient {
//
//    @RequestMapping(value = "/receive")
//    void submitTask(@RequestParam(value = "taskJson") String taskJson);
//}
