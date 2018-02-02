package cn.guanxiaoda.spider.scheduler.seed;

import cn.guanxiaoda.spider.core.item.Task;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/2/2.
 */
public abstract class BaseTaskGenerator implements ITaskGenerator{

    @Autowired
    protected
    NutDao dao;

    @Override
    public abstract List<Task> getTaskListFromDB(int site, int source, int entity, int type);
}
