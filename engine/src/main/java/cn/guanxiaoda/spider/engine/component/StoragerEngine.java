package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.mq.MQManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
public class StoragerEngine {

    @Value("mq.topic.storager.task.list")
    String parserListMq = "";


    @Autowired
    MQManager mqManager;


    void run() {

        while(true) {
            Task task = mqManager.receiveTask(parserListMq);
            // TODO handle task
        }

    }
}
