package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Slf4j
public abstract class BaseStorager<T> implements IStorager {

    protected abstract List<T> clean(List<T> items, Task task);

    protected abstract T clean(T item, Task task);

    @Override
    public abstract boolean processe(Task task);
}
