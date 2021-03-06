package cn.guanxiaoda.spider.engine.manager.proxy;

import cn.guanxiaoda.spider.engine.ctx.Selector;
import cn.guanxiaoda.spider.engine.http.HttpHandler;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by guanxiaoda on 2018/1/15.
 */
@Slf4j
@Component
@ConfigurationProperties
public class BaseProxyFetcher implements IProxyFetcher {

    @Value("${proxy.fetchRate}")
    double rateLimit = 4.0;
    @Value("${proxy.api}")
    String url = "";
    @Autowired
    HttpHandler httpHandler;
    private RateLimiter rateLimiter = RateLimiter.create(rateLimit);

    /**
     * 后续按照配置选择代理获取器
     */
    @Override
    @Scheduled(fixedRate = 1000)
    public void flushProxyList() {
        List<String> proxyListFromProvider = new ArrayList<>();
        rateLimiter.acquire();
        String cont = httpHandler.request(url);
        if (StringUtils.isNotEmpty(cont)) {
            try {
                proxyListFromProvider = Stream.of(cont.split("\n")).parallel().map(String::trim).collect(Collectors.toList());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        Selector.setProxyList(proxyListFromProvider);
    }
}
