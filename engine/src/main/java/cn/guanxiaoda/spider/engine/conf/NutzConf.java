package cn.guanxiaoda.spider.engine.conf;

import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Configuration
@ConfigurationProperties
public class NutzConf {

    @Value("${mysql.url}")
    String url;
    @Value("${mysql.user}")
    String user;
    @Value("${mysql.pwd}")
    String pwd;

    @Bean
    SimpleDataSource simpleDataSource() {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    @Bean
    NutDao nutDao(SimpleDataSource simpleDataSource) {
        return new NutDao(simpleDataSource);
    }
}
