package cn.guanxiaoda.spider.scheduler.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@FeignClient(value = "crawlerengine")
public interface CrawlerScheduleService {

    @RequestMapping(value = "/crawlTask", method = RequestMethod.POST)
    String submitTask(@RequestBody String taskJson);
}