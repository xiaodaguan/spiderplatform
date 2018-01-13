package cn.guanxiaoda.spider.engine.service;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.mq.MQManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@Service
public class CrawlerService {


    @Value("mq.topic.fetcher.task.list")
    String fetcherListMq = "";

    @Autowired
    MQManager mqManager;


    public void handleTask(Task task) {
        mqManager.submitTask(fetcherListMq, task);
    }


}
