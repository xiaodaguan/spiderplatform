package cn.guanxiaoda.spider.engine.component.impl.parser;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.component.BaseParser;
import cn.guanxiaoda.spider.engine.component.IParser;
import im.nll.data.extractor.Extractors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Parser(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.LIST)
@Slf4j
public class LianjiaMobileHouseListParser extends BaseParser<HouseInfo> implements IParser {


    @Override
    protected int extractTotal(String content) {
        String totalStr = Extractors.on(content).extract(Extractors.selector("div.mod_cont>ul.lists.attr(data-info)")).asString();
        if (totalStr != null && totalStr.contains(Const.Seps.EQUAL)) {
            String totalNumStr = totalStr.substring(totalStr.lastIndexOf(Const.Seps.EQUAL) + 1);
            log.info("{} total number: {}", this.getClass().getSimpleName(), totalNumStr);
            return Integer.valueOf(totalNumStr) / 30;
        }
        return 0;
    }

    //  <div class="mod_cont">
//                <ul class="lists" data-mark="list_container" data-info="total=28696">
    @Override
    protected List<HouseInfo> extractList(String content) {
        return Extractors.on(content)
                .split(Extractors.selector("div.mod_cont>ul.lists>li.pictext.html"))
                .extract("itemId", Extractors.selector("a.attr(href)"))
                .extract("name", Extractors.selector("div.flexbox>div.item_list>div.item_main"))
                .extract("price", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.price_total>em"))
                .extract("unitPrice", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.unit_price.text"))
                .extract("tags", Extractors.selector("div.flexbox>div.item_list>div.tag_box>span"))
                .asBeanList(HouseInfo.class);
    }

}
