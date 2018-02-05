package cn.guanxiaoda.spider.engine.ctx;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.engine.annotation.Fetcher;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.annotation.Storager;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import cn.guanxiaoda.spider.engine.component.IParser;
import cn.guanxiaoda.spider.engine.component.IStorager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Component
@Slf4j
public class Selector implements ApplicationContextAware {


    private static ConcurrentHashMap<String, IFetcher> fetcherMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, IParser> parserMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, IStorager> storagerMap = new ConcurrentHashMap<>();

    private static List<String> proxyList = new ArrayList<>();

    public static IFetcher selectFetcher(int site, int source, int entity, int type) {
        while(parserMap.size() == 0){
            try {
                log.info("waiting for fetcher register");
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String name = String.format(Const.KEY_FORMAT, Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return fetcherMap.get(name);

    }

    public static IParser selectParser(int site, int source, int entity, int type) {
        while(parserMap.size() == 0){
            try {
                log.info("waiting for parser register");
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String name = String.format(Const.KEY_FORMAT, Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return parserMap.get(name);

    }

    public static IStorager selectStorager(int site, int source, int entity, int type) {
//        String name = String.format(Const.KEY_FORMAT, Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        while(parserMap.size() == 0){
            try {
                log.info("waiting for storager register");
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String name = String.format(Const.KEY_FORMAT, "", "", Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return storagerMap.get(name);

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
                Fetcher anno = value.getClass().getAnnotation(Fetcher.class);
                String name = String.format(Const.KEY_FORMAT, anno.site().name(), anno.source().name(), anno.entity().name(), anno.type().name());
                fetcherMap.put(name, value);
            }
        });
        log.info("fetcher: {} registered.", fetcherMap.size());

        /* register parser */
        log.info("registering parser...");
        Map<String, IParser> iParserMap = applicationContext.getBeansOfType(IParser.class);
        iParserMap.forEach((key, value) -> {
            if (value.getClass().isAnnotationPresent(Parser.class)) {
                Parser anno = value.getClass().getAnnotation(Parser.class);
                String name = String.format(Const.KEY_FORMAT, anno.site().name(), anno.source().name(), anno.entity().name(), anno.type().name());
                parserMap.put(name, value);
            }
        });
        log.info("parser: {} registered.", parserMap.size());

        /* register storager */
        log.info("registering storager...");
        Map<String, IStorager> iStoragerMap = applicationContext.getBeansOfType(IStorager.class);
        iStoragerMap.forEach((key, value) -> {
            if (value.getClass().isAnnotationPresent(Storager.class)) {
                Storager anno = value.getClass().getAnnotation(Storager.class);
//                String name = String.format(Const.KEY_FORMAT, anno.site().name(), anno.source().name(), anno.entity().name(), anno.type().name());
                String name = String.format(Const.KEY_FORMAT, "", "", anno.entity().name(), anno.type().name());
                storagerMap.put(name, value);
            }
        });
        log.info("storager: {} registered.", storagerMap.size());
    }
}
