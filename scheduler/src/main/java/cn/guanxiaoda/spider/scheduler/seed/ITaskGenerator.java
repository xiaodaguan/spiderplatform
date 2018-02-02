package cn.guanxiaoda.spider.scheduler.seed;

import cn.guanxiaoda.spider.core.item.Task;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/2/2.
 */
public interface ITaskGenerator {

    List<Task> getTaskListFromDB(int site, int source, int entity, int type);
}
