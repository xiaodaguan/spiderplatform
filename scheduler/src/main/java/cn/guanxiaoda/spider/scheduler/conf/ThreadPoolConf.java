package cn.guanxiaoda.spider.scheduler.conf;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/21.
 */
@Configuration
public class ThreadPoolConf {

    @Bean(name = "schedulerPool")
    ThreadPoolExecutor schedulerPool() {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("SCHEDULER-POOL-%d").build();
        return new ThreadPoolExecutor(100, 100, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(Integer.MAX_VALUE), namedFactory);
    }
}
