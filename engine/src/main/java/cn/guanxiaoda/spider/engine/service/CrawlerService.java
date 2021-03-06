package cn.guanxiaoda.spider.engine.service;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 对外提供crawl服务
 * Created by guanxiaoda on 2018/1/11.
 */
@Service
@Slf4j
public class CrawlerService {


    @Value("mq.topic.fetcher.task.list")
    String fetcherListMq = "";

    @Autowired
    MQManager mqManager;

    public void handleTask(Task task) {
        mqManager.submitTask(fetcherListMq, task);
    }


}
