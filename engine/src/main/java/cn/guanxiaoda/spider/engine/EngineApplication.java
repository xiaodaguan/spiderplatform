package cn.guanxiaoda.spider.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@EnableJpaRepositories("cn.guanxiaoda.spider.engine.dao")
@EntityScan("cn.guanxiaoda.spider.core.po")
public class EngineApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(EngineApplication.class, args);
//        System.out.println(ctx.getBeanDefinitionNames());
    }


}
