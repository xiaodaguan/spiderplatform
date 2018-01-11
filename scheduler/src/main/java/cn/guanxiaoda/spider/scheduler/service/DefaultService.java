package cn.guanxiaoda.spider.scheduler.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@FeignClient(value = "crawlerengine")
public interface DefaultService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHelloFromOne(@RequestParam(value = "name") String name);
}
