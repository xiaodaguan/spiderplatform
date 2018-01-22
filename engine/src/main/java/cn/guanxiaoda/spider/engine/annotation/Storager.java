package cn.guanxiaoda.spider.engine.annotation;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface Storager {
//    Site site();
//    Source source();
    Entity entity();
    Type type();
}
