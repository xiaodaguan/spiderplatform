package cn.guanxiaoda.spider.engine.service;

import cn.guanxiaoda.spider.engine.service.impl.DefaultCrawlerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@Service
public class AbstractCrawlerService implements CrawlerService{



    @Override
    public String crawl(String taskJson) {

        CrawlerService crawlerService = selectCrawlerService(taskJson);
        return crawlerService.crawl(taskJson);
    }

    private CrawlerService selectCrawlerService(String taskJson) {
        // TODO service router
        return new DefaultCrawlerServiceImpl();
    }
}
