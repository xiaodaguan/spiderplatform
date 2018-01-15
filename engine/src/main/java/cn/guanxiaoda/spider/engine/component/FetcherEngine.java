package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
public class FetcherEngine {

    @Value("mq.topic.fetcher.task.list")
    String fetcherListMq = "";

    @Value("mq.topic.parser.task.list")
    String parserListMq = "";

    @Autowired
    MQManager mqManager;

    private ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("FETCHER-POOL-%d").build();
    private ThreadPoolExecutor fetcherPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(Integer.MAX_VALUE), namedFactory);

    public void run() {
        log.info("Fetch engine started.");
        fetcherPool.submit(this::listener);
    }

    private void listener() {
        while (true) {
            // receive task
            Task task = mqManager.receiveTask(fetcherListMq);
            if (task == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            // select fetcher
            IFetcher fetcher = Selector.selectFetcher(task.getSite(), task.getSource(), task.getEntity(), task.getType());
            // fetch
            FetchResult fetchResult = fetcher.fetch(task);
            task.setFetchResult(fetchResult);
            // post process
            if (fetchResult != null && fetchResult.getStatus() == Status.SUCCESS) {
                mqManager.submitTask(parserListMq, task);
            } else {
                // retry
                synchronized (this) {
                    task.setRetryNum(task.getRetryNum() + 1);
                }
                if (task.getRetryNum() > task.getMaxRetry()) {
                    // drop task
                    log.error("drop task(max retry), task={}", task);
                } else {
                    mqManager.submitTask(fetcherListMq, task);
                }
            }
        }
    }
}
