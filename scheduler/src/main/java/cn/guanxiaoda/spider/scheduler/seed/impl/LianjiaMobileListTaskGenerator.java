package cn.guanxiaoda.spider.scheduler.seed.impl;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.scheduler.annotation.TaskGenerator;
import cn.guanxiaoda.spider.scheduler.seed.BaseTaskGenerator;
import cn.guanxiaoda.spider.scheduler.seed.ITaskGenerator;
import com.google.common.io.Resources;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guanxiaoda
 * @date 2018/2/22
 */
@Slf4j
@TaskGenerator(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.LIST)
public class LianjiaMobileListTaskGenerator extends BaseTaskGenerator implements ITaskGenerator {

    @Override
    public List<Task> getTaskList(int site, int source, int entity, int type) {
        DocumentContext ctx = null;
        try {
            ctx = JsonPath.parse(new File(Resources.getResource("list.bj.json").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> bizs = ctx.read("$..data.info[*].district[*].bizcircle[*].bizcircle_quanpin", List.class);
        return bizs.stream().map(bizName -> {
            Task task = new Task(Site.LIANJIA.ordinal(), Source.MOBEL.ordinal(), Entity.HOUSE.ordinal(), Type.LIST.ordinal());
            task.getMeta().put(Const.TaskParams.BIZ_NAME, bizName);
            task.getMeta().put(Const.TaskParams.PAGE_NUM, "1");
            task.setMaxRetry(5);
            task.setStartTime(LocalDateTime.now());
            task.genTaskId();
            return task;
        }).collect(Collectors.toList());
    }
}
