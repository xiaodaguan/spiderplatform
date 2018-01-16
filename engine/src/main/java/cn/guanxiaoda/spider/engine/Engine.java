package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.engine.component.engine.FetcherEngine;
import cn.guanxiaoda.spider.engine.component.engine.ParseEngine;
import cn.guanxiaoda.spider.engine.component.engine.StoragerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
public class Engine implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FetcherEngine fetcherEngine;
    @Autowired
    ParseEngine parseEngine;
    @Autowired
    StoragerEngine storagerEngine;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        fetcherEngine.run();
        parseEngine.run();
        storagerEngine.run();
    }
}
