package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.engine.service.SchedulerClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = SchedulerClient.class)
@EnableDiscoveryClient
public class EngineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ooooooooooooo");
    }
}
