package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
public interface IStorager {

    boolean processe(Task task);
}
