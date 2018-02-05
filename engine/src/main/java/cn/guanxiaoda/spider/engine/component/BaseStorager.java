package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.item.Task;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Slf4j
public abstract class BaseStorager<T> implements IStorager {

    @Autowired
    protected NutDao dao;

    protected abstract List<T> clean(List<T> items, Task task);
    protected abstract T clean(T item, Task task);

    @Override
    public abstract boolean processe(Task task) ;
}
