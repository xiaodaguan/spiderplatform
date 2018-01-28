package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.engine.component.engine.FetcherEngine;
import cn.guanxiaoda.spider.engine.component.engine.ParseEngine;
import cn.guanxiaoda.spider.engine.component.engine.StoragerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
//public class Engine implements ApplicationListener<ContextRefreshedEvent> {
public class Engine {

    @Autowired
    FetcherEngine fetcherEngine;
    @Autowired
    ParseEngine parseEngine;
    @Autowired
    StoragerEngine storagerEngine;

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        fetcherEngine.run();
//        parseEngine.run();
//        storagerEngine.run();
//    }

    @PostConstruct
    public void boot() {
        fetcherEngine.run();
        parseEngine.run();
        storagerEngine.run();
    }
}
