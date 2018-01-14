package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.engine.component.FetcherEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
public class ComponentBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FetcherEngine fetcherEngine;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        fetcherEngine.run();
    }
}
