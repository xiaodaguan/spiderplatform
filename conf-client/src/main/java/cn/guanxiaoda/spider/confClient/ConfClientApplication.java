package cn.guanxiaoda.spider.confClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/2/3.
 */
@SpringBootApplication
@RestController
public class ConfClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfClientApplication.class, args);
    }


    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }

}
