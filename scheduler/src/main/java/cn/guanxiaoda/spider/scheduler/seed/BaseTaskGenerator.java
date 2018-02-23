package cn.guanxiaoda.spider.scheduler.seed;

import cn.guanxiaoda.spider.core.item.Task;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/2/2.
 */
public abstract class BaseTaskGenerator implements ITaskGenerator {


    @Override
    public abstract List<Task> getTaskList(int site, int source, int entity, int type);
}
