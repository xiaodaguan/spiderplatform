package cn.guanxiaoda.spider.engine.controller;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.service.CrawlerService;
import cn.guanxiaoda.spider.engine.service.ITaskManager;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    CrawlerService crawlerService;

    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String receiveTaskMsg(@RequestBody String taskJson) {
        Task task = JSON.parseObject(taskJson, Task.class);
        crawlerService.handleTask(task);
        return "ok.";
    }

}
