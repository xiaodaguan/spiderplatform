package cn.guanxiaoda.spider.engine.component.impl.fetcher;

import cn.guanxiaoda.spider.core.enums.*;
import cn.guanxiaoda.spider.core.item.FetchResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.annotation.Fetcher;
import cn.guanxiaoda.spider.engine.component.BaseFetcher;
import cn.guanxiaoda.spider.engine.component.IFetcher;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Fetcher(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileHouseListFetcher extends BaseFetcher implements IFetcher {

    @Override
    public String genRequestUrl(Task task) {
        return null;
    }




}
