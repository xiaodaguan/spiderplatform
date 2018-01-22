package cn.guanxiaoda.spider.scheduler;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.controller.CrawlerScheduleController;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerApplicationTests {
    Task task = new Task();

    @Autowired
    CrawlerScheduleController controller;

    @Before
    public void createTask() {
        task.setEntity(Entity.HOUSE.ordinal());
        task.setSite(Site.LIANJIA.ordinal());
        task.setSource(Source.MOBEL.ordinal());
        task.setType(Type.LIST.ordinal());
        task.setMaxRetry(1);
        task.setStartTime(LocalDateTime.now());
    }

    @Test
    public void sendTask() {
        controller.receiveTaskJson(JSON.toJSONString(task));
    }

}
