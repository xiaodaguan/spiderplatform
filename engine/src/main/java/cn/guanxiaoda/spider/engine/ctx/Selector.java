package cn.guanxiaoda.spider.engine.ctx;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.engine.annotation.Fetcher;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import cn.guanxiaoda.spider.engine.proxy.IProxyFetcher;
import cn.guanxiaoda.spider.engine.proxy.KDLProxyFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
public class Selector implements ApplicationContextAware {


    private static ConcurrentHashMap<String, IFetcher> fetcherMap = new ConcurrentHashMap<>();

    private static List<String> proxyList = new ArrayList<>();

    public static IFetcher selectFetcher(int site, int source, int entity, int type) {
        String name = String.format(Const.FETCHER_KEY_FORMAT, Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return fetcherMap.get(name);

    }

    public static void setProxyList(List<String> list) {
        proxyList = list;
    }

    /**
     * 随机选择count个代理，如果count>proxyList.size，直接返回proxyList
     *
     * @return 如果proxyList中无代理，则返回null
     */
    public static List<String> selectRandomProxy(int count) {
        Random rand = new Random();
        int total = proxyList.size();
        if (total == 0) {
            return null;
        }
        if (count >= total) {
            return proxyList;
        }
        List<String> results = new ArrayList<>();
        rand.ints(0, total).limit(count).parallel().forEach(i -> results.add(proxyList.get(i)));
        return results;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* register fetcher */
        log.info("registering fetcher...");
        Map<String, IFetcher> iFetcherMap = applicationContext.getBeansOfType(IFetcher.class);
        iFetcherMap.forEach((key, value) -> {
            if (value.getClass().isAnnotationPresent(Fetcher.class)) {
                Fetcher fetcherAnno = value.getClass().getAnnotation(Fetcher.class);
                String name = String.format(Const.FETCHER_KEY_FORMAT, fetcherAnno.site().name(), fetcherAnno.source().name(), fetcherAnno.entity().name(), fetcherAnno.type().name());
                fetcherMap.put(name, value);
            }
        });
        log.info("fetcher: {} registered.", fetcherMap.size());
    }
}
