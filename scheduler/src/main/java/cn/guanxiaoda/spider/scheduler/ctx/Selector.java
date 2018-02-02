package cn.guanxiaoda.spider.scheduler.ctx;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.scheduler.annotation.TaskGenerator;
import cn.guanxiaoda.spider.scheduler.seed.ITaskGenerator;
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


    private static ConcurrentHashMap<String, ITaskGenerator> taskGeneratorMap = new ConcurrentHashMap<>();


    public static ITaskGenerator selectTaskGenerator(int site, int source, int entity, int type) {
        String name = String.format(Const.KEY_FORMAT, Site.valueOf(site).name(), Source.valueOf(source).name(), Entity.valueOf(entity).name(), Type.valueOf(type).name());
        return taskGeneratorMap.get(name);

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* register fetcher */
        log.info("registering fetcher...");
        Map<String, ITaskGenerator> iFetcherMap = applicationContext.getBeansOfType(ITaskGenerator.class);
        iFetcherMap.forEach((key, value) -> {
            if (value.getClass().isAnnotationPresent(TaskGenerator.class)) {
                TaskGenerator anno = value.getClass().getAnnotation(TaskGenerator.class);
                String name = String.format(Const.KEY_FORMAT, anno.site().name(), anno.source().name(), anno.entity().name(), anno.type().name());
                taskGeneratorMap.put(name, value);
            }
        });
        log.info("taskGenerator: {} registered.", taskGeneratorMap.size());

    }
}
