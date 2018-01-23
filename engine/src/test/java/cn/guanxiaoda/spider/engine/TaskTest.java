package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.service.SchedulerClient;
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
public class TaskTest {
    Task lTask = null;
    Task dTask = null;
    @Autowired
    SchedulerClient schedulerClient;

    @Before
    public void createTask() {
        lTask = new Task(Site.LIANJIA.ordinal(), Source.MOBEL.ordinal(), Entity.HOUSE.ordinal(), Type.LIST.ordinal());
        lTask.getMeta().put(Const.TaskParams.PAGE_NUM, "1");
        lTask.genTaskId();
        lTask.setMaxRetry(5);
        lTask.setStartTime(LocalDateTime.now());

        dTask = new Task(Site.LIANJIA.ordinal(), Source.MOBEL.ordinal(), Entity.HOUSE.ordinal(), Type.DETAIL.ordinal());
        dTask.setMaxRetry(5);
        dTask.setStartTime(LocalDateTime.now());

    }

    @Test
    public void sendTask() {
        schedulerClient.sendTaskMsg(JSON.toJSONString(lTask));
    }

}
