package cn.guanxiaoda.spider.engine.http;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.FetchResult;
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
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by guanxiaoda on 2018/1/15.
 */
@Component
@Slf4j
public class HttpHandler {
    @Autowired
    ConnManager connManager;

    /**
     * 简易版
     *
     * @param url GET url
     * @return response body
     */
    public String request(String url) {
        FetchResult fr = request(url, null, RequestMethod.GET, null, null, null);
        return fr.getStatus().equals(Status.SUCCESS) ? fr.getContent() : null;
    }

    /**
     * @param url         required
     * @param headers     nullable
     * @param method      required default get
     * @param proxy       nullable
     * @param body        nullable
     * @param contentType nullable if method is get
     * @return fetchresult
     */
    public FetchResult request(String url, HashMap<String, String> headers, RequestMethod method, String proxy, String body, ContentType contentType) {
        // get conn from pool or create default
        CloseableHttpClient client = connManager.getPoolHttpClient(false);

        // build config
        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(6000)
                .setSocketTimeout(6000)
                .setConnectionRequestTimeout(6000);
        if (proxy != null) {
            configBuilder.setProxy(new HttpHost(proxy.split(Const.Seps.COLON)[0], Integer.parseInt(proxy.split(Const.Seps.COLON)[1])));
        }
        RequestConfig config = configBuilder.build();

        // build request
        RequestBuilder requestBuilder = RequestBuilder.create(method.name()).setUri(url);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }

        if (method == RequestMethod.POST && StringUtils.isNotEmpty(body)) {
            requestBuilder.setEntity(EntityBuilder.create().setContentType(contentType).setText(body).build());
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
            log.error("http client execute failure, url={}", url, e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fetchResult;
    }

}
