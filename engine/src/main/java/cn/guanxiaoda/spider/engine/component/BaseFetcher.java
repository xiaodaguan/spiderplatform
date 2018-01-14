package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.http.ConnManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by guanxiaoda on 2018/1/14.
 */
@Slf4j
public abstract class BaseFetcher implements IFetcher {

    @Autowired
    ConnManager connManager;

    @Override
    public FetchResult fetch(Task task) {
        String url = genRequestUrl(task);
        HashMap<String, String> headers = genRequestHeaders(task);
        RequestMethod method = genRequestMethod(task);
        String proxy = genProxy(task);
        String body = genRequestBody(task);

        // get conn from pool or create default
        CloseableHttpClient client = connManager.getPoolHttpClient(false);

        // build config
        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(6000)
                .setSocketTimeout(6000)
                .setConnectionRequestTimeout(6000);
        if (proxy != null) {
            configBuilder.setProxy(new HttpHost(proxy.split(Const.SEP.COLON)[0], Integer.parseInt(proxy.split(Const.SEP.COLON)[1])));
        }
        RequestConfig config = configBuilder.build();

        // build request
        RequestBuilder requestBuilder = RequestBuilder.create(method.name()).setUri(url);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }

        if (StringUtils.isNotEmpty(body)) {
            requestBuilder.setEntity(EntityBuilder.create().setContentType(genEntityType(task)).setText(body).build());
        }
        HttpUriRequest request = requestBuilder.setConfig(config).build();

        // exe
        CloseableHttpResponse response = null;
        FetchResult fetchResult = new FetchResult(Status.FAILURE, null, null);
        try {
            response = client.execute(request);
            Map<String, String> resHeaders = Arrays.stream(response.getAllHeaders()).collect(Collectors.toMap(Header::getName, Header::getValue));
            fetchResult = new FetchResult(Status.SUCCESS, EntityUtils.toString(response.getEntity()), resHeaders);
        } catch (IOException e) {
            log.error("http client execute failure, task={}", task, e);
        }
        return fetchResult;
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
