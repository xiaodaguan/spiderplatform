package cn.guanxiaoda.spider.engine.component.engine;

import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.component.IParser;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
public class ParseEngine implements IEngine {

    @Value("mq.topic.parser.task.list")
    String mqFrom = "";

    @Value("mq.topic.storager.task.list")
    String mqTo = "";

    @Autowired
    MQManager mqManager;

    @Autowired
    @Qualifier("parserPool")
    ThreadPoolExecutor parserPool;

    public void run() {
        log.info("Parse engine started.");
        parserPool.submit(this::listener);

    }


    @Override
    public void listener() {
        while (true) {
            // receive task
            try {
                Thread.sleep(RECEIVE_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Task task = mqManager.receiveTask(mqFrom);
            if (task == null) {
                continue;
            }
            // select processer
            IParser parser = Selector.selectParser(task.getSite(), task.getSource(), task.getEntity(), task.getType());
            // process
            ParseResult parseResult = parser.process(task);
            task.setParseResult(parseResult);
            // post process
            if (parseResult != null && parseResult.getStatus() == Status.SUCCESS) {
                mqManager.submitTask(mqTo, task);
            } else {
                log.error("parse failed, taskId={}", task.getTaskId());
            }
        }
    }
}
