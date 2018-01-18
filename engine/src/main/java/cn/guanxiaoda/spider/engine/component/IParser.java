package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
public interface IParser {

    ParseResult process(Task task);

    IParser getPropertyAsList(String property, String select, int expectCount);

    IParser getPropertyAsList(String property, String select);

    IParser getSingleProperty(String property, String select, boolean required);

    IParser getSingleProperty(String property, String select);
}
