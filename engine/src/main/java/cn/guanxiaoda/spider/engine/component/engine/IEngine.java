package cn.guanxiaoda.spider.engine.component.engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@ConfigurationProperties
public interface IEngine {

    /**
     * unit: ms
     */
    @Value("${mq.receive.interval}")
    int RECEIVE_INTERVAL = 50;

    void listener();
}
