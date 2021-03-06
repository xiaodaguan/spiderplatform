package cn.guanxiaoda.spider.engine.mq;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.manager.mq.MQManager;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by guanxiaoda on 2018/1/12.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MqTest {
    @Value("${mq.topic.crawler.task.test}")
    public String MQ_TEST = "";
    @Autowired
    MQManager mqManager;

    Task lTask = null;
    Task dTask = null;

    @Before
    @Test
    public void genTask() {
        lTask = new Task(Site.LIANJIA.ordinal(), Source.MOBEL.ordinal(), Entity.HOUSE.ordinal(), Type.LIST.ordinal());
        lTask.getMeta().put(Const.TaskParams.PAGE_NUM, "1");
        lTask.setMaxRetry(5);
        lTask.setStartTime(LocalDateTime.now());

        dTask = new Task(Site.LIANJIA.ordinal(), Source.MOBEL.ordinal(), Entity.HOUSE.ordinal(), Type.DETAIL.ordinal());
        dTask.setMaxRetry(5);
        dTask.setStartTime(LocalDateTime.now());
        System.out.println(JSON.toJSONString(lTask));
        System.out.println(JSON.toJSONString(dTask));
    }

    @Test
    public void submitTest() {
        if (mqManager.submitTask(MQ_TEST, lTask)) {
            System.out.println("submit succeed");
        } else {
            System.err.println("submit failed");
        }
    }

    @Test
    public void receiveTest() {
        System.out.println(mqManager.receiveTask(MQ_TEST));
    }


}
