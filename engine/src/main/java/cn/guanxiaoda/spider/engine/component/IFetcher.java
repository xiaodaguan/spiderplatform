package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
public interface IFetcher {
    /**
     * 不允许返回空值
     */
    FetchResult fetch(Task task);

    String genRequestUrl(Task task);

    HashMap<String, String> genRequestHeaders(Task task);

    RequestMethod genRequestMethod(Task task);

    /**
     * 一定保证"xxx.xx.xx.xx:xxxx"格式
     */
    String genProxy(Task task);

    String genRequestBody(Task task);

    ContentType genEntityType(Task task);

}
