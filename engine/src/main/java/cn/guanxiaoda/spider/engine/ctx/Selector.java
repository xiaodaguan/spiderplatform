package cn.guanxiaoda.spider.engine.ctx;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.engine.annotation.Fetcher;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
public class Selector implements ApplicationContextAware {

    public static ConcurrentHashMap<String, IFetcher> fetcherMap = new ConcurrentHashMap<>();

    public static IFetcher selectFetcher(int site, int source, int entity, int type) {
        String name = String.format("%s%s%s%sFetcher", Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return fetcherMap.get(name);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        log.info("registering fetcher...");
        Map<String, IFetcher> iFetcherMap = applicationContext.getBeansOfType(IFetcher.class);
        iFetcherMap.forEach((key, value) -> {
            if (value.getClass().isAnnotationPresent(Fetcher.class)) {
                Fetcher fetcherAnno = value.getClass().getAnnotation(Fetcher.class);
                String name = String.format("%s%s%s%sFetcher", fetcherAnno.site().name(), fetcherAnno.source().name(), fetcherAnno.entity().name(), fetcherAnno.type().name());
                fetcherMap.put(name, value);
            }
        });
        log.info("fetcher: {} registered.", fetcherMap.size());
    }
}
