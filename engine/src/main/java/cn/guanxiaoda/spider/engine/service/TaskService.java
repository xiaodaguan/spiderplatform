package cn.guanxiaoda.spider.engine.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@FeignClient(value = "scheduler")
public interface TaskService {

    @RequestMapping(value = "/scheduler/receive", method = RequestMethod.POST)
    String sendTaskMsg(@RequestBody String taskMsg);

}
