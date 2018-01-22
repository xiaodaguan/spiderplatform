package cn.guanxiaoda.spider.engine.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@FeignClient("scheduler")
@Service(value = "taskManager")
public interface ITaskManager {


    @RequestMapping(value = "/scheduler/receive", method = RequestMethod.POST)
    void sendTaskMsg(String taskMsg);

}
