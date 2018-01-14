package cn.guanxiaoda.spider.engine;

import cn.guanxiaoda.spider.engine.component.FetcherEngine;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import cn.guanxiaoda.spider.engine.component.ParseEngine;
import cn.guanxiaoda.spider.engine.component.StoragerEngine;
import cn.guanxiaoda.spider.engine.ctx.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "cn.guanxiaoda.spider.engine")
public class EngineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(EngineApplication.class, args);
//        Arrays.stream(ctx.getBeanFactory().getBeanDefinitionNames()).forEach(beanName-> System.out.println(beanName));
    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ooooooooooooo");
    }
}
