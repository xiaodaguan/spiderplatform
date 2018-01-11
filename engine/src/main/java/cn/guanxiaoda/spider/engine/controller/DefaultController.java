package cn.guanxiaoda.spider.engine.controller;

import cn.guanxiaoda.spider.engine.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
public class DefaultController {


    @Value("${server.port}")
    private String port;



    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return String.format("hi %s, i am from port: %s", name, port);
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return String.format("hello %s, LTNS, port=%s", name, port);
    }




}
