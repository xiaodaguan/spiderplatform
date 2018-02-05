package cn.guanxiaoda.spider.engine.component.engine;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.component.IParser;
import cn.guanxiaoda.spider.engine.controller.CrawlerController;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
@ConfigurationProperties
public class ParseEngine implements IEngine {

    @Value("mq.topic.parser.task.list")
    String mqFrom = "";

    @Value("mq.topic.storager.task.list")
    String mqTo = "";

    @Value("${thread.parser}")
    int parserThread = 10;

    @Autowired
    MQManager mqManager;

    @Autowired
    CrawlerController crawlerController;

    @Autowired
    @Qualifier("parserPool")
    ThreadPoolExecutor parserPool;

    public void run() {
        log.info("Parse engine started.");
        IntStream.range(0, parserThread).parallel().forEach(threadNum -> parserPool.submit(this::listener));


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
            IParser parser = Selector.selectParser(task.getSite(), task.getSource(), task.getEntity(), task.getType());
            // process
            ParseResult parseResult = parser.process(task);
            task.setParseResult(parseResult);

            try {
                gennerateAndSendFollowingPageTasks(task);
            } catch (Exception e) {
                log.error("{} gennerateAndSendFollowingPageTasks exception", this.getClass().getSimpleName(), e);
            }

            // post process
            if (parseResult != null && parseResult.getStatus() == Status.SUCCESS) {
                log.info("parse success, send task: {}", task.getTaskId());
                mqManager.submitTask(mqTo, task);
            } else {
                log.error("parse failed, taskId={}", task.getTaskId());
            }
        }
    }

    private void gennerateAndSendFollowingPageTasks(Task task) {
        ParseResult parseResult = task.getParseResult();
        if (Const.Strings.ONE.equals(task.getMeta().get(Const.TaskParams.PAGE_NUM)) && parseResult.getStatus() == Status.SUCCESS && parseResult.getTotalPage() > 1) {
            IntStream.range(2, parseResult.getTotalPage() + 1).forEach(pageNum -> {
                try {
                    Task newTask = new Task(task.getSite(), task.getSource(), task.getEntity(), task.getType());
                    BeanUtils.copyProperties(task, newTask, "taskId", "startTime", "fetchResult", "parseResult", "site", "source", "entity", "type");
                    newTask.getMeta().put(Const.TaskParams.PAGE_NUM, String.valueOf(pageNum));
                    newTask.setStartTime(LocalDateTime.now());
                    newTask.genTaskId();
                    crawlerController.submitTaskMsg(JSON.toJSONString(newTask));
                } catch (Exception e) {
                    log.warn("{} create&submit following page task failure, task={}", this.getClass().getSimpleName(), task.getTaskId(), e);
                }
            });
        }
    }
}
