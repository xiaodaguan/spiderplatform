package cn.guanxiaoda.spider.engine.service.impl;

import cn.guanxiaoda.spider.engine.service.CrawlerService;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
public class DefaultCrawlerServiceImpl implements CrawlerService {
    @Override
    public String crawl(String taskJson) {
        return String.format("[%s]执行抓取任务, task=%s", this.getClass().getSimpleName(), taskJson);
    }
}
