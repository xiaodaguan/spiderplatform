package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class GenTaskTest {
    Task lTask = null;
    Task dTask = null;

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
    public void genTaskStr() {
        System.out.println(JSON.toJSONString(lTask));
    }

}
