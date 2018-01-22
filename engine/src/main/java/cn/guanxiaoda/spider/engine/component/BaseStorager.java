package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Slf4j
public class BaseStorager<T> implements IStorager {

    @Autowired
    NutDao dao;

    @Override
    public boolean processer(Task task) {
        dao.create(Entity.valueOf(task.getEntity()).getEntityClass(), false);
        ParseResult parseResult = task.getParseResult();

        ((List<T>) parseResult.getParseResult()).stream().forEach(
                houseInfo -> {
                    try {
                        dao.insertOrUpdate(houseInfo);
                    } catch (Exception e) {
                        log.error("{} insert or update DB failure.");
                    }
                }
        );


        return true;

    }
}
