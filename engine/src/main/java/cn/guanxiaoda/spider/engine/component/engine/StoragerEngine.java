package cn.guanxiaoda.spider.engine.component.engine;

import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.component.IStorager;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
@ConfigurationProperties
public class StoragerEngine implements IEngine {

    @Value("mq.topic.storager.task.list")
    String mqFrom = "";


    @Autowired
    MQManager mqManager;

    @Autowired
    @Qualifier("storagerPool")
    ThreadPoolExecutor storagerPool;

    public void run() {

        log.info("Storage engine started.");
        storagerPool.submit(this::listener);

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
            log.info("{} receive task success, taskId={}", this.getClass().getSimpleName(), task.getTaskId());
            // select processer
            IStorager storager = Selector.selectStorager(task.getSite(), task.getSource(), task.getEntity(), task.getType());
            // process
            boolean storageStatus = false;
            try {
                storageStatus = storager.processe(task);
            } catch (Exception e) {
                log.error("{} process exception", this.getClass().getSimpleName(), e);
            }
            if (storageStatus) {
                task.setEndTime(LocalDateTime.now());
                log.info("task[{}] succeed.", task.getTaskId());
            } else {
                log.error("task[{}] failed.", task.getTaskId());
            }
        }
    }
}
