package cn.guanxiaoda.spider.engine.mq;

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

    Task litTask = new Task();

    @Before
    @Test
    public void genTask() {
        litTask.setEntity(Entity.HOUSE.ordinal());
        litTask.setSite(Site.LIANJIA.ordinal());
        litTask.setSource(Source.MOBEL.ordinal());
        litTask.setType(Type.LIST.ordinal());
        litTask.setTaskId("task-test-001");
        System.out.println(JSON.toJSONString(litTask));
    }

    @Test
    public void submitTest() {
        if (mqManager.submitTask(MQ_TEST, litTask)) {
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
