package cn.guanxiaoda.spider.engine.component.engine;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.component.IParser;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import cn.guanxiaoda.spider.engine.service.ITaskManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    MQManager mqManager;

    @Autowired
    @Qualifier("taskManager")
    ITaskManager taskManager;

    @Autowired
    @Qualifier("parserPool")
    ThreadPoolExecutor parserPool;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

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
            /* transfer page tasks */
            if (Const.Strings.ONE.equals(task.getMeta().get(Const.TaskParams.PAGE_NUM)) && parseResult.getStatus() == Status.SUCCESS && parseResult.getTotalPage() > 1) {
                IntStream.range(2, parseResult.getTotalPage() + 1).forEach(pageNum -> {
                    Task newTask = new Task();
                    BeanUtils.copyProperties(task, newTask, "taskId", "startTime", "fetchResult", "parseResult");
                    newTask.getMeta().put(Const.TaskParams.PAGE_NUM, String.valueOf(pageNum));
                    newTask.setTaskId(String.format("%s-%s-%s-%s-%s-%d", LocalDateTime.now().format(dtf), task.getSite(), task.getSource(), task.getEntity(), task.getType(), pageNum));
                    newTask.setStartTime(LocalDateTime.now());

                    taskManager.sendTaskMsg(JSON.toJSONString(newTask));
                });
            }

            // post process
            if (parseResult != null && parseResult.getStatus() == Status.SUCCESS) {
                mqManager.submitTask(mqTo, task);
            } else {
                log.error("parse failed, taskId={}", task.getTaskId());
            }
        }
    }
}
