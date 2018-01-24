package cn.guanxiaoda.spider.engine.component.engine;

import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
@ConfigurationProperties
public class FetcherEngine implements IEngine {

    @Value("mq.topic.fetcher.task.list")
    String mqFrom = "";

    @Value("mq.topic.parser.task.list")
    String mqTo = "";

    @Autowired
    @Qualifier("fetcherPool")
    ThreadPoolExecutor fetcherPool;

    @Autowired
    MQManager mqManager;


    public void run() {
        log.info("Fetch engine started.");
        fetcherPool.submit(this::listener);
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
            IFetcher fetcher = Selector.selectFetcher(task.getSite(), task.getSource(), task.getEntity(), task.getType());
            // process
            FetchResult fetchResult = fetcher.process(task);
            task.setFetchResult(fetchResult);
            // post process
            if (fetchResult != null && fetchResult.getStatus() == Status.SUCCESS) {
                mqManager.submitTask(mqTo, task);
            } else {
                // retry
                synchronized (this) {
                    task.setRetryNum(task.getRetryNum() + 1);
                }
                if (task.getRetryNum() > task.getMaxRetry()) {
                    // drop task
                    log.error("drop task(max retry), taskId={}", task.getTaskId());
                } else {
                    mqManager.submitTask(mqFrom, task);
                }
            }
        }
    }
}
