package cn.guanxiaoda.spider.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class EngineApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(EngineApplication.class, args);
//        System.out.println(ctx.getBeanDefinitionNames());
    }


}
