package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.http.HttpHandler;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/14.
 */
@Slf4j
public abstract class BaseFetcher implements IFetcher {

    @Autowired
    HttpHandler httpHandler;

    @Override
    public FetchResult process(Task task) {
        String url = genRequestUrl(task);
        HashMap<String, String> headers = genRequestHeaders(task);
        RequestMethod method = genRequestMethod(task);
        String proxy = genProxy(task);
        String body = genRequestBody(task);
        ContentType contentType = genEntityType(task);
        return httpHandler.request(url, headers, method, proxy, body, contentType);
    }


    @Override
    public abstract String genRequestUrl(Task task);


    @Override
    public HashMap<String, String> genRequestHeaders(Task task) {
        return null;
    }

    @Override
    public RequestMethod genRequestMethod(Task task) {
        return RequestMethod.GET;
    }

    @Override
    public String genProxy(Task task) {
        List<String> proxyList = Selector.selectRandomProxy(1);
        if (!CollectionUtils.isEmpty(proxyList)) {
            return proxyList.get(0);
        }
        return null;

    }

    @Override
    public String genRequestBody(Task task) {
        return null;
    }

    @Override
    public ContentType genEntityType(Task task) {
        return ContentType.APPLICATION_JSON;
    }
}
