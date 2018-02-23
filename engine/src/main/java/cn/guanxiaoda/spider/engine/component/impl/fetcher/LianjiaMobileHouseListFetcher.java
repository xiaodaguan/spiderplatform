package cn.guanxiaoda.spider.engine.component.impl.fetcher;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.annotation.Fetcher;
import cn.guanxiaoda.spider.engine.component.BaseFetcher;
import cn.guanxiaoda.spider.engine.component.IFetcher;

/**
 * Created by guanxiaoda on 2018/1/13.
 */
@Fetcher(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.LIST)
public class LianjiaMobileHouseListFetcher extends BaseFetcher implements IFetcher {


    @Override
    public String genRequestUrl(Task task) {
        return String.format("https://m.lianjia.com/bj/ershoufang/index/%s/co32pg%s/",task.getMeta().get(Const.TaskParams.BIZ_NAME), task.getMeta().get(Const.TaskParams.PAGE_NUM));
//        return String.format("https://m.lianjia.com/bj/ershoufang/pg%s/?_t=1", task.getMeta().get(Const.TaskParams.PAGE_NUM));
    }


}
