package cn.guanxiaoda.spider.engine.service;

import org.springframework.stereotype.Service;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@Service
public interface CrawlerService {

    String crawl(String taskJson);
}
