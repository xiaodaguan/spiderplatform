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
@Fetcher(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileHouseListFetcher extends BaseFetcher implements IFetcher {


    @Override
    public String genRequestUrl(Task task) {
        return String.format("https://bj.lianjia.com/api/newhouserecommend?type=1&query=https://bj.lianjia.com/ershoufang/pg%s/", task.getMeta().get(Const.TASK_PARAM.PAGE_NUM));
    }


}
