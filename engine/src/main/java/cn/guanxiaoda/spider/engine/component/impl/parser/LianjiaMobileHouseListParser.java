package cn.guanxiaoda.spider.engine.component.impl.parser;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.component.BaseParser;
import cn.guanxiaoda.spider.engine.component.IParser;
import im.nll.data.extractor.Extractors;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Parser(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileHouseListParser extends BaseParser<HouseInfo> implements IParser {


    @Override
    protected int extractTotal(Extractors extractors) {
        String totalStr = extractors.extract("div.mod_cont>ul.lists").asString();
        if (totalStr != null && totalStr.contains(Const.Seps.EQUAL)) {
            String totalNumStr = totalStr.substring(totalStr.lastIndexOf(Const.Seps.EQUAL) + 1);
            return Integer.valueOf(totalNumStr) / 30;
        }
        return 0;
    }

    @Override
    protected List<HouseInfo> extractList(Extractors extractors) {
        return extractors
                .split(Extractors.selector("div.mod_cont>ul.lists>li.pictext.html"))
                .extract("name", Extractors.selector("div.flexbox>div.item_list>div.item_main"))
                .extract("price", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.price_total>em"))
                .extract("unitPrice", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.unit_price.text"))
                .extract("tags", Extractors.selector("div.flexbox>div.item_list>div.tag_box>span"))
                .asBeanList(HouseInfo.class);
    }

}