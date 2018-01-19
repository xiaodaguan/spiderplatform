package cn.guanxiaoda.spider.engine.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by guanxiaoda on 2018/1/19.
 */
@Configuration
public class RestTemplateConf {


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
