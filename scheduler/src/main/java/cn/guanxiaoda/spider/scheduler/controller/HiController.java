package cn.guanxiaoda.spider.scheduler.controller;

import cn.guanxiaoda.spider.scheduler.service.DefaultService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@RestController
public class HiController {

    @Autowired
    DefaultService defaultService;

    @ApiOperation(value = "say hi", notes = "就是say hi")
    @ApiImplicitParam(name = "hi", value = "说嗨", required = true, dataType = "String")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return defaultService.sayHiFromOne(name);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String name){
        return defaultService.sayHelloFromOne(name);
    }
}
