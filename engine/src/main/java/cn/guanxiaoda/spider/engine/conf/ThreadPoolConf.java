package cn.guanxiaoda.spider.engine.conf;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@Configuration
public class ThreadPoolConf {


    @Value("thread.fetcher")
    int fetcherThread = 1;
    @Value("thread.parser")
    int parserThread = 1;


    @Bean(name = "fetcherPool")
    ThreadPoolExecutor fetcherPool() {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("FETCHER-POOL-%d").build();
        return new ThreadPoolExecutor(fetcherThread, fetcherThread, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(Integer.MAX_VALUE), namedFactory);

    }

    @Bean(name = "parserPool")
    ThreadPoolExecutor parserPool() {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("PARSER-POOL-%d").build();
        return new ThreadPoolExecutor(parserThread, parserThread, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(Integer.MAX_VALUE), namedFactory);

    }
}
