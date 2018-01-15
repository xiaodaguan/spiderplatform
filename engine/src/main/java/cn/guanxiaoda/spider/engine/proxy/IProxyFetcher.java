package cn.guanxiaoda.spider.engine.proxy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by guanxiaoda on 2018/1/15.
 */
@Component
public interface IProxyFetcher {

    double rateLimit = 1;

    void flushProxyList();
}
